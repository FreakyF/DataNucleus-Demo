package org.datanucleusdemo.persistence.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceHandler {
	private static final String PERSISTENCE_UNIT_NAME = "DataNucleus-Demo";
	private static final PersistenceManagerFactory pmfInstance =
			JDOHelper.getPersistenceManagerFactory(PERSISTENCE_UNIT_NAME);

	private PersistenceHandler() {
	}

	public static PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}
}