package com.test.rupeek.firstRound.utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

	private Map<String, Object> context;

	public TestContext() {
		context = new HashMap<String, Object>();
	}

	public Object getContext(String key) {
		if (!context.containsKey(key)) {
			return null;
		}
		return context.get(key);
	}

	public void setContext(String key, Object value) {
		context.put(key, value);
	}
}