package org.application.io;

import java.util.HashMap;
import java.util.Map;

public enum FileFormatType {
	TYPE_1(1), TYPE_2(2);

	private int value;

	private static Map<Integer, FileFormatType> map = new HashMap<Integer, FileFormatType>();

	FileFormatType(int t) {
		this.value = t;
	}

	static {
		for (FileFormatType pageType : FileFormatType.values()) {
			map.put(pageType.value, pageType);
		}
	}

	public static FileFormatType valueOf(int pageType) {
		return map.get(pageType);
	}

	public int getValue() {
		return value;
	}

}
