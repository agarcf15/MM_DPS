package maristmessage.modelo.dao;

import maristmessage.modelo.vo.Usuario;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import maristmessage.modelo.conexion.Conexion;
//import maristmessage.modelo.vo.Informe;

/**
 * 
 */
public class InformeDao implements IInforme {
	public void crearInforme(Usuario usuario) {
		
	}
	
	public void insertarInforme(Usuario usuario, String textoInforme) {
		Conexion conex = Conexion.getInstancia();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO Informes (idUsuario, informe) VALUES ('" + usuario.getNombreUsuario() 
						+ "', '" + textoInforme+ "')");
			estatuto.close();

			JOptionPane.showMessageDialog(null, "El informe se ha guardado", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El informe no se ha guardado: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
