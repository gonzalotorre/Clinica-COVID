import java.util.Date;
import java.util.Calendar;

/**
 * Clase que representa las vacunas de Moderna. Sobrescribe el metodo isModerna para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra reservada "instanceof".
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Moderna extends Vacuna {

    /**
     * Constructor for objects of class Moderna
     */
    public Moderna() {
        super(2);
    }
    
    /**
     * Metodo sobreescrito para identificar las vacunas de Moderna que hay en la clinica.
     */
    @Override
    public boolean isModerna() { return true; }

}
