package ar.com.kompass.dao;

import ar.com.kompass.model.Cuenta;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.ServletException;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 13/06/2019 20:04:44
 */
@Local
public interface CuentaDao {

   public List<Cuenta> findAllCuentas();  //publicList<Persona>listarPersonas();

   public Cuenta findCuentaById(int id);  

   void insertCuenta(Cuenta cuenta); 
   
   void updateCuenta(Cuenta cuenta); 
   
   void deleteCuenta(int id);   
   
   //long findUltimaCuenta();
   long findUltimaCuenta() throws ServletException;
   
   // Trae todas las cuentas en Formato Json
   JsonObject getCuentasJson(); 
}
