package ar.com.kompass.controller;

import ar.com.kompass.dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com>
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("txtUsuario");
        String pass = request.getParameter("txtPassword");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (usuarioDAO.checkUser(usuario, pass)) {
                RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
                rs.forward(request, response);
            } else {
                //Ambito REQUEST: solo sirve para una peticion
                request.setAttribute("Mensaje", "Usuario o Contraseña Incorrecta");
                
                //Ambito Session: la sesion existe mientras no se destruya con una linea de codigo
                // o la ventana del navegador no se cierre
                //HttpSession sesion = request.getSession();
                //sesion.setAttribute("Mensaje", "Usuario o Contraseña Incorrecta");
                
                //Ambito Contexto: existe mientras el servlet no ejecute un metodo llamado destroy
                //ServletContext context = getServletContext();
                //context.setAttribute("Mensaje", "Usuario o Contraseña Incorrecta");
                
                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
                rs.include(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException("Sucedio un Error !", ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
