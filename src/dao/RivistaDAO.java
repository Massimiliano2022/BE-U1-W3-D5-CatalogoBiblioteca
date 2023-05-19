package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Rivista;

public class RivistaDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public RivistaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Rivista r) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(r);
		transaction.commit();
		logger.info("Rivista salvata " + r.toString());
	}

	public Rivista getById(Long id) {
		Rivista found = em.find(Rivista.class, id);
		return found;
	}

	public void findById(Long id) {
		Rivista found = em.find(Rivista.class, id);
		if (found != null) {
			logger.info("Rivista cercata: " + found.toString());
		} else {
			logger.info("Rivista non TROVATA!");
		}
	}

	public void findByIdAndDelete(Long id) {
		Rivista found = em.find(Rivista.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Rivista con id " + id + " eliminata!");
		} else {
			logger.info("Rivista non TROVATA!");
		}
	}

}
