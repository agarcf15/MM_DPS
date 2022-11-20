package maristmessage.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite crear la conexión hacia la base de datos
 * 
 * @author Raúl González
 *
 */
public class Conexion {
	/**
	 * Instancia para el uso del patron Singleton
	 */
	private static Conexion instancia;
	
	/**
	 * Url de la base de datos MySQL
	 */
	private String url = "jdbc:mysql://db4free.net:3306/maristmessage_bb?autoReconnect=true";

	/**
	 * Usuario de la base de datos
	 */
	private String user = "maristmessage";

	/**
	 * Contraseña de la base de datos
	 */
	private String password = "h7Hdb3Jkr";

	/**
	 * Objeto donde se va a guardar la conexión
	 */
	private Connection c;

	/**
	 * Constructor de Conexion
	 */
	public Conexion() {
		// Cargar el controlador (se registra solo)
		try {
			c = DriverManager.getConnection(url, user, password);
			System.out.println("¡Conexion exitosa a la base de datos!");
		} catch (SQLException e) {
			System.err.println("Error conectando a la base de datos: " + e);
		}
	}
	
	/**
	 * Devuelve la instancia del patron singleton
	 * 
	 * @return instancia de conexion
	 */
	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	/**
	 * Devuelve el objeto de la conexión
	 * 
	 * @return c conexión
	 */
	public Connection getConnection() {
		return c;
	}

	/**
	 * Cierra la conexión
	 * 
	 * @return true Si se ha cerrado la conexion O false Si no se ha cerrado la
	 *         conexion por algun error
	 */
	public boolean closeConnection() {
		try {
			c.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Error cerrando la conexion a la base de datos: " + e);
			return false;
		}
	}
}
