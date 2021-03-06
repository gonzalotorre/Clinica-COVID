
/**
 * Clase que representa las pruebas de tipo Test Serologico. Sobrescribe el metodo isSerologico para identificar las vacunas de este tipo en la clinica y asi evitar el uso de la palabra 
 * reservada "instanceof". 
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Serologico extends Prueba {

    /**
     * Constructor for objects of class Serologico
     */
    public Serologico() {}
    
    /**
     * Metodo sobreescrito que identifica las pruebas de tipo Test Serologico que hayan llevado a cabo enfermeros y tecnicos.
     */
    @Override
    public boolean isSerologico() { return true; }

}
