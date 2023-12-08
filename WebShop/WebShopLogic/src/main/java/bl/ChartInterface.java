package bl;

import java.util.List;

import dto.OrdineDto;

public interface ChartInterface<T> {

	public boolean add(T element, int i);
	public boolean remove(T element, int i);
	public List<OrdineDto> readChart();
	public boolean dropChart();
	public boolean purchase();
	
}
