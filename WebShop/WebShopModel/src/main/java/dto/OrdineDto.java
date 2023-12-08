package dto;

import entities.Ordine;
import entities.Prodotto;

public class OrdineDto {

	private long idOrdine;
	private ProdottoDto prodottoDto;
	private int quantita;
	
	public OrdineDto(Builder build) {
		this.idOrdine = build.idOrdine;
		this.prodottoDto = build.prodottoDto;
		this.quantita = build.quantita;
	}
	
	public long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public ProdottoDto getProdottoDto() {
		return prodottoDto;
	}

	public void setProdottoDto(ProdottoDto prodottoDto) {
		this.prodottoDto = prodottoDto;
	}

	public static class Builder {
		private long idOrdine;
		private ProdottoDto prodottoDto;
		private int quantita;
		
		public Builder addidOrdine(long i) {
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
		//non porto in model la lista per non creare il loop
		return new Ordine.Builder().addId(idOrdine).addProdotto(prodotto).addQuantita(quantita).build();
	}
	
	@Override
	public String toString() {
		return "idOrdine=" + idOrdine + ", prodottoDto=" + prodottoDto + ", quantita=" + quantita ;
	}
}
