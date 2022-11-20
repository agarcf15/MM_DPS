package maristmessage.modelo.vo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que representa un mensaje
 * 
 * @author Raúl González
 *
 */
public class Mensaje {
	/**
	 * Autor del mensaje
	 */
	private Usuario author;

	/**
	 * Texto del mensaje
	 */
	private String message;

	/**
	 * Fecha en la que se envio el mensaje
	 */
	//private Date date;
	private java.sql.Timestamp timestamp;
	/**
	 * Fecha en la que se envio el mensaje
	 */
	private String nombreGrupo;


	/**
	 * Constructor de Mensaje
	 */
	public Mensaje(Usuario author, String message) {
		this.setAuthor(author);
		this.setMessage(message);
		this.setDate();
	}
	/**
	 * Constructor de Mensaje vacio
	 */
	public Mensaje() {
	}


	/**
	 * Obtiene el autor del mensaje
	 * 
	 * @return author Autor del mensaje
	 */
	public Usuario getAuthor() {
		return author;
	}

	/**
	 * Establece el autor del mensaje
	 * 
	 * @param author Autor del mensaje
	 */
	public void setAuthor(Usuario author) {
		this.author = author;
	}

	/**
	 * Devuelve el contenido del mensaje
	 * 
	 * @return message Texto del mensaje
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Establece el contenido del mensaje
	 * 
	 * @param message El mensaje a enviar
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Obtiene la fecha del envio del mensaje
	 * 
	 * @return date Fecha
	 */
	public Date getDate() {
		return timestamp;
	}

	/**
	 * Establece la fecha y hora actual
	 */
	
	public void setDate(Timestamp timestamp) {	
		this.timestamp = timestamp;
	}
	
	public void setDate() {
		//this.date = new Date();
		Calendar calendar = Calendar.getInstance();
		this.timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
	}
	/**
	 * Establece la fecha y hora a la que fue hecho el mensaje
	 */
/*	@SuppressWarnings("deprecation")
	public void setDate(String fecha) {
		this.date = new Date(fecha);
	}*/
	/**
	 * Obtiene el id de la conversacion a la que pertenece
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	/**
	 * Establece el id de la conversacion a la que pertenece
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

}
