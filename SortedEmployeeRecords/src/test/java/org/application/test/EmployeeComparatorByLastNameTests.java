package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.application.employee.Employee;
import org.application.employee.EmployeeComparatorByLastName;

import org.junit.jupiter.api.Test;

class EmployeeComparatorByLastNameTests {
	
	Comparator<Employee> comp=new EmployeeComparatorByLastName();

	@Test
	void test_comparisons() {
		Employee e1=new Employee();
		Employee e2=new Employee();
		
		e1.setLastName("Abner");
		e2.setLastName("Banhausen");
		
		assertEquals(-1,comp.compare(e1, e2));
		
		assertEquals(1,comp.compare(e2, e1));
		
		assertEquals(-1,comp.compare(null, e2));
		
		assertEquals(1,comp.compare(e1, null));
		
		assertEquals(0,comp.compare(null, null));
		
		e2.setLastName("Abner");
		
		assertEquals(0,comp.compare(e1, e2));
	}
}
