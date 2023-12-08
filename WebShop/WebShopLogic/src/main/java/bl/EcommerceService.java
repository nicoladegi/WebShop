package bl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.ListeOrdiniDao;
import dao.ProdottoDao;
import dao.ProduttoreDao;
import dto.ListeOrdiniDto;
import dto.OrdineDto;
import dto.ProdottoDto;
import dto.ProduttoreDto;
import entities.ListeOrdini;

/**
 * Session Bean implementation class EcommerceService
 * Ogni tanto quando la servlet si bugga è perchè effettua un deploy farlocco del .war. Con il progetto in running sul server ho aperto il buildpath di un progetto
 * il server ha rideployato il progetto e questa volta la .war è stata letta correttamente.
 */
@Stateless(name="eService", mappedName="eService")
public class EcommerceService implements EcommerceServiceLocal {

		@PersistenceContext(unitName = "MagNegozio")
		private EntityManager em;
		
		private ProdottoDao prodottoDao;
		private ProduttoreDao produttoreDao;
		private ListeOrdiniDao ordiniDao;
		
	    public EcommerceService() {
	    	System.out.println("Ejb istanziato");
	    }

	    @PostConstruct //verrà eseguito automaticamente dal contenitore EJB dopo la creazione dell’istanza di EcommerceService (durante l'invocazione remota) e prima che venga utilizzata per la prima volta.
	    private void initializeDao() {
	    	
	    	prodottoDao = new ProdottoDao(em);
	    	produttoreDao = new ProduttoreDao(em);
	    	ordiniDao = new ListeOrdiniDao(em);
	    	System.out.println("Sono stato richiamato, Dao inizializzati");
	    }
	    
	    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	    public ProdottoDto searchProduct(ProdottoDto pDto) {
	    	
	    	ProdottoDto result = prodottoDao.ricercaPerId(pDto.getId());
	    	if(result==null) {
	    		result = prodottoDao.ricercaPerNome(pDto.getModello());
	    	}
	    	return result;
	    }

		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ProdottoDto> readProducts() {
			System.out.println("readProducts");
	    	List<ProdottoDto> interrogazione = prodottoDao.estraiArchivio();
	    	return interrogazione;
		}

		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ProduttoreDto> readMakers() {
			
			List<ProduttoreDto> interrogazione = produttoreDao.estraiArchivio();
	    	return interrogazione;
		}

		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ProdottoDto> filterProducts4Makers(String nomeProduttore) {
			
	    	List<ProdottoDto> outList = prodottoDao.ricercaPerProduttoreHQL(nomeProduttore);
	    	return outList;
		}

		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void deleteProduct(int id) {
			
	    	prodottoDao.cancella(id);			
		}

		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void updateProduct(ProdottoDto pDto) {
			
	    	prodottoDao.aggiorna(pDto);			
		}
		
		

		//***********************************************************************************************************************
		//***********************************************************************************************************************
		//***********************************************************************************************************************
		//***********************************************************************************************************************

		
		
		
		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean purchase(ListeOrdiniDto lDto) {
			try {
				ordiniDao.inserisci(lDto);
				for(OrdineDto oDto : lDto.getOrdini()) {
					ProdottoDto pDto = prodottoDao.ricercaPerId(oDto.getProdottoDto().getId());
					pDto.setQuantita(pDto.getQuantita()-oDto.getQuantita());
					prodottoDao.aggiorna(pDto);
				}
				
			}	catch(Exception e) {
					return false;
				}
			return true;
		}
		
		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ListeOrdini> readPurchaseHistory(String user) {
			List<ListeOrdini> storico = ordiniDao.estrazioneStorico(user);
			
			return storico;
		}
	    
	    //Aggiungi prodotto
	    //Aggiungi produttore
	    //questi metodi useranno le versioni dei DTO contenenti i valori di ID ed i rispettivi riferimenti agli oggetti ProdottoDto e ProduttoreDto
}
