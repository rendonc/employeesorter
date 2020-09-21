package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.application.employee.Employee;

import org.application.employee.EmployeeComparatorByStartDate;
import org.junit.jupiter.api.Test;

class EmployeeComparatorByStartDateTests {
	
	Comparator<Employee> comp=new EmployeeComparatorByStartDate();

	@Test
	void test_comparisons() throws ParseException {
		Employee e1=new Employee();
		Employee e2=new Employee();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date1 = sdf.parse("08-12-2003");
		
		e1.setStartDate(date1);
		
		Date date2 = sdf.parse("09-12-2003");
		e2.setStartDate(date2);
		
		
		
		assertEquals(-1,comp.compare(e1, e2));
		
		assertEquals(1,comp.compare(e2, e1));
		
		assertEquals(-1,comp.compare(null, e2));
		
		assertEquals(1,comp.compare(e1, null));
		
		assertEquals(0,comp.compare(null, null));
		
		Date date3 = sdf.parse("09-12-2003");
		e1.setStartDate(date3);
		
		assertEquals(0,comp.compare(e1, e2));
	}
}
