package org.application.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.application.employee.Employee;
import org.application.exception.InvalidRecordFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Type1FormatParser implements EmployeeStringParser {
	
	private static Logger log = LoggerFactory.getLogger(Type1FormatParser.class);

	private static final int FIRST_NAME_LENGTH = 10;
	private static final int LAST_NAME_LENGTH = 17;
	private static final int START_DATE_LENGTH = 8;
	private static final int ADRESS1_LENGTH = 10;
	private static final int ADRESS2_LENGTH = 10;
	private static final int CITY_LENGTH = 10;
	private static final int STATE_LENGTH = 2;
	private static final int COUNTRY_LENGTH = 3;
	private static final int ZIP_LENGTH = 10;

	private final SimpleDateFormat formatter;

	public Type1FormatParser() {
		formatter = new SimpleDateFormat("yyyyMMdd");
	}

	@Override
	public Employee parseEmployeeRecord(String line) throws InvalidRecordFormat, ParseException {
		Employee e = new Employee();

		if (line == null) {
			log.warn("Employee record is null");
			throw new InvalidRecordFormat("The record is null");
		}

		if (line.length() != 80) {
			log.error("Employee record has incorrect size: "+line.length());
			throw new InvalidRecordFormat("The record doesn't have length of 80 characters: <" + line + ">");
		}
		int i = 0;
		
		String s=line.substring(i, i + FIRST_NAME_LENGTH).trim();
		
		if(s.isEmpty()) {
			log.error("Employee first name is empty");
			throw new InvalidRecordFormat("First name can not be a blank string");
		}
		
		e.setFirstName(s);
		i += FIRST_NAME_LENGTH;

		s=line.substring(i, i + LAST_NAME_LENGTH).trim();
		
		if(s.isEmpty()) {
			log.error("Employee last name is empty");
			throw new InvalidRecordFormat("Last name can not be a blank string");
		}
		
		e.setLastName(s);
		i += LAST_NAME_LENGTH;

		s = line.substring(i, i + START_DATE_LENGTH).trim();
		i += START_DATE_LENGTH;
		
		if(s.isEmpty()) {
			log.error("Employee start date is empty");
			throw new InvalidRecordFormat("Start date can not be a blank string");
		}

		Date date = formatter.parse(s);

		e.setStartDate(date);

		e.setAddress1(line.substring(i, i + ADRESS1_LENGTH).trim());
		i += ADRESS1_LENGTH;

		e.setAddress2(line.substring(i, i + ADRESS2_LENGTH).trim());
		i += ADRESS2_LENGTH;

		e.setCity(line.substring(i, i + CITY_LENGTH).trim());
		i += CITY_LENGTH;
		
	

		e.setState(line.substring(i, i + STATE_LENGTH).trim());
		i += STATE_LENGTH;
		
	

		e.setCountry(line.substring(i, i + COUNTRY_LENGTH).trim());
		i += COUNTRY_LENGTH;
		
	
		s=line.substring(i, i + ZIP_LENGTH).trim();
		if(EmployeeStringParser.validZipCode(s)) {
			e.setZip(s);
		}else {
			log.error("Zip code is not numeric");
			throw new InvalidRecordFormat("Zip code is not numeric: "+s);
		}
	
		return e;
	}
}
