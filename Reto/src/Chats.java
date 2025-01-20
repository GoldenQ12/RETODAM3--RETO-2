import java.io.Serializable;
import java.time.LocalDateTime;

public class Chats implements Serializable {
	
	private String usuario;
    private LocalDateTime fechaConexion;
    private String duracion;
	
    public Chats() {
		super();
		this.usuario = "";
		this.fechaConexion = LocalDateTime.now();
		this.duracion = "";
	}
    
    public Chats(String usuario, LocalDateTime fechaConexion, String duracion) {
		super();
		this.usuario = usuario;
		this.fechaConexion = fechaConexion;
		this.duracion = duracion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFechaConexion() {
		return fechaConexion;
	}

	public void setFechaConexion(LocalDateTime fechaConexion) {
		this.fechaConexion = fechaConexion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Chats [usuario: " + usuario + ", fechaConexion: " + fechaConexion + ", duracion: " + duracion + "]";
	}
}
