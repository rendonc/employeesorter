package org.application.employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSorter {

	public void sort(List<Employee> employees, EmployeeOrder order) {
		Comparator<Employee> comparator = getComparator(order);
		if(employees==null) {
			return;
		}
		Collections.sort(employees, comparator);
	}

	private Comparator<Employee> getComparator(EmployeeOrder order) {
		Comparator<Employee> comparator = null;
		switch (order) {
		case BY_FIRST_NAME:
			comparator = new EmployeeComparatorByFirstName();
			break;
		case BY_LAST_NAME:
			comparator = new EmployeeComparatorByLastName();
			break;
		case BY_START_DATE:
			comparator = new EmployeeComparatorByStartDate();
			break;
		}
		return comparator;
	}

}
