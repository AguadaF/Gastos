package ar.com.kompass.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Fernando Aguada  <fernandoaguada@protonmail.com> 16/10/2018 06:47:39
 */
@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, length=30)
    private String nombre;
    private short tipo;
    
    /*
    *Este parámetro hace referencia a que la relación ya fue construida por la clase "movimien" a traves de su variable
    * "cuenta"
    */
    @OneToMany(mappedBy="cuenta")  
//    @OneToMany()  
    private List<Movimien> movimien;  


    // Constructor - Requerido para lo EJB
    public Cuenta() {
        this.nombre="";
        this.tipo = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Cuenta other = (Cuenta) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() 
    {
        return"Cuenta [idCuenta="+id+", nombre="+nombre+", Tipo="+tipo+"]";
    }

}
