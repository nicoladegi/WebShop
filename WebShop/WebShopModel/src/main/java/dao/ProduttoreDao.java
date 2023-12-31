package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dto.ProduttoreDto;
import entities.Produttore;
import javax.persistence.*;

public class ProduttoreDao implements Dao<ProduttoreDto> {
	
	private EntityManager em; //em rappresenta la sessione di connessione al DB
	
	public ProduttoreDao(EntityManager em) {
		this.em = em;
	}
	
	//CRUDs
	public void inserisci(ProduttoreDto produttoreDto) {
    	Produttore produttore = produttoreDto.toModel();
        try {
        	em.persist(produttore);
        } 	catch (Exception e) {
            e.printStackTrace();
        	}
    }

    public void cancella(int id) {
        try {
        	Produttore produttore = em.find(Produttore.class, id);
	        if (produttore != null) {
	            em.remove(produttore);
	        }
        } 	catch (Exception e) {
            	e.printStackTrace();
        	}
    }

    public ProduttoreDto ricercaPerId(int id) {
    	Produttore produttore = null;
    	try {
    		produttore = em.find(Produttore.class, id);
        } 	catch (Exception e) {
        		e.getMessage();
        	}
        return produttore != null ? produttore.toDto() : null;
    }
    
    public ProduttoreDto ricercaPerNome(String nome) {
    	Produttore produttore = null;
    	try {
    		produttore = em.find(Produttore.class, nome);
        } 	catch (Exception e) {
        		e.getMessage();
        	}
        return produttore != null ? produttore.toDto() : null;
    }

    public void aggiorna(ProduttoreDto produttoreDto) {
        try {
	        Produttore produttore = em.find(Produttore.class, produttoreDto.getIdProduttore());
	        if (produttore != null) {
	            produttore.setNomeProduttore(produttoreDto.getNome());
	            em.merge(produttore);
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@SuppressWarnings("unchecked")
    public List<ProduttoreDto> estraiArchivio() {
    	List<Produttore> auxList = new ArrayList<>();
		try {
			Query q = em.createQuery("FROM Produttore ");
			auxList = (List<Produttore>) q.getResultList();
		}	catch(Exception e) {
				e.printStackTrace();
			}
		
		List<ProduttoreDto> outList = auxList.stream().map(Produttore::toDto).collect(Collectors.toList());
		return outList;
    }
}
