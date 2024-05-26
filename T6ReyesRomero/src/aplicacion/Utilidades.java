package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import dominio.Estado;
import dominio.Incidencia;
import persistencia.IncidenciaDAO;
import presentacion.Interfaz;

/**
 * Esta clase proporciona metodos utiles para la gestion de incidencias.
 * 
 * @author reyes
 */
public class Utilidades {
	static IncidenciaDAO incidao = new IncidenciaDAO();

	/**
	 * Metodo que convierte un estado en su correspondiente identificador numérico.
	 * 
	 * @param estado El estado que queremos convertir.
	 * @return id_estado
	 */
	public static int comprobarEstado(Estado estado) {
		int id_estado = 0;
		if (estado == Estado.PENDIENTE) {
			id_estado = 1;

		} else if (estado == Estado.ELIMINADA) {
			id_estado = 2;
		} else if (estado == Estado.RESUELTA) {
			id_estado = 3;
		}
		return id_estado;
	}

	/**
	 * Metodo que convierte un identificador numérico en su correspondiente estado.
	 * 
	 * @param idEstado El identificador numérico del estado.
	 * @return estado
	 */
	public static Estado comprobarEstado2(int idEstado) {
		Estado estado = Estado.PENDIENTE;
		if (idEstado == 1) {
			estado = Estado.PENDIENTE;

		} else if (idEstado == 2) {
			estado = Estado.ELIMINADA;
		} else if (idEstado == 3) {
			estado = Estado.RESUELTA;
		}
		return estado;
	}

	/**
	 * Metodo que lee y valida la opcion seleccionada por el usuario en el menu.
	 * 
	 * @return opcion La opcion seleccionada por el usuario.
	 */
	public static int leerValidarMenu() {
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);

		do {
			Interfaz.mostrarMenu();
			opcion = scanner.nextInt();
		} while (opcion < 1 || opcion > 12);
		/**
		 * Condicion de salida: si opcion >= 1 && opcion <= 12 entrada: opcion <1 ||
		 * opcion > 12
		 */
		return opcion;
	}

	/**
	 * Metodo que lee y valida una fecha ingresada por el usuario.
	 * 
	 * @return fecha La fecha ingresada por el usuario.
	 */
	public static LocalDate leerValidarFecha() {
		System.out.println("Por favor, ingrese la fecha en el formato (YYYY-MM-DD):");
		Scanner scanner = new Scanner(System.in);

		String fechaTexto = scanner.nextLine();
		LocalDate fecha = null;

		// Intenta convertir la cadena ingresada en un objeto LocalDate
		try {
			fecha = LocalDate.parse(fechaTexto);
			System.out.println("Fecha ingresada: " + fecha);
		} catch (Exception e) {
			System.out.println("Formato de fecha incorrecto. Asegúrese de que esté en el formato YYYY-MM-DD.");
		}

		return fecha;
	}

	/**
	 * Metodo que lee y valida una hora ingresada por el usuario.
	 * 
	 * @return hora La hora ingresada por el usuario.
	 */
	public static LocalTime leerValidarHora() {
		System.out.println("Por favor, ingrese la hora en el formato (HH:MM:SS):");
		Scanner scanner = new Scanner(System.in);

		String horaTexto = scanner.nextLine();
		LocalTime hora = null;

		// Intenta convertir la cadena ingresada en un objeto LocalTime
		try {

			hora = LocalTime.parse(horaTexto);
			System.out.println("Hora ingresada: " + hora);
		} catch (Exception e) {
			System.out.println("Formato de hora incorrecto. Asegúrese de que esté en el formato HH-MM-SS.");
		}

		return hora;
	}

	/**
	 * Metodo que crea una nueva incidencia solicitando los datos al usuario.
	 */
	public static void crearIncidencia() {
		System.out.println("Deme los datos de la incidencia a registrar: ");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Puesto: ");
		int puesto = scanner.nextInt();
		System.out.println("Problema: ");
		String problema = scanner.next();

		// Creamos una nueva instancia de Incidencia con los datos proporcionados.
		Incidencia incidencia = new Incidencia(problema, puesto);

		// Llamamos al metodo crearIncidencia de la clase IncidenciaDAO para guardar la
		// nueva incidencia.
		incidao.crearIncidencia(incidencia);
	}

	/**
	 * Metodo que busca una incidencia por el numero de registro que establece el
	 * usuario.
	 */
	public static void buscarIncidencia() {
		System.out.println("Deme el numero de registro de la incidencia a buscar: ");
		Scanner scanner = new Scanner(System.in);
		int numero = scanner.nextInt();
		// Creamos una nueva variable incidencia que guardara la incidencia encontrada
		// con el numero que nos ha proporcionado el usuario.
		Incidencia incidencia = incidao.buscarIncidencia(numero);

		// Si la incidencia existe, mostramos los datos.
		if (incidencia != null) {
			System.out.println("Incidencia encontrada: ");
			System.out.println("Código: " + incidencia.getFechaRegistro() + "-" + incidencia.getHoraRegistro() + "-"
					+ incidencia.getNumRegistro());

		} else {
			System.out.println("No se encontró ninguna incidencia con el número de registro proporcionado.");
		}
	}

	/**
	 * Metodo que modifica una incidencia solicitando las nuevas modificaciones al
	 * usuario.
	 */
	public static void modificarIncidencia() {
		Scanner sc = new Scanner(System.in);

		// Primero mostramos todas las incidencias
		incidao.recuperarListaIncidencias();

		// Ahora leemos la que quiere modificar
		System.out.println("Dime el número a buscar: ");
		int numero = sc.nextInt();

		Incidencia incidencia = incidao.buscarIncidencia(numero);

		if (incidencia == null) {
			System.out.println("No se encontró ninguna incidencia con el número de registro proporcionado.");

			return;
		}
		// Solicitamos las nuevas modificaciones.
		System.out.println("Dime las nuevas modificaciones: ");
		System.out.println("Problema: ");
		incidencia.setProblema(sc.next());
		System.out.println("Puesto: ");
		incidencia.setPuesto(sc.nextInt());

		// Mostramos las nuevas modificaciones.
		System.out.println("Las nuevas modificaciones son: ");
		incidao.modificarIncidencia(incidencia);
	}

	/**
	 * Metodo que elimina una incidencia solicitando la causa de la eliminacion al
	 * usuario.
	 */
	public static void eliminarIncidencia() {
		Scanner sc = new Scanner(System.in);
		// Primero mostramos todas las incidencias
		incidao.recuperarListaIncidencias();

		System.out.println("Dime el número a buscar: ");
		int numero = sc.nextInt();
		Incidencia incidencia = incidao.buscarIncidencia(numero);

		if (incidencia == null) {
			System.out.println("No se ha encontrado una incidencia con ese número de registro");

			return;
		}
		// Solicitamos al usuario la causa de eliminacion.
		System.out.println("Dime la causa de la eliminación: ");
		incidencia.setCausaEliminacion(sc.next());

		// Establecemos el estado de la incidencia a eliminado.
		incidencia.setEstado(Estado.ELIMINADA);

		// Mostramos las nuevas modificaciones de la incidencia.
		incidao.modificarIncidencia(incidencia);
	}

	/**
	 * Metodo que resuelve una incidencia solicitando la resolucion al usuario.
	 */
	public static void resolverIncidencia() {
		Scanner sc = new Scanner(System.in);

		// Primero mostramos todas las incidencias
		incidao.recuperarListaIncidencias();
		System.out.println("Dime el número a buscar: ");
		int numero = sc.nextInt();
		Incidencia incidencia = incidao.buscarIncidencia(numero);

		if (incidencia == null) {
			System.out.println("No se ha encontrado una incidencia con ese número de registro");

			return;
		}

		// Solicitamos al usuario los datos de la resolucion.
		System.out.println("Dime la resolución: ");
		incidencia.setResolucion(sc.next());

		// Establecemos el estado de la incidencia a resuelto.
		incidencia.setEstado(Estado.RESUELTA);

		// Mostramos la incidencia resuelta.
		incidao.modificarIncidencia(incidencia);

	}

	/**
	 * Metodo que modifica una incidencia resuelta solicitando los nuevos datos al
	 * usuario.
	 */
	public static void modificarIncidenciaResuelta() {
		Scanner sc = new Scanner(System.in);

		// Primero mostramos todas las incidencias
		incidao.recuperarListaIncidencias();

		// Ahora leemos la que quiere modificar
		System.out.println("Dime el número a buscar: ");
		int numero = sc.nextInt();

		Incidencia incidencia = incidao.buscarIncidencia(numero);

		// Si el estado de la incidencia es resuelto, solicita al usuario las nuevas
		// modificaciones y las mostramos.
		if (incidencia.getEstado().equals(Estado.RESUELTA)) {
			System.out.println("Dime las nuevas modificaciones: ");
			System.out.println("Resolución: ");
			incidencia.setResolucion(sc.next());
			System.out.println("Puesto: ");
			incidencia.setPuesto(sc.nextInt());
			System.out.println("Las nuevas modificaciones son: ");
			incidao.modificarIncidencia(incidencia);
			// Si el estado no es resuelto, pedimos al usuario que elija una incidencia
			// resuelta.
		} else {
			System.out.println("Por favor, elija una incidencia resuelta");
		}

	}

	/**
	 * Metodo que devuelve una incidencia resuelta a pendiente.
	 */
	public static void devolverIncidenciaResuelta() {
		Scanner sc = new Scanner(System.in);

		// Primero mostramos todas las incidencias
		incidao.recuperarListaIncidencias();
		System.out.println("Dime el número a buscar: ");
		int numero = sc.nextInt();
		Incidencia incidencia = incidao.buscarIncidencia(numero);

		// Si el estado de la incidencia es resuelto, el estado pasa a ser pendiente y
		// mostramos la incidencia ya modificado.
		if (incidencia.getEstado().equals(Estado.RESUELTA)) {
			incidencia.setEstado(Estado.PENDIENTE);
			incidao.modificarIncidencia(incidencia);
		} else {
			System.out.println("Por favor, elija una incidencia resuelta");
		}

	}

	/**
	 * Metodo que muestra todas las incidencias pendientes.
	 * 
	 * @param estado El estado de las incidencias a mostrar.
	 */
	public static void mostrarIncidenciasPendientes(Estado estado) {
		incidao.recuperarListaIncidenciasPorEstado(Estado.PENDIENTE);
	}

	/**
	 * Metodo que muestra todas las incidencias resueltas.
	 * 
	 * @param estado El estado de las incidencias a mostrar.
	 */
	public static void mostrarIncidenciasResueltas(Estado estado) {
		incidao.recuperarListaIncidenciasPorEstado(Estado.RESUELTA);
	}

	/**
	 * Metodo que muestra todas las incidencias eliminadas.
	 * 
	 * @param estado El estado de las incidencias a mostrar.
	 */
	public static void mostrarIncidenciasEliminadas(Estado estado) {
		incidao.recuperarListaIncidenciasPorEstado(Estado.ELIMINADA);
	}

}
