package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Utente;

public class UtenteDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Utente u) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(u);
		transaction.commit();
		logger.info("Utente salvato " + u.toString());
	}

	public Utente getById(Long id) {
		Utente found = em.find(Utente.class, id);
		return found;
	}

	public void findByIdAndDelete(Long id) {
		Utente found = em.find(Utente.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Utente con id " + id + " eliminato!");
		} else {
			logger.info("Utente non TROVATO!");
		}
	}

}
