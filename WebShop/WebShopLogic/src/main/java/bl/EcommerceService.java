package bl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.ProdottoDao;
import dao.ProduttoreDao;
import dto.ProdottoDto;
import dto.ProduttoreDto;

/**
 * Session Bean implementation class EcommerceService
 * Ogni tanto quando la servlet si bugga è perchè effettua un deploy farlocco del .war. Con il progetto in running sul server ho aperto il buildpath di un progetto
 * il server ha rideployato il progetto e questa volta la .war è stata letta correttamente.
 */
@Stateless
@LocalBean
public class EcommerceService implements EcommerceServiceLocal {

		private ProdottoDao prodottoDao;
		private ProduttoreDao produttoreDao;
		
	    public EcommerceService() {
	    	System.out.println("Ejb istanziato");

	    }

	    @PostConstruct //verrà eseguito automaticamente dal contenitore EJB dopo la creazione dell’istanza di EcommerceService (durante l'invocazione remota) e prima che venga utilizzata per la prima volta.
	    private void inizializzaDao() {
	    	prodottoDao = new ProdottoDao();
	    	produttoreDao = new ProduttoreDao();
	    	System.out.println("Sono stato richiamato, Dao inizializzati");
	    }
	    
	    //estrapola lista degli articoli del magazzino
	    public Map<Integer, ProdottoDto> consultaProdotti() {
	    	Map<Integer, ProdottoDto> interrogazione = prodottoDao.estraiArchivio();
	    	return interrogazione;
	    }

	    //estrapola la lista dei brand commercializzati
	    public List<ProduttoreDto> consultaProduttori() {
	    	List<ProduttoreDto> interrogazione = produttoreDao.estraiArchivio();
	    	return interrogazione;
	    }  
	    
	    public List<ProdottoDto> filtraProdottiPerProduttore(String nomeProduttore){
	    	List<ProdottoDto> outList = prodottoDao.ricercaPerProduttoreHQL(nomeProduttore);
	    	return outList;
	    }
	    
	    public void eliminaProdotto(int id) {
	    	prodottoDao.cancella(id);
	    }
	    
	    public void aggiornaProdotto(ProdottoDto pDto) {
	    	prodottoDao.aggiorna(pDto);
	    }
	    
	    public ProdottoDto cercaProdotto(int id) {
	    	ProdottoDto result = prodottoDao.ricercaPerId(id);
	    	return result;
	    }
	    
	    //Aggiungi prodotto
	    //Aggiungi produttore
	    //questi metodi useranno le versioni dei DTO contenenti i valori di ID ed i rispettivi riferimenti agli oggetti ProdottoDto e ProduttoreDto
}
