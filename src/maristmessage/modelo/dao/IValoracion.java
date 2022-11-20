package maristmessage.modelo.dao;

import java.util.ArrayList;

import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Usuario;

/**
 * Interfaz referente al guardado a la valoracion realizada en torno a cada una
 * de las conversaciones realizados por los usuarios de la aplicacion
 * 
 * @author Raúl González
 *
 */
public interface IValoracion {
	/**
	 * Se establece una valoracion para una conversacion en concreto
	 * 
	 * @param conversacion a valorar
	 * @param usuario      que valora
	 * @param valoracion   que se da a la conversacion
	 */
	public void setValoracion(Conversacion conversacion, Usuario usuarioQueValora, Usuario usuarioValorado, int valoracion);

	/**
	 * Se obtiene la valoracion de una conversacion especifica
	 * 
	 * @param conversacion valorada
	 * @param usuario      que ha hecho la valoracion
	 * @return valoracion realizada
	 */
	public int getValoracion(Conversacion conversacion, Usuario usuarioQueValora, Usuario usuarioValorado);
	
	/**
	 * Obtiene una lista de los usuarios sin valorar de la conversacion pasada por parametro
	 * 
	 * @param conversacion actual
	 * @param usuario que va a valorar
	 * @return arraylist con los usuarios sin valorar por parte de la persona
	 */
	public ArrayList<Usuario> getUsuariosSinValorar(Conversacion conversacion, Usuario usuario);
}
