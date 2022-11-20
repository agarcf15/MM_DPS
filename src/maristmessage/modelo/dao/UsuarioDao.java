package maristmessage.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import maristmessage.modelo.conexion.Conexion;
import maristmessage.modelo.vo.Usuario;

/**
 * Clase creada para realizar las acciones de usuario siguiendo el patrón DAO
 * 
 * @author Raúl Seara
 * @author Raúl González
 * 
 */
public class UsuarioDao implements IUsuario {
	public void registrarUsuario(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO Usuarios VALUES ('" + usuario.getNombreUsuario() + "', '"
					+ usuario.getContrasenya() + "', '" + usuario.getNombre() + "', '" + usuario.getApellidos() + "')");
			estatuto.executeUpdate("INSERT INTO Roles VALUES ('" + usuario.getNombreUsuario() + "', '"
					+ usuario.getRol().getName() + "')");
			estatuto.close();

			JOptionPane.showMessageDialog(null, "Usuario registrado en el programa", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario no se ha registrado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void modificarUsuario(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();

			estatuto.executeUpdate("UPDATE Usuarios SET nombre = '" + usuario.getNombre() + "', apellidos = '"
					+ usuario.getApellidos() + "', contrasena = '" + usuario.getContrasenya() + "' WHERE idUsuario= '"
					+ usuario.getNombreUsuario() + "'");

			estatuto.executeUpdate("UPDATE Roles SET rol = '" + usuario.getRol().getName() + "' WHERE idUsuario= '"
					+ usuario.getNombreUsuario() + "'");

			estatuto.close();

			JOptionPane.showMessageDialog(null, "El usuario ha sido modificado correctamente", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario no ha sido modificado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void borrarUsuario(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			// Habria que eliminar mensajes, informes...
			// Se elimina primero el rol
			estatuto.executeUpdate("DELETE FROM Roles WHERE idUsuario='" + usuario.getNombreUsuario() + "'");
			// Luego el usuario
			estatuto.executeUpdate("DELETE FROM Usuarios WHERE idUsuario='" + usuario.getNombreUsuario() + "'");
			estatuto.close();
			JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario no ha sido eliminado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Usuario buscarUsuario(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet resultado = estatuto
					.executeQuery("SELECT * FROM Usuarios WHERE idUsuario='" + usuario.getNombreUsuario() + "'");

			// Se guarda lo encontrado en el objeto usuario
			while (resultado.next()) {
				usuario.setContrasenya(resultado.getString("contrasena"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
			}

			resultado = estatuto
					.executeQuery("SELECT * FROM Roles WHERE idUsuario='" + usuario.getNombreUsuario() + "'");

			// Se guarda lo encontrado en el objeto usuario
			while (resultado.next()) {
				usuario.setRol(resultado.getString("rol"));
			}

			estatuto.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El usuario no ha sido encontrado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return usuario;
	}

	public Usuario iniciarSesion(Usuario usuario) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();

			// Se guarda la contrasena obtenida en el formulario
			String contrasenya = usuario.getContrasenya();
			// Se obtiene todos los datos del usuario
			usuario = buscarUsuario(usuario);

			// Si no se ha encontrado el usuario (el nombre propio obtenido
			// es null) se devuelve el usuario como estaba (no ha cambiado)
			if (usuario.getNombre() != null) {
				// Si la contrasena no coincide se invalida y se devuelve el objeto usuario
				// con la contrasena 'null'
				if (!usuario.getContrasenya().equals(contrasenya)) {
					usuario.setContrasenya(null);
					JOptionPane.showMessageDialog(null, "La contraseña es incorrecta", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				usuario.setContrasenya(null);
			}
			estatuto.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fallo en el inicio de sesión: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return usuario;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			ResultSet resultado = estatuto.executeQuery("SELECT * FROM Usuarios");

			// Se guarda lo encontrado en el objeto usuario
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setNombreUsuario(resultado.getString("idUsuario"));
				usuario.setContrasenya(resultado.getString("contrasena"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
				listaUsuarios.add(usuario);
			}
			
			estatuto.close();			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "La lista de usuarios no se ha obtenido: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return listaUsuarios;
	}
}
