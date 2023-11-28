package bl;

import java.util.List;
import java.util.Map;
import dto.ProdottoDto;
import dto.ProduttoreDto;

public interface EcommerceServiceInterface {
	
    public Map<Integer, ProdottoDto> readProducts();

    public Map<Integer, ProduttoreDto> readMakers();
    
    public List<ProdottoDto> filterProducts4Makers(String nomeProduttore);
    
    //Cancella proprio il prodotto sulla base dell'id
    public void deleteProduct(int id);
    
    //aggiorna ad esempio la giacenza
    public void updateProduct(ProdottoDto pDto);
    
    public boolean purchase(List<ProdottoDto> lpDto);
    
    public ProdottoDto searchProduct(int id);
}
