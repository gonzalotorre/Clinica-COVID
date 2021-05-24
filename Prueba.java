import java.util.Date;

/**
 * Clase que representa las pruebas que se van a realizar en la clinica por enfermeros y ser analizadas por los tecnicos. Contiene unos metodos que serviran para identificar los tipos de 
 * vacunas que hay en la clinica y que seran sobrescritos por las clases hijas para asi  identificar cada vacuna y poder usar el polimorfismo de manera correcta evitando palabras reservadas 
 * como "instanceof".
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Prueba {
    
    private int codPrueba;
    private Date fechaRealización;
    private boolean pruebaRealizada;
    private boolean pruebaAnalizada;
    private boolean resultado;
    private Paciente paciente;
    private Enfermero enfermero;
    private Tecnico tecnico;

    /**
     * Constructor for objects of class Prueba
     */
    public Prueba() {}
    
    // Getters y Setters de la clase Prueba
    public int getCodPrueba() { return codPrueba; }
    public void setCodPrueba(int codPrueba) { this.codPrueba = codPrueba; }
    public Date getFechaRealización() { return fechaRealización; }
    public void setFechaRealización(Date fechaRealización) { this.fechaRealización = fechaRealización; }
    public boolean isPruebaRealizada() { return pruebaRealizada; }
    public void setPruebaRealizada(boolean pruebaRealizada) { this.pruebaRealizada = pruebaRealizada; }
    public boolean isPruebaAnalizada() { return pruebaAnalizada; }
    public void setPruebaAnalizada(boolean pruebaAnalizada) { this.pruebaAnalizada = pruebaAnalizada; }
    public boolean isResultado() { return resultado; }
    public void setResultado(boolean resultado) { this.resultado = resultado; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Enfermero getEnfermero() { return enfermero; }
    public void setEnfermero(Enfermero enfermero) { this.enfermero = enfermero; }
    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }
    
    // Metodos para identificar los tipos de prueba que se van a poder hacer en la clinica.
    public boolean isPcr() { return false; }
    
    public boolean isSerologico() { return false; }
    
    public boolean isPruebaRapida() { return false; }
    
    public boolean isPruebaClasica() { return false; }

}
