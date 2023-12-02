package dao;

import javax.persistence.EntityManager;

import dto.OrdineDto;
import entities.Ordine;

public class OrdiniDao implements Dao<OrdineDto>{
	public EntityManager em;
	
	
	public OrdiniDao(EntityManager em) {
		this.em = em;
	}


	   @Override
	    public void inserisci(OrdineDto t) {
	        try {
	            Ordine ordine = t.toModel();
	            em.persist(ordine);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void cancella(int id) {
	        try {
	            Ordine ordine = em.find(Ordine.class, id);
	            if (ordine != null) {
	                em.remove(ordine);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public OrdineDto ricercaPerId(int id) {
	        try {
	            Ordine ordine = em.find(Ordine.class, id);
	            return ordine != null ? ordine.toDto() : null;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @Override
	    public void aggiorna(OrdineDto t) {
	        try {
	            Ordine ordine = em.find(Ordine.class, t.getIdOrdine());
	            if (ordine != null) {
	                ordine.setProdotto(t.getProdotto().toModel());
	                ordine.setQuantita(t.getQuantita());
	                em.merge(ordine);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
