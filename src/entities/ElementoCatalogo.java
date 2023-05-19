package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "elementi_catalogo")
@NamedQuery(name = "searchByAnnoPubblicazione", query = "select e from ElementoCatalogo e where e.annoPubblicazione = :annoPubblicazione")
@NamedQuery(name = "searchByTitolo", query = "SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE CONCAT('%', :titolo, '%')")
public abstract class ElementoCatalogo {

	@Id
	@SequenceGenerator(name = "elemento_seq", sequenceName = "elemento_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elemento_seq")
	private Long id;
	private String titolo;
	private int annoPubblicazione;
	private int numeroPagine;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setNumeroPagine(int numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	public ElementoCatalogo() {

	}

	public ElementoCatalogo(String titolo, int annoPubblicazione, int numeroPagine) {
		setTitolo(titolo);
		setAnnoPubblicazione(annoPubblicazione);
		setNumeroPagine(numeroPagine);
	}
}
