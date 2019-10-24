package ar.com.kompass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Fernando Aguada   <fernandoaguada@protonmail.com> 15/10/2018 12:36:56
 */
public class UsuarioDAO {


    public boolean checkUser(String usuario, String pass) throws SQLException{
        boolean st = false;
        
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(MovimienDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //OJO TIENE QUE ESTAR DEFINIDO EN MINUSCULAS
        DataSource dataSource = null;
        try {
            dataSource = (DataSource) initialContext.lookup("jdbc/gastos");
        } catch (NamingException ex) {
            Logger.getLogger(MovimienDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("select * from usuario where username=? and password=?");
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            st = rs.next();

        } catch (SQLException ex) {
          throw ex;  
        }
      
        return st;
    }
}
