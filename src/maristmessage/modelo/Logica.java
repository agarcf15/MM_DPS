package maristmessage.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.dao.UsuarioDao;
import maristmessage.modelo.dao.ValoracionDao;
import maristmessage.modelo.vo.Usuario;
import maristmessage.modelo.dao.CopiaBDDao;
import maristmessage.modelo.dao.MensajeDao;
import maristmessage.modelo.vo.Mensaje;
import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.dao.GrupoDao;
import maristmessage.modelo.dao.InformeDao;

/**
 * Clase que se encarga de realizar comprobaciones
 * en torno a la logica de negocio
 * 
 * @author Raúl González
 *
 */
public class Logica {
	private Coordinador coordinador;

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	
	public void validarRegistro(Usuario usuario) {
		UsuarioDao usuarioDao;
		if (usuario.getNombreUsuario().length() < 1 || usuario.getNombreUsuario().length() > 10) {
			JOptionPane.showMessageDialog(null,"Debe introducir un nombre de usuario que tenga entre 1 y 10 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else if (usuario.getContrasenya().length() < 1 || usuario.getContrasenya().length() > 20) {
			JOptionPane.showMessageDialog(null,"Debe introducir una contraseña que tenga entre 1 y 20 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else if (usuario.getNombre().length() < 1 || usuario.getNombre().length() > 20) {
			JOptionPane.showMessageDialog(null,"Debe introducir un nombre que tenga entre 1 y 20 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else if (usuario.getApellidos().length() < 1 || usuario.getApellidos().length() > 40) {
			JOptionPane.showMessageDialog(null,"Debe introducir unos apellidos que tengan entre 1 y 40 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else {
			usuarioDao = new UsuarioDao();
			usuarioDao.registrarUsuario(usuario);
			return;
		}
		JOptionPane.showMessageDialog(null,"No se ha creado el usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	public void validarBorrado(Usuario usuario) {
		UsuarioDao usuarioDao = new UsuarioDao();
		// Comprobar si existe usuario
		if (usuarioDao.buscarUsuario(usuario).getNombre()!=null) {
			usuarioDao.borrarUsuario(usuario);
		} else {
			JOptionPane.showMessageDialog(null,"El usuario introducido no existe","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
	}

	public Usuario validarBusqueda(Usuario usuario) {
		UsuarioDao usuarioDao = new UsuarioDao();
		if (usuario.getNombreUsuario()!=null) {
			return usuarioDao.buscarUsuario(usuario);
		}else {
			return usuario;
		}
	}

	public void validarModificacion(Usuario usuario) {
		UsuarioDao usuarioDao;
		if (usuario.getContrasenya().length() < 1 || usuario.getContrasenya().length() > 20) {
			JOptionPane.showMessageDialog(null,"Debe introducir una contraseña que tenga entre 1 y 20 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else if (usuario.getNombre().length() < 1 || usuario.getNombre().length() > 20) {
			JOptionPane.showMessageDialog(null,"Debe introducir un nombre que tenga entre 1 y 20 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else if (usuario.getApellidos().length() < 1 || usuario.getApellidos().length() > 40) {
			JOptionPane.showMessageDialog(null,"Debe introducir unos apellidos que tengan entre 1 y 40 caracteres","Advertencia",JOptionPane.WARNING_MESSAGE);
		} else {
			usuarioDao = new UsuarioDao();
			usuarioDao.modificarUsuario(usuario);
			return;
		}
		JOptionPane.showMessageDialog(null,"No se ha modificado el usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
	}
	
	public void validarInicioSesion(Usuario usuario) {
			UsuarioDao usuarioDao = new UsuarioDao();
			usuario = usuarioDao.iniciarSesion(usuario);
			
			// Si el campo contrasena esta vacio, datos incorrectos
			// por lo que no se establece usuario
			if (usuario.getContrasenya()!=null) {
				coordinador.setUsuario(usuario);	
			}
	}

	public void validarCierreSesion() {
		coordinador.setUsuario(null);
	}
	
	public ArrayList<Usuario> obtenerListaUsuarios() {
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.getUsuarios();
	}
	
	public void enviarMensaje(Usuario usuario, String cadena) {
		MensajeDao mensajeDao = new MensajeDao();
		Mensaje mensajevo = new Mensaje(usuario, cadena);
		// Conversacion de grupo
		if (coordinador.getGrupo()!=null) {
			mensajevo.setNombreGrupo(coordinador.getGrupo().getNombre());
		}
		
		mensajeDao.enviarMensaje(mensajevo, coordinador.getConversacion());
	}
	
	public void enviarMensajeGlobal(String cadena) {
		//Primero: creamos el usuario Global que será el emisor independientemente
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("Global");
		
		//Creamos el grupo y le ponemos el nombre de Mensajes Globales
		/*Grupo grupo = new Grupo();
		grupo.setNombre("MensajesGlobales");
		
		//Enviamos el grupo a la clase Dao para registrarlo en la base
		//Junto a su único miembro
		//Aprovechamos el código ya creado así que creamos un ArrayList de Usuarios con 1 solo
		ArrayList<Usuario> listaGrupo = new ArrayList<Usuario>();
		listaGrupo.add(usuario);
		
		GrupoDao grupoDao = new GrupoDao();
		grupoDao.crearGrupo(listaGrupo, grupo);*/
		
		//Por último, enviamos el mensaje
		MensajeDao mensajeDao = new MensajeDao();
		Mensaje mensajevo = new Mensaje(usuario, cadena);
		//mensajevo.setNombreGrupo(grupo.getNombre());
		mensajeDao.enviarMensaje(mensajevo, new Conversacion("global"));
	}
	public ArrayList<Mensaje> obtenerMensajesGlobales(){
		MensajeDao mensjDao = new MensajeDao();
		Conversacion temporalConversacion;
		Grupo grupo = new Grupo();
		grupo.setNombre("MensajesGlobales");
		temporalConversacion=mensjDao.obtenerConversacionGrupo(grupo);
		return temporalConversacion.getMessages();
	}
	
	public void crearCopiaBD() {
		CopiaBDDao copiaBDDao = new CopiaBDDao();
		copiaBDDao.realizarCopia();
	}

	public void validarValoracionMensaje(Usuario usuarioValorado, int it) {
		ValoracionDao valoracion = new ValoracionDao();
		if (it >= 0 && it <= 5) {
			valoracion.setValoracion(coordinador.getConversacion(), coordinador.getUsuario(), usuarioValorado, it);
		} else { // El usuario no ha querido valorar el mensaje
			valoracion.setValoracion(coordinador.getConversacion(), coordinador.getUsuario(), usuarioValorado, -1);
		}
	}
	
	public void validarCrearGrupo(ArrayList<String> listaUsuarios, String nombreGrupo) {
		//Lista usuarios miembros del grupo final
		ArrayList<Usuario> listaGrupo = new ArrayList<Usuario>();
		
		//UsuarioDAO para buscar cada usuario
		UsuarioDao usuarioDao = new UsuarioDao();
		
		//Recorremos la lista String de usuarios 
		//y añadimos cada uno a la lista del Grupo
		for(String e: listaUsuarios) {
			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(e);
			listaGrupo.add(usuarioDao.buscarUsuario(usuario));
		}
		
		//Creamos el grupo y le ponemos nombre
		Grupo grupo = new Grupo();
		grupo.setNombre(nombreGrupo);
		
		//Enviamos el grupo a la clase Dao para registrarlo en la base
		//Junto a sus miembros
		// Y guardamos la conversacion
		GrupoDao grupoDao = new GrupoDao();
		coordinador.setConversacion(grupoDao.crearGrupo(listaGrupo, grupo));
		
		if (grupo.getNombre()!=null) {
			coordinador.setGrupo(grupo);	
		}
	}
	
	public HashMap<Integer, String> verGrupos(Usuario usuario){
		GrupoDao grupoDao = new GrupoDao();
		return grupoDao.verGrupos(usuario);
	}
	
	
	//En caso de que hiciera falta
	public void obtenerGrupo(String nombreGrupo) {
		//Creamos un grupo temporal 
		Grupo grupo = new Grupo();
		grupo.setNombre(nombreGrupo);
		
		GrupoDao grupoDao = new GrupoDao();
		grupo = grupoDao.buscarGrupo(grupo);
		
		if (grupo.getNombre()!=null) {
			coordinador.setGrupo(grupo);	
		}
	}
	
	// Validar si la conversacion existe para crear o no otra nueva
	// Si existe guardarla en coordinador
	public void verificarConversacion(Usuario usuarioReceptor) {
		MensajeDao mensajeDao = new MensajeDao();
		// TODO: comprobar si conversacion necesita tantos atributos
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>(Arrays.asList(coordinador.getUsuario(), usuarioReceptor));
		Conversacion conversacionObtenida;
		// Si grupo no es null, sera una conversacion de grupo
		if (coordinador.getGrupo() == null) {
			conversacionObtenida = mensajeDao.obtenerConversacion(usuarios);
		} else {
			conversacionObtenida = mensajeDao.obtenerConversacionGrupo(coordinador.getGrupo());
		}
		
		if (conversacionObtenida!=null) {
			System.out.println("conversacion existente, se guarda en el coordinador");
			coordinador.setConversacion(conversacionObtenida);
		} else {
			System.out.println("conversacion no existente, se crea una nueva");
			// TODO: crear conversacion vacia (hay que pasarle con valores instanciados)
			if (coordinador.getGrupo() != null) {
				coordinador.setConversacion(mensajeDao.crearConversacionGrupo(new Conversacion(coordinador.getGrupo().getNombre())));
			} else {
				coordinador.setConversacion(mensajeDao.crearConversacion(usuarios));
			}		
		}
	}

	public ArrayList<Usuario> validarUsuariosSinValorar() {
		ValoracionDao valoracionDao = new ValoracionDao();
		return valoracionDao.getUsuariosSinValorar(coordinador.getConversacion(), coordinador.getUsuario());
	}
	
	public ArrayList<Mensaje> obtenerMensajesConversacion(){
		MensajeDao mensjDao = new MensajeDao();
		Conversacion temporalConversacion;
		//Comprobamos si es una conversación de grupo o entre dos personas
		//Si grupo no es null, se trata de una grupal
		if (coordinador.getGrupo() != null) {
			temporalConversacion=mensjDao.obtenerConversacionGrupo(coordinador.getGrupo());
			return temporalConversacion.getMessages();
		} else {
			ArrayList<Usuario> conversantes = new ArrayList<Usuario>();
			conversantes.add(coordinador.getUsuario());
			conversantes.add(coordinador.getConversacion().getReceiver());
			temporalConversacion=mensjDao.obtenerConversacion(conversantes);
			return temporalConversacion.getMessages();
		}
	}
	
	public ArrayList<Integer> obtenerValoracionesUsuario(Usuario usuario){
		ValoracionDao valoracionDao = new ValoracionDao();
		return valoracionDao.obtenerValoracionesUsuario(usuario);
	}
	
	public void insertarInforme(Usuario usuario, String textoInforme) {
		InformeDao informeDao = new InformeDao();
		informeDao.insertarInforme(usuario, textoInforme);
	}
}
