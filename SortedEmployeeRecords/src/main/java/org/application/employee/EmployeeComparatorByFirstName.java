package org.application.employee;

import java.util.Comparator;

public class EmployeeComparatorByFirstName implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
		
		if (e1 == e2) {
			return 0;
		}
		
		if (e1 == null || e1.getFirstName()==null) {
			return -1;
		}
		if (e2 == null || e2.getFirstName()==null) {
			return 1;
		}
		
		int comp=e1.getFirstName().compareTo(e2.getFirstName());
		
		return comp;
	}

}
