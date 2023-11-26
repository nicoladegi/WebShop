package dto;

import java.util.List;
import java.util.stream.Collectors;

import entities.Prodotto;
import entities.Produttore;

public class ProduttoreDto {

	private Integer idProduttore;
	private String nome;
	private List<ProdottoDto> prodotti;

	public ProduttoreDto(Builder builder) {
		this.idProduttore = builder.idProduttore;
		this.nome = builder.nomeProduttore;
		this.prodotti = builder.prodotti;
	}
	
	public ProduttoreDto(int idProduttore, String nome) {
		super();
		this.idProduttore = idProduttore;
		this.nome = nome;
		this.prodotti = null;
	}
	
	public ProduttoreDto(Integer idProduttore) {
		this.idProduttore = idProduttore;
		this.nome = null;
		this.prodotti = null;

	}
	
	public ProduttoreDto(String nome) {
		this.idProduttore = null;
		this.nome = nome;
		this.prodotti = null;

	}

	public Integer getIdProduttore() {
		return idProduttore;
	}

	public void setIdProduttore(Integer idProduttore) {
		this.idProduttore = idProduttore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdottoDto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoDto> prodotti) {
		this.prodotti = prodotti;
	}
	
	public static class Builder {
		
		private Integer idProduttore;
		private String nomeProduttore;
		private List<ProdottoDto> prodotti;
		
		public Builder addidProduttore(int i) {
			this.idProduttore = i;
			return this;
		}
		
		public Builder addNomeProduttore(String n) {
			this.nomeProduttore = n;
			return this;
		}
		
		public Builder addProdotti (List<ProdottoDto> p) {
			this.prodotti = p;
			return this;
		}
		
		public ProduttoreDto build() {
			return new ProduttoreDto(this);
		}
	}
	
	public Produttore toModel() {
		List<Prodotto> prodotti = null;
		Produttore produttore;
		//.map(...): Prende una funzione come argomento e applica questa funzione a ciascun elemento dello stream. Il risultato è un nuovo stream che contiene gli elementi trasformati.
	    if(this.prodotti!=null) { //nelle relazioni molti a uno, dal lato di uno non è necessario inserire la lista dei molti nell'inserimento
			prodotti = this.prodotti.stream().map(ProdottoDto::toModel).collect(Collectors.toList());
	    }
	    if(this.idProduttore==null) {
		    produttore = new Produttore.Builder().addNomeProduttore(this.nome).addProdotti(prodotti).build();
	    }	else {
		    produttore = new Produttore.Builder().addidProduttore(this.idProduttore).addNomeProduttore(this.nome).addProdotti(prodotti).build();
	    }
	    return produttore;
	}

	@Override
	public String toString() {
		return "produttore =" + nome ;
	}
	
	public static void print(ProduttoreDto p) {
		System.out.println(p.toString());
	}
}
