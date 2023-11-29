package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ordini database table.
 * 
 */
@Entity
@NamedQuery(name="Ordini.findAll", query="SELECT o FROM Ordini o")
public class Ordini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrdine;

	@ManyToOne
	@JoinColumn(name="idProdotto")
	private int idProdotto;

	private int quantita;

	public Ordini() {
	}

	public int getIdOrdine() {
		return this.idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}