package org.application.employee;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeOrder {
	BY_FIRST_NAME(1),
	BY_LAST_NAME(2),
	BY_START_DATE(3);
	
	private int value;
	
	private static Map<Integer, EmployeeOrder> map = new HashMap<Integer, EmployeeOrder>();

	EmployeeOrder(int i) {
		this.value=i;
	}

	public int getValue() {
		return value;
	}
	
	public static EmployeeOrder valueOf(int orderType) {
		return map.get(orderType);
	}
	
	static {
		for (EmployeeOrder type : EmployeeOrder.values()) {
			map.put(type.value, type);
		}
	}

	public static boolean isValidValue(int intOrder) {
		return map.containsKey(intOrder);
	}
}
