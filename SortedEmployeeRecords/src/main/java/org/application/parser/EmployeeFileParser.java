package org.application.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.application.employee.Employee;
import org.application.exception.InvalidFileFormatException;
import org.application.exception.InvalidRecordFormat;
import org.application.io.FileFormatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeFileParser {

	private static Logger log = LoggerFactory.getLogger(EmployeeFileParser.class);

	public List<Employee> parse(File in)
			throws IOException, InvalidFileFormatException, InvalidRecordFormat, ParseException {
		BufferedReader br = null;

		Charset charset = StandardCharsets.US_ASCII;

		List<Employee> list = new LinkedList<>();

		try {
			br = new BufferedReader(new FileReader(in, charset));

			String line = br.readLine().trim();

			if (line == null || line.isEmpty()) {
				throw new InvalidFileFormatException("The file doesn't contain a file format code value");
			}

			FileFormatType type = getFormatType(line);

			EmployeeStringParser parser = null;
			if (type == FileFormatType.TYPE_1) {
				parser = new Type1FormatParser();
			}
			if (type == FileFormatType.TYPE_2) {
				parser = new Type2FormatParser();
			}

			while ((line = br.readLine()) != null) {
				try {
					// Employee e = parseEmployeeRecord(line);
					Employee e = parser.parseEmployeeRecord(line);
					if (e != null) {
						list.add(e);
					}
				} catch (InvalidRecordFormat e) {
					log.error("Invalid record format, skipping record: " + line, e);
				}
			}
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				log.error("Error while closing the file: " + in, e);
			}
		}

		return list;
	}

	private FileFormatType getFormatType(String fileType)
			throws FileNotFoundException, IOException, InvalidFileFormatException {

		if (fileType == null || fileType.trim().isEmpty()) {
			throw new InvalidFileFormatException("Invalid file format code. (Valid values are 1 or 2)");
		}

		int parserType = 0;

		try {
			parserType = Integer.parseInt(fileType);
		} catch (NumberFormatException e) {
			throw new InvalidFileFormatException("Invalid file format code. (Valid values are 1 or 2)");
		}

		FileFormatType ft = FileFormatType.valueOf(parserType);

		if (ft == null) {
			throw new InvalidFileFormatException("Invalid file format code. (Valid values are 1 or 2)");
		}

		return ft;
	}

	protected boolean isNumeric(String s) {
		return Pattern.matches("\\d+", s);
	}

	protected boolean validZipCode(String s) {
		return s.isEmpty() || isNumeric(s);
	}
}
