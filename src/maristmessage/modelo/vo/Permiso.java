package maristmessage.modelo.vo;

/**
 * Clase que representa a un permiso que tiene
 * un usuario en el sistema
 * 
 * @author Raúl González
 *
 */
public class Permiso {
	/**
	 * Constructor que guarda el nombre del permisor
	 * 
	 * @param name Nombre del permiso
	 */
	public Permiso(String name) {
		this.setName(name);
	}

	/**
	 * Nombre que identifica al permiso
	 */
	private String name;

	/**
	 * Obtiene el nombre del permiso
	 * 
	 * @return name Nombre del permiso
	 */
	public String getName() {
		return name;
	}

	/**
	 * Guarda el nombre del permiso
	 * 
	 * @param name Nombre del permiso
	 */
	public void setName(String name) {
		this.name = name;
	}
}
