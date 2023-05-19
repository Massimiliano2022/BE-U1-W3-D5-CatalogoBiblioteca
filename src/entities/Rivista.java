package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "riviste_catalogo")
public class Rivista extends ElementoCatalogo {

	@Column(name = "periodicita")
	private Periodicita periodicita;

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public Rivista() {

	}

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
		super(titolo, annoPubblicazione, numeroPagine);
		setPeriodicita(periodicita);
	}

	@Override
	public String toString() {
		return "Rivista [id=" + getId() + ", titolo=" + getTitolo() + ", annoPubblicazione=" + getAnnoPubblicazione()
				+ ", numeroPagine=" + getNumeroPagine() + ", periodicita=" + periodicita + "]";
	}

}
