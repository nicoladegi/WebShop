package bl;

import java.util.List;
import dto.ListeOrdiniDto;
import dto.ProdottoDto;
import dto.ProduttoreDto;
import entities.ListeOrdini;

public interface EcommerceServiceInterface {
	
    public List<ProdottoDto> readProducts();

    public List<ProduttoreDto> readMakers();
    
    public List<ProdottoDto> filterProducts4Makers(String nomeProduttore);
    
    //Cancella proprio il prodotto sulla base dell'id
    public void deleteProduct(int id);
    
    //aggiorna ad esempio la giacenza
    public void updateProduct(ProdottoDto pDto);
    
    public boolean purchase(ListeOrdiniDto lDto);
    
    public ProdottoDto searchProduct(ProdottoDto pDto);
    
    public List<ListeOrdini> readPurchaseHistory(String user);
}
