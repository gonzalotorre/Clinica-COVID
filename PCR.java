
/**
 * Clase que representa las pruebas de tipo PCR. Sobrescribe el metodo isPcr para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra reservada "instanceof".
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class PCR extends Prueba {

    /**
     * Constructor for objects of class PCR
     */
    public PCR() {}
    
    /**
     * Metodo sobreescrito que identifica las pruebas de tipo PCR que hayan llevado a cabo enfermeros y tecnicos.
     */
    @Override
    public boolean isPcr() { return true; }

}
