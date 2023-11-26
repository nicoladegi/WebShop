package srv;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.EcommerceServiceLocal;
import dto.ProdottoDto;
import dto.ProduttoreDto;



@WebServlet("/Consult")
public class Consult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<Integer, ProdottoDto> pdto1;
	private List<ProduttoreDto> pdto;


	@EJB
	EcommerceServiceLocal serv;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		pdto = serv.consultaProduttori();
		pdto.forEach(ProduttoreDto::print);
		
		pdto1 = serv.consultaProdotti();
		pdto1.forEach((k,v)->v.print());;
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/*
	 * Il frontend riceverà una versione dei dto completa e inoltrerà questa versione completa anche al carrello, in quanto serviranno sia l'id che le informazioni sul produttore
	 */
}
