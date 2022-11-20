package maristmessage.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import maristmessage.modelo.conexion.Conexion;
import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Usuario;

/**
 * 
 */
public class ValoracionDao implements IValoracion {
	public void setValoracion(Conversacion conversacion, Usuario usuarioQueValora, Usuario usuarioValorado,
			int valoracion) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();

			estatuto.executeUpdate(
					"INSERT INTO Valoraciones (idConversacion, usuarioValorado, usuarioQueValora, valoracion) VALUES ("
							+ conversacion.getIdConversacion() + ", '" + usuarioValorado.getNombreUsuario() + "', '"
							+ usuarioQueValora.getNombreUsuario() + "', " + valoracion + ")");
			JOptionPane.showMessageDialog(null, "Valoracion enviada", "Info", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		} catch (SQLException e) {
			System.out.println();
			JOptionPane.showMessageDialog(null, "La valoracion no ha sido enviada: " + e.getMessage());
		}
	}

	public int getValoracion(Conversacion conversacion, Usuario usuarioQueValora, Usuario usuarioValorado) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();

			ResultSet resultado = estatuto
					.executeQuery("SELECT valoracion FROM Valoraciones WHERE idConversacion = '"
					+ conversacion.getIdConversacion() + "' AND usuarioValorado = '"
					+ usuarioValorado.getNombreUsuario() + "' AND usuarioQueValora = '" + usuarioQueValora + "';");
			estatuto.close();
			return resultado.getInt("valoracion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "La valoracion no ha sido encontrada: " + e.getMessage());
		}
		return 0;
	}

	public ArrayList<Usuario> getUsuariosSinValorar(Conversacion conversacion, Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		ArrayList<Usuario> losUsuarios = new ArrayList<Usuario>();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet resultado;
			
			// Obtener usuarios de la conversacion
			// Si no es un grupo
			if (conversacion.getIdGrupo() < 1) {
				resultado = estatuto
						.executeQuery("SELECT idUsuario FROM UsuConversaciones WHERE idConversacion = '"
						+ conversacion.getIdConversacion() + "';");
			} else { // es un grupo
				resultado = estatuto
						.executeQuery("SELECT idUsuario FROM UsuGrupos WHERE idGrupo = '"
						+ conversacion.getIdGrupo() + "';");
			}
			
			// se anaden al arraylist
			while (resultado.next()) {
				Usuario user = new Usuario();
				user.setNombreUsuario(resultado.getString("idUsuario"));
				// Si el usuario es el que ha iniciado sesion no se anade
				if (!user.getNombreUsuario().equals(usuario.getNombreUsuario())) {
					losUsuarios.add(user);
				}
			}
			
			// se obtienen los usuarios valorados y se les quita de la lista
			resultado = estatuto
					.executeQuery("SELECT usuarioValorado FROM Valoraciones WHERE idConversacion = "
					+ conversacion.getIdConversacion() + " AND usuarioQueValora='" + usuario.getNombreUsuario() + "';");
	

			while(resultado.next()) {
				String usuarioValorado = resultado.getString("usuarioValorado");
				// Borrar si el nombre de usuario coincide
				losUsuarios.removeIf(usuarioComprobado -> usuarioComprobado.getNombreUsuario().equals(usuarioValorado));
			}
			estatuto.close();
			
			return losUsuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "La valoracion no ha sido encontrada");
		}
		return losUsuarios;
	}
	
	public ArrayList<Integer> obtenerValoracionesUsuario(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		ArrayList<Integer> valoracionesUsuario = new ArrayList<Integer>();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet resultado = estatuto
					.executeQuery("SELECT valoracion FROM Valoraciones WHERE usuarioValorado='" + usuario.getNombreUsuario() + "'");

			// Se guarda lo encontrado en el objeto usuario
			int val=0;
			while (resultado.next()) {	
				val=resultado.getInt("valoracion");
				valoracionesUsuario.add(val);
			}
			
			estatuto.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se han encontrado valoraciones");
		}
		return valoracionesUsuario;
	}
}
