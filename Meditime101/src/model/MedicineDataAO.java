package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.EMF;

public class MedicineDataAO {
	EntityManager em;
	
	public boolean add(long id, String name, String startDate, String endDate, String duration, int tablet) {
		em = EMF.get().createEntityManager();
		MedicineData data = new MedicineData();
	
		 data.setId(id);
		 data.setName(name);
		 data.setStartDate(startDate);
		 data.setEndDate(endDate);
		 data.setDuration(duration);
		 data.setTablet(tablet);

		em.persist(data);
		em.close();
		return true;
	}
	
	public List<MedicineData> getMedicineData() {
		em = EMF.get().createEntityManager();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date = new Date();
		String today = dateFormat.format(date);
		
		Query q = em.createQuery("select d from MedicineData d where endDate >='" + today + "'");
		List<MedicineData> list = q.getResultList();
		em.close();
		
		
		return list;
	}	
	
	public List<MedicineData> getPastData() {
		em = EMF.get().createEntityManager();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date = new Date();
		String today = dateFormat.format(date);
		
		Query q = em.createQuery("select d from MedicineData d where endDate <'" + today + "'");
		List<MedicineData> list = q.getResultList();
		em.close();
		return list;
	}	
	
}
