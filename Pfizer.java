import java.util.Date;
import java.util.Calendar;

/**
 * Clase que representa las vacunas de Pfizer.  Sobrescribe el metodo isPfizer  para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra reservada "instanceof". 
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Pfizer extends Vacuna {

    /**
     * Constructor for objects of class Pfizer
     */
    public Pfizer() {
        super(2);
    }
    
    /**
     * Metodo sobreescrito para identificar las vacunas de Pfizer que hay en la clinica.
     */
    @Override
    public boolean isPfizer() { return true; }

}
