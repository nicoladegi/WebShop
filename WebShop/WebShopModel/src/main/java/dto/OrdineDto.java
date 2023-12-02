package dto;

import entities.Ordine;
import entities.Prodotto;

public class OrdineDto {

	private int idOrdine;
	private ProdottoDto prodottoDto;
	private int quantita;
	
	public OrdineDto(Builder build) {
		this.idOrdine = build.idOrdine;
		this.prodottoDto = build.prodottoDto;
		this.quantita = build.quantita;
	}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public ProdottoDto getProdotto() {
		return prodottoDto;
	}
	public void setProdotto(ProdottoDto prodottoDto) {
		this.prodottoDto = prodottoDto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public static class Builder {
		private int idOrdine;
		private ProdottoDto prodottoDto;
		private int quantita;
		
		public Builder addidOrdine(int i) {
			this.idOrdine = i;
			return this;
		}
		
		public Builder addProdotto(ProdottoDto p) {
			this.prodottoDto = p;
			return this;
		}
		
		public Builder addQuantità(int s) {
			this.quantita = s;
			return this;
		}

		public OrdineDto build() {
			return new OrdineDto(this);
		}
	}

	public Ordine toModel() {
		Prodotto prodotto;
		
		if(this.prodottoDto != null) {
			prodotto = this.prodottoDto.toModel();
		}	else{
			prodotto = null;
		}
		return new Ordine.Builder().addidOrdine(idOrdine).addProdotto(prodotto).addQuantità(idOrdine).build();
	}
	
	@Override
	public String toString() {
		return "idOrdine=" + idOrdine + ", prodottoDto=" + prodottoDto + ", quantita=" + quantita ;
	}
}
