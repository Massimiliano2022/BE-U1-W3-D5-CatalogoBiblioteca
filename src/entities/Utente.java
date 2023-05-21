package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "utenti")

@NamedQuery(name = "deleteUtente", query = "DELETE FROM Utente u WHERE u.id = :id")
public class Utente {

	@Id
	@SequenceGenerator(name = "utente_seq", sequenceName = "utente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_seq")
	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private UUID numeroTessera;

	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	@OrderBy(value = "evento.dataEvento")
	private List<Prestito> listaPrestiti;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setNumeroTessera(UUID numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public UUID getNumeroTessera() {
		return numeroTessera;
	}

	public void setListaPrestiti(List<Prestito> listaPrestiti) {
		this.listaPrestiti = listaPrestiti;
	}

	public List<Prestito> getListaPrestiti() {
		return listaPrestiti;
	}

	public Utente() {

	}

	public Utente(String nome, String cognome, LocalDate dataNascita) {
		setNome(nome);
		setCognome(cognome);
		setDataNascita(dataNascita);
		setNumeroTessera(UUID.randomUUID());
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", numeroTessera=" + numeroTessera + "]";
	}

}
