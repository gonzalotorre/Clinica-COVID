import java.util.List;
import java.util.ArrayList;

/**
 * Clase almacen que guarda todas las vacunas de la clinica, ademas tiene dos metodos para actualizar y ver el stock de vacunas.
 * 
 * @author Gonzalo García Torre
 * @version 07/04/2021
 */
public class Almacen {
    
    private List<Vacuna> vacunas;

    /**
     * Constructor sin parametros para la clase Almacen.
     */
    public Almacen(){
        vacunas = new ArrayList<Vacuna>();
        recibirStockVacunas();
    }
    
    /**
     * Metodo que crea una serie de vacunas que sirve para actualizar el stock de vacunas de la clinica.
     */
    public void recibirStockVacunas() {
        vacunas.add(new Moderna());
        vacunas.add(new Moderna());
        vacunas.add(new Moderna());
        vacunas.add(new Moderna());
        vacunas.add(new Moderna());
        vacunas.add(new Moderna());
        vacunas.add(new Pfizer());
        vacunas.add(new Pfizer());
        vacunas.add(new Pfizer());
        vacunas.add(new Pfizer());
        vacunas.add(new Pfizer());
        vacunas.add(new Pfizer());
        vacunas.add(new Johnson());
        vacunas.add(new Johnson());
        vacunas.add(new Johnson());
        vacunas.add(new Johnson());
        vacunas.add(new Johnson());
        vacunas.add(new Johnson());
    }
    
    /**
     * Metodo para ver el stock de vacunas que hay en la clinica.
     */
    public void visualizarStock() {
        int contModerna = 0;
        int contPfizer = 0;
        int contJohnson = 0;
        for (Vacuna vacuna : vacunas) {
            if (vacuna.isModerna()) {
                contModerna++;
            } else if (vacuna.isPfizer()) {
                contPfizer++;
            } else if (vacuna.isJohnson()) {
                contJohnson++;
            }
        }
        System.out.println("Hay un total de " + contModerna + " vacunas de Moderna.");
        System.out.println("Hay un total de " + contPfizer + " vacunas de Pfizer.");
        System.out.println("Hay un total de " + contJohnson + " vacunas de Johnson.\n");
    }
    
    //Getters y Setters de la clase Almacen.
    public List<Vacuna> getVacunas() { return vacunas; }
    public void setVacunas(List<Vacuna> vacunas) { this.vacunas = vacunas; }
    
}
