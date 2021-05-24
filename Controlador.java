import java.util.*;

/**
 * Esta clase controla el funcionamiento completo de la aplicacion, se encarga de mostrar los menus de cada tipo de usuario y hacer las correspondientes llamadas a los metodos que se piden
 * en esta practica. Cuenta con métodos que inicializan la lista de personas de la clínica, los menus y la gestion de estos.
 * 
 * @author Gonzalo García Torre 
 * @version 07/04/2021
 */
public class Controlador {
    
    List<Persona> personas = new ArrayList<Persona>();
    Scanner teclado = new Scanner(System.in);
    Almacen almacen;

    /**
     * Constructor sin parametros del Controlador
     */
    public Controlador() {
        crearPersonas();
        almacen = new Almacen();
    }
    
    /**
     * Metodo para gestionar el menu principal de la clinica (con que tipo de usuaro se va a entrar a la clinica).
     */
    public void menuGeneral() {
        menuInicial();
        System.out.print("Elige una opción numérica: ");
        int opcionMenu = teclado.nextInt();
        int codEmpleado;
        switch(opcionMenu){
            case 1:
                Administrador admin = verElegirAdmin();
                System.out.println("\nBienvenido/a " + admin.getNombre() + "\n");
                // Llamar al menu admin
                menuAdmin(admin);
                break;
            case 2:
                Enfermero enfermero = verElegirEnfermero();
                System.out.println("\nBienvenido/a " + enfermero.getNombre() + "\n");
                // Lllamar al menú enfermero
                menuEnfermero(enfermero);
                break;
            case 3:
                Tecnico tecnico = verElegirTecnico();
                System.out.println("\nBienvenido/a " + tecnico.getNombre() + "\n");
                // Llamar al menú de un técnico
                menuTecnico(tecnico);
                break;
            case 0:
                System.out.println("Has salido de la aplicación.");
                break;
            default:
                System.out.println("Opcion Incorrecta. Por favor introduce una opción del menú.");
        }
    }
    
    /**
     * Metodo que gestiona las acciones que puede llevar a cabo un administrador.
     */
    public void menuAdmin(Administrador admin) {
        int opcion;
        int codEmpleado;
        int opcionAlta;
        Empleado empleado = new Empleado();
        Enfermero enfermero = new Enfermero();
        Tecnico tecnico = new Tecnico();
        Paciente paciente = new Paciente();
        do{
            //Mostrar el menú general
            printMenuAdmin();
            //Pedir la opción
            System.out.print("Introduce una opción numérica: ");
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1:
                    // Menú alta personas
                    menuAlta();
                    System.out.print("Introduce una opción numérica: ");
                    opcionAlta = teclado.nextInt();
                    switch(opcionAlta){
                        case 1:
                            paciente = admin.altaPaciente();
                            paciente.setCodPersona(personas.size() + 1);
                            personas.add(paciente);
                            break;
                        case 2:
                            empleado = admin.altaEmpleado(enfermero);
                            enfermero = (Enfermero) empleado;
                            enfermero.setCodPersona(personas.size() + 1);
                            personas.add(enfermero);
                            break;
                        case 3:
                            empleado = admin.altaEmpleado(tecnico);
                            tecnico = (Tecnico) empleado;
                            tecnico.setCodPersona(personas.size() + 1);
                            personas.add(tecnico);
                            break;
                        case 0:
                            System.out.println("No se dan personas de alta en la clínica.");
                            break;
                        default:
                            System.out.println("Opcion Incorrecta. Por favor introduce una opción del menú.");
                    }
                    break;
                case 2:
                    admin.bajaUsuario(personas);
                    break;
                case 3:
                    // Menú alta personas
                    menuModificacion();
                    System.out.print("Introduce una opción numérica: ");
                    opcionAlta = teclado.nextInt();
                    switch(opcionAlta){
                        case 1:
                            System.out.println("Modificar paciente.");
                            paciente = verElegirPacientes();
                            admin.modificarPaciente(paciente);
                            break;
                        case 2:
                            System.out.println("Modificar un enfermero.");
                            enfermero = verElegirEnfermero();
                            admin.modificarEmpleado(enfermero);
                            break;
                        case 3:
                            System.out.println("Modificar un tecnico.");
                            tecnico = verElegirTecnico();
                            admin.modificarEmpleado(tecnico);
                            break;
                        case 0:
                            System.out.println("No se modifican personas de la clínica.");
                            break;
                        default:
                            System.out.println("Opcion Incorrecta. Por favor introduce una opción del menú.");
                    }
                    break;
                case 4:
                    admin.verUsuarios(personas);
                    break;
                case 5:
                    admin.verPacientesConfinados(personas);
                    break;
                case 6:
                    enfermero = verElegirEnfermero();
                    admin.verPacientesEnfermero(enfermero);
                    break;
                case 7:
                    tecnico = verElegirTecnico(); 
                    admin.verPacientesTecnico(tecnico);
                    break;
                case 8:
                    enfermero = verElegirEnfermero();
                    tecnico = verElegirTecnico();
                    paciente = verElegirPacientes();
                    admin.asignarPacienteEnfermero(paciente, enfermero, tecnico);
                    break;
                case 9:
                    if (almacen.getVacunas().size() > 0) {
                        enfermero = verElegirEnfermero();
                        paciente = verPacientesVacunacion();
                        admin.asignarPacienteEnfermeroVauna(paciente, enfermero);
                    } else {
                        System.out.println("El stock de vacunas está agotado, no se pueden vacunar a los pacientes hasta que se reciba una nueva carga de vacunas.");
                    }
                    break;
                case 10: 
                    almacen.visualizarStock();
                    menuAdmin(admin);
                    break;
                case 11: 
                    almacen.recibirStockVacunas();
                    System.out.println("Stock de vacunas actualizado.\n");
                    menuAdmin(admin);
                    break;
                case 0:
                    System.out.println("Has salido del menú de administrador de la aplicación.\n");
                    menuGeneral();
                    break;
                default:
                    System.out.println("Opcion Incorrecta. Por favor introduce una opción del menú.");
            }
        }while(opcion != 0);
    }
    
    /**
     * Metodo que gestiona las acciones que puede llevar a cabo un enfermero. 
     */
    public void menuEnfermero(Enfermero enfermero) {
        int opcion;
        Paciente paciente = null;
        do{
            //Mostrar el menú general
            printMenuEnfermero();
            //Pedir la opción
            System.out.print("Introduce una opción numérica: ");
            opcion = teclado.nextInt();
            switch(opcion){
                case 1:
                    enfermero.verPacientes();
                    break;
                case 2:
                    if (enfermero.comprobarNumPruebas()) {
                        paciente = enfermero.elegirPaciente();
                        if (!paciente.isPruebaRealizada()) {
                            Prueba prueba = null;
                            if (paciente.getTipoPrueba().equalsIgnoreCase("PCR")) {
                                System.out.println("\nEl paciente quiere realizar una prueba PCR.\n");
                                prueba = new PCR();
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Serologica")) {
                                System.out.println("\nEl paciente quiere realizar un test Serológico.\n");
                                prueba = new Serologico();
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Prueba rapida")) {
                                System.out.println("\nEl paciente quiere realizar una prueba rápida.\n");
                                prueba = new PruebaRapida();
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Prueba clasica")) {
                                System.out.println("\nEl paciente quiere realizar una prueba clásica.\n");
                                prueba = new PruebaClasica();
                            }
                                prueba.setCodPrueba(personas.size() + 1);
                            if (prueba != null) {
                                if (prueba.isSerologico()) {
                                    if (paciente.nuevoTestSerologico(prueba)) {
                                        prueba.setEnfermero(enfermero);
                                        prueba.setPaciente(paciente);
                                        enfermero.registrarPrueba(prueba, paciente);
                                    } else {
                                        System.out.println("\nEl paciente no puede realizar un nuevo test serológico ya que no ha pasado el tiempo suficiente desde la última que hizo.\n");
                                    }
                                }
                                if (prueba.isPcr()) {
                                    if (paciente.nuevaPCR(prueba)) {
                                        prueba.setEnfermero(enfermero);
                                        prueba.setPaciente(paciente);
                                        enfermero.registrarPrueba(prueba, paciente);
                                    } else {
                                    System.out.println("\nEl paciente no puede realizar una nueva PCR ya que no ha pasado el tiempo suficiente desde la última que hizo.\n");
                                    }
                                }
                                if (prueba.isPruebaRapida() || prueba.isPruebaClasica()) {
                                    prueba.setEnfermero(enfermero);
                                    prueba.setPaciente(paciente);
                                    enfermero.registrarPrueba(prueba, paciente);
                                }
                            } else {
                                System.out.println("\nHa habído un error con la realización de la prueba.\n");
                            }
                        } else {
                            System.out.println("No hay pruebas para realizar con este paciente, el administrador debe de modificarle la prueba asignada si el paciente quiere realizar una nueva prueba.\n");
                        }
                    } else {
                        System.out.println("\n--- Ya has hecho el mínimo de 5 pruebas semanales. Para poder hacer nuevas pruebas debes esperar a la semana que viene ---\n");
                    }
                    break;
                case 3:
                    enfermero.verDatosPruebas();
                    break;
                case 4:
                    if (almacen.getVacunas().size() > 0) {
                        paciente = enfermero.elegirPaciente();
                        if (paciente.getVacuna() == null) {
                            Vacuna vacuna;
                            int resultadoVacuna = (int) Math.round(Math.random() * 2);
                            if (resultadoVacuna == 0) {
                                vacuna = new Moderna();
                                System.out.println("\nSe vacuna al paciente con la vacuna Moderna.");
                            } else if (resultadoVacuna == 1) {
                                vacuna = new Pfizer();
                                System.out.println("\nSe vacuna al paciente con la vacuna Pfizer.");
                            } else {
                                vacuna = new Johnson();
                                System.out.println("\nSe vacuna al paciente con la vacuna Johnson.");
                            }
                            enfermero.registrarVacuna(paciente, vacuna, almacen.getVacunas());
                        } else {
                            System.out.println(paciente);
                            System.out.println("\nEl paciente ya está vacunado.\n");
                        }
                    } else {
                        System.out.println("\nEl stock de vacunas está agotado, no se pueden vacunar a los pacientes hasta que se reciba una nueva carga de vacunas.\n");
                    }
                    break;
                case 5:
                    enfermero.comprobarPacientesSegundaDosis();
                    break;
                case 6:
                    int comprobarPacientes = enfermero.verPacientesSegundaDosis();
                    if (comprobarPacientes > 0 && almacen.getVacunas().size() > 0) {
                        // Elegir paciente
                        System.out.print("\nElige un paciente por su código: ");
                        int codEmpleado = teclado.nextInt();
                        // Obtener el administrador elegido por el usuario
                        for (Persona p : personas) {
                            if(p.getCodPersona() == codEmpleado && p.isPaciente()) {
                                paciente = (Paciente) p;
                            }
                        }
                        if (paciente != null) {
                            enfermero.registrarVacunaSegundaDosis(paciente, almacen.getVacunas());
                        } else {
                            System.out.print("\nHa habido un error al seleccionar el paciente.\n");
                        }
                    } else {
                        System.out.print("\nNo hay pacientes que puedan poner la segunda dosis o el stock de vacunas está agotado.\n");
                    }
                    break;
                case 0:
                    System.out.println("\nHas salido del menú de enfermero de la aplicación.\n");
                    menuGeneral();
                    break;
                default:
                    System.out.println("\nOpcion Incorrecta. Por favor introduce una opción del menú.");
            }
        }while(opcion != 0);
    }

    /**
     * Metodo que gestiona las acciones que puede llevar a cabo un tecnico.
     */
    public void menuTecnico(Tecnico tecnico) {
        int opcion;
        do{
            //Mostrar el menú general
            printMenuTecnico();
            //Pedir la opción
            System.out.print("Introduce una opción numérica: ");
            opcion = teclado.nextInt();
            
            switch(opcion){
                case 1:
                    tecnico.verPacientes();
                    break;
                case 2:
                    if (tecnico.comprobarNumPruebas()) {
                        Paciente paciente = tecnico.elegirPaciente();
                        if (!paciente.isPruebaAnalizada()) {
                            Prueba prueba = elegirPruebaTecnico(paciente);
                            if (paciente.getTipoPrueba().equalsIgnoreCase("PCR")) {
                                System.out.println("Analizando prueba PCR del paciente " + paciente.getNombre() + " " + paciente.getApellidos() + ".\n");
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Serologica")) {
                                System.out.println("Analizando test serológico del paciente " + paciente.getNombre() + " " + paciente.getApellidos() + ".\n");
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Prueba rapida")) {
                                System.out.println("Analizando prueba rápida del paciente " + paciente.getNombre() + " " + paciente.getApellidos() + ".\n");
                            } else if(paciente.getTipoPrueba().equalsIgnoreCase("Prueba clasica")) {
                                System.out.println("Analizando prueba clásica del paciente " + paciente.getNombre() + " " + paciente.getApellidos() + ".\n");
                            }
                            if (prueba != null) {
                                prueba.setTecnico(tecnico);
                                tecnico.registrarPrueba(paciente, prueba);
                            } else {
                                System.out.println("Ha habído un error con los resultados de la prueba.\n");
                            }
                        } else {
                            System.out.println("No hay pruebas para analizar con este paciente, el administrador debe de modificarle la prueba asignada si el paciente quiere realizar una nueva prueba.\n");
                        }
                    } else {
                        System.out.println("--- Ya has analizado el mínimo de 4 pruebas semanales. Para poder hacer nuevas pruebas debes esperar a la semana que viene ---\n");
                    }
                    break;
                case 3:
                    tecnico.verDatosPruebas();
                    break;
                case 4:
                    tecnico.actualizarPrueba();
                    break;
                case 0:
                    System.out.println("Has salido del menú de tecnico de la aplicación.\n");
                    menuGeneral();
                    break;
                default:
                    System.out.println("Opcion Incorrecta. Por favor introduce una opción del menú.");
            }
        }while(opcion != 0);
    }
    
    /**
     * Metodo que se encarga de la funcionalidad para mostrar los enfermeros que hay en la clinica y que pide por teclado un enfermero para trabajar con el.
     */
    public Enfermero verElegirEnfermero() {
        System.out.println();
        Enfermero enfermero = new Enfermero();
        // Mostrar enfermeros 
        for (Persona p : personas) {
            if(p.isEnfermero()) {
                System.out.print("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + "\n");
            }
        }
        // Elegir enfermero
        System.out.print("\nElige un enfermero por su código de empleado: ");
        int codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Persona p : personas) {
            if(p.getCodPersona() == codEmpleado && p.isEnfermero()) {
                enfermero = (Enfermero) p;
            }
        }
        if(enfermero == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return verElegirEnfermero();
        }
        return enfermero;
    }
    
    /**
     * Metodo que se encarga de la funcionalidad para mostrar los tecnicos que hay en la clinica y que pide por teclado un tecnico para trabajar con el.
     */
    public Tecnico verElegirTecnico() {
        System.out.println();
        Tecnico tecnico = new Tecnico();
        // Mostrar tecnicos 
        for (Persona p : personas) {
            if(p.isTecnico()) {
                System.out.print("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + "\n");
            }
        }
        // Elegir tecnico
        System.out.print("\nElige un técnico por su código de empleado: ");
        int codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Persona p : personas) {
            if(p.getCodPersona() == codEmpleado && p.isTecnico()) {
                tecnico = (Tecnico) p;
            }
        }
        if(tecnico == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return verElegirTecnico();
        }
        return tecnico;
    }
    
    
    /**
     * Metodo que se encarga de la funcionalidad para mostrar los administradores que hay en la clinica y que pide por teclado un administrador para trabajar con el.
     */
    public Administrador verElegirAdmin() {
        System.out.println();
        Administrador admin = null;
        int codEmpleado;
        // Mostrar admins 
        for (Persona p : personas) {
            if(p.isAdmin()) {
                System.out.print("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + "\n");
            }
        }
        // Elegir admin
        System.out.print("\nElige un administrador por su código de empleado: ");
        codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Persona p : personas) {
            if (p.getCodPersona() == codEmpleado && p.isAdmin()) {
                admin = (Administrador) p;
                break;
            }
        }
        if(admin == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return verElegirAdmin();
        }
        return admin;
    }
    
    /**
     * Metodo que se encarga de la funcionalidad para mostrar los pacientes que hay en la clinica y que pide por teclado un paciente para trabajar con el.
     */
    public Paciente verElegirPacientes() {
        System.out.println();
        Paciente paciente = new Paciente();
        // Mostrar pacientes 
        for (Persona p : personas) {
            if(p.isPaciente()) {
                System.out.print("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + "\n");
            }
        }
        // Elegir paciente
        System.out.print("\nElige un paciente por su código: ");
        int codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Persona p : personas) {
            if(p.getCodPersona() == codEmpleado && p.isPaciente()) {
                paciente = (Paciente) p;
            }
        }
        if(paciente == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return verElegirPacientes();
        }
        return paciente;
    }
    
    public Prueba elegirPruebaTecnico(Paciente paciente) {
        Prueba pruebaAnalizar = new Prueba();
        for (Prueba prueba : paciente.getPruebasRealizadas()) {
            if (!prueba.isPruebaAnalizada()) {
                if (prueba.isPcr()) {
                    System.out.println("Cod " + prueba.getCodPrueba() +".- PCR.");
                } else if (prueba.isSerologico()) {
                    System.out.println("Cod " + prueba.getCodPrueba() +".- Análisis serológico.");
                } else if (prueba.isPruebaRapida()) {
                    System.out.println("Cod " + prueba.getCodPrueba() +".- Prueba rápida.");
                } else if (prueba.isPruebaClasica()) {
                    System.out.println("Cod " + prueba.getCodPrueba() +".- Prueba clásica.");
                }
            }
        }
        // Elegir prueba
        System.out.print("\nElige una prueba por su código: ");
        int codPrueba = teclado.nextInt();
        // Obtener la prueba elegida por el usuario
        for (Prueba prueba : paciente.getPruebasRealizadas()) {
            if(prueba.getCodPrueba() == codPrueba) {
                pruebaAnalizar = prueba;
            }
        }
        if(pruebaAnalizar == null){
            System.out.println("\nPrueba no encontrada, por favor introduce un código de prueba que aparezca en la lista.\n");
            return elegirPruebaTecnico(paciente);
        }
        return pruebaAnalizar;
    }
    
    /**
     * Metodo generalizado para ver los pacientes que pueden vacunarse y elegir uno para trabajar con el.
     */
    public Paciente verPacientesVacunacion() {
        System.out.println();
        Paciente paciente = new Paciente();
        boolean pacientesMenores65 = true;
        // Ordenar la lista de personas por edad, hay que añadir metodo compareTo en la clase persona.
        Collections.sort(personas);
        for (Persona p : personas) {
            if (p.isPaciente()) {
                Paciente pacienteVacuna = (Paciente) p;
                if (pacienteVacuna.getEdad() >= 65) {
                    if (pacienteVacuna.getVacuna() == null) {
                        System.out.println("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + ", " + p.getEdad() + " años.");
                        pacientesMenores65 = false;
                    }
                }
                if (pacientesMenores65) {
                    System.out.println("Cod: " + p.getCodPersona() + ".- " + p.getNombre() + " " + p.getApellidos() + ", " + p.getEdad() + " años.");
                }
            }
        }
        // Elegir paciente
        System.out.print("\nElige un paciente por su código: ");
        int codEmpleado = teclado.nextInt();
        // Obtener el administrador elegido por el usuario
        for (Persona p : personas) {
            if(p.getCodPersona() == codEmpleado && p.isPaciente()) {
                paciente = (Paciente) p;
            }
        }
        if(paciente == null){
            System.out.println("\nPersona no encontrada, por favor introduce un código de empleado que aparezca en la lista.\n");
            return verPacientesVacunacion();
        }
        return paciente;
    }
    
    /**
     * Método que muestra el menu principal de la clinica.
     */
    public void menuInicial() {
        System.out.println("-------------------------------");
        System.out.println("-------- Clínica COVID --------");
        System.out.println("-------------------------------");
        System.out.println("1.- Entrar como administrador");
        System.out.println("2.- Entrar como enfermero");
        System.out.println("3.- Entrar como técnico");
        System.out.println("0.- Salir del menu");
        System.out.println("-------------------------------\n");
    }
    
    /**
     * Metodo que muestra el menu de los usuarios que sean administradores.
     */
    public void printMenuAdmin() {
        System.out.println("------------------------------------");
        System.out.println("-------- Menú administrador --------");
        System.out.println("------------------------------------");
        System.out.println("1.- Dar de alta una persona");
        System.out.println("2.- Dar de baja una persona");
        System.out.println("3.- Modificar una persona");
        System.out.println("4.- Visualizar personas en el sistema");
        System.out.println("5.- Visualizar pacientes confinados");
        System.out.println("6.- Visualizar pacientes asignados a un enfermero");
        System.out.println("7.- Visualizar pacientes asignados a un tecnico");
        System.out.println("8.- Asignar paciente a un enfermero y a un técnico");
        System.out.println("9.- Asignar vacuna a un enfermero");
        System.out.println("10.- Ver stock de vacunas");
        System.out.println("11.- Recibir stock de vacunas");
        System.out.println("0.- Salir del menu");
        System.out.println("------------------------------------\n");
    }
    
    /**
     * Metodo que muestra el menu que ven los administradores al dar de alta un nuevo usuario en la clinica.
     */
    public void menuAlta() {
        System.out.println("-------------------------------");
        System.out.println("-------- Alta personas --------");
        System.out.println("-------------------------------");
        System.out.println("1.- Dar de alta un paciente");
        System.out.println("2.- Dar de alta un enfermero");
        System.out.println("3.- Dar de alta un técnico");
        System.out.println("0.- Salir");
        System.out.println("-------------------------------\n");
    }
    
    /**
     * Metodo que muestra el menu que ven los administradores al modificar personas de la clinica.
     */
    public void menuModificacion() {
        System.out.println("-----------------------------");
        System.out.println("----- Modificar usuario -----");
        System.out.println("-----------------------------");
        System.out.println("1.- Modificar paciente");
        System.out.println("2.- Modificar enfermero");
        System.out.println("3.- Modificar técnico");
        System.out.println("0.- Salir");
        System.out.println("-----------------------------\n");
    }
    
    /**
     * Metodo que muestra el menu de los usuarios que sean enfermeros.
     */
    public void printMenuEnfermero() {
        System.out.println("--------------------------------");
        System.out.println("-------- Menú enfermero --------");
        System.out.println("--------------------------------");
        System.out.println("1.- Visualizar pacientes");
        System.out.println("2.- Registrar prueba");
        System.out.println("3.- Ver pruebas realizadas");
        System.out.println("4.- Registrar vacuna");
        System.out.println("5.- Ver tiempos de vacunación de pacientes asignados");
        System.out.println("6.- Vacunar de la segunda dosis a pacientes");
        System.out.println("0.- Salir del menu");
        System.out.println("-------------------------------\n");
    }

    /**
     * Metodo que muestra el menu de los usuarios que sean tecnicos.
     */
    public void printMenuTecnico() {
        System.out.println("--------------------------------");
        System.out.println("--------- Menú técnico ---------");
        System.out.println("--------------------------------");
        System.out.println("1.- Visualizar pacientes");
        System.out.println("2.- Registrar prueba");
        System.out.println("3.- Ver pruebas analizadas");
        System.out.println("4.- Actualizar resultado de una prueba");
        System.out.println("0.- Salir del menu");
        System.out.println("-------------------------------\n");
    }
    
    /**
     * Crear una serie de personas de inicio en la clinica para poder realizar acciones del menu.
     * @return lista con personas creadas de la clinica.
     */
    public List<Persona> crearPersonas() {    
        this.personas.add(new Enfermero("María", "García Nicieza", 24, "71733436P", "789456123", 1));
        this.personas.add(new Enfermero("Diego", "Villa Escalante", 21, "85624967A", "123456789", 2));
        this.personas.add(new Tecnico("Miguel", "Rodríguez González", 23, "71733436P", "789456123", 3));
        this.personas.add(new Tecnico("Ana", "González García", 22, "85624967A", "123456789", 4));
        this.personas.add(new Administrador("Gonzalo", "García Torre", 22, "71733436P", "789456123", 5));
        this.personas.add(new Administrador("Alba", "Gallego González", 22, "85624967A", "123456789", 6));
        this.personas.add(new Paciente("PCR", "Alejandro", "Ramos Caval", 22, "71733436P", "789456123", 7, false));
        this.personas.add(new Paciente("Serologica", "Isaac", "Moreno Suarez", 90, "85624967A", "123456789", 8, true, new Date()));
        this.personas.add(new Paciente("PCR", "Miguel", "Calvo Ruiz", 70, "85624967A", "123456789", 9, false));
        return this.personas;
    }

}
