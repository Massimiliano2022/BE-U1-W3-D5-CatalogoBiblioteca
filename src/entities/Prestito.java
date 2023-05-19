package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "prestiti")
public class Prestito {

	@Id
	@SequenceGenerator(name = "prestito_seq", sequenceName = "prestito_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestito_seq")
	private Long id;

	@ManyToOne
	// @JoinColumn(name = "utente_id", referencedColumnName = "id", nullable =
	// false)
	// @Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Utente utente;

	@ManyToOne
	// @JoinColumn(name = "utente_id", referencedColumnName = "id", nullable =
	// false)
	// @Cascade(org.hibernate.annotations.CascadeType.ALL)
	private ElementoCatalogo elemento;

	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzione;
	private LocalDate dataRestituzioneEffettiva;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setElemento(ElementoCatalogo elemento) {
		this.elemento = elemento;
	}

	public ElementoCatalogo getElemento() {
		return elemento;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public void setDataRestituzione(LocalDate dataRestituzione) {
		this.dataRestituzione = dataRestituzione;
	}

	public LocalDate getDataRestituzione() {
		return dataRestituzione;
	}

	public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public LocalDate getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public Prestito() {

	}

	public Prestito(Utente utente, ElementoCatalogo elemento, LocalDate dataInizioPrestito,
			LocalDate dataRestituzioneEffettiva) {
		setUtente(utente);
		setElemento(elemento);
		setDataInizioPrestito(dataInizioPrestito);
		setDataRestituzione(dataInizioPrestito.plusDays(30));
		setDataRestituzioneEffettiva(dataRestituzioneEffettiva);
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente.toString() + ", elemento=" + elemento.toString()
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzione=" + dataRestituzione
				+ ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}
