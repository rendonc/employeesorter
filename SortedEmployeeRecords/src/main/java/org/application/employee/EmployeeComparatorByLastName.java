package org.application.employee;

import java.util.Comparator;

public class EmployeeComparatorByLastName implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
		if (e1 == e2) {
			return 0;
		}
		
		if (e1 == null || e1.getLastName()==null) {
			return -1;
		}
		if (e2 == null || e2.getLastName()==null) {
			return 1;
		}
		
		int comp= e1.getLastName().compareTo(e2.getLastName());
		
		return comp;
	}

}
