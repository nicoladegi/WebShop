package entities;

import java.io.Serializable;
import javax.persistence.*;

import dto.OrdineDto;


/**
 * The persistent class for the ordini database table.
 * 
 */
@Entity
@Table(name = "Ordini")
@NamedQuery(name="Ordini.findAll", query="SELECT o FROM Ordine o")
public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrdine;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idProdotto")
	private Prodotto prodotto;
	private int quantita;

	public Ordine(Builder builder) {
		super();
		this.idOrdine = builder.idOrdine;
		this.prodotto = builder.prodotto;
		this.quantita = builder.quantita;
	}
	
	public Ordine() {
		
	}

	public int getIdOrdine() {
		return this.idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	public static class Builder{
		
		private int idOrdine;
		private Prodotto prodotto;
		private int quantita;
		
		public Builder addidOrdine(int i) {
			this.idOrdine = i;
			return this;
		}
		
		public Builder addProdotto(Prodotto p) {
			this.prodotto = p;
			return this;
		}
		
		public Builder addQuantità(int s) {
			this.quantita = s;
			return this;
		}
	
		public Ordine build() {
			return new Ordine(this);
		}
	}

	public OrdineDto toDto() {
	    return new OrdineDto.Builder().addidOrdine(idOrdine).addProdotto(null).addQuantità(idOrdine).build();
	}
}