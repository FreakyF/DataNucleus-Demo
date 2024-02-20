package org.datanucleusdemo.persistance;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceManagerUtil {
	private static final String PERSISTENCE_UNIT_NAME = "Tutorial";
	private static final PersistenceManagerFactory pmfInstance =
			JDOHelper.getPersistenceManagerFactory(PERSISTENCE_UNIT_NAME);

	private PersistenceManagerUtil() {
	}

	public static PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}
}