package ar.com.kompass.dao;

import ar.com.kompass.model.Cuenta;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 16/10/2018 06:57:44
 */
@Stateless
public class CuentaDaoImp implements CuentaDao {

    @PersistenceContext(unitName = "GastosPU")
    private EntityManager em;

    @Override
    public void insertCuenta(Cuenta cuenta) {
        em.persist(cuenta);
    }

    @Override
    public void updateCuenta(Cuenta cuenta) {
        em.merge(cuenta);
    }

    @Override
    public void deleteCuenta(int id) {
        Cuenta cuenta = em.find(Cuenta.class, id);
        em.remove(cuenta);
    }

    @Override
    public Cuenta findCuentaById(int id) {
        return em.find(Cuenta.class, id);
    }

    // Codigo que orderna las cuentas en la grilla
    @Override
    public List<Cuenta> findAllCuentas() {
        Query query = em.createQuery("SELECT c FROM Cuenta c order by c.nombre");         
        List<Cuenta> resultlist = query.getResultList();
        return resultlist;
    }

    @Override
    public JsonObject getCuentasJson() {

        JsonObject json_response = new JsonObject();
        JsonArray data_json = new JsonArray();

        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        Root<Cuenta> from = cq.from(Cuenta.class);
        CriteriaQuery<Cuenta> select = cq.select(from);
        select.orderBy(criteriaBuilder.asc(from.get("nombre")));
        
        TypedQuery<Cuenta> typedQuery = em.createQuery(select);
        
        List<Cuenta> resultlist = typedQuery.getResultList();
        
        for (Cuenta cue : resultlist) {
            JsonObject json = new JsonObject();
            
            json.addProperty("id", cue.getId());
            json.addProperty("nombre", cue.getNombre());
            
            data_json.add(json);
        }
        json_response.add("aaData", data_json);

        return json_response;
    }

    
    @Override
    public long findUltimaCuenta() throws ServletException {
        long numero = 0;
        try {
            String jpql = "SELECT nextval('cuentas_id_seq')";
            Query query = em.createNativeQuery(jpql);

            query.setMaxResults(1);
            numero = (long) query.getSingleResult();

        } catch (Exception ex) {
            throw new ServletException("Ocurrio un Error !",ex);
        }

        return numero;
    }

}
