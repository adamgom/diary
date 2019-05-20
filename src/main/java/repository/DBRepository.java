package repository;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import main.Bean;
import repository.model.Inscription;

public class DBRepository implements IRepository {
	
	private EntityManager entityManager;
	
	public DBRepository() {
		this.entityManager = Persistence.createEntityManagerFactory("pamietnikDB").createEntityManager();	
	}

	@Override
	public void save(Long date, String title, String content) {
		Inscription newEntry = new Inscription();
		newEntry.setDate(new Date(date));
		newEntry.setTitle(title);
		newEntry.setContent(content);
		
		entityManager.getTransaction().begin();
		
		try {
			entityManager.persist(newEntry);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void remove(Long date) {
		Inscription removeData = findInscryprion(date);

		entityManager.getTransaction().begin();
		
		try {
			entityManager.remove(removeData);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	
	@Override
	public Bean find(Long date) {
		Inscription findInscription = findInscryprion(date);
		Bean findedBean = new Bean(findInscription.getTitle(), findInscription.getContent(), findInscription.getDate());
		return findedBean;
	}
	
	private Inscription findInscryprion(Long date) {
		TypedQuery<Inscription> query = entityManager.createQuery("SELECT i. FROM Inscription i WHERE i.date = :date", Inscription.class);
		query.setParameter("date", new Date(date));
		return query.getSingleResult();
	}
}
