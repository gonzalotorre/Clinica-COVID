import java.util.List;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Clase administrador, contiene los metodos que corresponden con las acciones que puede realizar las personas que son administradores en la clinica. Se encarga de dar altas, bajas, modificar
 * datos de las personas del sistema, cuando un paciente quiera realizar una nueva prueba este debe de modificarsela al paciente.
 * 
 * @author Gonzalo García Torre 
 * @version 21/03/2021
 */
public class Administrador extends Empleado {
    
    Scanner teclado = new Scanner(System.in);
    
    /**
     * Constructor sin parametros para la clase Administrador.
     */
    public Administrador() { }

    /**
     * Constructor con parametros para la clase Administrador.
     */
    public Administrador(String nombre, String apellidos, int edad, String dni, String telefono, int codPersona) {
        super(nombre, apellidos, edad, dni, telefono, codPersona);
    }
    
    /**
     * Metodo para dar de alta un paciente en la clinica.
     */
    public Paciente altaPaciente() {
        System.out.println("Dar de alta nuevo paciente.");
        Paciente paciente = new Paciente();
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        paciente.setNombre(nombre);
        System.out.print("Primer apellido: ");
        String apellido1 = teclado.next();
        System.out.print("Segundo apellido: ");
        String apellido2 = teclado.next();
        paciente.setApellidos(apellido1 + " " + apellido2);
        System.out.print("Edad: ");
        int edad = teclado.nextInt();
        paciente.setEdad(edad);
        System.out.print("Teléfono: ");
        String tlf = teclado.next();
        paciente.setTelefono(tlf);
        System.out.print("DNI: ");
        String dni = teclado.next();
        paciente.setDni(dni);
        System.out.println("-- Tipo de prueba --");
        System.out.println("1.- PCR");
        System.out.println("2.- Serologica");
        System.out.println("3.- Prueba rapida");
        System.out.println("4.- Prueba clasica");
        System.out.print("Elige una opción numérica: ");
        int opcion = teclado.nextInt();
        paciente.setTipoPrueba(getPruebaPaciente(opcion));
        paciente.setAlta(new Date());
        System.out.println("--- Paciente dado de alta correctamente ---\n");
        return paciente;
    }
    
    private String getPruebaPaciente(int opcion) {
        if (opcion == 1) {
            return "PCR";
        } else if (opcion == 2) {
            return "Serologica";
        } else if (opcion == 2) {
            return "Prueba rapida";
        } else if (opcion == 2) {
            return "Prueba clasica";
        } else {
            return "";
        }
    }
    
    /**
     * Metodo para dar de alta un empleado (Enfermero o Tecnico) en la clinica.
     */
    public Empleado altaEmpleado(Empleado empleado) {
        System.out.println("Dar de alta nuevo empleado.");
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        empleado.setNombre(nombre);
        System.out.print("Primer apellido: ");
        String apellido1 = teclado.next();
        System.out.print("Segundo apellido: ");
        String apellido2 = teclado.next();
        empleado.setApellidos(apellido1 + " " + apellido2);
        System.out.print("Edad: ");
        int edad = teclado.nextInt();
        empleado.setEdad(edad);
        System.out.print("Teléfono: ");
        String tlf = teclado.next();
        empleado.setTelefono(tlf);
        System.out.print("DNI: ");
        String dni = teclado.next();
        empleado.setDni(dni);
        empleado.setAlta(new Date());
        System.out.println("--- Empleado dado de alta correctamente ---\n");
        return empleado;
    }
    
    /**
     * Metodo para dar de baja usuarios de la clinica.
     */
    public void bajaUsuario(List<Persona> personas) {
        Persona p = null;
        verPersonasBaja(personas);
        System.out.print("\nElige la persona que deseas dar de baja por su código: ");
        int codPersona = teclado.nextInt();
        Iterator it = personas.iterator();
        while(it.hasNext()) {
            p = (Persona) it.next();
            if (p.getCodPersona() == codPersona) {
                it.remove();
                System.out.println("--- Se ha eliminado la persona de la clínica correctamente ---\n");
            }
        }
        if (p == null) {
            System.out.print("\nNo se ha encontrado la persona.\n");
        }
    }
    
    /**
     * Metodo para modificar datos de un paciente de la clinica.
     */
    public void modificarPaciente(Paciente paciente) {
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        paciente.setNombre(nombre);
        System.out.print("Primer apellido: ");
        String apellido1 = teclado.next();
        System.out.print("Segundo apellido: ");
        String apellido2 = teclado.next();
        paciente.setApellidos(apellido1 + " " + apellido2);
        System.out.print("Edad: ");
        int edad = teclado.nextInt();
        paciente.setEdad(edad);
        System.out.print("Teléfono: ");
        String tlf = teclado.next();
        paciente.setTelefono(tlf);
        System.out.print("DNI: ");
        String dni = teclado.next();
        paciente.setDni(dni);
        System.out.println("-- Tipo de prueba --");
        System.out.println("1.- PCR");
        System.out.println("2.- Serologica");
        System.out.println("3.- Prueba rapida");
        System.out.println("4.- Prueba clasica");
        System.out.print("Elige una opción numérica: ");
        int opcion = teclado.nextInt();
        paciente.setTipoPrueba(getPruebaPaciente(opcion));
        paciente.setPruebaRealizada(false);
        paciente.setPruebaAnalizada(false);
        teclado.close();
        System.out.println("--- Datos de paciente actualizado correctamente ---");
    }
    
    /**
     * Metodo para modificar datos de un empleado (Enfermero o Tecnico) de la clinica.
     */
    public void modificarEmpleado(Empleado empleado) {
        System.out.println("Dar de alta nuevo empleado.");
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        empleado.setNombre(nombre);
        System.out.print("Primer apellido: ");
        String apellido1 = teclado.next();
        System.out.print("Segundo apellido: ");
        String apellido2 = teclado.next();
        empleado.setApellidos(apellido1 + " " + apellido2);
        System.out.print("Edad: ");
        int edad = teclado.nextInt();
        empleado.setEdad(edad);
        System.out.print("Teléfono: ");
        String tlf = teclado.next();
        empleado.setTelefono(tlf);
        System.out.print("DNI: ");
        String dni = teclado.next();
        empleado.setDni(dni);
        teclado.close();
        System.out.println("--- Datos de empleado actualizados correctamente ---");
    }
    
    /**
     * Ver todos los ususarios que hay en la clinica mostrando el tipo de rol que tiene en la clinica.
     */
    public void verUsuarios(List<Persona> personas) {
        System.out.println("\n------ Personas registradas en la clínica ------");
        for(Persona persona : personas) {
            if (persona.isAdmin()) {
                System.out.println("ADMINISTRADOR: " + persona);
            } else if (persona.isEnfermero()) {
                System.out.println("ENFERMERO: " + persona);
            } else if(persona.isTecnico()) {
                System.out.println("TÉCNICO: " + persona);
            } else if(persona.isPaciente()) {
                System.out.println("PACIENTE: " + persona);
            }
        }
        System.out.println();
    }
    
    /**
     * Ver los pacientes que tiene asignados un enfermero de la clinica.
     */
    public void verPacientesEnfermero(Enfermero enfermero) {
        for(Paciente paciente : enfermero.getPacientes()) {
            System.out.println("(Cod: " + paciente.getCodPersona() + ") " + paciente.getNombre() + " " + paciente.getApellidos() + ". " + paciente.getEdad() + " años");
        }
    }
    
    /**
     * Ver los pacientes que tiene asignado un tecnico de la clinica.
     */
    public void verPacientesTecnico(Tecnico tecnico) {
        for(Paciente paciente : tecnico.getPacientes()) {
            System.out.println("(Cod: " + paciente.getCodPersona() + ") " + paciente.getNombre() + " " + paciente.getApellidos() + ". " + paciente.getEdad() + " años.");
            System.out.println("Tipo de prueba a realizar: " + paciente.getTipoPrueba());
        }
    }
    
    /**
     * Metodo para ver los pacientes que estan confinados y los dias que les quedan de confinamiento.
     */
    public void verPacientesConfinados(List<Persona> personas) {
        // Tratamiento de fechas
        long diasRestantes;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for(Persona persona : personas) {
            if (persona.isPaciente()) {
                Paciente p = (Paciente) persona;
                if (p.isConfinado()) {
                    System.out.println("(Cod: " + p.getCodPersona() + ") " + p.getNombre() + " " + p.getApellidos());
                    System.out.println("Inicio confinamiento: " + format.format(p.getInicioConfinamiento()));
                    calendar.setTime(p.getInicioConfinamiento());
                    calendar.add(Calendar.DATE, 10);
                    System.out.println("Fin confinamiento: " + format.format(calendar.getTime()));
                    diasRestantes = TimeUnit.DAYS.convert(calendar.getTime().getTime() - p.getInicioConfinamiento().getTime(), TimeUnit.MILLISECONDS);
                    if (diasRestantes > 0) {
                        System.out.println("Quedan " + diasRestantes + " días de confinamiento.");
                    }
                }
            }
        }
    }
    
    /**
     * Metodo que asigna un paciente a un efermero y a un tecnico para realizar una prueba comprobando que, ni el enfermero ni el tecnico tengan ese paciente ya asignado.
     */
    public void asignarPacienteEnfermero(Paciente paciente, Enfermero enfermero, Tecnico tecnico) {
        boolean existePacienteEnfermero = false;
        boolean existePacienteTecnico = false;
        // Comprobar que el paciente no exista para añadirlo a la lista.
        for(Paciente p : enfermero.getPacientes()) {
            if (p.getCodPersona() == paciente.getCodPersona()) {
                existePacienteEnfermero = true;
            }
        }
        for(Paciente p : tecnico.getPacientes()) {
            if (p.getCodPersona() == paciente.getCodPersona()) {
                existePacienteTecnico = true;
            }
        }
        if (!existePacienteEnfermero) {
            enfermero.getPacientes().add(paciente);
            System.out.println("Paciente asignado a enfermero para prueba correctamente.\n");
        } else {
            System.out.println("El enfermero ya tiene el paciente asignado.\n");
        }
        if (!existePacienteTecnico) {
            tecnico.getPacientes().add(paciente);
            System.out.println("Paciente asignado a tecnico para prueba correctamente.\n");
        } else {
            System.out.println("El técnico ya tiene el paciente asignado.\n");
        }
    }
    
    /**
     * Método que asigna un paciente a un efermero para la vacunación comprobando que el enfermero no tenga ese paciente ya asignado.
     */
    public void asignarPacienteEnfermeroVauna(Paciente paciente, Enfermero enfermero) {
        paciente.setVacunar(true);
        boolean existePaciente = false;
        // Comprobar que el paciente no exista para añadirlo a la lista.
        for(Paciente p : enfermero.getPacientes()) {
            if (p.getCodPersona() == paciente.getCodPersona()) {
                existePaciente = true;
            }
        }
        if (!existePaciente) {
            enfermero.getPacientes().add(paciente);
            System.out.println("Paciente asignado para vacuna correctamente.\n");
        } else {
            System.out.println("El enfermero ya tiene el paciente asignado.\n");
        }
    }
        
    /**
     * Metodo que muestra la lista de personas que hay en la clinca para dar de baja una persona de esta.
     */
    public void verPersonasBaja(List<Persona> personas) {
        System.out.println("\n------ Personas registradas en la clínica ------");
        for(Persona persona : personas) {
            if (persona.isEnfermero()) {
                System.out.println("ENFERMERO: " + persona);
            } else if(persona.isTecnico()) {
                System.out.println("TÉCNICO: " + persona);
            } else if(persona.isPaciente()) {
                System.out.println("PACIENTE: " + persona);
            }
        }
    }
    
    /**
     * Metodo sobreescrito para identificar a los Administradores en la lista de personas de la clinica.
     */
    @Override
    public boolean isAdmin() {
        return true;
    }
}
