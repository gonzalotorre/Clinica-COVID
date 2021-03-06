
/**
 * Clase que representa las pruebas de antigenos de tipo Prueba Clasica. Sobrescribe el metodo isPruebaClasica para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra 
 * reservada "instanceof". 
 * 
 * @author Gonzalo García Torre 
 * @version 21/03/2021
 */
public class PruebaClasica extends Antigenos {

    /**
     * Constructor for objects of class PruebaClasica
     */
    public PruebaClasica() {}
    
    /**
     * Metodo sobreescrito que identifica las pruebas de antigenos de tipo Prueba Clasica que hayan llevado a cabo enfermeros y tecnicos.
     */
    @Override
    public boolean isPruebaClasica() { return true; }

}
