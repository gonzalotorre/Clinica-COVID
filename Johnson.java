import java.util.Date;

/**
 * Clase que representa las vacunas de Johnson. Sobrescribe el metodo isJohnson para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra reservada "instanceof". 
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Johnson extends Vacuna {

    /**
     * Constructor for objects of class Johnson
     */
    public Johnson() {
        super(1);
    }
    
    /**
     * Metodo sobreescrito para identificar las vacunas de Johnson que hay en la clinica.
     */
    @Override
    public boolean isJohnson() { return true; }

}
