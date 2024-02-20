package org.datanucleusdemo.persistance;

import org.datanucleusdemo.model.Product;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import java.util.logging.Logger;

public class LifecycleOperations {
	private static final Logger LOGGER = Logger.getLogger(LifecycleOperations.class.getName());

	public static void performOperations(PersistenceManager pm) {
		transientClean(pm);
	}

	private static void transientClean(PersistenceManager pm) {
		// Transient  -> Persistent New
		pm.currentTransaction().begin();
		var redBullEnergyDrink = new Product(1, "RedBull Energy Drink");
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink));
		pm.makePersistent(redBullEnergyDrink);
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink));
		pm.currentTransaction().commit();

		System.out.println();

		// Persistent Clean -> Transient
		pm.currentTransaction().begin();
		var redBullEnergyDrink1 = pm.getObjectById(Product.class, 1);
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink1));
		pm.makeTransient(redBullEnergyDrink1);
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink1));
		pm.currentTransaction().commit();

		System.out.println();

		// Persistent Clean -> Hollow / Persistent Nontransactional
		pm.currentTransaction().begin();
		var redBullEnergyDrink2 = pm.getObjectById(Product.class, 1);
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink2));
		pm.currentTransaction().setRetainValues(false);
		pm.currentTransaction().commit();
		System.out.println(JDOHelper.getObjectState(redBullEnergyDrink2));
	}

	private LifecycleOperations() {
	}
}