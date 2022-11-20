package maristmessage.controlador;

import java.util.ArrayList;
import java.util.HashMap;

import maristmessage.modelo.Logica;
import maristmessage.modelo.vo.Conversacion;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.vo.Mensaje;
import maristmessage.modelo.vo.Usuario;
import maristmessage.vista.*;

/**
 * Clase que coordina las ventanas
 * 
 * @author Raúl González
 *
 */
public class Coordinador {
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
	
	/*
	 * Logica
	 */
	Logica logica;
	
	public Logica getLogica() {
		return logica;
	}
	public void setLogica(Logica logica) {
		this.logica = logica;
	}
	
	/*
	 * Usuario que ha iniciado sesion
	 */
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/*
	 * Nombre del grupo actual
	 */
	private Grupo grupo;
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	/*
	 * Conversacion actual
	 */
	private Conversacion conversacion;
	
	public Conversacion getConversacion() {
		return conversacion;
	}
	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}
	
	/*
	 * Getter y setter de las ventanas
	 */
	public IniciarSesion getvIniciarSesion() {
		return vIniciarSesion;
	}
	public void setvIniciarSesion(IniciarSesion vIniciarSesion) {
		this.vIniciarSesion = vIniciarSesion;
	}
	public Principal getvPrincipal() {
		return vPrincipal;
	}
	public void setvPrincipal(Principal vPrincipal) {
		this.vPrincipal = vPrincipal;
	}
	public SeleccionarUsuario getvSeleccionarUsuario() {
		return vSeleccionarUsuario;
	}
	public void setvSeleccionarUsuario(SeleccionarUsuario vSeleccionarUsuario) {
		this.vSeleccionarUsuario = vSeleccionarUsuario;
	}
	public EnviarMensaje getvEnviarMensaje() {
		return vEnviarMensaje;
	}
	public void setvEnviarMensaje(EnviarMensaje vEnviarMensaje) {
		this.vEnviarMensaje = vEnviarMensaje;
	}
	public ValorarMensaje getvValorarMensaje() {
		return vValorarMensaje;
	}
	public void setvValorarMensaje(ValorarMensaje vValorarMensaje) {
		this.vValorarMensaje = vValorarMensaje;
	}
	public SeleccionarUsuarioMul getvSeleccionarUsuarioMul() {
		return vSeleccionarUsuarioMul;
	}
	public void setvSeleccionarUsuarioMul(SeleccionarUsuarioMul vSeleccionarUsuarioMul) {
		this.vSeleccionarUsuarioMul = vSeleccionarUsuarioMul;
	}
	public MensajeGrupal getvMensajeGrupal() {
		return vMensajeGrupal;
	}
	public void setvMensajeGrupal(MensajeGrupal vMensajeGrupal) {
		this.vMensajeGrupal = vMensajeGrupal;
	}
	public EnviarMensajeGlobal getvEnviarMensajeGlobal() {
		return vEnviarMensajeGlobal;
	}
	public void setvEnviarMensajeGlobal(EnviarMensajeGlobal vEnviarMensajeGlobal) {
		this.vEnviarMensajeGlobal = vEnviarMensajeGlobal;
	}
	public CrearUsuario getvCrearUsuario() {
		return vCrearUsuario;
	}
	public ModificarUsuario getvModificarUsuario() {
		return vModificarUsuario;
	}
	public void setvCrearUsuario(CrearUsuario vCrearUsuario) {
		this.vCrearUsuario = vCrearUsuario;
	}
	public void setvModificarUsuario(ModificarUsuario vModificarUsuario) {
		this.vModificarUsuario = vModificarUsuario;
	}
	public EliminarUsuario getvVentanaElimina() {
		return vVentanaElimina;
	}
	public void setvVentanaElimina(EliminarUsuario vVentanaElimina) {
		this.vVentanaElimina = vVentanaElimina;
	}
	
	public Ayuda getvAyuda() {
		return vAyuda;
	}
	public void setvAyuda(Ayuda vAyuda) {
		this.vAyuda = vAyuda;
	}
	
	public Informes getvInformes() {
		return vInformes;
	}
	
	public void setvInformes(Informes vInformes) {
		this.vInformes = vInformes;
	}
	
	/*
	 *  Métodos setVisible de las ventanas
	 */
	public void mostrarVentanaLogin() {
		vIniciarSesion.setVisible(true);
	}
	public void mostrarVentanaPrincipal() {
		vPrincipal.setVisible(true);
		vIniciarSesion.setVisible(false);
	}
	public void mostrarVentanaSeleccUser() {
		vSeleccionarUsuario.setVisible(true);
		vPrincipal.setVisible(false);
	}
	public void mostrarVentanaSeleccMultiUser() {
		vSeleccionarUsuarioMul.setVisible(true);
		vPrincipal.setVisible(false);
	}
	public void mostrarVentanaEnviarMensaje() {
		vSeleccionarUsuario.setVisible(false);
		vEnviarMensaje.setVisible(true);
	}
	public void mostrarVentanaEnviarMensajeGrupal() {
		vSeleccionarUsuarioMul.setVisible(false);
		vMensajeGrupal.setVisible(true);
	}
	public void mostrarVentanaEnviarMensajeGlobal() {
		vEnviarMensajeGlobal.setVisible(true);
		vPrincipal.setVisible(false);
	}
	public void mostrarVentanaValor() {
		vValorarMensaje.setVisible(true);
		vEnviarMensaje.setVisible(false);
		vMensajeGrupal.setVisible(false);
	}
	
	public void mostrarVentanaCrearUsuario() {
		vCrearUsuario.setVisible(true);
		vPrincipal.setVisible(false);
		
	}
	public void mostrarVentanaModificarUsuario() {
		vModificarUsuario.setVisible(true);
		vPrincipal.setVisible(false);
	}
	public void mostrarVentanaEliminarUsuario() {
		vVentanaElimina.setVisible(true);
		vPrincipal.setVisible(false);
	}
	public void mostrarVentanaAyuda() {
		vAyuda.setVisible(true);
	}
	
	public void mostrarVentanaInformes() {
		vInformes.setVisible(true);
		vPrincipal.setVisible(false);
	}
	
	/*
	 * Métodos operativos de la aplicación
	 */
	public void registrarUsuario(Usuario usuario) {
		logica.validarRegistro(usuario);
	}
	public void modificarUsuario(Usuario usuario) {
		logica.validarModificacion(usuario);
	}
	public void eliminarUsuario(Usuario usuario) {
		logica.validarBorrado(usuario);
	}
	public Usuario buscarUsuario(Usuario usuario) {
		return logica.validarBusqueda(usuario);
	}
	public void cerrarVentanaEliminaAPrincipal() {
		vVentanaElimina.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaModificaAPrincipal() {
		vModificarUsuario.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaCrearAPrincipal() {
		vCrearUsuario.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaSeleccionUserAPrincipal() {
		vSeleccionarUsuario.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaSeleccionUsersAPrincipal() {
		vSeleccionarUsuarioMul.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaMensajeIndAPrincipal() {
		vEnviarMensaje.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaMensajeGrupAPrincipal() {
		vMensajeGrupal.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaMensajeGlobalAPrincipal() {
		vEnviarMensajeGlobal.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaValorarAPrincipal() {
		vValorarMensaje.setVisible(false);
		vPrincipal.setVisible(true);
	}
	public void cerrarVentanaPrincipalAIniciarSesion() {
		vPrincipal.setVisible(false);
		vIniciarSesion.setVisible(true);
	}
	public void iniciarSesion(Usuario usuario) {
		logica.validarInicioSesion(usuario);
	}
	public void cerrarSesion() {
		logica.validarCierreSesion();
	}
	
	public void crearCopiaBase() {
		logica.crearCopiaBD();
	}
	
	public void enviarMensaje(String cadena) {
		logica.enviarMensaje(usuario,cadena);
	}
	
	public void enviarMensajeGlobal(String cadena) {
		logica.enviarMensajeGlobal(cadena);
	}
	
	public ArrayList<Usuario> obtenerListaUsuarios(){
		return logica.obtenerListaUsuarios();
	}
	public void valorarMensaje(Usuario usuarioValorado, int it) {
		logica.validarValoracionMensaje(usuarioValorado, it);
	}
	
	public void crearGrupo(ArrayList<String> listaUsuarios, String nombreGrupo) {
		logica.validarCrearGrupo(listaUsuarios, nombreGrupo); 
	}
	
	// TODO: cuando funcione, eliminar
	/*public void crearGrupoDos(String nombreOtroUser) {
		ArrayList<String> listaUsuarios = new ArrayList<String>();
		listaUsuarios.add(usuario.getNombreUsuario());
		listaUsuarios.add(nombreOtroUser);
		String nombreGrupo = usuario.getNombreUsuario()+"-"+nombreOtroUser;
		logica.validarCrearGrupo(listaUsuarios, nombreGrupo); 
	}*/
	
	// TODO: nuevo metodo
	public void iniciarConversacion(Usuario receptor) {
		logica.verificarConversacion(receptor);
	}
	
	public HashMap<Integer, String> verGrupos(){
		return logica.verGrupos(usuario);
	}
	public ArrayList<Usuario> getListaUsuariosSinValorar() {
		return logica.validarUsuariosSinValorar();
	}
	
	public ArrayList<Mensaje> obtenerMensajesConversacion(){
		return logica.obtenerMensajesConversacion();	
	}
	
	public ArrayList<Mensaje> obtenerMensajesGlobales(){
		return logica.obtenerMensajesGlobales();
	}
	
	public ArrayList<Integer> obtenerValoracionesUsuario(Usuario usuario){
		return logica.obtenerValoracionesUsuario(usuario);
	}
	public void cerrarVentanaInformeAPrincipal() {
		// TODO Auto-generated method stub
		vInformes.setVisible(false);
		vPrincipal.setVisible(true);
	}
	
	public void insertarInforme(Usuario usuario, String textoInforme) {
		logica.insertarInforme(usuario, textoInforme);
	}
}
