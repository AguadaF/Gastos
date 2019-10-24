package ar.com.kompass.controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.com.kompass.dao.CuentaDao;

/**
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 22/12/2018 - 11:41
 */
public class CuentaBuscaController extends HttpServlet {
    //Injected DAO EJB:

    @EJB
    private CuentaDao cuentaDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userInput = request.getParameter("term");
        System.out.println(userInput);
        PrintWriter out = response.getWriter();
        JsonObject json_response = new JsonObject();
        json_response = cuentaDao.getCuentasJson();
        response.setContentType("application/Json");
        response.getWriter().write(json_response.toString());
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Devuelve las Cuentas en formato json";
    }// </editor-fold>

}
