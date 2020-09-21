package org.application.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.application.employee.Employee;
import org.application.exception.InvalidRecordFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Type2FormatParser implements EmployeeStringParser {
	
	private static Logger log = LoggerFactory.getLogger(Type2FormatParser.class);
	
	private SimpleDateFormat formatter;

	public Type2FormatParser() {
		formatter = new SimpleDateFormat("yyyyMMdd");
	}

	@Override
	public Employee parseEmployeeRecord(String line) throws InvalidRecordFormat, ParseException {
		Employee e=new Employee();
		
		if(line==null) {
			throw new InvalidRecordFormat("The record is null");
		}
		String[] tokens = line.split(",");
		
		if(tokens.length!=9) {
			log.error("The record has incorrect format: "+line);
			throw new InvalidRecordFormat("The record has incorrect format: "+line);
		}
		
		String s=tokens[0].trim();
		if(s.isEmpty()) {
			log.error("First name is empty");
			throw new InvalidRecordFormat("First name can not be a blank string");
		}
		
		e.setFirstName(s);
		
		s=tokens[1].trim();
		
		if(s.isEmpty()) {
			log.error("Last name is empty");
			throw new InvalidRecordFormat("Last name can not be a blank string");
		}
		
		e.setLastName(s);
		
		s=tokens[2].trim();
		if(s.isEmpty()) {
			log.error("Start date is empty");
			throw new InvalidRecordFormat("Start date can not be a blank string");
		}
		
		//the parser verifies that the string is a valid date or throws an exception
		Date date = formatter.parse(tokens[2]);

		e.setStartDate(date);
		
		e.setAddress1(tokens[3].trim());
		
		e.setAddress2(tokens[4].trim());
	
		e.setCity(tokens[5].trim());
		
		e.setState(tokens[6].trim());
		
		e.setCountry(tokens[7].trim());
		
		
		s=tokens[8].trim();
		if(EmployeeStringParser.validZipCode(s)) {
			e.setZip(s);
		}else {
			throw new InvalidRecordFormat("Zip code is not numeric: "+s);
		}
		return e;
	}

	

}
