package maristmessage.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import maristmessage.modelo.conexion.Conexion;
import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.vo.Mensaje;
import maristmessage.modelo.vo.Usuario;

/**
 * Clase creada para realizar las acciones de los mensajes siguiendo el patrón
 * DAO
 * 
 * @author Raúl Seara
 * @author Raúl González
 * 
 */

public class MensajeDao implements IMensaje {
	public void enviarMensaje(Mensaje mensaje, Conversacion conversacion) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			//primero buscamos la conversacion, con el nombre del grupo al que se envia el mensaje
			// TODO: mejor la conversacion se guarda en el coordinador
			//Conversacion aux = obtenerConversacion(new Conversacion(mensaje.getNombreGrupo()));
			
			// Si es un mensaje global
			if (conversacion.getNombreConversacion()!=null) {
				if (conversacion.getNombreConversacion().equals("global")) {
					// Obtener id del grupo
					int idGrupo = 0;
					ResultSet resultIdGrupo = estatuto.executeQuery("SELECT idGrupo FROM Grupos WHERE nombreGrupo = 'MensajesGlobales';");
					if (resultIdGrupo.next()) {
						idGrupo = resultIdGrupo.getInt("idGrupo");
					}
					// Obtener id de la conversacion de grupo
					int idConv = 0;
					ResultSet resultIdConv = estatuto.executeQuery("SELECT idConversacion FROM Conversaciones WHERE idGrupo = " + idGrupo + ";");
					if (resultIdConv.next()) {
						idConv = resultIdConv.getInt("idConversacion");
					}
					estatuto.executeUpdate(
							"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +idConv + "', '" + mensaje.getAuthor().getNombreUsuario() + "', '"+ mensaje.getDate() + "', '" + mensaje.getMessage() + "')");
					return;
				}
			}
			
			//System.out.print(
					//"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +conversacion.getIdConversacion() + "', '" + mensaje.getAuthor().getNombreUsuario() + "', '"+ mensaje.getDate() + "', '" + mensaje.getMessage() + "')");
			estatuto.executeUpdate(
					"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +conversacion.getIdConversacion() + "', '" + mensaje.getAuthor().getNombreUsuario() + "', '"+ mensaje.getDate() + "', '" + mensaje.getMessage() + "')");
			JOptionPane.showMessageDialog(null, "Mensaje enviado", "Info", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El mensaje no ha sido enviado: " + e.getMessage());
		}
	}
	
	public Conversacion crearConversacionGrupo(Conversacion conversacion) {//aqui entrará la conversacion con todos sus mensajes y con su NOMBRE
			
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			
			// obtener id del grupo
			ResultSet idResult =  estatuto.executeQuery("SELECT idGrupo FROM Grupos WHERE nombreGrupo='" + conversacion.getNombreConversacion() + "';");
			if (idResult.next()) {
				conversacion.setIdGrupo(idResult.getInt("idGrupo"));
			}
			
			// anadir nueva conversacion
			estatuto.executeUpdate(
					"INSERT INTO Conversaciones (idGrupo) VALUES (" + conversacion.getIdGrupo()+ ")"); //REVISAR por favor
			// Guardamos el id de la conversacion
			// Obtener el id que tendra la futura conversacion que se va a crear
			int proximoId = 0;
			ResultSet resultadoId = estatuto.executeQuery("SELECT LAST_INSERT_ID();");
			if (resultadoId.next()) {
				proximoId = resultadoId.getInt(1);
			}
			conversacion.setIdConversacion(proximoId);
			
			// obtener usuario del grupo
			ResultSet usuariosResult =  estatuto.executeQuery("SELECT idUsuario FROM UsuGrupos WHERE idGrupo='" + conversacion.getIdGrupo() + "';");
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			while (usuariosResult.next()) {
				Usuario usuario = new Usuario();
				usuario.setNombreUsuario(usuariosResult.getString("idUsuario"));
				usuarios.add(usuario);
			}
			
			// crear mensaje inicial de "ha entrado en la conversacion"
			for (Usuario usuario : usuarios) {
				estatuto.executeUpdate(
						"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +conversacion.getIdConversacion() + "', '" + usuario.getNombreUsuario() + "', '"+ new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()) + "', '" + usuario.getNombreUsuario() + " ha entrado en la conversación" + "')");
			}
			
			JOptionPane.showMessageDialog(null, "Conversacion creada", "Info", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "La conversación no se ha creado: " + e.getMessage());
		}
		return conversacion;
	}
	
	public Conversacion crearConversacion(ArrayList<Usuario> usuarios) {//aqui entrará la conversacion con todos sus mensajes y con su NOMBRE
		
		Conexion conex = Conexion.getInstancia();
		Conversacion conversacion = new Conversacion(usuarios.get(1).getNombreUsuario());
		try {
			Statement estatuto = conex.getConnection().createStatement();
			int proximoId = 0;
						
			estatuto.executeUpdate("INSERT INTO Conversaciones (idGrupo) VALUES (NULL)");
			
			// Obtener el id que tendra la futura conversacion que se va a crear
			ResultSet resultadoId = estatuto.executeQuery("SELECT LAST_INSERT_ID();");
			if (resultadoId.next()) {
				proximoId = resultadoId.getInt(1);
			}
			
			// Anadir a los dos participantes
			for (Usuario usuario : usuarios) {
				estatuto.executeUpdate("INSERT INTO UsuConversaciones (idConversacion, idUsuario) VALUES (" + proximoId + ", '" + usuario.getNombreUsuario() + "')");
			}
			 
			conversacion.setIdConversacion(proximoId);
			conversacion.setReceiver(usuarios.get(1));
			
			// guardar dos mensajes iniciales para que no haya errores
			estatuto.executeUpdate(
					"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +conversacion.getIdConversacion() + "', '" + usuarios.get(0).getNombreUsuario() + "', '"+ new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()) + "', '" + usuarios.get(0).getNombreUsuario() + " ha entrado en la conversación" + "')");
			estatuto.executeUpdate(
					"INSERT INTO Mensajes (idConversacion, emisor, fecha, mensaje) VALUES ('" +conversacion.getIdConversacion() + "', '" + usuarios.get(1).getNombreUsuario() + "', '"+ new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()) + "', '" + usuarios.get(1).getNombreUsuario() + " ha entrado en la conversación" + "')");
			JOptionPane.showMessageDialog(null, "Conversacion creada", "Info", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "La conversación no se ha creado");
		}
		return conversacion;
		
	}
	
	// TODO: si existe una conversacion activa sin cerrar se devuelve un objeto conversacion, sino null
	public Conversacion obtenerConversacion(ArrayList<Usuario> conversantes) {
		Conexion conex = Conexion.getInstancia();
		
		try {
			Statement estatuto = conex.getConnection().createStatement();
			
			// Primero se obtienen de los mensajes del emisor y del receptor su idConversacion
			ResultSet mensajesEmisor = estatuto.executeQuery("SELECT idConversacion FROM Mensajes WHERE emisor='" + conversantes.get(0).getNombreUsuario() + "';");
			ArrayList<Integer> idConversacionesEmisor = new ArrayList<Integer>();
			while (mensajesEmisor.next()) {
				idConversacionesEmisor.add(mensajesEmisor.getInt("idConversacion"));
			}
			ResultSet mensajesReceptor = estatuto.executeQuery("SELECT idConversacion FROM Mensajes WHERE emisor='" + conversantes.get(1).getNombreUsuario() + "';");
			ArrayList<Integer> idConversacionesReceptor = new ArrayList<Integer>();
			while (mensajesReceptor.next()) {
				idConversacionesReceptor.add(mensajesReceptor.getInt("idConversacion"));
			}
			
			// Se buscan los valores que coincidan y se mantienen en idConversacionesEmisor
			idConversacionesEmisor.retainAll(idConversacionesReceptor);
			
			// Si no hay conversaciones comunes, no existe conversacion
			if (idConversacionesEmisor.size() == 0) {
				return null;
			}
			
			// Para esas conversaciones se comprueba si se han realizado las valoraciones
			// Tiene que haber 2, valoracion mutua
			// TODO: Esto podria ser un metodo a parte compatible con grupos
			ArrayList<Integer> valoracionesExistentes = new ArrayList<Integer>();
			int idConversacionIncompleta = -1;
			for (Integer idConversacion: idConversacionesEmisor) {
				valoracionesExistentes.clear();
				ResultSet setValoracion = estatuto.executeQuery("SELECT valoracion FROM Valoraciones WHERE idConversacion=" + idConversacion + ";");
				while (setValoracion.next()) {
					valoracionesExistentes.add(setValoracion.getInt("valoracion"));
				}
				// Comprobar si el numero de valoraciones esta completo, conversacion cerrada, se devuelve null, no hay conversacion
				// Numero de valoraciones completo: usuariosAValorar por usuariosAValorar-1
				if (valoracionesExistentes.size() != (conversantes.size() * (conversantes.size() - 1))) {
					idConversacionIncompleta = idConversacion;
				}
			}
			
			// Si el id es -1 es que no hay ninguna conversacion sin completar
			if (idConversacionIncompleta == -1) {
				return null;
			}

			// Recoger datos de la conversacion
			Conversacion conversacion = new Conversacion(conversantes.get(1));
			conversacion.setIdConversacion(idConversacionIncompleta);
			ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
			ResultSet mensajesBase = estatuto.executeQuery("SELECT * FROM Mensajes WHERE idConversacion='" + idConversacionIncompleta + "'");

			while (mensajesBase.next()) {
				Mensaje aux = new Mensaje();
				Usuario auxus = new Usuario();
				auxus.setNombreUsuario(mensajesBase.getString("emisor"));// solo creamos el usuario con su id, de necesitar
																		// mas datos podriamos llamar a buscarUsuario,
																		// de UsuarioDao
				aux.setAuthor(auxus);
				aux.setMessage(mensajesBase.getString("mensaje"));
				aux.setDate(mensajesBase.getTimestamp("fecha"));
				mensajes.add(aux);
			}
			

			conversacion.setMessages(mensajes);
			return conversacion;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error comprobando si existe conversacion: " + e.getMessage());
		}	
		return null;
	}
	
	// TODO: si todo funciona, este metodo se quedara para conversaciones de grupo
	public Conversacion obtenerConversacionGrupo(Grupo grupo) {
		Conexion conex = Conexion.getInstancia();
		Conversacion conversacion = new Conversacion(grupo.getNombre());
		try {
		//	ArrayList<String> idMensajes = new ArrayList<String>();
		//	ArrayList<Integer> idMensajes = new ArrayList<Integer>();
			ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
			//String idGrupo=null;
			//String idConversacion=null;
			//int idGrupo=0;
			//int idConversacion=0;

			Statement estatuto = conex.getConnection().createStatement();
			System.out.print("Inicio obtener Conver");
			// Obtenemos los grupos actuales con ese nombre
			ResultSet resultado = estatuto
					.executeQuery("SELECT idGrupo FROM Grupos WHERE nombreGrupo='" + grupo.getNombre() + "'");
			if (resultado.next()) {
				//idGrupo=resultado.getString("idGrupo");
				conversacion.setIdGrupo(resultado.getInt("idGrupo"));
				System.out.print("ID grupo: "+conversacion.getIdGrupo());
			} else { // Si no hay grupos devolvemos null, no hay grupo, se supone que existe?
				return null;
			}
			
			// Obtengo todas las conversaciones existentes para ese grupo
			resultado = estatuto
					.executeQuery("SELECT idConversacion FROM Conversaciones WHERE idGrupo=" + conversacion.getIdGrupo() + ";");
			
			// Y las guardo en un arraylist
			ArrayList<Integer> idConversaciones = new ArrayList<Integer>();
			while (resultado.next()) {
				idConversaciones.add(resultado.getInt("idConversacion"));
			}
			
			// Guardo el numero de personas en ese grupo
			int numeroPersonasGrupo = 0;
			resultado = estatuto
					.executeQuery("SELECT COUNT(*) FROM UsuGrupos WHERE idGrupo=" + conversacion.getIdGrupo() + "");
			if (resultado.next()) {
				numeroPersonasGrupo = resultado.getInt(1);
			}
			
			// Y compruebo si queda alguna conversacion por valorar
			// Para esas conversaciones se comprueba si se han realizado las valoraciones
			// Tiene que una valoracion mutua
			// TODO: Esto podria ser un metodo a parte compatible con individual
			ArrayList<Integer> valoracionesExistentes = new ArrayList<Integer>();
			int idConversacionIncompleta = -1;
			for (Integer idConv : idConversaciones) {
				valoracionesExistentes.clear();
				ResultSet setValoracion = estatuto.executeQuery(
						"SELECT valoracion FROM Valoraciones WHERE idConversacion=" + idConv + ";");
				while (setValoracion.next()) {
					valoracionesExistentes.add(setValoracion.getInt("valoracion"));
				}
				// Comprobar si el numero de valoraciones esta completo, conversacion cerrada,
				// se devuelve null, no hay conversacion
				// Numero de valoraciones completo: usuariosAValorar por
				// usuariosAValorar-1
				if (valoracionesExistentes.size() != (numeroPersonasGrupo * (numeroPersonasGrupo - 1))) {
					idConversacionIncompleta = idConv;
				}
			}
		
			if (idConversacionIncompleta != -1) {
				//idConversacion=resultado.getString("idConversacion");
				conversacion.setIdConversacion(idConversacionIncompleta);
				System.out.print("idConversacion obtenido: "+conversacion.getIdConversacion());
			} else { // conversaciones completadas, crear una nueva
				return null;
			}
			
			// Obtengo los mensajes
			/*resultado = estatuto
					.executeQuery("SELECT idMensaje FROM Mensajes WHERE idConversacion='" + idConversacion + "'");
			while(resultado.next()) {//si lo encuentra
				//idMensajes.add(resultado.getString("idMensaje"));//obtenidos los mensajes, los bajamos, creamos objeto y lo cargamos en el array
				idMensajes.add(resultado.getInt("idMensaje"));
			}
			//	for (String string : idMensajes) { //con el bucle cargamos al array de mensajes todos los mensajes de la conversacion
			for(int idMsn : idMensajes) {*/
			//	resultado = estatuto
			//		.executeQuery("SELECT * FROM Mensajes WHERE idMensaje='" + string + "'");
				
			resultado = estatuto
					.executeQuery("SELECT * FROM Mensajes WHERE idConversacion='" + conversacion.getIdConversacion() + "'");
				
				while(resultado.next()) {
					Mensaje aux = new Mensaje();
					Usuario auxus = new Usuario();
					auxus.setNombreUsuario(resultado.getString("emisor"));//solo creamos el usuario con su id, de necesitar mas datos podriamos llamar a buscarUsuario, de UsuarioDao
					aux.setAuthor(auxus);
					aux.setMessage(resultado.getString("mensaje"));
					aux.setDate(resultado.getTimestamp("fecha"));
					mensajes.add(aux);
				}
				
			//}
			
			conversacion.setMessages(mensajes);
			
		
			estatuto.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Conversacion no encontrada");
		}		
		return conversacion;
		
	}
}
