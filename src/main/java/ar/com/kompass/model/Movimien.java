package ar.com.kompass.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 03/11/2018 12:13:05
 */
@Entity
@Table(name = "movimien")
public class Movimien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha;
  //  private short cuentaid;
    private float importe;
    private String concepto;

    /**
     * ManytoOne automaticamente crea un campo CUENTA_ID para la asignación
     * Se puede anular a través de @JoinColumn (o @JoinColumns para claves foráneas compuestas).
     */
    @ManyToOne
    @JoinColumn(name = "cuentaid", insertable = true, updatable = true)
    private Cuenta cuenta;

    // Constructor - Requerido para lo EJB
    public Movimien() {
        this.cuenta = new Cuenta();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
  
    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movimien other = (Movimien) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
