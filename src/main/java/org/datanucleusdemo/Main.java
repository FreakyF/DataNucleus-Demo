package org.datanucleusdemo;

import org.datanucleusdemo.persistance.lifecycle.LifecycleOperations;
import org.datanucleusdemo.persistance.util.PersistenceManagerUtil;
import org.datanucleusdemo.persistance.transaction.TransactionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		try (var pm = PersistenceManagerUtil.getPersistenceManager()) {
			TransactionHandler.handleTransaction(pm, () -> LifecycleOperations.performOperations(pm));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred: ", e);
		}
	}
}
