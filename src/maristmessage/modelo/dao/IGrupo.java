package maristmessage.modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.vo.Usuario;



/**
 * Interfaz para los métodos DAO de Grupos
 * 
 * @author Raúl Seara
 * @author Raúl González
 *
 */
public interface IGrupo {
	/**
	 * Crea un nuevo grupo en la base de datos
	 * 
	 * @param lista de usuarios del grupo y objeto grupo
	 * 
	 */
	public Conversacion crearGrupo(ArrayList<Usuario> listaMiembros, Grupo grupo);

	/**
	 * Busca un grupo y si existe lo devuelve
	 * 
	 * @param nombre del grupo
	 * @return objeto de grupo con su nombre
	 * 
	 */
	public Grupo buscarGrupo(Grupo grupo);
	
	/**
	 * Busca los grupos del usuario recibido
	 * 
	 * @param nombre del grupo
	 * @return objeto de grupo con su nombre
	 * 
	 */
	public HashMap<Integer, String> verGrupos(Usuario usuario);
}
