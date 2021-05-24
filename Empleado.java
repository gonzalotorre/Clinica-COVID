import java.util.Date;

/**
 * Clase empleado que sirve para diferenciar las personas que son empleados de la clínica, tiene el alta y la baja de empleados.
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Empleado extends Persona {
    
    private Date alta;
    private Date baja;

    /**
     * Constructor sin parametros de la clase Empleado
     */
    public Empleado() { }
    
    /**
     * Constructor con parametros de la clase Empleado
     */
    public Empleado(String nombre, String apellidos, int edad, String dni, String telefono, int codEmpleado) {
        super(nombre, apellidos, edad, dni, telefono, codEmpleado);
    }
    
    // Getters y Setters de la clase Empleado.
    public Date getAlta() { return alta; }
    public void setAlta(Date alta) { this.alta = alta; }
    public Date getBaja() { return baja; }
    public void setBaja(Date baja) { this.baja = baja; }
    
}
