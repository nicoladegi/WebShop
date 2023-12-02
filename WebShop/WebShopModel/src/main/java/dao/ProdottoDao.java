package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dto.ProdottoDto;
import entities.Prodotto;
import javax.persistence.*;

public class ProdottoDao implements Dao<ProdottoDto>{

	private EntityManager em;
	
	public ProdottoDao(EntityManager em) {
		this.em = em;
	}
	
	//CRUDs
    public void inserisci(ProdottoDto prodottoDto) {
    	Prodotto prodotto = new Prodotto.Builder()
		        			.addModello(prodottoDto.getModello())
		        			.addStock(prodottoDto.getQuantita())
		        			.addProduttore(prodottoDto.getProduttore().toModel())
		        			.addPrezzo(prodottoDto.getPrezzo())
		        			.build();
        try {
        	em.persist(prodotto);
        } 	catch (Exception e) {
            e.printStackTrace();
        	}
    }

    public void cancella(int id) {
        try {
        	Prodotto prodotto = em.find(Prodotto.class, id);
	        if (prodotto != null) {
	            em.remove(prodotto);
	        }
        } 	catch (Exception e) {
            	e.printStackTrace();
        	}
    }

    public ProdottoDto ricercaPerId(int id) {
    	Prodotto prodotto = null;
    	try {
    		prodotto = em.find(Prodotto.class, id);
        } 	catch (Exception e) {
        		e.getMessage();
        	}
        return prodotto != null ? prodotto.toDto() : null;
    }
    
    public ProdottoDto ricercaPerNome(String nome) {
    	Prodotto prodotto = null;
    	try {
    		prodotto = em.find(Prodotto.class, nome);
        } 	catch (Exception e) {
        		e.getMessage();
        	}
        return prodotto != null ? prodotto.toDto() : null;
    }
    
	@SuppressWarnings("unchecked")
	public List<ProdottoDto> ricercaPerProduttoreHQL(String nomeProduttore){
		List<Prodotto> auxList = new ArrayList<>();
		try {
			javax.persistence.Query q = em.createQuery("FROM Prodotto p WHERE p.produttore.nomeProduttore = :x");
			q.setParameter("x", nomeProduttore);
			auxList = (List<Prodotto>) q.getResultList();
		}	catch(Exception e) {
				e.printStackTrace();
			}
		List<ProdottoDto> outList = auxList.stream().map(Prodotto::toDto).collect(Collectors.toList());
		return outList;
	}

    public void aggiorna(ProdottoDto prodottoDto) {
        try {
	        Prodotto prodotto = em.find(Prodotto.class, prodottoDto.getId());
	        if (prodotto != null) {
	            prodotto.setModello(prodottoDto.getModello());
	            prodotto.setStock(prodottoDto.getQuantita());
	            prodotto.setProduttore(prodottoDto.getProduttore().toModel());
	            prodotto.setPrezzo(prodottoDto.getPrezzo());
	            em.merge(prodotto);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
	@SuppressWarnings("unchecked")
    public List<ProdottoDto> estraiArchivio() {
    	List<Prodotto> auxList = new ArrayList<>();
		try {
			Query q = em.createQuery("FROM Prodotto ");
			auxList = (List<Prodotto>) q.getResultList();
		}	catch(Exception e) {
				e.printStackTrace();
			}
		
		List<ProdottoDto> outList = auxList.stream().map(Prodotto::toDto).collect(Collectors.toList());
		return outList;
    }
 
}
