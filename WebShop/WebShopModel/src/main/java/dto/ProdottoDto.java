package dto;

import java.util.Objects;

import entities.Prodotto;
import entities.Produttore;

public class ProdottoDto {

	private int id;
	private String modello;
	private ProduttoreDto produttore;
	private float prezzo;
	private int quantita;
	private char tipo;
	
	public ProdottoDto() {
		
	}
	
	public ProdottoDto(Builder builder) {
		this.id = builder.idProdotto;
		this.modello = builder.modello;
		this.produttore = builder.produttore;
		this.prezzo = builder.prezzo;
		this.quantita = builder.stock;
		this.tipo = builder.tipo;
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public ProduttoreDto getProduttore() {
		return produttore;
	}

	public void setProduttore(ProduttoreDto produttore) {
		this.produttore = produttore;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public static class Builder {
		private Integer idProdotto;
		private String modello;
		private int stock;
		private float prezzo;
		private char tipo;
		private ProduttoreDto produttore;

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
		
		public Builder addProduttore (ProduttoreDto p) {
			this.produttore = p;
			return this;
		}
		
		public ProdottoDto build() {
			return new ProdottoDto(this);
		}
	}

	public Prodotto toModel() {
		Produttore produttore;
		
		if(this.produttore != null) {
			produttore = this.produttore.toModel();
		}	else{
			produttore = null;
		}
		return new Prodotto.Builder().addidProdotto(this.id).addModello(this.modello).addStock(this.quantita).addProduttore(produttore).addPrezzo(this.prezzo).addTipo(this.tipo).build();
	}

	@Override
	public String toString() {
		return "modello=" + modello + ", "+ produttore.toString() + ", prezzo=" + prezzo + ", quantita=" + quantita + " , tipo=" + tipo ;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, modello, produttore, tipo);
	}

	@Override //No quantità
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdottoDto other = (ProdottoDto) obj;
		return id == other.id && Objects.equals(modello, other.modello)
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo)
				&& Objects.equals(produttore, other.produttore) && tipo == other.tipo;
	}

	public void print() {
		System.out.println(this.toString());
	}
}
