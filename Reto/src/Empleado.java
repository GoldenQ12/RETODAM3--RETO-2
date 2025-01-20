import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa a un empleado con sus datos personales.
 * Implementa la interfaz Serializable para permitir la serialización de objetos de esta clase.
 */
public class Empleado implements Serializable {

    private String codigo;
    private String nombre;
    private String categoria;
    private String fechaNacimiento;
    private String contrasena;

    /**
     * Constructor con parámetros para inicializar los atributos de un empleado.
     * 
     * @param codigo          Código único del empleado.
     * @param nombre          Nombre del empleado.
     * @param categoria       Categoría o rol del empleado (ej. "COCINERO", "GERENTE").
     * @param contrasena      Contraseña del empleado.
     * @param fechaNacimiento Fecha de nacimiento del empleado en formato "yyyy-MM-dd".
     */
    public Empleado(String codigo, String nombre, String categoria, String contrasena, String fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor sin parámetros que inicializa los atributos con valores por defecto.
     * La fecha de nacimiento se establece con la fecha actual.
     */
    public Empleado() {
        this.codigo = "";
        this.nombre = "";
        this.categoria = "";
        this.contrasena = "";
        this.fechaNacimiento = LocalDate.now().toString();
    }

    /**
     * Obtiene el código único del empleado.
     * 
     * @return Código del empleado.
     */
    public String getCodigo() { return codigo; }

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return Nombre del empleado.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene la categoría o rol del empleado.
     * 
     * @return Categoría del empleado.
     */
    public String getCategoria() { return categoria; }

    /**
     * Obtiene la contraseña del empleado.
     * 
     * @return Contraseña del empleado.
     */
    public String getContrasena() { return contrasena; }

    /**
     * Obtiene la fecha de nacimiento del empleado.
     * 
     * @return Fecha de nacimiento del empleado en formato "yyyy-MM-dd".
     */
    public String getFechaNacimiento() { return fechaNacimiento; }

    /**
     * Establece el código del empleado.
     * 
     * @param codigo Nuevo código del empleado.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del empleado.
     * 
     * @param nombre Nuevo nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la categoría o rol del empleado.
     * 
     * @param categoria Nueva categoría del empleado.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Establece la contraseña del empleado.
     * 
     * @param contrasena Nueva contraseña del empleado.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Establece la fecha de nacimiento del empleado.
     * 
     * @param fechaNacimiento Nueva fecha de nacimiento en formato "yyyy-MM-dd".
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    

}
