package maristmessage.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import maristmessage.modelo.conexion.Conexion;
import maristmessage.modelo.vo.Usuario;
import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;


public class GrupoDao implements IGrupo{
	public Conversacion crearGrupo(ArrayList<Usuario> listaMiembros, Grupo grupo) {
		Conexion conex = Conexion.getInstancia();
		int idGrupo = 0;
		Conversacion nueva = new Conversacion(grupo.getNombre());
		try {
			Statement estatuto = conex.getConnection().createStatement();
			
			// Guardar nombre del grupo
			String nombreGrupo = grupo.getNombre();
			
			// Si existe el grupo, se crea uno nuevo
			if (buscarGrupo(grupo).getNombre()==null) {
				estatuto.executeUpdate("INSERT INTO Grupos (nombreGrupo) VALUES ('" + nombreGrupo + "')");
				grupo.setNombre(nombreGrupo);
			}
			
			// Se obtiene el id del grupo
			ResultSet resultado = estatuto.executeQuery("SELECT idGrupo FROM Grupos WHERE nombreGrupo='" + grupo.getNombre() + "'");
			
			//Primero: insertar el grupo en la base
			//Insertar el nombre del grupo + cada miembro 
			if (resultado.next()) {
				idGrupo = resultado.getInt("idGrupo");
				for(Usuario e: listaMiembros) {
					estatuto.executeUpdate("INSERT INTO UsuGrupos VALUES (" + idGrupo + ", '"
							+ e.getNombreUsuario()+"')");	
				}
			}
			estatuto.close();
			
			JOptionPane.showMessageDialog(null, "El grupo ha sido creado", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			//ahora se seguiria con la conversacion 
			nueva.setIdGrupo(idGrupo);
			MensajeDao mensajeDao = new MensajeDao();
			mensajeDao.crearConversacionGrupo(nueva);
			
			//REVISAR
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El grupo no ha sido creado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return nueva;
	}

	public Grupo buscarGrupo(Grupo grupo) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet resultado = estatuto.executeQuery("SELECT * FROM Grupos WHERE nombreGrupo='" + grupo.getNombre() + "'");

			// Se guarda lo encontrado en el objeto grupo
			if (!resultado.next()) {
				grupo.setNombre(null);
			}

			estatuto.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El grupo no ha sido encontrado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return grupo;
	}
	
	public HashMap<Integer, String> verGrupos(Usuario usuario){
		HashMap<Integer, String> listaGrupos = new HashMap<Integer, String>();
		
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			Statement estatuto2 = conex.getConnection().createStatement();
			
			//Buscamos la ID de los grupos a los que pertenece el usuario
			ResultSet resultado = estatuto.executeQuery("SELECT idGrupo FROM UsuGrupos WHERE idUsuario='" + usuario.getNombreUsuario() + "'");

			//De todos esos grupos encontrados, obtenemos el nombre
			while(resultado.next()) {
				int numGrupo = resultado.getInt("idGrupo");
				ResultSet nombreGrupo = estatuto2.executeQuery("SELECT nombreGrupo FROM Grupos WHERE idGrupo='" + numGrupo + "'");
				if (nombreGrupo.next()) {
					//Añadimos el nombre del grupo a la lista
					listaGrupos.put(numGrupo, nombreGrupo.getString("nombreGrupo"));
				}
			}
		
			estatuto.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario no tiene grupos creados: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		return listaGrupos;
	}
}
