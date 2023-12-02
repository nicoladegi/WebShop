package entities;

import javax.persistence.*;
import dto.ProdottoDto;
import dto.ProduttoreDto;

/*
 * Nel tuo caso, l’annotazione @ManyToOne sulla proprietà produttore nella classe Prodotto indica che ogni Prodotto ha un Produttore. 
 * Quando persisti un oggetto Prodotto, dovresti avere un oggetto Produttore associato a quel Prodotto.
 * Non è necessario inserire manualmente l’ID del Produttore. Quando chiami il metodo persist sull’oggetto Prodotto, JPA dovrebbe automaticamente prendere l’ID dal Produttore associato e 
 * inserirlo nel campo idProduttore nel database.
 */

@Entity
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idProdotto;
	
	private String modello;
	private int stock;
	private float prezzo;
	private char tipo;
	
	//String catMerceologica;
	@ManyToOne(fetch = FetchType.EAGER)
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
	
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public Produttore getProduttore() {
		return produttore;
	}
	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}	
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public ProdottoDto toDto() {
	    ProduttoreDto produttoreDtoLight = this.produttore != null ? this.produttore.toDtoLight() : null;
	    return new ProdottoDto.Builder().addidProdotto(this.idProdotto).addModello(this.modello).addProduttore(produttoreDtoLight).addPrezzo(this.prezzo).addStock(this.stock).addTipo(tipo).build();
	}
}
