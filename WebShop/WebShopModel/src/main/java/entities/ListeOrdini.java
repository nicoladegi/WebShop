package entities;

import java.io.Serializable;
import javax.persistence.*;

import dto.ListeOrdiniDto;
import dto.OrdineDto;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ListeOrdini database table.
 * 
 */
@Entity
@NamedQuery(name="ListeOrdini.findAllByUser", query="SELECT l FROM ListeOrdini l WHERE l.user = :user")
public class ListeOrdini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String user;

	//bi-directional many-to-one association to Ordine
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL) //questo abilita alla propagazione della persistenza
	@JoinColumn(name="lista_id")
	private List<Ordine> ordini;

	public ListeOrdini() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Ordine> getOrdini() {
		return this.ordini;
	}

	public void setOrdini(List<Ordine> ordines) {
		this.ordini = ordines;
	}

	public Ordine addOrdine(Ordine ordine) {
		getOrdini().add(ordine);
		return ordine;
	}

	public Ordine removeOrdine(Ordine ordine) {
		getOrdini().remove(ordine);
		return ordine;
	}

	public ListeOrdiniDto toDto() {
		ListeOrdiniDto lDto = new ListeOrdiniDto();
		List<OrdineDto> lDtoAux = new ArrayList<OrdineDto>();
		for(Ordine o : this.ordini) {
			lDtoAux.add(o.toDto());
		}
		lDto.setOrdini(lDtoAux);
		lDto.setUser(this.user);
		return lDto;
	}
}