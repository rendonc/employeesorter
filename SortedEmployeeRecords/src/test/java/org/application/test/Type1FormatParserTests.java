package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.application.employee.Employee;
import org.application.exception.InvalidRecordFormat;
import org.application.parser.EmployeeStringParser;
import org.application.parser.Type1FormatParser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class Type1FormatParserTests {
	private String test_1=null;

	private String test_2="                                                     ";
	
	private String test_3="Jon       Abner            200312081 Jay St  Apt 498   Dublin    CA   49567     ";
	
	private String test_4="          Abner            200312081 Jay St  Apt 498   Dublin    CA   49567     ";
	
	private String test_5="Jon                        200312081 Jay St  Apt 498   Dublin    CA   49567     ";
	
	private String test_6="Jon       Abner                    1 Jay St  Apt 498   Dublin    CA   49567     ";
	
	private String test_7="Jon       Abner            200312081 Jay St  Apt 498   Dublin    CA   49567          ";
	
	private String test_8="Jon       Abner            200312081 Jay St  Apt 498   Dublin    CAUSA49567     ";
	
	private String test_9="Jon       Abner            200312081 Jay St  Apt 498   Dublin    CAUSA49567A    ";
	
	EmployeeStringParser parser = new Type1FormatParser();
	
	@Test
	void test_record_is_null() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            	parser.parseEmployeeRecord(test_1);
            }
        });	
	}
	
	@Test
	void test_record_length_is_less_than_80_chars() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            	
            	parser.parseEmployeeRecord(test_2);
            }
        });
	}
	
	@Test
	void test_record_is_correct() throws InvalidRecordFormat, ParseException {
		
		
    	Employee e = parser.parseEmployeeRecord(test_3);
		assertEquals("Jon",e.getFirstName());
		assertEquals("Abner",e.getLastName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		String dateInString = "08-12-2003";
		Date date = sdf.parse(dateInString);
		
		assertEquals(date,e.getStartDate());
		
		assertEquals("1 Jay St",e.getAddress1());
		assertEquals("Apt 498",e.getAddress2());
		assertEquals("Dublin",e.getCity());
		assertEquals("CA",e.getState());
		assertEquals("",e.getCountry());
		assertEquals("49567",e.getZip());
	}
	
	@Test
	void test_first_name_is_blank() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            	
            	parser.parseEmployeeRecord(test_4);
            }
        });
		
	}
	
	@Test
	void test_last_name_is_blank() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            
            	parser.parseEmployeeRecord(test_5);
            }
        });
		
	}
	
	@Test
	void test_start_date_is_blank() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            	
            	parser.parseEmployeeRecord(test_6);
            }
        });
		
	}
	
	@Test
	void test_record_length_is_greater_than_80_chars() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            	
            	parser.parseEmployeeRecord(test_7);
            }
        });
	}
	
	@Test
	void test_record_is_correct2() throws InvalidRecordFormat, ParseException {
		
		
    	Employee e = parser.parseEmployeeRecord(test_8);
		assertEquals("Jon",e.getFirstName());
		assertEquals("Abner",e.getLastName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		String dateInString = "08-12-2003";
		Date date = sdf.parse(dateInString);
		
		assertEquals(date,e.getStartDate());
		
		assertEquals("1 Jay St",e.getAddress1());
		assertEquals("Apt 498",e.getAddress2());
		assertEquals("Dublin",e.getCity());
		assertEquals("CA",e.getState());
		assertEquals("USA",e.getCountry());
		assertEquals("49567",e.getZip());
	}
	
	@Test
	void test_zip_is_not_numeric() {
		assertThrows(InvalidRecordFormat.class, new Executable() {
            @Override
            public void execute() throws Throwable {
            
            	parser.parseEmployeeRecord(test_9);
            }
        });
		
	}

}
