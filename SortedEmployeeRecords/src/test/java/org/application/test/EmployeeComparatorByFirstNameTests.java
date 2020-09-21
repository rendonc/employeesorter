package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.application.employee.Employee;
import org.application.employee.EmployeeComparatorByFirstName;

import org.junit.jupiter.api.Test;

class EmployeeComparatorByFirstNameTests {
	
	Comparator<Employee> comp=new EmployeeComparatorByFirstName();

	@Test
	void test_comparisons() {
		Employee e1=new Employee();
		Employee e2=new Employee();
		
		e1.setFirstName("Alan");
		e2.setFirstName("Ben");
		
		assertEquals(-1,comp.compare(e1, e2));
		
		assertEquals(1,comp.compare(e2, e1));
		
		assertEquals(-1,comp.compare(null, e2));
		
		assertEquals(1,comp.compare(e1, null));
		
		assertEquals(0,comp.compare(null, null));
		
		e2.setFirstName("Alan");
		
		assertEquals(0,comp.compare(e1, e2));
	}
}
