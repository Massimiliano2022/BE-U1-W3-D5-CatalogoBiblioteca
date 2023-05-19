package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Prestito;

public class PrestitoDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Prestito p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(p);
		transaction.commit();
		logger.info("Prestito salvato " + p.toString());
	}

	public Prestito getById(Long id) {
		Prestito found = em.find(Prestito.class, id);
		return found;
	}

	public void findByIdAndDelete(Long id) {
		Prestito found = em.find(Prestito.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Prestito con id " + id + " eliminato!");
		} else {
			logger.info("Prestito non TROVATO!");
		}
	}
}
