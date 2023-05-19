package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ElementoCatalogoDAO;
import dao.LibroDAO;
import dao.RivistaDAO;
import entities.ElementoCatalogo;
import entities.Genere;
import entities.Libro;
import entities.Periodicita;
import entities.Rivista;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = util.JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		ElementoCatalogoDAO elementoD = new ElementoCatalogoDAO(em);
		LibroDAO libroD = new LibroDAO(em);
		RivistaDAO rivistaD = new RivistaDAO(em);

		Libro libro1 = new Libro("Il nome della rosa", 1980, 536, "Umberto Eco", Genere.ROMANZO);

		libroD.save(libro1);

		Rivista rivista1 = new Rivista("National Geographic", 2021, 120, Periodicita.MENSILE);

		rivistaD.save(rivista1);

		// libroD.findByIdAndDelete(libro1.getId());
		// rivistaD.findByIdAndDelete(rivista1.getId());

		libroD.findById(libro1.getId());
		rivistaD.findById(rivista1.getId());

		ricercaElementoCatalogoPerAnno(elementoD, 2021);
		ricercaLibroPerAutore(libroD, "Umberto Eco");
		ricercaElementoPerTitolo(elementoD, "rosa");

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
