package ar.com.kompass.dao;

import ar.com.kompass.model.Movimien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 20/06/2019 20:04:44
 */

// la interfaz local se utiliza cuando el cliente se encuentra en el mismo servidor
@Local
public interface MovimienDao {

    public List<Movimien> findAllMovimien();

    public Movimien findMovimienById(int id);   
    
    void insertMovimien(Movimien movimien);
    
    void updateMovimien(Movimien movimien);
    
    void deleteMovimien(int id);    
    
}
