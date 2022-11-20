package maristmessage.controlador;

import maristmessage.modelo.Logica;
import maristmessage.vista.*;

/**
 * Clase que crea los objetos necesarios para su funcionamiento
 * 
 * @author Raúl González
 *
 */
public class Inicio {
	/**
	 * Coordinador de la aplicación
	 */
	private Coordinador coordinador;

	/**
	 * Lógica de la aplicación
	 */
	private Logica logica;

	/*
	 * Ventanas
	 */
	private IniciarSesion vIniciarSesion;
	private Principal vPrincipal;
	private SeleccionarUsuario vSeleccionarUsuario;
	private EnviarMensaje vEnviarMensaje;
	private ValorarMensaje vValorarMensaje;
	private SeleccionarUsuarioMul vSeleccionarUsuarioMul;
	private MensajeGrupal vMensajeGrupal;
	private EnviarMensajeGlobal vEnviarMensajeGlobal;
	private CrearUsuario vCrearUsuario;
	private ModificarUsuario vModificarUsuario;
	private EliminarUsuario vVentanaElimina;
	private Ayuda vAyuda;
	private Informes vInformes;

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Inicio inicio = new Inicio();
		inicio.iniciar();
	}

	/**
	 * Creacion de objetos
	 */
	private void iniciar() {
		// Instancias ventanas
		vIniciarSesion = new IniciarSesion();
		vPrincipal = new Principal();
		vSeleccionarUsuario = new SeleccionarUsuario();
		vEnviarMensaje = new EnviarMensaje();
		vValorarMensaje = new ValorarMensaje();
		vSeleccionarUsuarioMul = new SeleccionarUsuarioMul();
		vMensajeGrupal = new MensajeGrupal();
		vEnviarMensajeGlobal = new EnviarMensajeGlobal();
		vCrearUsuario = new CrearUsuario();
		vModificarUsuario = new ModificarUsuario();
		vVentanaElimina = new EliminarUsuario();
		vAyuda = new Ayuda();
		vInformes = new Informes();

		// Instancia coordinador y logica
		coordinador = new Coordinador();
		logica = new Logica();

		// Relaciones entre ventanas y coordinador
		vIniciarSesion.setCoordinador(coordinador);
		vPrincipal.setCoordinador(coordinador);
		vSeleccionarUsuario.setCoordinador(coordinador);
		vEnviarMensaje.setCoordinador(coordinador);
		vValorarMensaje.setCoordinador(coordinador);
		vSeleccionarUsuarioMul.setCoordinador(coordinador);
		vMensajeGrupal.setCoordinador(coordinador);
		vEnviarMensajeGlobal.setCoordinador(coordinador);
		vCrearUsuario.setCoordinador(coordinador);
		vModificarUsuario.setCoordinador(coordinador);
		vVentanaElimina.setCoordinador(coordinador);
		vAyuda.setCoordinador(coordinador);
		vInformes.setCoordinador(coordinador);
		
		// Relaciones del coordinador con las ventanas
		coordinador.setvIniciarSesion(vIniciarSesion);
		coordinador.setvPrincipal(vPrincipal);
		coordinador.setvSeleccionarUsuario(vSeleccionarUsuario);
		coordinador.setvEnviarMensaje(vEnviarMensaje);
		coordinador.setvValorarMensaje(vValorarMensaje);
		coordinador.setvSeleccionarUsuarioMul(vSeleccionarUsuarioMul);
		coordinador.setvMensajeGrupal(vMensajeGrupal);
		coordinador.setvEnviarMensajeGlobal(vEnviarMensajeGlobal);
		coordinador.setvCrearUsuario(vCrearUsuario);
		coordinador.setvModificarUsuario(vModificarUsuario);
		coordinador.setvVentanaElimina(vVentanaElimina);
		coordinador.setvAyuda(vAyuda);
		coordinador.setvInformes(vInformes);
		
		// Logica en el coordinador
		coordinador.setLogica(logica);
		
		// Coordinador en la logica
		logica.setCoordinador(coordinador);
		
		// Mostrar la ventana de inicio de sesion
		vIniciarSesion.setVisible(true);
	}
}
