package model;

import java.util.*;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.*;
import model.*;


public class ResourceCredentialsAO {
	private static final Logger log = Logger.getLogger(ResourceCredentialsAO.class.getName());
	EntityManager em;
	
	// add Resource Credentials
	public boolean add(ResourceCredentials rc) {
		em = EMF.get().createEntityManager();
		em.persist(rc);
		em.close();
		return true;
	}	
	
	// Get all Resource Credentials
	public List<ResourceCredentials> getResourceCredentials() {
		em = EMF.get().createEntityManager();
		Query q = em.createQuery("select rc from ResourceCredentials rc");
		List<ResourceCredentials> list = q.getResultList();
		em.close();
		return list;
	}	
}
