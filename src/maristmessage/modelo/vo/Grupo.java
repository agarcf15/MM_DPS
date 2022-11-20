package maristmessage.modelo.vo;

/**
 * Clase que guarda cada grupo creado
 * 
 * @author Raúl Seara
 * 
 */
public class Grupo {
	/**
	 * Nombre que identifica al grupo
	 */
	private String nombre;
	

	/**
	 * Obtiene el nombre del grupo
	 * 
	 * @return name Nombre del grupo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del grupo
	 * 
	 * @param nombre Nombre del grupo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
