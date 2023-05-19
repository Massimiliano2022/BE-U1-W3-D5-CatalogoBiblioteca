package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.ElementoCatalogo;

public class ElementoCatalogoDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public ElementoCatalogoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(ElementoCatalogo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		logger.info("Elemento salvato " + e.toString());
	}

	public ElementoCatalogo getById(Long id) {
		ElementoCatalogo found = em.find(ElementoCatalogo.class, id);
		return found;
	}

	public void findByIdAndDelete(Long id) {
		ElementoCatalogo found = em.find(ElementoCatalogo.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Elemento con id " + id + " eliminato!");
		} else {
			logger.info("Elemento non TROVATO!");
		}
	}

	public List<ElementoCatalogo> getElementoByAnnoPubblicazione(int annoPubblicazione) {
		TypedQuery<ElementoCatalogo> query = em.createNamedQuery("searchByAnnoPubblicazione", ElementoCatalogo.class);
		query.setParameter("annoPubblicazione", annoPubblicazione);
		return query.getResultList();
	}

	public List<ElementoCatalogo> getElementoByTitolo(String titolo) {
		TypedQuery<ElementoCatalogo> query = em.createNamedQuery("searchByTitolo", ElementoCatalogo.class);
		query.setParameter("titolo", titolo);
		return query.getResultList();
	}

	public List<ElementoCatalogo> getElementiPrestitoByNumeroTessera(UUID numeroTessera) {
		TypedQuery<ElementoCatalogo> query = em.createNamedQuery("searchPrestitiByNumeroTessera",
				ElementoCatalogo.class);
		query.setParameter("numeroTessera", numeroTessera);
		return query.getResultList();
	}

	public List<ElementoCatalogo> getElementiPrestitiScaduti() {
		TypedQuery<ElementoCatalogo> query = em.createNamedQuery("searchPrestitiScaduti", ElementoCatalogo.class);
		return query.getResultList();
	}
}
