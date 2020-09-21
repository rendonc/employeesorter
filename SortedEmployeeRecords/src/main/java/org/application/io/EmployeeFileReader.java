package org.application.io;


import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.List;

import org.application.employee.Employee;
import org.application.exception.InvalidFileFormatException;
import org.application.exception.InvalidRecordFormat;
import org.application.parser.EmployeeFileParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeFileReader {
	private static Logger log = LoggerFactory.getLogger(EmployeeFileReader.class);

	public List<Employee> read(final File in)
			throws InvalidParameterException, InvalidFileFormatException, IOException, InvalidRecordFormat, ParseException {

		if (in == null) {
			log.error("Input employee file is null");
			throw new InvalidParameterException("Input file is null");
		}

		if (!in.exists()) {
			log.error("Input employee file does not exist");
			throw new FileNotFoundException("Input file does not exists");
		}

		EmployeeFileParser parser = new EmployeeFileParser();

		List<Employee> employeeList = parser.parse(in);

		return employeeList;

	}
}
