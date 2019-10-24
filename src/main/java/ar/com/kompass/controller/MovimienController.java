package ar.com.kompass.controller;

import ar.com.kompass.model.Movimien;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ar.com.kompass.dao.MovimienDao;
import ar.com.kompass.model.Cuenta;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com>
 */
@Stateless
public class MovimienController extends HttpServlet {

    //Inyeccion de Dependencia, se puede usar @EJB o @Inject
    @Inject
    private MovimienDao movimienDao;
    Logger log = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "INSERT":
                this.beforeInsertMovimien(request, response);
                break;
            case "EDIT":
                this.beforeEditMovimien(request, response);
                break;
            case "DELETE":
                this.deleteMovimien(request, response);
                break;
        }

    }

    /*
    * Llama al Formulario Insert/Alta
     */
    protected void beforeInsertMovimien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Movimien movimien = new Movimien();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fecha = new java.util.Date();

        Calendar calendar = Calendar.getInstance(); // Obtiene una instancia de Calendar
        calendar.setTime(fecha); // Asigna la fecha al Calendar
        java.sql.Date sqlfecha = new Date(fecha.getTime());

        movimien.setFecha(sqlfecha);

        //Hace el Reenvio        
        request.setAttribute("movimien", movimien);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/movimienInsert.jsp");
        rd.forward(request, response);
    }

    /*
    * Llama al Formulario Edit/Modifica
     */
    protected void beforeEditMovimien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        Movimien movimien = null;

        movimien = movimienDao.findMovimienById(id);

        //Hace el Reenvio
        request.setAttribute("movimien", movimien);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/movimienEdit.jsp");
        rd.forward(request, response);

    }

    // Trae todo los movimientos 
    protected void Grilla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NamingException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String datos = "";

        // ResultSet rs = movimienDao.getAllMovimien();
        List<Movimien> movimientos = null;
        movimientos = movimienDao.findAllMovimien();

        JsonObject gson = new JsonObject();
        JsonArray array = new JsonArray();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for (Movimien movi : movimientos) {
            String strFecha = df.format(movi.getFecha());
            JsonObject item = new JsonObject();

            item.addProperty("fecha", strFecha);
            item.addProperty("importe", movi.getImporte());
            item.addProperty("concepto", movi.getConcepto());
            item.addProperty("nombre", movi.getCuenta().getNombre());

            item.addProperty("acciones", "<a class=\"btn btn-info\" href=\"Movimien?action=EDIT&id=" + movi.getId() + "\" role=\"button\"><i class=\"fas fa-user-edit\"></i></a> <a class=\"btn btn-danger\" href=\"Movimien?action=DELETE&id=" + movi.getId() + "\" role=\"button\" onclick=\"return confirm('Confirma Eliminar la Cuenta?')\"><i class=\"fas fa-trash-alt\"></i></a>");
            array.add(item);
        }

        gson.add("datos", array);

        out.print(gson.toString());
    }

    /*
    * Proceso en confima el insert u edit
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equals("INSERT") || action.equals("UPDATE")) {
            String periodo = request.getParameter("periodo");
            int id = Integer.parseInt(request.getParameter("id"));
            java.util.Date fecha = null;

            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha"));
            } catch (ParseException ex) {
                log.debug(ex);
                throw new ServletException("Ocurrio un Error !",ex);
            }
            java.sql.Date sqlfecha = new Date(fecha.getTime());

            Cuenta cuenta = new Cuenta();
            cuenta.setId(Short.parseShort(request.getParameter("cbocuenta")));

            String conce = request.getParameter("conce");
            float impo = Float.parseFloat(request.getParameter("importe"));

            Movimien movimien = new Movimien();
 //           movimien.setCuentaid(cuenta);
            movimien.setCuenta(cuenta);
            movimien.setConcepto(conce);
            movimien.setFecha(sqlfecha);
            movimien.setImporte(impo);

            // Si es una modificacion
            if (action.equals("INSERT")) {
                movimienDao.insertMovimien(movimien);
            } else {
                movimien.setId(id);
                movimienDao.updateMovimien(movimien);
            }

            //Hace el Reenvio
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/movimientos.jsp");
            rd.forward(request, response);
        }

        try {
            switch (action) {
                case "LIST":
                    this.Grilla(request, response);
                    break;

                case "LISTADO1":
                    this.getListado1(request, response);
                    break;

                case "LISTADO2":
                    this.getListado2(request, response);
                    break;

            }

        } catch (SQLException | NamingException ex) {
            log.debug(ex);
            throw new ServletException("Ocurrio un Error !",ex);
        }

    }

    protected void deleteMovimien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        movimienDao.deleteMovimien(id);

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/movimientos.jsp");
        rd.forward(request, response);
    }

    /*
    * El listado de todos los movimientos por fecha
     */
    protected void getListado1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        InitialContext initialContext = new InitialContext();

        //OJO TIENE QUE ESTAR DEFINIDO EN MINUSCULAS
        DataSource dataSource = (DataSource) initialContext.lookup("jdbc/gastos");
        Connection connection = dataSource.getConnection();

        java.util.Date pdefec = null;
        java.util.Date phafec = null;

        try {
            pdefec = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("defec"));
            phafec = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hafec"));
        } catch (ParseException ex) {
            log.debug(ex);
        }

        java.sql.Date sqldefec = new Date(pdefec.getTime());
        java.sql.Date sqlhafec = new Date(phafec.getTime());

        // se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrint = null;
        try {
            HashMap parametros = new HashMap(); //Parametros que usa el jasperreports
            parametros.put("pdefec", sqldefec);
            parametros.put("phafec", sqlhafec);

            jasperPrint = JasperFillManager.fillReport("C:\\Kompass\\JAVA\\GastosJSP\\movimientosporfecha.jasper", parametros, connection);
            // Exporta el PDF
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.setContentType("application/pdf");
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (JRException ex) {
            log.debug(ex);
        }

    }

    /*
    * El listado de todos los movimientos por fecha
     */
    protected void getListado2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        InitialContext initialContext = new InitialContext();

        //OJO TIENE QUE ESTAR DEFINIDO EN MINUSCULAS
        DataSource dataSource = (DataSource) initialContext.lookup("jdbc/gastos");
        Connection connection = dataSource.getConnection();

        java.util.Date pdefec = null;
        java.util.Date phafec = null;

        try {
            pdefec = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("defec"));
            phafec = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hafec"));
        } catch (ParseException ex) {
            log.debug(ex);
        }

        java.sql.Date sqldefec = new Date(pdefec.getTime());
        java.sql.Date sqlhafec = new Date(phafec.getTime());

        Short cuenta = Short.parseShort(request.getParameter("cbodecue"));

        // se muestra en una ventana aparte para su descarga
        JasperPrint jasperPrint = null;
        try {
            HashMap parametros = new HashMap(); //Parametros que usa el jasperreports
            parametros.put("pdefec", sqldefec);
            parametros.put("phafec", sqlhafec);
            parametros.put("pcuenta", cuenta);

            jasperPrint = JasperFillManager.fillReport("C:\\Kompass\\JAVA\\GastosJSP\\MovimientosPorCuenta.jasper", parametros, connection);
            // Exporta el PDF
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.setContentType("application/pdf");
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (JRException ex) {
            log.debug(ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
