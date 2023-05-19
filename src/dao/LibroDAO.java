package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Libro;

public class LibroDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public LibroDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Libro l) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(l);
		transaction.commit();
		logger.info("Libro salvato " + l.toString());
	}

	public Libro getById(Long id) {
		Libro found = em.find(Libro.class, id);
		return found;
	}

	public void findById(Long id) {
		Libro found = em.find(Libro.class, id);
		if (found != null) {
			logger.info("Libro cercato: " + found.toString());
		} else {
			logger.info("Libro non TROVATO!");
		}
	}

	public void findByIdAndDelete(Long id) {
		Libro found = em.find(Libro.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Libro con id " + id + " eliminato!");
		} else {
			logger.info("Libro non TROVATO!");
		}
	}

	public List<Libro> getLibroByAutore(String autore) {
		TypedQuery<Libro> query = em.createNamedQuery("searchByAutore", Libro.class);
		query.setParameter("autore", autore);
		return query.getResultList();
	}

}
