package bl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
@Stateless(name="eService", mappedName="eService")
public class EcommerceService implements EcommerceServiceLocal {

		private ProdottoDao prodottoDao;
		private ProduttoreDao produttoreDao;
		
	    public EcommerceService() {
	    	System.out.println("Ejb istanziato");

	    }

	    @PostConstruct //verrà eseguito automaticamente dal contenitore EJB dopo la creazione dell’istanza di EcommerceService (durante l'invocazione remota) e prima che venga utilizzata per la prima volta.
	    private void initializeDao() {
	    	prodottoDao = new ProdottoDao();
	    	produttoreDao = new ProduttoreDao();
	    	System.out.println("Sono stato richiamato, Dao inizializzati");
	    }
	    
	    public ProdottoDto searchProduct(int id) {
	    	ProdottoDto result = prodottoDao.ricercaPerId(id);
	    	return result;
	    }

		@Override
		public List<ProdottoDto> readProducts() {
	    	List<ProdottoDto> interrogazione = prodottoDao.estraiArchivio();
	    	return interrogazione;
		}

		@Override
		public List<ProduttoreDto> readMakers() {
			List<ProduttoreDto> interrogazione = produttoreDao.estraiArchivio();
	    	return interrogazione;
		}

		@Override
		public List<ProdottoDto> filterProducts4Makers(String nomeProduttore) {
	    	List<ProdottoDto> outList = prodottoDao.ricercaPerProduttoreHQL(nomeProduttore);
	    	return outList;
		}

		@Override
		public void deleteProduct(int id) {
	    	prodottoDao.cancella(id);			
		}

		@Override
		public void updateProduct(ProdottoDto pDto) {
	    	prodottoDao.aggiorna(pDto);			
		}

		@Override
		public boolean purchase(List<ProdottoDto> lpDto) {
			try {
				lpDto.forEach(pDto->prodottoDao.inserisci(pDto));
				}	catch(Exception e) {
					return false;
					}
			return true;
		}
	    
	    //Aggiungi prodotto
	    //Aggiungi produttore
	    //questi metodi useranno le versioni dei DTO contenenti i valori di ID ed i rispettivi riferimenti agli oggetti ProdottoDto e ProduttoreDto
}
