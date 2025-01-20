package modeloDB_DTO;

import java.util.Objects;

public class Empleados_DTO {
	private String nombre;
	private String color;
	private String RutaFondo;
	private String colorFondo;
	
	public Empleados_DTO(String nombre, String color, String rutaFondo, String colorFondo) {
		super();
		this.nombre = nombre;
		this.color = color;
		RutaFondo = rutaFondo;
		this.colorFondo = colorFondo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRutaFondo() {
		return RutaFondo;
	}
	public void setRutaFondo(String rutaFondo) {
		RutaFondo = rutaFondo;
	}
	public String getColorFondo() {
		return colorFondo;
	}
	public void setColorFondo(String colorFondo) {
		this.colorFondo = colorFondo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(RutaFondo, color, colorFondo, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleados_DTO other = (Empleados_DTO) obj;
		return Objects.equals(RutaFondo, other.RutaFondo) && Objects.equals(color, other.color)
				&& Objects.equals(colorFondo, other.colorFondo) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Empleados_DTO [nombre=" + nombre + ", color=" + color + ", RutaFondo=" + RutaFondo + ", colorFondo="
				+ colorFondo + "]";
	}
	
	
	
}
