package presentacion;

import java.time.LocalDate;
import java.util.Scanner;

import dominio.Estado;
import dominio.Incidencia;

/**
 * La clase Interfaz proporciona los métodos necesarios para interactuar con el
 * usuario y gestionar incidencias.
 *
 * @author reyes
 */
public class Interfaz {
	/**
	 * Metodo que muestra el menu de opciones para la gestion de incidencias.
	 */
	public static void mostrarMenu() {
		System.out.println("Elija una opcion del 1 al 11");
		System.out.println("Menú:");
		System.out.println("1. Registrar incidencia");
		System.out.println("2. Buscar incidencia");
		System.out.println("3. Modificar incidencia");
		System.out.println("4. Eliminar incidencia");
		System.out.println("5. Resolver incidencia");
		System.out.println("6. Modificar incidencia resuelta");
		System.out.println("7. Devolver incidencia resuelta");
		System.out.println("8. Mostrar incidencias pendientes");
		System.out.println("9. Mostrar incidencias resueltas");
		System.out.println("10. Mostrar incidencias eliminadas");
		System.out.println("11. Salir");
	}

	/**
	 * Muestra los datos de una incidencia resuelta.
	 * 
	 * @param incidencia La incidencia resuelta.
	 */
	public static void mostrarModificaciones(Incidencia incidencia) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime las nuevas modificaciones: ");
		System.out.println("Fecha de Resolución: ");
		String fechaResolucion = sc.next();
		LocalDate fecha = LocalDate.parse(fechaResolucion);
		System.out.println("Resolución: ");
		String resolucionModificada = sc.next();
		System.out.println("Las nuevas modificaciones son: ");
		System.out.println("Código: " + incidencia.getFechaRegistro() + "-" + incidencia.getHoraRegistro() + "-"
				+ incidencia.getNumRegistro());
		System.out.println("Estado: " + Estado.RESUELTA);
		System.out.println("Puesto: " + incidencia.getPuesto());
		System.out.println("Problema: " + incidencia.getProblema());
		System.out.println("Fecha resolución: " + fecha);
		System.out.println("Resolución: " + resolucionModificada);
	}
}
