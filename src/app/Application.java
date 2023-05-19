package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ElementoCatalogoDAO;
import dao.LibroDAO;
import dao.PrestitoDAO;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.ElementoCatalogo;
import entities.Libro;
import entities.Utente;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = util.JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		ElementoCatalogoDAO elementoD = new ElementoCatalogoDAO(em);
		LibroDAO libroD = new LibroDAO(em);
		RivistaDAO rivistaD = new RivistaDAO(em);
		UtenteDAO utenteD = new UtenteDAO(em);
		PrestitoDAO prestitoD = new PrestitoDAO(em);

		// Libro libro1 = new Libro("Il nome della rosa", 1980, 536, "Umberto Eco",
		// Genere.ROMANZO);

		// libroD.save(libro1);

		// Rivista rivista1 = new Rivista("National Geographic", 2021, 120,
		// Periodicita.MENSILE);

		// rivistaD.save(rivista1);

		/*
		 * libroD.findByIdAndDelete(libro1.getId());
		 * rivistaD.findByIdAndDelete(rivista1.getId());
		 */

		// libroD.findById(libro1.getId());
		// rivistaD.findById(rivista1.getId());

		ricercaElementoCatalogoPerAnno(elementoD, 2021);
		ricercaLibroPerAutore(libroD, "Umberto Eco");
		ricercaElementoPerTitolo(elementoD, "rosa");

		// Utente utente1 = new Utente("Mario", "Rossi",
		// LocalDate.now().minusYears(29));

		// utenteD.save(utente1);

		// Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.now(),
		// LocalDate.now().plusWeeks(6));
		// Prestito prestito2 = new Prestito(utente1, rivista1, LocalDate.now(),
		// LocalDate.now().plusWeeks(6));

		// List<Prestito> listaPrestitiU1 = new ArrayList<>();
		// listaPrestitiU1.add(prestito1);
		// listaPrestitiU1.add(prestito2);

		// utente1.setListaPrestiti(listaPrestitiU1);

		// List<Prestito> listaPrestitiLibro1 = new ArrayList<>();
		// listaPrestitiLibro1.add(prestito1);

		// libro1.setListaPrestiti(listaPrestitiLibro1);

		// prestitoD.save(prestito1);
		// prestitoD.save(prestito2);

		Utente utente1 = utenteD.getById(3L);

		List<ElementoCatalogo> elementiInPrestito = elementoD
				.getElementiPrestitoByNumeroTessera(utente1.getNumeroTessera());
		if (!elementiInPrestito.isEmpty()) {
			logger.info("ELEMENTI IN PRESTITO DALL'UTENTE " + utente1.toString());
			for (ElementoCatalogo elemento : elementiInPrestito) {
				logger.info(elemento.toString());
			}
		} else {
			logger.info("L'utente non ha nessun elemento in prestito!");
		}

		List<ElementoCatalogo> elementiNonRestituiti = elementoD.getElementiPrestitiScaduti();
		if (!elementiNonRestituiti.isEmpty()) {
			logger.info("ELEMENTI IN PRESTITO E NON RESTITUITI SONO:");
			for (ElementoCatalogo elemento : elementiNonRestituiti) {
				logger.info(elemento.toString());
			}
		} else {
			logger.info("Nessun ELEMENTO NON RESTITUITO!");
		}

		em.close();
		emf.close();
	}

	private static void ricercaElementoCatalogoPerAnno(ElementoCatalogoDAO elementoD, int annoSearched) {
		List<ElementoCatalogo> listaElementiPerAnno = elementoD.getElementoByAnnoPubblicazione(annoSearched);
		if (!listaElementiPerAnno.isEmpty()) {
			logger.info("Risultato ricerca per anno " + annoSearched + ":");
			for (ElementoCatalogo elementoCatalogo : listaElementiPerAnno) {
				logger.info(elementoCatalogo.toString());
			}
		} else {
			logger.info("Nessun risultato per l'anno cercato!");
		}
	}

	private static void ricercaLibroPerAutore(LibroDAO libroD, String autore) {
		List<Libro> listaElementiPerAutore = libroD.getLibroByAutore(autore);
		if (!listaElementiPerAutore.isEmpty()) {
			logger.info("Risultato ricerca per autore " + autore + ":");
			for (Libro libroCercato : listaElementiPerAutore) {
				logger.info(libroCercato.toString());
			}
		} else {
			logger.info("Nessun LIBRO TROVATO per l'autore cercato!");
		}
	}

	private static void ricercaElementoPerTitolo(ElementoCatalogoDAO elementoD, String titolo) {
		List<ElementoCatalogo> listaElementiPerTitolo = elementoD.getElementoByTitolo(titolo);
		if (!listaElementiPerTitolo.isEmpty()) {
			logger.info("Risultato ricerca per titolo " + titolo + ":");
			for (ElementoCatalogo elementoCatalogo : listaElementiPerTitolo) {
				logger.info(elementoCatalogo.toString());
			}
		} else {
			logger.info("Nessun ELEMENTO TROVATO per il titolo cercato!");
		}
	}

}
