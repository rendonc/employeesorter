package org.application.employee;

import java.util.Comparator;

public class EmployeeComparatorByStartDate implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		if (e1 == e2) {
			return 0;
		}
		
		if (e1 == null || e1.getStartDate()==null) {
			return -1;
		}
		if (e2 == null || e2.getStartDate()==null) {
			return 1;
		}
		
		return e1.getStartDate().compareTo(e2.getStartDate());
	}

}
