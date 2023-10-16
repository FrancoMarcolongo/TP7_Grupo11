package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.DaoSeguro;
import dominio.DaoTipoSeguro;
import dominio.Seguro;

@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletSeguro() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoSeguro daoSeguro = new DaoSeguro();
		DaoTipoSeguro daoTipoSeguro = new DaoTipoSeguro();
		request.setAttribute("seguroProximoId", daoSeguro.obtenerProximoId());
		request.setAttribute("tipoSeguro", daoTipoSeguro.obtenerTipoSeguros());
		RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoSeguro daoSeguro = new DaoSeguro();
		DaoTipoSeguro daoTipoSeguro = new DaoTipoSeguro();
		Seguro seguro = new Seguro();
		seguro.setTipo(daoTipoSeguro.obtenerSeguroPorId(Integer.parseInt(request.getParameter("tipoSeguro"))));
		seguro.setDescripcion(request.getParameter("txtDescripcion"));
		seguro.setCostoContratacion(Double.parseDouble(request.getParameter("txtcostoContratacion")));
		seguro.setCostoAsegurado(Double.parseDouble(request.getParameter("txtcostoMaximoAsegurado")));
		boolean insertado = daoSeguro.insert(seguro);
		
		request.setAttribute("seguroProximoId", daoSeguro.obtenerProximoId());
		request.setAttribute("tipoSeguro", daoTipoSeguro.obtenerTipoSeguros());
		request.setAttribute("insertado", insertado);
		RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguro.jsp");
		rd.forward(request, response);
	}

}
