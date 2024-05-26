package aplicacion;

import dominio.Estado;



/**
 * La clase Main contiene el método principal para ejecutar el programa de
 * gestión de incidencias. Este programa permite registrar, buscar, modificar,
 * eliminar, resolver y mostrar incidencias.
 * 
 * @author reyes
 */
public class Main {
	/**
	 * Método principal para ejecutar el programa de gestión de incidencias. Se crea
	 * un menú de opciones para que el usuario interactúe con las incidencias.
	 * 
	 * @param args Argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		int opcion = Utilidades.leerValidarMenu();

		while (opcion != 11) {
			switch (opcion) {
			case 1:
				// Registrar incidencia
				Utilidades.crearIncidencia();

				break;

			case 2:
				// Buscar incidencia
				Utilidades.buscarIncidencia();

				break;

			case 3:
				// Modificar incidencia
				Utilidades.modificarIncidencia();
				break;

			case 4:
				// Eliminar incidencia
				Utilidades.eliminarIncidencia();
				break;

			case 5:
				// Resolver incidencia
				Utilidades.resolverIncidencia();
				break;

			case 6:
				// Modificar incidencia resuelta
				Utilidades.modificarIncidenciaResuelta();
				break;
			case 7:
				// Devolver incidencia resuelta
				Utilidades.devolverIncidenciaResuelta();
				break;

			case 8:
				// Mostrar incidencias pendientes
				Utilidades.mostrarIncidenciasPendientes(Estado.PENDIENTE);
				break;

			case 9:
				// Mostrar incidencias resueltas
				Utilidades.mostrarIncidenciasResueltas(Estado.RESUELTA);
				break;

			case 10:
				// Mostrar incidencias eliminadas
				Utilidades.mostrarIncidenciasEliminadas(Estado.ELIMINADA);

				break;

			}

			opcion = Utilidades.leerValidarMenu();
		}

		System.out.println("Programa terminado. Hasta la próxima.");
	}

}