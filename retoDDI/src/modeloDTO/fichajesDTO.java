package modeloDTO;

import java.sql.Date;
import java.sql.Time;

public class fichajesDTO {
	
	private int idFichaje;          // Identificador único del fichaje
    private int idEmpleado;         // Identificador del empleado (relación con la tabla Empleado)
    private Date fecha;        // Fecha del fichaje
    private Time horaEntrada;  // Hora de entrada del empleado
    private Time horaSalida;   // Hora de salida del empleado
    private String modo;            // Método de fichaje (remoto, presencial, etc.)
	/**
	 * @return the idFichaje
	 */
	public int getIdFichaje() {
		return idFichaje;
	}
	/**
	 * @param idFichaje the idFichaje to set
	 */
	public void setIdFichaje(int idFichaje) {
		this.idFichaje = idFichaje;
	}
	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the horaEntrada
	 */
	public Time getHoraEntrada() {
		return horaEntrada;
	}
	/**
	 * @param horaEntrada the horaEntrada to set
	 */
	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	/**
	 * @return the horaSalida
	 */
	public Time getHoraSalida() {
		return horaSalida;
	}
	/**
	 * @param horaSalida the horaSalida to set
	 */
	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}
	/**
	 * @return the modo
	 */
	public String getModo() {
		return modo;
	}
	/**
	 * @param modo the modo to set
	 */
	public void setModo(String modo) {
		this.modo = modo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result + ((horaSalida == null) ? 0 : horaSalida.hashCode());
		result = prime * result + idEmpleado;
		result = prime * result + idFichaje;
		result = prime * result + ((modo == null) ? 0 : modo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		fichajesDTO other = (fichajesDTO) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSalida == null) {
			if (other.horaSalida != null)
				return false;
		} else if (!horaSalida.equals(other.horaSalida))
			return false;
		if (idEmpleado != other.idEmpleado)
			return false;
		if (idFichaje != other.idFichaje)
			return false;
		if (modo == null) {
			if (other.modo != null)
				return false;
		} else if (!modo.equals(other.modo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "fichajesDTO [idFichaje=" + idFichaje + ", idEmpleado=" + idEmpleado + ", fecha=" + fecha
				+ ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", modo=" + modo + "]";
	}
	public fichajesDTO(int idFichaje, int idEmpleado, Date fecha, Time horaEntrada, Time horaSalida, String modo) {
		super();
		this.idFichaje = idFichaje;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.modo = modo;
	}
	public fichajesDTO(int idEmpleado, Date fecha, Time horaEntrada, Time horaSalida, String modo) {
		super();
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.modo = modo;
	}
	public fichajesDTO(Time horaSalida) {
		super();
		this.horaSalida = horaSalida;
	}
}
