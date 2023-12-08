package entities;

import java.io.Serializable;
import javax.persistence.*;

import dto.ProdottoDto;
import dto.ProduttoreDto;


/**
 * The persistent class for the prodotto database table.
 * 
 */
@Entity
@NamedQuery(name="Prodotto.findAll", query="SELECT p FROM Prodotto p")
public class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idProdotto;

	private String modello;

	private float prezzo;

	private int stock;

	private char tipo;

	//bi-directional many-to-one association to Produttore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idProduttore")
	private Produttore produttore;

	public Prodotto() {
	}

	public Prodotto(Builder builder) {
		this.idProdotto = builder.idProdotto;
	    this.modello = builder.modello;
	    this.stock = builder.stock;
	    this.produttore = builder.produttore;
	    this.prezzo = builder.prezzo;
	    this.tipo = builder.tipo;
	}
	
	public static class Builder {
		private Integer idProdotto;
		private String modello;
		private int stock;
		private float prezzo;
		private char tipo;
		private Produttore produttore;

		public Builder addidProdotto(int i) {
			this.idProdotto = i;
			return this;
		}
		
		public Builder addModello(String m) {
			this.modello = m;
			return this;
		}
		
		public Builder addStock(int s) {
			this.stock = s;
			return this;
		}
		
		public Builder addPrezzo(float p) {
			this.prezzo = p;
			return this;
		}
		
		public Builder addTipo(char t) {
			this.tipo = t;
			return this;
		}
		
		public Builder addProduttore (Produttore p) {
			this.produttore = p;
			return this;
		}
		
		public Prodotto build() {
			return new Prodotto(this);
		}
	}
	
	public int getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public float getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public char getTipo() {
		return this.tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}


	public Produttore getProduttore() {
		return this.produttore;
	}

	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}

	public ProdottoDto toDto() {
	    ProduttoreDto produttoreDtoLight = this.produttore != null ? this.produttore.toDtoLight() : null;
	    return new ProdottoDto.Builder().addidProdotto(this.idProdotto).addModello(this.modello).addProduttore(produttoreDtoLight).addPrezzo(this.prezzo).addStock(this.stock).addTipo(tipo).build();
	}

	@Override
	public String toString() {
		return "Prodotto [idProdotto=" + idProdotto + ", modello=" + modello + ", prezzo=" + prezzo + ", stock=" + stock
				+ ", tipo=" + tipo + ", " + produttore.toString();
	}
	
	
	
}