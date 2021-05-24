import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * Clase paciente que representa las personas de tipo paciente de la clinica, tienen como atributos una lista de pruebas realizadas, la vacuna que se le ha puesto, la fecha en la que se
 * le ha puesto la vacuna. El paciente llega a la clinica con un tipo de prueba que se le asigna cuando se da de alta en la clinica. Tiene un atributo booleano vacunar que es el que 
 * nos va a indicar si el paciente se puede vacunar o no de la segunda dosis. Tiene además dos fechas que indican el inicio y el fin del confinamiento y tiene también un atributo
 * booleano que nos dice si el paciente esta confinado o no. Contiene unos atributos que se cambian cuando se realiza y analiza una prueba y que se reinician cuando este quiere hacer una nueva
 * prueba y el administrador le modifica el tipo de prueba.
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Paciente extends Persona {
    
    private Date fechaVacunacion;
    private Date alta;
    private Date baja;
    private String tipoPrueba;
    private boolean confinado;
    private boolean vacunar;
    private boolean pruebaRealizada;
    private boolean pruebaAnalizada;
    private Date inicioConfinamiento;
    private Date finConfinamiento;
    private int diasConfinamiento;
    private List<Prueba> pruebasRealizadas;
    private Vacuna vacuna;
    
    /**
     * Constructor sin parametros para la clase Paciente
     */
    public Paciente() {
        this.pruebasRealizadas = new ArrayList<Prueba>();
    }
    
    /**
     * Constructor con parametros para la clase Paciente
     */
    public Paciente(String tipoPrueba, String nombre, String apellidos, int edad, String dni, String tlf, int codPersona, boolean confinado) {
        super(nombre, apellidos, edad, dni, tlf, codPersona);
        this.tipoPrueba = tipoPrueba;
        this.confinado = confinado;
        this.pruebasRealizadas = new ArrayList<Prueba>();
    }
    
    /**
     * Constructor con parametros para la clase Paciente (incluye fecha de inicio de confinamiento).
     */
    public Paciente(String tipoPrueba, String nombre, String apellidos, int edad, String dni, String tlf, int codPersona, boolean confinado, Date inicioConfinamiento) {
        super(nombre, apellidos, edad, dni, tlf, codPersona);
        this.tipoPrueba = tipoPrueba;
        this.confinado = confinado;
        this.pruebasRealizadas = new ArrayList<Prueba>();
        this.inicioConfinamiento = inicioConfinamiento;
    }
    
    /**
     * Comprobar si el paciente esta confinado
     */
    public boolean comprobarConfinamiento() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicioConfinamiento); 
        calendar.add(Calendar.DATE, 10);
        Date confinamiento = calendar.getTime();
        // Si la fecha de confinamiento es mayor que esa fecha + 10 dias, el paciente acaba el confinamiento
        if (inicioConfinamiento.after(confinamiento)) {
            this.confinado = false;
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Comprobar si han pasado los 15 dias para hacer una nueva PCR
     */
    public boolean nuevaPCR(Prueba prueba) {
        Calendar calendar = Calendar.getInstance();
        if (pruebasRealizadas.size() == 0) {
            return true;
        } else if (pruebasRealizadas.size() == 1 && pruebasRealizadas.get(0).isPcr()) {
            calendar.setTime(pruebasRealizadas.get(0).getFechaRealización()); 
            calendar.add(Calendar.DATE, 15);
            Date nuevaPCR = calendar.getTime();
            return (pruebasRealizadas.get(0).getFechaRealización().after(nuevaPCR)) ? true : false;
        } else {
            boolean resultado = false;
            Date fechaPrueba = new Date();
            for (Prueba pruebaRealizada : pruebasRealizadas) {
                if (pruebaRealizada.isPcr() && (fechaPrueba == new Date())) {
                    fechaPrueba = pruebaRealizada.getFechaRealización();
                } else if (pruebaRealizada.isPcr() && (pruebaRealizada.getFechaRealización().after(fechaPrueba))) {
                    fechaPrueba = pruebaRealizada.getFechaRealización();
                }
            }
            if (prueba.isPcr()) {
                calendar.setTime(fechaPrueba); 
                calendar.add(Calendar.DATE, 15);
                Date nuevaPCR = calendar.getTime();
                resultado = (fechaPrueba.after(nuevaPCR)) ? true : false;
            }
            return resultado;
        }
    }
    
    /**
     * Comprobar si han pasado los 6 meses para hacer una nueva prueba de antigenos
     */
    public boolean nuevoTestSerologico(Prueba prueba) {
        Calendar calendar = Calendar.getInstance();
        if (pruebasRealizadas.size() == 0) {
            return true;
        } else if (pruebasRealizadas.size() == 1 && (pruebasRealizadas.get(0).isSerologico())) {
            calendar.setTime(pruebasRealizadas.get(0).getFechaRealización()); 
            calendar.add(Calendar.MONTH, 6);
            Date nuevoSerologico = calendar.getTime();
            return (pruebasRealizadas.get(0).getFechaRealización().after(nuevoSerologico)) ? true : false;
        } else {
            boolean resultado = false;
            Date fechaPrueba = new Date();
            for (Prueba pruebaRealizada : pruebasRealizadas) {
                if (pruebaRealizada.isSerologico() && (fechaPrueba == new Date())) {
                    fechaPrueba = pruebaRealizada.getFechaRealización();
                } else if (pruebaRealizada.isPcr() && (pruebaRealizada.getFechaRealización().after(fechaPrueba))) {
                    fechaPrueba = pruebaRealizada.getFechaRealización();
                }
            }
            if (prueba.isSerologico()) {
                calendar.setTime(fechaPrueba); 
                calendar.add(Calendar.MONTH, 6);
                Date nuevoTestSerlogico = calendar.getTime();
                resultado = (fechaPrueba.after(nuevoTestSerlogico)) ? true : false;
            }
            return resultado;
        }
    }
    
    // Getters y Setters de la clase Paciente
    public Date getFechaVacunacion() { return fechaVacunacion; }
    public void setFechaVacunacion(Date fechaVacunacion) { this.fechaVacunacion = fechaVacunacion; }
    public Date getAlta() { return alta; }
    public void setAlta(Date alta) { this.alta = alta; }
    public Date getBaja() { return baja; }
    public void setBaja(Date baja) { this.baja = baja; }
    public String getTipoPrueba() { return tipoPrueba; }
    public void setTipoPrueba(String tipoPrueba) { this.tipoPrueba = tipoPrueba; }
    public boolean isPruebaRealizada() { return pruebaRealizada; }
    public void setPruebaRealizada(boolean pruebaRealizada) { this.pruebaRealizada = pruebaRealizada; }
    public boolean isPruebaAnalizada() { return pruebaAnalizada; }
    public void setPruebaAnalizada(boolean pruebaAnalizada) { this.pruebaAnalizada = pruebaAnalizada; }
    public boolean isConfinado() { return confinado; }
    public void setConfinado(boolean confinado) {this.confinado = confinado; }
    public boolean isVacunar() { return vacunar; }
    public void setVacunar(boolean vacunar) {this.vacunar = vacunar; }
    public Date getInicioConfinamiento() { return inicioConfinamiento; }
    public void setInicioConfinamiento(Date inicioConfinamiento) { this.inicioConfinamiento = inicioConfinamiento; }
    public Date getFinConfinamiento() { return finConfinamiento; }
    public void setFinConfinamiento(Date finConfinamiento) { this.finConfinamiento = finConfinamiento; }
    public int getDiasConfinamiento() { return diasConfinamiento; }
    public void setDiasConfinamiento(int diasConfinamiento) { this.diasConfinamiento = diasConfinamiento; }
    public List<Prueba> getPruebasRealizadas() { return pruebasRealizadas; }
    public void setPruebasRealizadas(List<Prueba> pruebasRealizadas) { this.pruebasRealizadas = pruebasRealizadas; }
    public Vacuna getVacuna() { return vacuna; }
    public void setVacuna(Vacuna vacuna) { this.vacuna = vacuna; }
    
    /**
     * Metodo sobreescrito para identificar a los Pacientes de la clinica.
     */
    @Override
    public boolean isPaciente() {
        return true;
    }

}
