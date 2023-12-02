package entities;

import java.util.List;
import java.util.stream.Collectors;

import dto.ProdottoDto;
import dto.ProduttoreDto;
import javax.persistence.*;


@Entity
public class Produttore {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idProduttore;
	
	private String nomeProduttore;
	
	@OneToMany(mappedBy = "produttore", fetch = FetchType.LAZY)
	private List<Prodotto> prodotti;

	public Produttore() {
	}
	
	public Produttore(Builder builder) {
		this.idProduttore = builder.idProduttore;
		this.nomeProduttore = builder.nomeProduttore;
		this.prodotti = builder.prodotti;
	}
	
	public static class Builder {
		
		private Integer idProduttore;
		private String nomeProduttore;
		private List<Prodotto> prodotti;
		
		public Builder addidProduttore(int i) {
			this.idProduttore = i;
			return this;
		}
		
		public Builder addNomeProduttore(String n) {
			this.nomeProduttore = n;
			return this;
		}
		
		public Builder addProdotti (List<Prodotto> p) {
			this.prodotti = p;
			return this;
		}
		
		public Produttore build() {
			return new Produttore(this);
		}
	}
	
	public int getIdProduttore() {
		return idProduttore;
	}

	public String getNomeProduttore() {
		return nomeProduttore;
	}

	public void setNomeProduttore(String nomeProduttore) {
		this.nomeProduttore = nomeProduttore;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
	public ProduttoreDto toDto() {
		//.map(...): Prende una funzione come argomento e applica questa funzione a ciascun elemento dello stream. Il risultato è un nuovo stream che contiene gli elementi trasformati.
	    List<ProdottoDto> prodottiDto = this.prodotti.stream()
	        .map(Prodotto::toDto)
	        .collect(Collectors.toList());
	    return new ProduttoreDto.Builder().addidProduttore(this.idProduttore).addNomeProduttore(this.nomeProduttore).addProdotti(prodottiDto).build();
	}
	
   public ProduttoreDto toDtoLight() {
        return new ProduttoreDto(this.idProduttore, this.nomeProduttore);
    }
}
