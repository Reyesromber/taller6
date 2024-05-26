package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


import aplicacion.Utilidades;
import dominio.Estado;
import dominio.Incidencia;

/**
 * Clase que maneja las operaciones de base de datos para la entidad Incidencia.
 * Proporciona metodos para conectar a la base de datos, crear, buscar,
 * modificar, y eliminar incidencias.
 * 
 * @author reyes
 */
public class IncidenciaDAO {

	private Connection conexion;
	private final String USUARIO = "pepe";
	private final String PASSWORD = "12345";
	private final String MAQUINA = "localhost";
	private final String BD = "gestion_incidencias";

	/**
	 * Metodo constructor de la clase IncidenciaDAO. Establece la conexión con la
	 * base de datos.
	 */
	public IncidenciaDAO() {
		conexion = conectar();
	}

	/**
	 * Metodo que crea una conexión con el SGBD y la devuelve.
	 * 
	 * @return con La conexion establecida.
	 */
	private Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
		try {
			con = DriverManager.getConnection(url, USUARIO, PASSWORD);
			System.out.println("Conectado a la base de datos");
		} catch (SQLException ex) {
			System.out.println("Error al conectar a la base de datos");
			ex.printStackTrace();
		}
		return con;
	}

	/**
	 * Metodo que cierra la conexión con la base de datos.
	 */
	public void cerrarConexion() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			e.printStackTrace();
		}
	}

	/**
	 * Metdo que crea una nueva incidencia en la base de datos.
	 * 
	 * @param incidencia La incidencia a crear.
	 */
	public void crearIncidencia(Incidencia incidencia) {
		if (incidencia != null) {
			String sql = "INSERT INTO incidencias (fecha_registro, hora_registro, puesto, id_estado, problema, fecha_eliminacion, causa_eliminacion, fecha_resolucion, resolucion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
				// Establece los valores en la sentencia.
				sentencia.setDate(1, java.sql.Date.valueOf(incidencia.getFechaRegistro()));
				sentencia.setTime(2, java.sql.Time.valueOf(incidencia.getHoraRegistro()));
				sentencia.setInt(3, incidencia.getPuesto());
				sentencia.setInt(4, Utilidades.comprobarEstado(incidencia.getEstado()));
				sentencia.setString(5, incidencia.getProblema());
				Date fechaEliminacion = null;
				if (incidencia.getFechaEliminacion() != null)
					fechaEliminacion = java.sql.Date.valueOf(incidencia.getFechaEliminacion());
				sentencia.setDate(6, fechaEliminacion);
				sentencia.setString(7, incidencia.getCausaEliminacion());
				Date fechaResolucion = null;
				if (incidencia.getFechaResolucion() != null)
					fechaResolucion = java.sql.Date.valueOf(incidencia.getFechaResolucion());
				sentencia.setDate(8, fechaResolucion);
				sentencia.setString(9, incidencia.getResolucion());
				// La ejecuta.
				sentencia.executeUpdate();
			} catch (SQLException ex) {
				System.out.println("Error al insertar una incidencia");
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que busca una incidencia en la base de datos por su numero de
	 * registro.
	 * 
	 * @param numRegistro El numero de registro de la incidencia a buscar.
	 * @return incidencia La incidencia encontrada.
	 */
	public Incidencia buscarIncidencia(int numRegistro) {
		Incidencia incidencia = null;
		String sql = "SELECT * FROM incidencias WHERE numero_registro = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
			sentencia.setInt(1, numRegistro);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				// Recupera los datos de la incidencia de la base de datos.
				LocalDate fechaRegistro = rs.getDate("fecha_registro").toLocalDate();
				LocalTime horaRegistro = rs.getTime("hora_registro").toLocalTime();
				int puesto = rs.getInt("puesto");
				int idEstado = rs.getInt("id_estado");
				String problema = rs.getString("problema");
				LocalDate fechaEliminacion = null;
				if (rs.getDate("fecha_eliminacion") != null)
					fechaEliminacion = rs.getDate("fecha_eliminacion").toLocalDate();

				String causaEliminacion = rs.getString("causa_eliminacion");
				LocalDate fechaResolucion = null;
				if (rs.getDate("fecha_resolucion") != null)
					fechaResolucion = rs.getDate("fecha_resolucion").toLocalDate();

				String resolucion = rs.getString("resolucion");

				// Crea un objeto Incidencia con los datos recuperados.
				incidencia = new Incidencia(fechaRegistro, horaRegistro, numRegistro,
						Utilidades.comprobarEstado2(idEstado), puesto, problema, fechaEliminacion, causaEliminacion,
						fechaResolucion, resolucion);
			}
		} catch (SQLException ex) {
			System.out.println("Error al consultar la incidencia");
			ex.printStackTrace();
		}
		return incidencia;
	}

	/**
	 * Metodo que recupera y muestra todas las incidencias de la base de datos.
	 */
	public void recuperarListaIncidencias() {
		Incidencia incidencia = null;
		String sql = "SELECT * FROM incidencias";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				// Recupera los datos de cada incidencia de la base de datos.
				LocalDate fechaRegistro = rs.getDate("fecha_registro").toLocalDate();
				LocalTime horaRegistro = rs.getTime("hora_registro").toLocalTime();
				int numRegistro = rs.getInt("numero_registro");
				int puesto = rs.getInt("puesto");
				int idEstado = rs.getInt("id_estado");
				String problema = rs.getString("problema");
				LocalDate fechaEliminacion = null;
				if (rs.getDate("fecha_eliminacion") != null)
					fechaEliminacion = rs.getDate("fecha_eliminacion").toLocalDate();

				String causaEliminacion = rs.getString("causa_eliminacion");
				LocalDate fechaResolucion = null;
				if (rs.getDate("fecha_resolucion") != null)
					fechaResolucion = rs.getDate("fecha_resolucion").toLocalDate();

				String resolucion = rs.getString("resolucion");

				// Crea un objeto Incidencia con los datos recuperados.
				incidencia = new Incidencia(fechaRegistro, horaRegistro, numRegistro,
						Utilidades.comprobarEstado2(idEstado), puesto, problema, fechaEliminacion, causaEliminacion,
						fechaResolucion, resolucion);
				// Muestra la incidencia generada.
				incidencia.mostrarIncidenciaGenerada();
			}
		} catch (SQLException ex) {
			System.out.println("Error al consultar la incidencia");
			ex.printStackTrace();
		}

	}

	/**
	 * Metodo que elimina una incidencia de la base de datos por su numero de
	 * registro.
	 * 
	 * @param numRegistro El numero de registro de la incidencia a eliminar.
	 */
	public void eliminarIncidencia(int numRegistro) {
		String sql = "DELETE FROM incidencias WHERE numero_registro = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
			sentencia.setInt(1, numRegistro);
			sentencia.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error al eliminar la incidencia");
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo que modifica los datos de una incidencia existente en la base de
	 * datos.
	 * 
	 * @param incidencia La incidencia a modificar.
	 */
	public void modificarIncidencia(Incidencia incidencia) {
		if (incidencia != null) {
			String sql = "UPDATE incidencias SET fecha_registro=?, hora_registro=?, puesto=?, id_estado=?, problema=?, fecha_eliminacion=?, causa_eliminacion=?, fecha_resolucion=?, resolucion=? WHERE numero_registro = ?";
			try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
				sentencia.setDate(1, java.sql.Date.valueOf(incidencia.getFechaRegistro()));
				sentencia.setTime(2, java.sql.Time.valueOf(incidencia.getHoraRegistro()));
				sentencia.setInt(3, incidencia.getPuesto());
				sentencia.setInt(4, Utilidades.comprobarEstado(incidencia.getEstado()));
				sentencia.setString(5, incidencia.getProblema());
				Date fechaEliminacion = null;
				if (incidencia.getFechaEliminacion() != null)
					fechaEliminacion = java.sql.Date.valueOf(incidencia.getFechaEliminacion());
				sentencia.setDate(6, fechaEliminacion);
				sentencia.setString(7, incidencia.getCausaEliminacion());
				Date fechaResolucion = null;
				if (incidencia.getFechaResolucion() != null)
					fechaResolucion = java.sql.Date.valueOf(incidencia.getFechaResolucion());
				sentencia.setDate(8, fechaResolucion);
				sentencia.setString(9, incidencia.getResolucion());
				sentencia.setInt(10, incidencia.getNumRegistro());

				sentencia.executeUpdate();
				System.out.println(incidencia.toString());
			} catch (SQLException ex) {
				System.out.println("Error al modificar los datos");
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que muestra la lista de incidencias segun su estado.
	 * 
	 * @param estado El estado establecido.
	 */
	public void recuperarListaIncidenciasPorEstado(Estado estado) {
		Incidencia incidencia = null;
		String sql = "SELECT * FROM incidencias where id_estado = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

			sentencia.setInt(1, Utilidades.comprobarEstado(estado));
			ResultSet rs = sentencia.executeQuery();

			while (rs.next()) {
				LocalDate fechaRegistro = rs.getDate("fecha_registro").toLocalDate();
				LocalTime horaRegistro = rs.getTime("hora_registro").toLocalTime();
				int numRegistro = rs.getInt("numero_registro");
				int puesto = rs.getInt("puesto");
				int idEstado = rs.getInt("id_estado");
				String problema = rs.getString("problema");

				LocalDate fechaEliminacion = (rs.getDate("fecha_eliminacion") != null)
						? rs.getDate("fecha_eliminacion").toLocalDate()
						: null;

				String causaEliminacion = rs.getString("causa_eliminacion");

				LocalDate fechaResolucion = (rs.getDate("fecha_resolucion") != null)
						? rs.getDate("fecha_resolucion").toLocalDate()
						: null;

				String resolucion = rs.getString("resolucion");
				incidencia = new Incidencia(fechaRegistro, horaRegistro, numRegistro,
						Utilidades.comprobarEstado2(idEstado), puesto, problema, fechaEliminacion, causaEliminacion,
						fechaResolucion, resolucion);
				incidencia.mostrarIncidenciaGenerada();
			}
		} catch (SQLException ex) {
			System.out.println("Error al consultar la incidencia");
			ex.printStackTrace();
		}

	}
}
