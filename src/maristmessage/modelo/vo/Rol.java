package maristmessage.modelo.vo;

import java.util.ArrayList;

/**
 * Clase que guarda los permisos de un
 * rol determinado y su nombre
 * 
 * @author Raúl González
 *
 */
public class Rol {
	/**
	 * Nombre que identifica al rol
	 */
	private String name;
	
	/**
	 * Permisos que tiene el rol
	 */
	private ArrayList<Permiso> permissions;

	/**
	 * Constructor de rol que inicializa el
	 * arraylist
	 */
	public Rol() {
		permissions = new ArrayList<Permiso>();
	}
	/**
	 * Obtiene el nombre del rol
	 * 
	 * @return name Nombre del rol
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del rol
	 * 
	 * @param name Nombre del rol
	 */
	public void setName(String name) {
		this.name = name;
		
		// Establecer los permisos
		switch (name) {
		case "Profesor":
			this.addPermiso(new Permiso("Enviar Mensaje")); // incluye en grupos
			this.addPermiso(new Permiso("Calificar Conversacion"));
			break;
		case "Jefe de Departamento":
		case "Jefe de Estudios Infantil":
		case "Jefe de Estudios Primaria":
		case "Jefe de Estudios Secundaria y Bachillerato":
			this.addPermiso(new Permiso("Enviar Mensaje"));
			this.addPermiso(new Permiso("Calificar Conversacion"));
			this.addPermiso(new Permiso("Crear Grupo"));
			break;
		case "Administración":
		case "Secretaría":
			this.addPermiso(new Permiso("Enviar Mensaje"));
			this.addPermiso(new Permiso("Enviar Mensaje Global"));
			break;
		case "Director":
		case "Subdirector":
			this.addPermiso(new Permiso("Enviar Mensaje"));
			this.addPermiso(new Permiso("Enviar Mensaje Global"));
			this.addPermiso(new Permiso("Crear Grupo"));
			this.addPermiso(new Permiso("Gestion de Usuarios"));
			this.addPermiso(new Permiso("Informes"));
			this.addPermiso(new Permiso("Base de datos"));
			break;
		default:
			break;
		}
	}
	
	public void addPermiso(Permiso permiso) {
		permissions.add(permiso);
	}

	/**
	 * Obtiene la lista de permisos que tiene asignado
	 * determinado rol
	 * 
	 * @return permissions Array con permisos
	 */
	public ArrayList<Permiso> getPermissions() {
		return permissions;
	}
}
