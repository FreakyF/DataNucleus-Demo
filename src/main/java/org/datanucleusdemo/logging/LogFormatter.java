package org.datanucleusdemo.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
	@Override
	public String format(LogRecord logRecord) {
		if ("INFO".equals(logRecord.getLevel().toString())) {
			return "INFO: " + logRecord.getMessage() + "\n";
		}
		return logRecord.getMessage() + "\n";
	}
}
