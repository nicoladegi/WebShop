package bl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import dto.ListeOrdiniDto;
import dto.OrdineDto;
import dto.ProdottoDto;


@Stateful
public class Chart implements ChartInterfaceLocal<ProdottoDto>, ChartInterfaceRemote<ProdottoDto> {

	@EJB
	EcommerceServiceLocal eCom;

	private List<OrdineDto> shoppingList;
	private String user;
	
    public Chart() {
    	shoppingList = new ArrayList<OrdineDto>();
    }
    
    public boolean add(ProdottoDto pDto, int quantita) {
    	OrdineDto oDto = new OrdineDto.Builder().addProdotto(pDto).addQuantità(quantita).build();
    	return shoppingList.add(oDto) ? true : false;
    }

    //ProdottoDto avrà al suo interno la quantità da rimuovere
    
    public boolean remove(ProdottoDto pDto, int quantitarm) {
    	boolean result = false;
    	
    	for(OrdineDto oDto : shoppingList) {    		
    		if(oDto.getProdottoDto().equals(pDto)) {
    			if(oDto.getQuantita() == quantitarm) {
        			result = shoppingList.remove(oDto);
    			}	else {
	    				try {
	        				int quantitafin = oDto.getQuantita() - quantitarm;
	        				if(quantitafin<0) {
	        					throw new Exception();
	        				}
	        				oDto.setQuantita(quantitafin);
	        				result = true;
	    				}	catch (Exception e) {
	    						System.err.println("La quantità che si è cercato di rimuovere è minore della quantità presente nel carrello.");
	    						result = false;
	    					}
    				}
    		}
    	}
    	return result;
    }

	@Override
	public List<OrdineDto> readChart() {
		return shoppingList;
	}

	@Override
	public boolean dropChart() {
		boolean result = true;
		try{
			shoppingList.clear();
		}	catch(Exception e) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean purchase() {
		ListeOrdiniDto purchaseListDto = new ListeOrdiniDto();
		purchaseListDto.setOrdini(shoppingList);
		purchaseListDto.setUser(user);
		eCom.purchase(purchaseListDto);
		return false;
	}
	
	//Quando faccio equals tramite un ProdottoDto devo assicurarmi di aver fatto override di equals per non tenere conto di prezzo e quantita, 
	//che sono due campi che possono variare nel tempo, dando problemi col carrello in quanto ejb statefull, quindi utilizzare vecchie versioni di ProdottoDto
}
