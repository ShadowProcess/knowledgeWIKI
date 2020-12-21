package com.example.stackhelp;

import java.util.ArrayList;
import java.util.List;

public class StackUtil {

	public static String getCallTrace(String basePackge, Throwable throwable) {
		List<StackTraceElement> stackList = new ArrayList<>();
		StackTraceElement[] stElement = throwable.getStackTrace();
		for (StackTraceElement element : stElement) {
			if (element.getClassName().trim().startsWith(basePackge)) {
				stackList.add(element);
			}
		}
		String callTraceString = "";
		int listSize = stackList.size();
		for (int i = 0; i < listSize; i++) {
			StackTraceElement element = stackList.get(i);
			callTraceString += element.getClassName() + "." + element.getMethodName() + "(" + element.getLineNumber()
					+ ")";
			if ((i + 1) != listSize) {
				callTraceString += " <-- ";
			}
		}
		return callTraceString;
	}
}
