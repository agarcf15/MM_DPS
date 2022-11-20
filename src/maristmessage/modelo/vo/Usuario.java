package maristmessage.modelo.vo;

/**
 * Clase que representa un usuario
 * 
 * @author Raúl González 
 *
 */
public class Usuario {
	/**
	 * Identificador del usuario
	 */
	private String nombreDeUsuario;
	
	/**
	 * Rol del usuario
	 */
	private Rol rol;
	
	/**
	 * Datos personales (nombre)
	 */
	
	private String nombre;

	/**
	 * Datos personales (apellidos)
	 */
	private String apellidos;
	
	
	/**
	 * Contraseña del usuario
	 */
	private String contrasenya;
	
	/**
	 * Devuelve el nombre de usuario
	 * 
	 * @return usuario Nombre de usuario
	 */
	public String getNombreUsuario() {
		return nombreDeUsuario;
	}

	/**
	 * Establece el nombre de usuario
	 * 
	 * @param usuario Nombre de usuario
	 */
	public void setNombreUsuario(String user) {
		this.nombreDeUsuario = user;
	}

	/**
	 * Obtiene un objeto que contiene un rol
	 * 
	 * @return rol Objeto con un rol
	 */
	public Rol getRol() {
		return rol;
	}
	
	/**
	 * Establece el rol en funcion de lo que 
	 * se ha establecido en el campo
	 *
	 * @param rol Nombre de un rol
	 */
	public void setRol(String rol) {
		this.rol = new Rol();
		this.rol.setName(rol);
	}

	/**
	 * Obtiene el nombre de un usuario
	 * 
	 * @return nombre Nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de un usuario 
	 * 
	 * @param nombre String Nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establece apellidos 
	 * 
	 * @return apellidos String con apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece apellidos 
	 * 
	 * @param apellidos String con apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve la contraseña
	 * 
	 * @return contrasenya String de contraseña
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * Establece la contraseña
	 * 
	 * @param contrasenya String de contraseña
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
}
