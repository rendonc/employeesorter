package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.application.employee.EmployeeOrder;
import org.junit.jupiter.api.Test;

class FileFormatTypeTests {

	@Test
	void test_mapped_values() {
		assertEquals(EmployeeOrder.BY_FIRST_NAME,EmployeeOrder.valueOf(1));
		assertEquals(EmployeeOrder.BY_LAST_NAME,EmployeeOrder.valueOf(2));
		assertEquals(EmployeeOrder.BY_START_DATE,EmployeeOrder.valueOf(3));
	}
	
	@Test
	void test_invalid_values() {
		assertEquals(null,EmployeeOrder.valueOf(4));
		assertEquals(null,EmployeeOrder.valueOf(0));
	}

}
