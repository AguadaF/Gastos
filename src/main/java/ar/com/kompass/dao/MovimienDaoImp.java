package ar.com.kompass.dao;

import ar.com.kompass.model.Movimien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Fernando Aguada   <fernandoaguada@protonmail.com> 11/07/2019 12:26:26
 */

/**
* Convierte la clase en un EJB e indica que es un EJB sin estado
* esto hace que los metodos sean transaccionales, seguridad y
* puede acceder al entity manager y persistir información
* se ejecuta dentro de un servidor de aplicaciones
*/
 
@Stateless
public class MovimienDaoImp implements MovimienDao {
    // Injected database connection

    @PersistenceContext(unitName = "GastosPU")
    private EntityManager em;

    @Override
    public void insertMovimien(Movimien movimien) {
        em.persist(movimien);
    }

    @Override
    public void updateMovimien(Movimien movimien) {
        em.merge(movimien);
    }

    @Override
    public void deleteMovimien(int id) {
        Movimien movimien = em.find(Movimien.class, id);
        em.remove(movimien);
    }

    @Override
    public Movimien findMovimienById(int id) {
        return em.find(Movimien.class, id);
    }

    // se llama desde MovimienController para llenar la grilla
    @Override
    public List<Movimien> findAllMovimien() {      
     
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        Root<Movimien> from = cq.from(Movimien.class);

        CriteriaQuery<Movimien> select = cq.select(from);

        select.orderBy(criteriaBuilder.desc(from.get("fecha")));

        TypedQuery<Movimien> typedQuery = em.createQuery(select);

        List<Movimien> resultlist = typedQuery.getResultList();

        return resultlist;    
    }
}
