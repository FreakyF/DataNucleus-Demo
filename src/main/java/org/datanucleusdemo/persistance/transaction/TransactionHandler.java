package org.datanucleusdemo.persistance.transaction;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionHandler {
	private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class.getName());

	public static void handleTransaction(PersistenceManager pm, Runnable operations) {
		Transaction tx = pm.currentTransaction();
		try {
			operations.run();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred during the transaction, rolling back...", e);
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	private TransactionHandler() {
	}
}
