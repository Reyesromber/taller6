package dominio;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase Incidencia representa una incidencia en el sistema de gestion de
 * incidencias.
 * 
 * @author reyes
 */
public class Incidencia {
	private LocalDate fechaRegistro;

	private LocalTime horaRegistro;

	private int numRegistro;

	private int puesto;

	private Estado estado;

	private String problema;

	private LocalDate fechaEliminacion;

	private String causaEliminacion;

	private LocalDate fechaResolucion;

	private String resolucion;

	/**
	 * Metodo constructor por defecto que inicializa el número de registro
	 * automáticamente.
	 */
	public Incidencia() {
	}

	/**
	 * Constructor por parametros.
	 * 
	 * @param fechaRegistro    La fecha de registro de la incidencia.
	 * @param horaRegistro     La hora de registro de la incidencia.
	 * @param numRegistro      El numero de registro de la incidencia.
	 * @param estado           El estado actual de la incidencia.
	 * @param problema         La causa del problema.
	 * @param fechaEliminacion La fecha de eliminación de la incidencia.
	 * @param causaEliminacion La causa de eliminación de la incidencia.
	 * @param fechaResolucion  La fecha de resolucion de la incidencia.
	 * @param resolucion       La causa de resolucion de la incidencia.
	 */
	public Incidencia(LocalDate fechaRegistro, LocalTime horaRegistro, int numRegistro, Estado estado, int puesto,
			String problema, LocalDate fechaEliminacion, String causaEliminacion, LocalDate fechaResolucion,
			String resolucion) {
		this.fechaRegistro = fechaRegistro;
		this.horaRegistro = horaRegistro;
		this.numRegistro = numRegistro;
		this.estado = estado;
		this.puesto = puesto;
		this.problema = problema;
		this.fechaEliminacion = fechaEliminacion;
		this.causaEliminacion = causaEliminacion;
		this.fechaResolucion = fechaResolucion;
		this.resolucion = resolucion;
	}

	/**
	 * Constructor por parametros personalizados.
	 * 
	 * @param problema El problema de la incidencia.
	 * @param puesto   El puesto asociado a la incidencia.
	 */
	public Incidencia(String problema, int puesto) {
		this.fechaRegistro = LocalDate.now();
		this.horaRegistro = LocalTime.now();
		this.horaRegistro = horaRegistro.withNano(0);
		this.estado = Estado.PENDIENTE;
		this.problema = problema;
		this.fechaEliminacion = null;
		this.causaEliminacion = "";
		this.fechaResolucion = null;
		this.resolucion = "";
		this.puesto = puesto;
	}

	/**
	 * Devuelve el puesto asociado a la incidencia.
	 * 
	 * @return El puesto asociado a la incidencia.
	 */
	public int getPuesto() {
		return puesto;
	}

	/**
	 * Establece el puesto asociado a la incidencia.
	 * 
	 * @param puesto El puesto asociado a la incidencia.
	 */
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	/**
	 * Devuelve la fecha de registro de la incidencia.
	 * 
	 * @return La fecha de registro de la incidencia.
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Establece la fecha de registro de la incidencia.
	 * 
	 * @param fechaRegistro La fecha de registro de la incidencia.
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Devuelve la hora de registro de la incidencia.
	 * 
	 * @return La hora de registro de la incidencia.
	 */
	public LocalTime getHoraRegistro() {
		return horaRegistro;
	}

	/**
	 * Establece la hora de registro de la incidencia.
	 * 
	 * @param horaRegistro La hora de registro de la incidencia.
	 */
	public void setHoraRegistro(LocalTime horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	/**
	 * Devuelve el numero de registro de la incidencia.
	 * 
	 * @return El numero de registro de la incidencia.
	 */
	public int getNumRegistro() {
		return numRegistro;
	}

	/**
	 * Establece el numero de registro de la incidencia.
	 * 
	 * @param numRegistro El numero de registro de la incidencia.
	 */
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * Devuelve el estado de la incidencia.
	 * 
	 * @return El estado de la incidencia.
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Establece el estado de la incidencia.
	 * 
	 * @param estado El estado de la incidencia.
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve el problema asociado a la incidencia.
	 * 
	 * @return El problema asociado a la incidencia.
	 */
	public String getProblema() {
		return problema;
	}

	/**
	 * Establece el problema asociado a la incidencia.
	 * 
	 * @param problema El problema asociado a la incidencia.
	 */
	public void setProblema(String problema) {
		this.problema = problema;
	}

	/**
	 * Devuelve la fecha de eliminacion de la incidencia.
	 * 
	 * @return La fecha de eliminacion de la incidencia.
	 */
	public LocalDate getFechaEliminacion() {
		return fechaEliminacion;
	}

	/**
	 * Establece la fecha de eliminacion de la incidencia.
	 * 
	 * @param fechaEliminacion La fecha de eliminacion de la incidencia.
	 */
	public void setFechaEliminacion(LocalDate fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	/**
	 * Devuelve la causa de eliminacion de la incidencia.
	 * 
	 * @return La causa de eliminacion de la incidencia.
	 */
	public String getCausaEliminacion() {
		return causaEliminacion;
	}

	/**
	 * Establece la causa de eliminacion de la incidencia.
	 * 
	 * @param causaEliminacion La causa de eliminacion de la incidencia.
	 */
	public void setCausaEliminacion(String causaEliminacion) {
		this.causaEliminacion = causaEliminacion;
	}

	/**
	 * Devuelve la fecha de resolucion de la incidencia.
	 * 
	 * @return La fecha de resolucion de la incidencia.
	 */
	public LocalDate getFechaResolucion() {
		return fechaResolucion;
	}

	/**
	 * Establece la fecha de resolucion de la incidencia.
	 * 
	 * @param fechaResolucion La fecha de resolucion de la incidencia.
	 */
	public void setFechaResolucion(LocalDate fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	/**
	 * Devuelve la resolucion de la incidencia.
	 * 
	 * @return La resolucion de la incidencia.
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * Establece la resolucion de la incidencia.
	 * 
	 * @param resolucion La resolucion de la incidencia.
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Muestra la informacion de la incidencia generada en la consola.
	 */
	public void mostrarIncidenciaGenerada() {
		System.out.println("Codigo: " + this.fechaRegistro.toString() + "-" + this.horaRegistro.toString() + "-"
				+ this.numRegistro);
		System.out.println("Estado: " + this.estado);
		System.out.println("Puesto: " + this.puesto);
		System.out.println("Problema: " + this.problema);
	}

	/**
	 * Sobrescribe el método toString para proporcionar una representación textual
	 * del objeto Incidencia.
	 * 
	 * @return Los detalles de la incidencia.
	 */
	@Override
	public String toString() {
		return "\n Fecha registro: " + fechaRegistro + "\n Hora registro: " + horaRegistro + "\n Numero de registro: "
				+ numRegistro + "\n estado: " + estado + "\n puesto: " + puesto + "\n problema: " + problema
				+ "\n causa de eliminación: " + causaEliminacion + "\n resolución: " + resolucion;
	}

}
