package org.datanucleusdemo.persistance;

import javax.jdo.PersistenceManager;
import java.util.logging.Logger;

public class LifecycleOperations {
	private static final Logger LOGGER = Logger.getLogger(LifecycleOperations.class.getName());

	public static void performOperations(PersistenceManager pm) {
		transientClean(pm);
		transientDirty(pm);
		persistentNew(pm);
		persistentClean(pm);
		persistentDirty(pm);
		hollow(pm);
		persistentNewDeleted(pm);
		persistentDeleted(pm);
		persistentNonTransactional(pm);
	}

	private static void transientClean(PersistenceManager pm) {
		// Transient Clean -> Persistent New (pm.makePersistent)
		// Transient Clean -> Transient Dirty (write field/makeDirty, active txn)
		// Transient Clean -> Detached Clean (pm.detachCopy(), active txn)
	}

	private static void transientDirty(PersistenceManager pm) {
		// Transient Dirty -> Persistent New (pm.makePersistent)
		// Transient Dirty -> Transient Clean (tx.commit, retainValues=false)
		// Transient Dirty -> Transient Clean (tx.commit, retainValues=true)
		// Transient Dirty -> Transient Clean (tx.commit, DetachAllOnCommit=true)
		// Transient Dirty -> Transient Clean (tx.rollback, restoreValues=false)
		// Transient Dirty -> Transient Clean (tx.rollback, restoreValues=true)
		// Transient Dirty -> Detached Clean (pm.detachCopy(), active txn)
	}

	private static void persistentNew(PersistenceManager pm) {
		// Persistent New -> Persistent New Deleted (pm.deletePersistent)
		// Persistent New -> Hollow (tx.commit, retainValues=false)
		// Persistent New -> Persistent Non-transactional (tx.commit, retainValues=true)
		// Persistent New -> Detached Clean (tx.commit, DetachAllOnCommit=true)
		// Persistent New -> Transient Clean (tx.rollback, restoreValues=false)
		// Persistent New -> Transient (tx.rollback, restoreValues=true)
		// Persistent New -> Detached Clean (pm.detachCopy(), active txn)
	}

	private static void persistentClean(PersistenceManager pm) {
		// Persistent Clean -> Persistent Deleted (pm.deletePersistent)
		// Persistent Clean -> Persistent Non-transactional (pm.makeNontransactional)
		// Persistent Clean -> Transient Clean (pm.makeTransient)
		// Persistent Clean -> Hollow (tx.commit, retainValues=false)
		// Persistent Clean -> Persistent Non-transactional (tx.commit, retainValues=true)
		// Persistent Clean -> Detached Clean (tx.commit, DetachAllOnCommit=true)
		// Persistent Clean -> Hollow (tx.rollback, restoreValues=false)
		// Persistent Clean -> Persistent Non-transactional (tx.rollback, restoreValues=true)
		// Persistent Clean -> Hollow (pm.evict)
		// Persistent Clean -> Persistent Dirty (write field/makeDirty, active txn)
		// Persistent Clean -> Detached Clean (pm.detachCopy(), active txn)
	}

	private static void persistentDirty(PersistenceManager pm) {
		// Persistent Dirty -> Persistent Deleted (pm.deletePersistent)
		// Persistent Dirty -> Hollow (tx.commit, retainValues=false)
		// Persistent Dirty -> Persistent Non-transactional (tx.commit, retainValues=true)
		// Persistent Dirty -> Detached Clean (tx.commit, DetachAllOnCommit=true)
		// Persistent Dirty -> Hollow (tx.rollback, restoreValues=false)
		// Persistent Dirty -> Persistent Non-transactional (tx.rollback, restoreValues=true)
		// Persistent Dirty -> Persistent Clean (pm.refresh, active Datastore txn)
		// Persistent Dirty -> Persistent Non-transactional (pm.refresh, active Optimistic txn)
		// Persistent Dirty -> Detached Clean (pm.detachCopy(), active txn)
	}

	private static void hollow(PersistenceManager pm) {
		// Hollow -> Persistent Deleted (pm.deletePersistent)
		// Hollow -> Persistent Clean (pm.makeTransactional)
		// Hollow -> Transient Clean (pm.makeTransient)
		// Hollow -> Detached Clean (tx.commit, DetachAllOnCommit=true)
		// Hollow -> Persistent Non-transactional (read field, outside txn)
		// Hollow -> Persistent Clean (read field, active Datastore txn)
		// Hollow -> Persistent Non-transactional (read field, active Optimistic txn)
		// Hollow -> Persistent Non-transactional (write field/makeDirty, outside txn)
		// Hollow -> Persistent Dirty (write field/makeDirty, active txn)
		// Hollow -> Persistent Non-transactional (retrieve(), outside txn or with active Optimistic txn)
		// Hollow -> Clean (pm.retrieve(), with active Datastore txn)
		// Hollow -> Detached-Clean pm.detachCopy(), outside txn, Nontx-read=true)
		// Hollow -> Detached-Clean (pm.detachCopy(), active txn)
	}

	private static void persistentNewDeleted(PersistenceManager pm) {
		// Persistent New Deleted -> Transient Clean (tx.commit, retainValues=false)
		// Persistent New Deleted -> Transient Clean (tx.commit, retainValues=true)
		// Persistent New Deleted -> Transient Clean (tx.commit, DetachAllOnCommit=false)
		// Persistent New Deleted -> Transient Clean (tx.rollback, restoreValues=false)
		// Persistent New Deleted -> Transient (tx.rollback, restoreValues=true)
	}

	private static void persistentDeleted(PersistenceManager pm) {
		// Persistent Deleted -> Transient Clean (tx.commit, retainValues=false)
		// Persistent Deleted -> Transient Clean (tx.commit, retainValues=true)
		// Persistent Deleted -> Transient Clean (tx.commit, DetachAllOnCommit=false)
		// Persistent Deleted -> Hollow (tx.rollback, restoreValues=false)
		// Persistent Deleted -> Persistent Non-transactional (tx.rollback, restoreValues=true)
	}

	private static void persistentNonTransactional(PersistenceManager pm) {
		// Persistent Non-transactional -> Persistent Deleted (pm.deletePersistent)
		// Persistent Non-transactional -> Persistent Clean (pm.makeTransactional)
		// Persistent Non-transactional -> Transient Clean (pm.makeTransient)
		// Persistent Non-transactional -> Detached Clean (tx.commit, DetachAllOnCommit=true)
		// Persistent Non-transactional -> Hollow (pm.evict)
		// Persistent Non-transactional -> Persistent Clean (read field, active Datastore txn)
		// Persistent Non-transactional -> Persistent Non-transactional Dirty (write field/makeDirty, outside txn)
		// Persistent Non-transactional -> Persistent Dirty (write field/makeDirty, active txn)
		// Persistent Non-transactional -> Persistent Clean (retrieve(), outside txn or with active Optimistic txn)
		// Persistent Non-transactional -> Detached Clean (pm.detachCopy(), outside txn, Nontx-read=true)
		// Persistent Non-transactional -> Detached Clean (pm.detachCopy(), outside txn, Nontx-read=false)
		// Persistent Non-transactional -> Detached Clean (pm.detachCopy(), active txn)-
	}

	private LifecycleOperations() {
	}
}