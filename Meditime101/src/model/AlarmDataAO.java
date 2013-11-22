package model;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.EMF;

public class AlarmDataAO {

	private static final Logger log = Logger.getLogger(AlarmDataAO.class.getName());
	EntityManager em;
	
	// add Alarm Data
	public boolean add(AlarmData ad) {
		em = EMF.get().createEntityManager();
		em.persist(ad);
		em.close();
		return true;
	}	
	
	// Get all Alarm
	public List<AlarmData> getAlarmData() {
		em = EMF.get().createEntityManager();
		Query q = em.createQuery("select rc from ResourceCredentials rc");
		List<AlarmData> list = q.getResultList();
		em.close();
		return list;
	}	
}
