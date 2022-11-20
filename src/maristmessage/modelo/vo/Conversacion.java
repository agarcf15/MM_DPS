package maristmessage.modelo.vo;

import java.util.ArrayList;

/**
 * Clase que representa una conversacion,
 * una serie de mensajes
 * 
 * @author Raúl González
 *
 */
public class Conversacion {
	/**
	 * ArrayList con los mensajes de la
	 * conversación
	 */
	private ArrayList<Mensaje> messages;
	/**
	 * Usuario con el que se mantiene la conversacion
	 */
	private Usuario receiver;
	/**
	 * Nombre de la conversacion
	 */
	private String nombreConversacion;
	/**
	 * ID de la conversacion
	 */
	private int idConversacion;
	/**
	 * ID del grupo al que pertenece la conversacion
	 */
	private int idGrupo;
	/**
	 * Constructor de la Conversacion 
	 */
	public Conversacion(Usuario receiver) {
		this.setReceiver(receiver);
	}
	/**
	 * Constructor de la Conversacion 
	 */
	public Conversacion(String nombre) {
		this.nombreConversacion=nombre;
	}
	/**
	 * Establece el usuario con el que se mantiene la conversacion
	 */
	public void setReceiver(Usuario receiver) {
		this.receiver=receiver;
	}

	public void setMessages() {
		
	}
	
	public ArrayList<Mensaje> getMessages() {
		return messages;
	}
	
	public void setMessages(ArrayList<Mensaje> messages) {
		this.messages = messages;
	}
	
	public Usuario getReceiver() {
		return receiver;
	}
	
	public String getNombreConversacion() {
		return nombreConversacion;
	}
	
	public void setNombreConversacion(String nombreConversacion) {
		this.nombreConversacion = nombreConversacion;
	}
	public int getIdConversacion() {
		return idConversacion;
	}
	public void setIdConversacion(int idConversacion) {
		this.idConversacion = idConversacion;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
}
