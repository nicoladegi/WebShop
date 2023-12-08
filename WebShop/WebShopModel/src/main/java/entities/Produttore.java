package entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dto.ProdottoDto;
import dto.ProduttoreDto;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The persistent class for the produttore database table.
 * 
 */
@Entity
@NamedQuery(name="Produttore.findAll", query="SELECT p FROM Produttore p")
public class Produttore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idProduttore;

	@Column(name="nomeProduttore")
	private String nomeProduttore;

	//bi-directional many-to-one association to Prodotto
	@OneToMany(mappedBy="produttore", fetch=FetchType.LAZY)
	@JsonIgnoreProperties("produttore")
	private List<Prodotto> prodotti;

	public Produttore(Builder builder) {
		this.idProduttore = builder.idProduttore;
		this.nomeProduttore = builder.nomeProduttore;
		this.prodotti = builder.prodotti;
	}

	
	public Produttore() {
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
		return this.idProduttore;
	}

	public void setIdProduttore(int idProduttore) {
		this.idProduttore = idProduttore;
	}

	public String getNomeProduttore() {
		return this.nomeProduttore;
	}

	public void setNomeProduttore(String nomeProduttore) {
		this.nomeProduttore = nomeProduttore;
	}

	public List<Prodotto> getProdotti() {
		return this.prodotti;
	}

	public void setProdotti(List<Prodotto> prodottos) {
		this.prodotti = prodottos;
	}

	public Prodotto addProdotto(Prodotto prodotto) {
		getProdotti().add(prodotto);
		prodotto.setProduttore(this);

		return prodotto;
	}

	public Prodotto removeProdotto(Prodotto prodotto) {
		getProdotti().remove(prodotto);
		prodotto.setProduttore(null);

		return prodotto;
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


	@Override
	public String toString() {
		return "idProduttore=" + idProduttore + ", nomeProduttore=" + nomeProduttore ;
	}
	   
	   
	   
}