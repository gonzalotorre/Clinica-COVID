
/**
 * Contiene los datos principales de una persona: nombre, apellidos, edad... Implementa la interfaz comparable para poder ordenar las listas de personas por edad. Y una serie de metodos que 
 * sirven para identificar a los distintos tipos de personas que hay en la clinica, que serán sobrescritas en las clases hijas para poder usar asi el polimorfismo de manera adecuada y evitar
 * usar palabras reservadas como "instanceof".
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Persona implements Comparable<Persona> {
    
    private String nombre;
    private String apellidos;
    private int edad;
    private String dni;
    private String telefono;
    private int codPersona;
    
    /**
     * Constructor sin parametros para la clase Persona.
     */
    public Persona() { }
    
    /**
     * Constructor con parametros para la clase Persona.
     */
    public Persona(String nombre, String apellidos, int edad, String dni, String telefono, int codPersona) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
        this.codPersona = codPersona;
    }
    
    // Getters y Setters de la clase Persona.
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public int getCodPersona() { return codPersona; }
    public void setCodPersona(int codPersona) { this.codPersona = codPersona; }
    
    // Metodos para identificar los distintos tipos de personas en la clinica.
    public boolean isAdmin() { return false; }
    
    public boolean isEnfermero() { return false; }
    
    public boolean isTecnico() { return false; }
    
    public boolean isPaciente() { return false; }

    /**
     * Metodo toString sobreescrito para mostrar los atributos de la clase.
     */
    @Override
    public String toString() {
        return "(Código: " + codPersona + ") " + nombre + " " + apellidos + ", " + edad + " años. DNI: " + dni + ". Telefono: " + telefono;
    }
    
    /**
     * Metodo compareTo sobrescrito para poder ordenar la lista de personas por edad.
     */
    @Override
    public int compareTo(Persona p) {
        if (edad < p.edad) {
            return 1;
        } else if (edad > p.edad) {
            return -1;
        } else {
            return 0;
        }
    }
 
}
