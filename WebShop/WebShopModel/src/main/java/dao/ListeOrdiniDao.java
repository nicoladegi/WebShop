package dao;

import java.util.List;

import javax.persistence.EntityManager;
import dto.ListeOrdiniDto;
import entities.ListeOrdini;

public class ListeOrdiniDao implements Dao<ListeOrdiniDto> {
	
    private EntityManager entityManager; 
    
    public ListeOrdiniDao(EntityManager em) {
    	entityManager = em;
    }
    
	   @Override
	    public void inserisci(ListeOrdiniDto listeOrdiniDto) {
	        try {
	            ListeOrdini listeOrdini = listeOrdiniDto.toModel();
	            entityManager.persist(listeOrdini);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void cancella(int id) {
	        try {
	            ListeOrdini listeOrdini = entityManager.find(ListeOrdini.class, id);
	            if (listeOrdini != null) {
	                entityManager.remove(listeOrdini);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public ListeOrdiniDto ricercaPerId(int id) {
	        try {
	            ListeOrdini listeOrdini = entityManager.find(ListeOrdini.class, id);
	            return listeOrdini != null ? listeOrdini.toDto() : null;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @Override
	    public void aggiorna(ListeOrdiniDto listeOrdiniDto) {
	    	ListeOrdini listeOrdini;
	        try {
	            listeOrdini = listeOrdiniDto.toModel();
	            listeOrdini = entityManager.merge(listeOrdini);
	        } 	catch (Exception e) {
	            	e.printStackTrace();
	        	}
	    }  
	    
	    //valutare l'aggiunta di un utente a cui associare lo storico
	    public List<ListeOrdini>  estrazioneStorico(String userEstrazione){
	    	return entityManager.createNamedQuery("ListeOrdini.findAllByUser", ListeOrdini.class).setParameter("user", userEstrazione).getResultList();	    
	    }
}