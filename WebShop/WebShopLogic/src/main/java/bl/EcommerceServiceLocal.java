package bl;

import java.util.List;

import javax.ejb.Local;


import dto.ProdottoDto;
import dto.ProduttoreDto;

@Local
public interface EcommerceServiceLocal {

    public List<ProdottoDto> consultaProdotti();

    public List<ProduttoreDto> consultaProduttori();
    
    public List<ProdottoDto> filtraProdottiPerProduttore(String nomeProduttore);
    
    public void eliminaProdotto(int id);
    
    public void aggiornaProdotto(ProdottoDto pDto);
}
