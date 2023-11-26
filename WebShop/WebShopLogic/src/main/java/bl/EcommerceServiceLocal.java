package bl;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;


import dto.ProdottoDto;
import dto.ProduttoreDto;

@Local
public interface EcommerceServiceLocal {

    public Map<Integer, ProdottoDto> consultaProdotti();

    public List<ProduttoreDto> consultaProduttori();
    
    public List<ProdottoDto> filtraProdottiPerProduttore(String nomeProduttore);
    
    public void eliminaProdotto(int id);
    
    public void aggiornaProdotto(ProdottoDto pDto);
}
