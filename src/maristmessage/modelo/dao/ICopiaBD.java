package maristmessage.modelo.dao;

/**
 * Interfaz para la creacion de la base de datos
 * 
 * @author Raúl González
 *
 */
public interface ICopiaBD {
	/**
	 * Se realiza una copia de la base de datos en un archivo SQL guardado en el
	 * sistema de archivos local
	 */
	public void realizarCopia();
}
