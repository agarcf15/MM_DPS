package maristmessage.modelo.dao;

import maristmessage.modelo.vo.Usuario;
//import maristmessage.modelo.vo.Informe;


/**
 * Interfaz para la creacion de informes
 * 
 * @author Raúl González
 *
 */
public interface IInforme {
	/**
	 * Se crea un informe para un usuario en especifico
	 * 
	 * @param usuario para cual se quiere crear el informe
	 * 
	 */
	public void insertarInforme(Usuario usuario, String textoInforme);
	
	/**
	 * Se devuelve un informe creado anteriormente
	 * 
	 * @param usuario del que se quiere obtener un informe
	 * @return informe generado
	 * 
	 */
	//public Informe obtenerInforme(Usuario usuario);
}
