import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase enfermero, contiene una lista de pacientes asignados, una lista de pruebas realizadas y una lista de vacunas puestas. Realiza pruebas, pone las dosis de las vacunas y puede consultar
 * los tiempos de vacunacion de los pacientes asignados.
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Enfermero extends Empleado {
    
    Scanner teclado = new Scanner(System.in);
    private List<Paciente> pacientes;
    private List<Prueba> pruebasRealizadas;
    private List<Vacuna> vacunasPuestas;

    /**
     * Constructor sin parametros para los enfermeros.
     */
    public Enfermero() {
        this.pacientes = new ArrayList<Paciente>();
        this.pruebasRealizadas = new ArrayList<Prueba>();
        this.vacunasPuestas = new ArrayList<Vacuna>();
    }

    /**
     * Constructor con parametros para los enfermeros.
     */
    public Enfermero(String nombre, String apellidos, int edad, String dni, String telefono, int codEmpleado) {
        super(nombre, apellidos, edad, dni, telefono, codEmpleado);
        this.pacientes = new ArrayList<Paciente>();
        this.pruebasRealizadas = new ArrayList<Prueba>();
        this.vacunasPuestas = new ArrayList<Vacuna>();
    }

    /**
     * Metodo que muestra todos los pacientes asignados a un enfermero.
     */
    public void verPacientes() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        for (Paciente p : this.pacientes) {
            System.out.println("\n----- Datos del paciente -----");
            System.out.println(p.getNombre() + " " + p.getApellidos() + ". " + p.getEdad() + " años.");
            // Visualizar datos de la vacuna.
            if (p.getVacuna() != null) {
                if (p.getVacuna().isModerna()) {
                    Moderna vacuna = (Moderna) p.getVacuna();
                    System.out.println("Fecha primera dosis: " + formater.format(vacuna.getPrimeraDosis()));
                    if(vacuna.getSegundaDosis() != null) {
                        System.out.println("Fecha segunda dosis: " + formater.format(vacuna.getSegundaDosis()));
                    } else {
                        System.out.println("Fecha segunda dosis: no se ha puesto la segunda dosis.");
                    }
                } else if (p.getVacuna().isPfizer()) {
                    Pfizer vacuna = (Pfizer) p.getVacuna();
                    System.out.println("Fecha primera dosis: " + formater.format(vacuna.getPrimeraDosis()));
                    if(vacuna.getSegundaDosis() != null) {
                        System.out.println("Fecha segunda dosis: " + formater.format(vacuna.getSegundaDosis()));
                    } else {
                        System.out.println("Fecha segunda dosis: no se ha puesto la segunda dosis.");
                    }
                } else if (p.getVacuna().isJohnson()) {
                    Johnson vacuna = (Johnson) p.getVacuna();
                    System.out.println("Fecha de vacunación: " + formater.format(vacuna.getPrimeraDosis()));
                }
            } else {
                System.out.println("El paciente todavía no se ha vacunado");
            }
            // Visualizar datos de las pruebas.
            if (p.getPruebasRealizadas().size() > 0) {
                System.out.println("<-- Pruebas realizadas -->");
                for (Prueba prueba : p.getPruebasRealizadas()) {
                    if (prueba.isPcr()) {
                        System.out.println("--> Prueba PCR <--");
                    } else if (prueba.isSerologico()) {
                        System.out.println("--> Análisis serológico <--");
                    } else if (prueba.isPruebaRapida()) {
                        System.out.println("--> Prueba rápida <--");
                    } else if (prueba.isPruebaClasica()) {
                        System.out.println("--> Prueba clásica <--");
                    }
                    System.out.println("Fecha de realización" + formater.format(prueba.getFechaRealización()));
                    if (prueba.isPruebaAnalizada()) {
                        String resultado = (prueba.isResultado()) ? "Positivo" : "Negativo";
                        System.out.println("Resultado " + resultado);
                    } else {
                        System.out.println("La prueba todavía no ha sido analizada.\n");
                    }
                }
            } else {
                System.out.println("El paciente todavía no ha realizado ninguna prueba.");
            }
            System.out.println("------------------------------------\n");
        }
    }
    
    /**
     * Metodo para hacer una prueba a un paciente.
     */
    public void registrarPrueba(Prueba prueba, Paciente paciente) {
        prueba.setCodPrueba(pruebasRealizadas.size() + 1);
        if (prueba != null) {
            if (prueba.isSerologico() || paciente.nuevaPCR(prueba) || paciente.nuevoTestSerologico(prueba)) {
                prueba.setFechaRealización(new Date());
                paciente.getPruebasRealizadas().add(prueba);
                pruebasRealizadas.add(prueba);
                prueba.setPruebaRealizada(true);
                paciente.setPruebaRealizada(true);
                System.out.println("Se ha realizado la prueba correctamente, esta se enviará al técnico para ser analizada y comprobar el resultado.\n");
            } else {
                System.out.println("Ha habido un error con la realización de la prueba.");
            }
        } else {
            System.out.println("Ha habido un error con la realización de la prueba.");
        }
    }
    
    /**
     * Metodo para poner la vacuna a un paciente.
     */
    public void registrarVacuna(Paciente paciente, Vacuna vacuna, List<Vacuna> vacunas) {
        Iterator it = vacunas.iterator();
        Vacuna v = null;
        vacuna.setPaciente(paciente);
        if (vacuna.isModerna()) {
            paciente.setVacuna(vacuna);
            vacuna.setPrimeraDosis(new Date());
            System.out.println("Se ha vacunado al paciente de la primera dosis con éxito.\n");
        } else if (vacuna.isPfizer()) {
            paciente.setVacuna(vacuna);
            vacuna.setPrimeraDosis(new Date());
            System.out.println("Se ha vacunado al paciente de la primera dosis con éxito.\n");
        } else if (vacuna.isJohnson()) {
            paciente.setVacuna(vacuna);
            vacuna.setPrimeraDosis(new Date());
            while(it.hasNext()) {
                v = (Vacuna) it.next();
                if (v.isJohnson()) {
                    it.remove();
                    break;
                }
            }
            System.out.println("Se ha vacunado al paciente con éxito.\n");
        } else {
            System.out.println("Ha habido un error con la vacunación.\n");
        }
    }
    
    /**
     * Metodo para poner la segunda dosis de la vacuna a aquellos pacientes que puedan ponerla.
     */
    public void registrarVacunaSegundaDosis(Paciente paciente, List<Vacuna> vacunas) {
        Iterator it = vacunas.iterator();
        Vacuna v = null;
        if (paciente.getVacuna() != null && paciente.getVacuna().comprobarDosis()) {
            paciente.getVacuna().setSegundaDosis(new Date());
            while(it.hasNext()) {
                v = (Vacuna) it.next();
                if (v.isModerna()) {
                    it.remove();
                    break;
                } else if (v.isPfizer()) {
                    it.remove();
                    break;
                }
            }
        }
    }
    
    /**
     * Metodo que muestra los pacientes a los que se les puede poner la segunda dosis.
     */
    public int verPacientesSegundaDosis() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        int contador = 0;
        for (Paciente paciente : pacientes) {
            if (paciente.getVacuna() != null) {
                if (paciente.getVacuna().isModerna() && paciente.getVacuna().comprobarDosis()) {
                    calendar.setTime(paciente.getVacuna().getPrimeraDosis());
                    calendar.add(Calendar.DATE, 21);
                    Date fecha21 = calendar.getTime();
                    if (new Date().after(fecha21)) {
                        System.out.println("--> " + paciente.getNombre() + " " + paciente.getApellidos() + ". " + paciente.getEdad() + " años.");
                        System.out.println("Fecha de la primera dosis: " + formater.format(paciente.getVacuna().getPrimeraDosis()));
                        contador++;
                    }
                } else if (paciente.getVacuna().isPfizer() && paciente.getVacuna().comprobarDosis()) {
                    calendar.setTime(paciente.getVacuna().getPrimeraDosis());
                    calendar.add(Calendar.DATE, 21);
                    Date fecha21 = calendar.getTime();
                    if (new Date().after(fecha21)) {
                        System.out.println("--> " + paciente.getNombre() + " " + paciente.getApellidos() + ". " + paciente.getEdad() + " años.");
                        System.out.println("--> Fecha de la primera dosis: " + formater.format(paciente.getVacuna().getPrimeraDosis()));
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
    
    /**
     * Metodo para mostrar en pantalla las pruebas que ha realizado el enfermero.
     */
    public void verDatosPruebas() {
        System.out.println();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Prueba prueba : pruebasRealizadas) {
            if (prueba.isPcr()) {
                System.out.println("--> Prueba PCR <--");
            } else if (prueba.isSerologico()) {
                System.out.println("--> Análisis serológico <--");
            } else if (prueba.isPruebaRapida()) {
                System.out.println("--> Prueba rápida <--");
            } else if (prueba.isPruebaClasica()) {
                System.out.println("--> Prueba clásica <--");
            }
            System.out.println("Fecha de realización: " + format.format(prueba.getFechaRealización()));
            System.out.println("Enfermero que realizó la prueba: " + prueba.getEnfermero().getNombre() + " " + prueba.getEnfermero().getApellidos());
            System.out.println("Paciente al que se le realizó la prueba: " + prueba.getPaciente().getNombre() + " " + prueba.getPaciente().getApellidos() + "\n");
        }
    }
    
    /**
     * Metodo que comprueba que el paciente pueda realizar una prueba, es decir, si en una semana ha realizado mas de 5 pruebas no puede volver a realizar ninguna mas hasta la siguiente
     * semana.
     */
    public boolean comprobarNumPruebas() {
        int minPruebas = 0;
        for(Prueba p : pruebasRealizadas) {
            if (p.getFechaRealización().after(getInicioSemana()) && p.getFechaRealización().before(getFinSemana())) {
                minPruebas++;
            }
        }
        if(minPruebas > 5) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Metodo que muestra los pacientes vacunados de la primera dosis y cuanto les queda para poder poner la segunda dosis.
     */
    public void comprobarPacientesSegundaDosis() {
        long diasRestantes;
        int contador = 0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Paciente p : pacientes) {
            if (p.getVacuna() != null) {
                if (p.getVacuna().isModerna()) {
                    Moderna vacunaModerna = (Moderna) p.getVacuna();
                    if (vacunaModerna.getPrimeraDosis() != null && vacunaModerna.getSegundaDosis() == null) {
                        System.out.println("(Cod: " + p.getCodPersona() + ") " + p.getNombre() + " " + p.getApellidos());
                        System.out.println("Fecha primera dosis: " + format.format(vacunaModerna.getPrimeraDosis()));
                        calendar.setTime(vacunaModerna.getPrimeraDosis());
                        calendar.add(Calendar.DATE, 21);
                        System.out.println("Fecha prevista para la segunda dosis: " + format.format(calendar.getTime()) + " o una fecha superior a esta.");
                    }
                } else if (p.getVacuna().isPfizer()) {
                    Pfizer vacunaPfizer = (Pfizer) p.getVacuna();
                    if (vacunaPfizer.getPrimeraDosis() != null && vacunaPfizer.getSegundaDosis() == null) {
                        System.out.println("(Cod: " + p.getCodPersona() + ") " + p.getNombre() + " " + p.getApellidos());
                        System.out.println("Fecha primera dosis: " + format.format(vacunaPfizer.getPrimeraDosis()));
                        calendar.setTime(vacunaPfizer.getPrimeraDosis());
                        calendar.add(Calendar.DATE, 21);
                        System.out.println("Fecha segunda dosis: " + format.format(calendar.getTime()) + " o una fecha superior a esta.");
                    }
                }
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("\nTodavía no hay pacientes que hayan puesto la primera dosis para comprobar qué pacientes pueden poner la segunda dosis.\n");
        }
    }
    
    /**
     * Metodo que obtiene el inicio de la semana en la que nos encontramos para calcular la restriccion del minimo de pruebas que puede hacer un enfermero.
     */
    public Date getInicioSemana() {
        // obtener el día actual
        Calendar cal = Calendar.getInstance();
        // "limpiar" el tiempo
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        
        // obtener el inicio de la semana
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }
    
    /**
     * Metodo que obtiene el fin de la semana en la que nos encontramos para calcular la restriccion del minimo de pruebas que puede hacer un enfermero.
     */
    public Date getFinSemana() {
        // obtener el día actual
        Calendar cal = Calendar.getInstance();
        // "limpiar" el tiempo
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        
        // obtener el inicio de la semana
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date inicioSemana = cal.getTime();
        
        // Obtener el último día de la semana
        cal.setTime(inicioSemana);
        cal.add(Calendar.DATE, 6);
        
        return cal.getTime();
    }
    
    /**
     * Recorrer la lista para elegir un paciente asignado al enfermero y poder trabajar con el.
     */
    public Paciente elegirPaciente() {
        System.out.println();
        Paciente paciente = new Paciente();
        // Mostrar pacientes 
        for (Paciente p : pacientes) {
            System.out.print("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + "\n");
        }
        // Elegir paciente
        System.out.print("Elige un paciente por su código: ");
        int codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Paciente p : pacientes) {
            if(p.getCodPersona() == codEmpleado && p.isPaciente()) {
                paciente = p;
            }
        }
        if(paciente == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return elegirPaciente();
        }
        return paciente;
    }
    
    // Getters y Setters para la clase enfermero.
    public List<Paciente> getPacientes() { return pacientes; }
    public void setPacientes(List<Paciente> pacientes) { this.pacientes = pacientes; }
    public List<Prueba> getPruebasRealizadas() { return pruebasRealizadas; }
    public void setPruebasRealizadas(List<Prueba> pruebasRealizadas) { this.pruebasRealizadas = pruebasRealizadas; }
    public List<Vacuna> getVacunasPuestas() { return vacunasPuestas; }
    public void setVacunasPuestas(List<Vacuna> vacunasPuestas) { this.vacunasPuestas = vacunasPuestas; }
    
    /**
     * Metodo sobreescrito para identificar enfermeros en la lista de personas que hay en la clinica.
     */
    @Override
    public boolean isEnfermero() {
        return true;
    }
    
}
