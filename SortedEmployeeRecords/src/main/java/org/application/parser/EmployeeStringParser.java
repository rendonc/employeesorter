package org.application.parser;

import java.text.ParseException;
import java.util.regex.Pattern;

import org.application.employee.Employee;
import org.application.exception.InvalidRecordFormat;

public interface EmployeeStringParser {
	public Employee parseEmployeeRecord(String s) throws InvalidRecordFormat, ParseException;

	public static boolean validZipCode(String s) {
		if (s == null)
			return false;
		return s.isEmpty() || Pattern.matches("\\d+", s);
	}
}
