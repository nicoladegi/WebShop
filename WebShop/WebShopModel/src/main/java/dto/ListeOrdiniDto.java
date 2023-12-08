package dto;

import java.util.ArrayList;
import java.util.List;

import entities.ListeOrdini;
import entities.Ordine;

public class ListeOrdiniDto {

    private long id;
    private List<OrdineDto> ordini;
	private String user;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<OrdineDto> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<OrdineDto> ordini) {
		this.ordini = ordini;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public ListeOrdini toModel() {
		
		ListeOrdini lOrdini = new ListeOrdini();
		List<Ordine> lOrdiniAux = new ArrayList<Ordine>();
 		for(OrdineDto oDto : this.ordini) {
			lOrdiniAux.add(oDto.toModel());
		}
 		lOrdini.setOrdini(lOrdiniAux);
 		lOrdini.setUser(this.user);
 		return lOrdini;
	}
}

