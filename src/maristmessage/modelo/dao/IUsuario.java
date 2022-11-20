package maristmessage.modelo.dao;

import java.util.ArrayList;

import maristmessage.modelo.vo.Usuario;

/**
 * Interfaz para los métodos DAO de usuario
 * 
 * @author Raúl Seara
 * @author Raúl González
 *
 */
public interface IUsuario {
	/**
	 * Registra un usuario en la base de datos
	 * 
	 * @param usuario que va a ser registrado
	 * 
	 */
	public void registrarUsuario(Usuario usuario);

	/**
	 * Modifica los datos de un usuario que ya existe en la base de datos
	 * 
	 * @param usuario que va a ser modificado
	 * 
	 */
	public void modificarUsuario(Usuario usuario);

	/**
	 * Borra los datos de un usuario que existe en la base de datos
	 * 
	 * @param usuario que va a ser borrado
	 * 
	 */
	public void borrarUsuario(Usuario usuario);

	/**
	 * Busca los datos de un usuario que existe en la base de datos
	 * 
	 * @param usuario que va a ser buscado
	 * @return usuario con los datos completos
	 * 
	 */
	public Usuario buscarUsuario(Usuario usuario);
	
	/**
	 * Obtiene una lista completa de los usuarios registrados en
	 * el sistema
	 * 
	 * @return usuarios en el sistema
	 */
	public ArrayList<Usuario> getUsuarios();

	/**
	 * Se comprueba que el usuario y la contrasena introducidos en el formulario son
	 * correctos y se inicia sesion
	 * 
	 * @param usuario que va a iniciar sesion
	 * @return usuario con los datos completos
	 * 
	 */
	public Usuario iniciarSesion(Usuario usuario);
}
