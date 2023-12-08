package entities;

import java.io.Serializable;
import javax.persistence.*;

import dto.OrdineDto;
import dto.ProdottoDto;


/**
 * The persistent class for the Ordine database table.
 * 
 */
@Entity
@NamedQuery(name="Ordine.findAll", query="SELECT o FROM Ordine o")
public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private int quantita;

	//bi-directional many-to-one association to Prodotto
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="prodotto_id")
	private Prodotto prodotto;

	public Ordine() {
	}

    private Ordine(Builder builder) {
        this.id = builder.id;
        this.prodotto = builder.prodotto;
        this.quantita = builder.quantita;
        //this.listeOrdini = builder.listeOrdini;
    }
    
    public static class Builder {
        private long id;
        private Prodotto prodotto;
        private int quantita;
        //private ListeOrdini listeOrdini;

        public Builder addId(long id) {
            this.id = id;
            return this;
        }

        public Builder addProdotto(Prodotto prodotto) {
            this.prodotto = prodotto;
            return this;
        }

        public Builder addQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        public Ordine build() {
            return new Ordine(this);
        }
    }
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Prodotto getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	public OrdineDto toDto() {
        OrdineDto dto = new OrdineDto.Builder().addidOrdine(this.id).addQuantità(this.quantita).build();

        if (this.prodotto != null) {
        	ProdottoDto pDto = this.prodotto.toDto();
            dto.setProdottoDto(pDto);
        }
        return dto;
    }

}