import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Clase tecnico, esta clase tiene una lista de pruebas analizadas y una lista de pacientes asignados. Tiene una serie de métodos que son las acciones que pueden llevar a cabo los tecnicos
 * de la clinica.
 * 
 * @author Gonzalo García Torre
 * @version 21/03/2021
 */
public class Tecnico extends Empleado {
    
    Scanner teclado = new Scanner(System.in);
    private List<Paciente> pacientes;
    private List<Prueba> pruebasAnalizadas;
    
    /**
     * Constructor sin parametros de la clase Tecnico
     */
    public Tecnico() {
        this.pacientes = new ArrayList<Paciente>();
        this.pruebasAnalizadas = new ArrayList<Prueba>();
    }

    /**
     * Constructor con parametros de la clase Tecnico
     */
    public Tecnico(String nombre, String apellidos, int edad, String dni, String telefono, int codEmpleado) {
        super(nombre, apellidos, edad, dni, telefono, codEmpleado);
        this.pacientes = new ArrayList<Paciente>();
        this.pruebasAnalizadas = new ArrayList<Prueba>();
    }
    
    /**
     * Metodo que muestra los pacientes asignados a un tecnico.
     */
    public void verPacientes() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        for (Paciente p : this.pacientes) {
            System.out.println("----- Datos del paciente -----");
            System.out.println(p.getNombre() + " " + p.getApellidos() + ". " + p.getEdad() + " años.");
            if (p.getPruebasRealizadas().size() > 0) {
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
            System.out.println("----------------------------------");
        }
    }

    /**
     * Metodo para analizar una prueba a un paciente.
     */
    public void registrarPrueba(Paciente paciente, Prueba prueba) {
        System.out.println("Analizando pruebaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
        boolean resultado = false;
        int resultadoMath;
        if (prueba.isPcr() || prueba.isPruebaRapida() || prueba.isPruebaClasica()) {
            resultadoMath = (int) Math.round(Math.random());
            resultado = (resultadoMath == 1) ? true : false;
            if (resultado) {
                paciente.setConfinado(true);
                paciente.setInicioConfinamiento(new Date());
                System.out.println("El resultado a sido positivo, el paciente " + paciente.getNombre() + " " + paciente.getApellidos() + " pasa a estar confinado durante 10 días.\n");
            } else {
                System.out.println("El resultado a sido negativo.\n");
            }
        } else if(prueba.isSerologico()) {
            resultadoMath = (int) Math.round(Math.random() * 10);
            resultado = (resultadoMath > 2) ? true : false;
            if (resultado) {
                System.out.println("El paciente " + paciente.getNombre() + " " + paciente.getApellidos() + " ha pasado la infección.\n");
            } else {
                System.out.println("El paciente " + paciente.getNombre() + " " + paciente.getApellidos() + " todavía no ha pasado la infección.\n");
            }
        }
        prueba.setPruebaAnalizada(true);
        paciente.setPruebaAnalizada(true);
        prueba.setResultado(resultado);
        pruebasAnalizadas.add(prueba);
    }
    
    /**
     * Ver los datos de las pruebas realizadas por un tecnico.
     */
    public void verDatosPruebas() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Prueba prueba : pruebasAnalizadas) {
            if (prueba.isPcr()) {
                System.out.println("--> Prueba PCR <--");
            } else if (prueba.isSerologico()) {
                System.out.println("--> Análisis serológico <--");
            } else if (prueba.isPruebaRapida()) {
                System.out.println("--> Prueba rápida <--");
            } else if (prueba.isPruebaClasica()) {
                System.out.println("--> Prueba clásica <--");
            }
            System.out.println("Codigo de prueba: " + prueba.getCodPrueba());
            System.out.println("Fecha de realización: " + format.format(prueba.getFechaRealización()));
            System.out.println("Enfermero que realizó la prueba: " + prueba.getEnfermero().getNombre() + " " + prueba.getEnfermero().getApellidos());
            System.out.println("Paciente al que se le realizó la prueba: " + prueba.getPaciente().getNombre() + " " + prueba.getPaciente().getApellidos());
            String resultado = (prueba.isResultado()) ? "Positivo" : "Negativo";
            System.out.println("Resultado: " + resultado + "\n");
        }
    }
    
    /**
     * Mostrar en pantalla y pedir por teclado una prueba para actualizar el resultado.
     */
    public void actualizarPrueba() {
        Prueba prueba = null;
        verDatosPruebas();
        System.out.print("Elige una prueba por su código: ");
        int codPrueba = teclado.nextInt();
        for(Prueba p : pruebasAnalizadas) {
            if (p.getCodPrueba() == codPrueba) {
                prueba = p;
            }
        }
        if (prueba != null) {
            System.out.println("Resultado de la prueba");
            System.out.println("1.- Positivo");
            System.out.println("2.- Negativo");
            System.out.print("Elige una opción numérica: ");
            int opcion = teclado.nextInt();
            if (opcion == 1) {
                prueba.setResultado(true);
            } else if (opcion == 2){
                prueba.setResultado(false);
            } else {
                System.out.print("Ha habido un error o has introducido un número erróneo.");
            }
        } else {
            System.out.print("Ha habido un error al obtener la prueba.");
        }
    }
    
    /**
     * Metodo para comprobar que el tecnico puede analizar una prueba.
     * Cuando el contador sea mayor que 4 el tecnico no puede analizar otra prueba hasta la semana siguiente.
     */
    public boolean comprobarNumPruebas() {
        int minPruebas = 0;
        for(Prueba p : pruebasAnalizadas) {
            if (p.getFechaRealización().after(getInicioSemana()) && p.getFechaRealización().before(getFinSemana())) {
                minPruebas++;
            }
        }
        if(minPruebas > 4) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Metodo para obtener la fecha del inicio de la semana para comprobar que el tecnico puede analizar una prueba. 
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
     * Metodo para obtener la fecha del fin de la semana para comprobar que el tecnico puede analizar una prueba. 
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
     * Recorrer la lista para elegir un paciente asignado al tecnico y poder trabajar con el.
     */
    public Paciente elegirPaciente() {
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

    // Getters y Setters de la clase tecnico
    public List<Paciente> getPacientes() { return pacientes; }
    public void setPacientes(List<Paciente> pacientes) { this.pacientes = pacientes; }
    public List<Prueba> getPruebasAnalizadas() { return pruebasAnalizadas; }
    public void setPruebasAnalizadas(List<Prueba> pruebasAnalizadas) { this.pruebasAnalizadas = pruebasAnalizadas; }

    /**
     * Metodo sobreescrito que sirve para identificar a los tecnicos en la lista de personas que hay en la clinica.
     */
    @Override
    public boolean isTecnico() {
        return true;
    }
    
}
