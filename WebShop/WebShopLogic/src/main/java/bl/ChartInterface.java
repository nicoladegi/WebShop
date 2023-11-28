package bl;

import java.util.List;

public interface ChartInterface<T> {

	public boolean add(T element);
	public boolean remove(T element);
	public List<T> readChart();
	public boolean dropChart();
	public boolean purchase();
	
}
