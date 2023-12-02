package dao;

public interface Dao <T> {
	
	public void inserisci(T t);
	
	public void cancella(int id);
	
    public T ricercaPerId(int id);
        
    public void aggiorna(T t);
    
}
