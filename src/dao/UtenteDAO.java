package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Prestito;
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
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Utente found = em.find(Utente.class, id);
			if (found != null) {
				List<Prestito> listaPrestiti = found.getListaPrestiti();
				for (Prestito prestito : listaPrestiti) {
					em.remove(prestito);
				}
				em.remove(found);
				transaction.commit();
				logger.info("Utente con id " + id + " eliminato!");
			} else {
				logger.info("Utente con id " + id + " non trovato!");
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			em.close();
		}

	}
}
