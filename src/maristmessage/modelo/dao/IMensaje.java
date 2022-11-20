package maristmessage.modelo.dao;

import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.vo.Mensaje;

/**
 * Interfaz para los métodos DAO de mensaje
 * 
 * @author Raúl González
 *
 */
public interface IMensaje {
	/**
	 * Recibe un mensaje de un usuario y lo anade
	 * a la conversacion
	 * 
	 * @param mensaje a enviar
	 * @param conversacion del mensaje
	 * 
	 */
	public void enviarMensaje(Mensaje mensaje, Conversacion conversacion);
	
	/**
	 * Se crea una nueva conversacion dentro de la base de datos
	 * 
	 * @param conversacion a anadir
	 * 
	 */
	public Conversacion crearConversacionGrupo(Conversacion conversacion);
	
	/**
	 * Se obtiene una conversacion y con ello una serie de mensajes
	 * 
	 * @param conversacion a recibir
	 * 
	 */
	public Conversacion obtenerConversacionGrupo(Grupo grupo);
}
