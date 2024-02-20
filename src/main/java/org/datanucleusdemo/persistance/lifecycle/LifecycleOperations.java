package org.datanucleusdemo.persistance.lifecycle;

import org.datanucleusdemo.model.Product;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import java.util.logging.Logger;

public class LifecycleOperations {
	private static final Logger LOGGER = Logger.getLogger(LifecycleOperations.class.getName());

	public static void performOperations(PersistenceManager pm) {
		performTransientToPersistent(pm);
		performPersistentCleanToTransient(pm);
		performPersistentCleanToHollow(pm);
	}

	private static void performTransientToPersistent(PersistenceManager pm) {
		logOperation("Transient -> Persistent New");
		pm.currentTransaction().begin();
		var redBullEnergyDrink = new Product(1, "RedBull Energy Drink");
		logObjectState("Before persistence", redBullEnergyDrink);
		pm.makePersistent(redBullEnergyDrink);
		logObjectState("After persistence", redBullEnergyDrink);
		pm.currentTransaction().commit();
	}

	private static void performPersistentCleanToTransient(PersistenceManager pm) {
		logOperation("Persistent Clean -> Transient");
		pm.currentTransaction().begin();
		var redBullEnergyDrink = pm.getObjectById(Product.class, 1);
		logObjectState("Before transient", redBullEnergyDrink);
		pm.makeTransient(redBullEnergyDrink);
		logObjectState("After transient", redBullEnergyDrink);
		pm.currentTransaction().commit();
	}

	private static void performPersistentCleanToHollow(PersistenceManager pm) {
		logOperation("Persistent Clean -> Hollow / Persistent Nontransactional");
		pm.currentTransaction().begin();
		var redBullEnergyDrink = pm.getObjectById(Product.class, 1);
		logObjectState("Before clean", redBullEnergyDrink);
		pm.currentTransaction().setRetainValues(false);
		pm.currentTransaction().commit();
		logObjectState("After nontransactional", redBullEnergyDrink);
	}

	private static void logOperation(String message) {
		LOGGER.info(() -> "Beginning operation: " + message);
	}

	private static void logObjectState(String message, Product product) {
		LOGGER.info(() -> message + ": " + JDOHelper.getObjectState(product).toString());
	}

	private LifecycleOperations() {
	}
}
