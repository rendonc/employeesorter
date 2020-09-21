package org.application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.application.io.FileFormatType;
import org.junit.jupiter.api.Test;

class EmployeeOrderTests {

	@Test
	void test_mapped_values() {
		assertEquals(FileFormatType.TYPE_1,FileFormatType.valueOf(1));
		assertEquals(FileFormatType.TYPE_2,FileFormatType.valueOf(2));
	}
	
	@Test
	void test_invalid_values() {
		assertEquals(null,FileFormatType.valueOf(4));
		assertEquals(null,FileFormatType.valueOf(0));
	}

}
