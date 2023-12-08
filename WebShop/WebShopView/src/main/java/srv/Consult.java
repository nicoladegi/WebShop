package srv;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.ChartInterfaceLocal;
import bl.EcommerceServiceLocal;
import dto.ProdottoDto;
import entities.ListeOrdini;



@WebServlet("/Consult")
public class Consult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<ProdottoDto> prodottiDto;
	private List<ListeOrdini> lord;
	
	@EJB
	EcommerceServiceLocal serv;
	
	@EJB 
	ChartInterfaceLocal<ProdottoDto> chart;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String path = "/WEB-INF/jsp/home.jsp";
		String in = request.getParameter("input");
		Integer input = (in == null) ? -1 : Integer.valueOf(in);
		
		switch(input) {
		
		case 1:
			break;
		case 2:
			break;	
			
		default: 	
					prodottiDto = serv.readProducts();
					request.getSession(true).setAttribute("lProdotti", prodottiDto);
					dispatcher = getServletContext().getRequestDispatcher(path);
					
					List<ProdottoDto> lProdotti = (List<ProdottoDto>) request.getSession().getAttribute("lProdotti");

					// Verifica se l'attributo è stato impostato correttamente
					if (lProdotti != null) {
						for(ProdottoDto pdto : lProdotti) {
						    System.out.println(pdto.toString());
						}
					} else {
					    System.out.println("L'attributo 'lProdotti' non è stato impostato.");
					}
					
					
					
					dispatcher.forward(request, response);
					
					
					break;
					

		}
		
		
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
