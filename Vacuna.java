import java.util.Date;
import java.util.Calendar;

/**
 * Clase que representa las vacunas. Contiene unos metodos que serviran para identificar los tipos de vacunas que hay en la clinica y que seran sobrescritos por las clases hijas para asi 
 * identificar cada vacuna y poder usar el polimorfismo de manera correcta evitando palabras reservadas como "instanceof".
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Vacuna {
    
    private Paciente paciente;
    private Enfermero enfermero;
    private int numDosis;
    private Date primeraDosis;
    private Date segundaDosis;
    
    /**
     * Constructor for objects of class Vacuna
     */
    public Vacuna(int numDosis) {
        this.numDosis = numDosis;
    }

    /**
     * Metodo que comprueba que un paciente se pueda poner la segunda dosis o no.
     */
    public boolean comprobarDosis() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(primeraDosis); 
        calendar.add(Calendar.DATE, 21);
        Date segundaDosis = calendar.getTime();
        if (new Date().after(segundaDosis)) {
            return true;
        } else {
            return false;
        }
    }
    
    // Getters y Setters de la clase Vacuna.
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Enfermero getEnfermero() { return enfermero; }
    public void setEnfermero(Enfermero enfermero) { this.enfermero = enfermero; }
    public int getNumDosis() { return this.numDosis; }
    public void setNumDosis(int numDosis) { this.numDosis = numDosis; }
    public Date getPrimeraDosis() { return primeraDosis; }
    public void setPrimeraDosis(Date primeraDosis) { this.primeraDosis = primeraDosis; }
    public Date getSegundaDosis() { return segundaDosis; }
    public void setSegundaDosis(Date segundaDosis) { this.segundaDosis = segundaDosis; }
    
    
    // Metodos para comprobar el tipo de vacuna en la lista de vacunas de la clinica
    public boolean isModerna() { return false; }
    
    public boolean isPfizer() { return false; }
    
    public boolean isJohnson() { return false; }

}
