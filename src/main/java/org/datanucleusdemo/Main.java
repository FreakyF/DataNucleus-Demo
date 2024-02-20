package org.datanucleusdemo;

import org.datanucleusdemo.logging.config.LoggerConfig;
import org.datanucleusdemo.persistence.lifecycle.LifecycleOperations;
import org.datanucleusdemo.persistence.util.PersistenceHandler;
import org.datanucleusdemo.persistence.transaction.TransactionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		LoggerConfig.configureGlobalInfoLogger();
		try (var pm = PersistenceHandler.getPersistenceManager()) {
			TransactionHandler.handleTransaction(pm, () -> LifecycleOperations.performOperations(pm));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred: ", e);
		}
	}
}
