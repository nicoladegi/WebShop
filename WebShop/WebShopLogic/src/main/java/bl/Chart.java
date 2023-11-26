package bl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import dto.ProdottoDto;


@Stateful
@LocalBean
public class Chart implements ChartLocal {

	@EJB
	EcommerceService eCom;

	private List<ProdottoDto> listaAcquisti;
	
    public Chart() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    private void inizializzaLista() {
    	listaAcquisti = new ArrayList<ProdottoDto>();
    }
    
    //questi metodi useranno le versioni dei DTO NON contenenti i valori di ID ed i rispettivi riferimenti agli oggetti ProdottoDto e ProduttoreDto

    
    public boolean addChart(ProdottoDto p) {
    	
    	boolean result = listaAcquisti.add(p) ? true : false;
    	return result;
    }

    //ProdottoDto avrà al suo interno la quantità da rimuovere
    
    public boolean removeFromChart(ProdottoDto p) {
    	boolean result = false;
    	
    	for(ProdottoDto prodottoDto : listaAcquisti) {
    		if(prodottoDto.equals(p)) {
    			if(prodottoDto.getQuantita() == p.getQuantita()) {
        			result = listaAcquisti.remove(p);
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
    
    //public boolean purchase() {
    	//eCom;

   // }
}
