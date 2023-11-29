package bl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import dto.ProdottoDto;


@Stateful
public class Chart implements ChartInterfaceLocal<ProdottoDto>, ChartInterfaceRemote<ProdottoDto> {

	@EJB
	EcommerceService eCom;

	private List<ProdottoDto> shoppingList;
	
    public Chart() {
    	shoppingList = new ArrayList<ProdottoDto>();
    }
    
    
    public boolean add(ProdottoDto p) {
    	
    	boolean result = shoppingList.add(p) ? true : false;
    	return result;
    }

    //ProdottoDto avrà al suo interno la quantità da rimuovere
    
    public boolean remove(ProdottoDto p) {
    	boolean result = false;
    	
    	for(ProdottoDto prodottoDto : shoppingList) {
    		if(prodottoDto.equals(p)) {
    			if(prodottoDto.getQuantita() == p.getQuantita()) {
        			result = shoppingList.remove(p);
    			}	else {
	    				try {
	        				int quantitafin = prodottoDto.getQuantita() - p.getQuantita();
	        				if(quantitafin<0) {
	        					throw new Exception();
	        				}
	        				prodottoDto.setQuantita(quantitafin);
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
	public List<ProdottoDto> readChart() {
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
		eCom.purchase(shoppingList);
		return false;
	}
    
}
