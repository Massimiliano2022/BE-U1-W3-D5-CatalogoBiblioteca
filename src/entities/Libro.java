package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "libri_catalogo")
@NamedQuery(name = "searchByAutore", query = "select l from Libro l where l.autore = :autore")
public class Libro extends ElementoCatalogo {

	@Column(name = "autore")
	private String autore;
	@Column(name = "genere")
	private Genere genere;

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getAutore() {
		return autore;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public Genere getGenere() {
		return genere;
	}

	public Libro() {

	}

	public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
		super(titolo, annoPubblicazione, numeroPagine);
		setAutore(autore);
		setGenere(genere);
	}

	@Override
	public String toString() {
		return "Libro [id=" + getId() + ", titolo=" + getTitolo() + ", annoPubblicazione=" + getAnnoPubblicazione()
				+ ", numeroPagine=" + getNumeroPagine() + ", autore=" + autore + ", genere=" + genere + "]";
	}

}
