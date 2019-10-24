package ar.com.kompass.controller;

import ar.com.kompass.model.Cuenta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import ar.com.kompass.dao.CuentaDao;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Fernando Aguada <fernandoaguada@protonmail.com>
 */
@Stateless
public class CuentaController extends HttpServlet {

    //Injected DAO EJB:
    @Inject
    private CuentaDao cuentaDao;

    Logger log =LogManager.getRootLogger();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "INSERT":
                this.beforeInsertCuenta(request, response);
                break;
            case "EDIT":
                this.beforeUpdateCuenta(request, response);
                break;
            case "DELETE":
                this.deleteCuenta(request, response);
                break;
            case "LIST":
                this.Grilla(request, response);
                break;
            case "LISTAR": {
                try {
                    this.getListadoJR(request, response);
                } catch (NamingException | SQLException ex) {
                  log.debug(ex);
                  throw new ServletException("Ocurrio un Error !",ex);
                }
            }
            break;
        }
    }

    protected void beforeInsertCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cuenta cuenta = new Cuenta();
        int ultimo = 0;

        ultimo = (int) cuentaDao.findUltimaCuenta();

        //Hace el Reenvio        
        cuenta.setId(ultimo);
        request.setAttribute("cuenta", cuenta);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/cuentaInsert.jsp");
        rd.forward(request, response);

    }

    protected void beforeUpdateCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        Cuenta cuenta = null;
        cuenta = cuentaDao.findCuentaById(id);

        //Hace el Reenvio
        request.setAttribute("cuenta", cuenta);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/cuentaEdit.jsp");
        rd.forward(request, response);

    }

    /*
     * Lista todas las cuentas
     */
    protected void getListadoJR(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        InitialContext initialContext = new InitialContext();
        
        //OJO TIENE QUE ESTAR DEFINIDO EN MINUSCULAS
        DataSource dataSource = (DataSource) initialContext.lookup("jdbc/gastos");
        Connection connection = dataSource.getConnection();
        
        // se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Kompass\\JAVA\\GastosJSP\\Cuentas.jasper",null,connection);
            // Exporta el PDF
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (JRException ex) {
          log.debug(ex);
        }

    }

    /*
    * Llamado por la grilla
     */
    protected void Grilla(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("ACCION");
        String nombre = request.getParameter("txtBuscar");

        List<Cuenta> lista = null;

        if (accion == null) {
            lista = cuentaDao.findAllCuentas();
        } else {
            //        lista = cuentaDao.getCuentasbyNombre(conn, nombre);
        }

        //Hace el Reenvio
        request.setAttribute("cuentas", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/cuentas.jsp");
        rd.forward(request, response);

    }

    /*
    * Se ocupa de hacer el insert o el edit de una cuenta
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("descrip");
        short tipo = Short.parseShort(request.getParameter("tipo"));

        Cuenta cuenta = new Cuenta();
        cuenta.setNombre(nombre);
        cuenta.setTipo(tipo);

        if (action.equals("INSERT")) {
            cuentaDao.insertCuenta(cuenta);
        } else {
            cuenta.setId(id);
            cuentaDao.updateCuenta(cuenta);
        }

        List<Cuenta> lista = null;
        lista = cuentaDao.findAllCuentas();

        //Hace el Reenvio
        request.setAttribute("cuentas", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/cuentas.jsp");
        rd.forward(request, response);
    }

    protected void deleteCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        cuentaDao.deleteCuenta(id);

        List<Cuenta> lista = null;
        lista = cuentaDao.findAllCuentas();

        request.setAttribute("cuentas", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/cuentas.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
