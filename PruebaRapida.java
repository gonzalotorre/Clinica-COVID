
/**
 * Clase que representa las pruebas de antigenos de tipo Prueba Rapida. Sobrescribe el metodo isPruebaRapida para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra 
 * reservada "instanceof". 
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class PruebaRapida extends Antigenos {

    /**
     * Constructor for objects of class PruebaRapida
     */
    public PruebaRapida() {}
    
    /**
     * Metodo sobreescrito que identifica las pruebas de antigenos de tipo Prueba Rapida que hayan llevado a cabo enfermeros y tecnicos.
     */
    @Override
    public boolean isPruebaRapida() { return true; }

}
