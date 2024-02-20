package org.datanucleusdemo.logging.config;

import org.datanucleusdemo.logging.LogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class LoggerConfig {
	public static void configureGlobalInfoLogger() {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		for (Handler handler : handlers) {
			if (handler instanceof ConsoleHandler) {
				handler.setFormatter(new LogFormatter());
				handler.setLevel(Level.INFO);
			}
		}
	}

	private LoggerConfig() {
	}
}
