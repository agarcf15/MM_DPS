package maristmessage.vista;

import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ListSelectionListener;

import maristmessage.controlador.Coordinador;

import javax.swing.event.ListSelectionEvent;

public class Ayuda extends JDialog {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador coordinadorVentana;
	private JTextField textFieldBuscar;

	/**
	 * Array con los elementos de la ayuda
	 */
	final static ArrayList<String> elementos = new ArrayList<String>(
			Arrays.asList("Presentación", "Inicio de sesión", "Cierre de sesión", "Seleccion Usuario", "Seleccion Usuarios", "Enviar Mensaje","Enviar Mensaje Grupal","Enviar Mensaje Global"
					,"Gestion de Usuarios","Crear Usuario","Modificar Usuario","Eliminar Usuario","Informe","Copia Base de Datos"));

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setTitle("MaristMessage - Ayuda");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][grow]"));

		JLabel lblBsqueda = new JLabel("Búsqueda:");
		getContentPane().add(lblBsqueda, "cell 0 0");

		JScrollPane scrollPaneBuscar = new JScrollPane();
		getContentPane().add(scrollPaneBuscar, "cell 0 1,grow");

		JList<String> listAyudas = new JList<String>();

		DefaultListModel<String> lista = new DefaultListModel<String>();
		for (String e : elementos) {
			lista.addElement(e);
		}
		listAyudas.setModel(lista);
		listAyudas.setBackground(new Color(79, 98, 155));
		scrollPaneBuscar.setViewportView(listAyudas);

		textFieldBuscar = new JTextField();
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				lista.removeAllElements();
				for (String e : elementos) {
					if (e.toLowerCase().contains(textFieldBuscar.getText().toLowerCase())) {
						lista.addElement(e);
					}
				}
			}
		});
		scrollPaneBuscar.setColumnHeaderView(textFieldBuscar);
		textFieldBuscar.setColumns(10);

		JScrollPane scrollPaneAyuda = new JScrollPane();
		getContentPane().add(scrollPaneAyuda, "cell 1 1,grow");

		JTextPane txtAyuda = new JTextPane();
		txtAyuda.setBackground(Color.LIGHT_GRAY);
		txtAyuda.setText(
				"Bienvenido a la ayuda de MaristMessage.\r\nPuedes elegir o buscar un tema en el menu de la izquierda.");
		scrollPaneAyuda.setViewportView(txtAyuda);

		listAyudas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String textoAyuda = "";
				String ayudaSeleccionada = listAyudas.getSelectedValue();
				// Aqui es donde se van a guardar los textos de ayuda para cada menu
				// TODO: Se podria mover a un metodo del coordinador, algo como coordinador.getAyuda(String);
				//"Selecion Usuario", "Seleccion Usuarios", "Enviar Mensaje","Enviar Mensaje Grupal","Enviar Mensaje Global"
				//"Gestion de Usuarios","Crear Usuario","Modificar Usuario","Eliminar Usuario","Generar Informe","Ver Informe","Copia Base de Datos"
				if (ayudaSeleccionada != null) {
					switch (ayudaSeleccionada) {
					case "Presentación":
						textoAyuda = "Bienvenido a la ayuda de MaristMessage.\r\nPuedes elegir o buscar un tema en el menu de la izquierda.";
						break;
						
					case "Inicio de sesión":
						textoAyuda = //"Texto de ayuda para inicio de sesión.\r\n"
								"Para iniciar correctamente sesion tendra que introducir un nombre de usuario\r\n"
								+ "y una contrasena que sean validos. Si se introduce uno de los 2 que sea incorecto, aparecera una ventana emergente que notificara\r\n"
								+ "de que vuelva a introducir estos datos\r\nUna vez que se introduzcan los casos correctos se procederá a la pantalla principal\r\n"
								+ "del usuario que se haya logueado en la aplicacion";
						break;
						
					case "Cierre de sesión":
						textoAyuda = //"Texto de ayuda para cierre de sesión\r\n
								"Una vez que se haya pulsado en esta opcion se le preguntara al usuario si desea volver a iniciar sesion\r\n"
								+"Si la respuesta es afirmativa se devolvera al user a la pantalla de logueo, si no es así, se pasara al cierre de la aplicacion";
						break;
						
					case "Seleccion Usuario":
						textoAyuda = //"Texto de ayuda para Seleccion Usuario\r\n
								"En este caso solo se podrá seleccionar un usuario como minimo y maximo. Bastara con pulsar encima\r\n"
								+ "del usuario con el que queramos hablar o iniciar la conversacion. Bastara con darle a empezar conversacion para que nos lleve a la siguiente ventana.\r\n"
								+ "Si no aparecen usuario, pulsando el boton recargar, apareceran los usuarios";
						break;
						
					case "Seleccion Usuarios":
						textoAyuda = //"Texto de ayuda para Seleccion Usuario\r\n
								"En este caso solo se podrá seleccionar un grupo de usuarios. Bastara con pulsar encima\r\n"
								+ "de los usuarios con los que queramos hablar o iniciar la conversacion. Se obliga a poner un nombre al grupoBastara con darle a empezar conversacion para que nos lleve a la siguiente ventana.\r\n"
								+ "Si no aparecen usuario, pulsando el boton recargar, apareceran los usuarios";
						break;
						
					case "Enviar Mensaje":
						textoAyuda = //"Texto de ayuda para Enviar Mensaje\r\n
								"Una vez que se haya pulsado en esta opcion aparecera una ventana con 2 areas de texto una no editable y la\r\n"
								+ "otra si sera editable, esta ultima es donde se redactara el mensaje y en el otro area solo se podrá visualizar lo que se ha escrito hasta el momento\r\n"
								+ "Luego esta el boton Valorar, con este se procede a valorar la conversacion actual. Se le da la opción al usuario de hacerla o no, si no realiza, se penalizará\r\n"
								+ "mediante la calificacion que se decida desde el colegio/instituto";
						break;
						
					case "Enviar Mensaje Grupal":
						textoAyuda = //"Texto de ayuda para Enviar Mensaje Grupal\r\n
								"Una vez que se haya pulsado en esta opcion aparecera una ventana con 2 areas de texto una no editable y la\r\n"
								+ "otra si sera editable, esta ultima es donde se redactara el mensaje y en el otro area solo se podrá visualizar lo que se ha escrito hasta el momento\r\n"
								+ "Luego esta el boton Valorar, con este se procede a valorar la conversacion actual. Se le da la opción al usuario de hacerla o no, si no realiza, se penalizará\r\n"
								+ "mediante la calificacion que se decida desde el colegio/instituto. La diferencia entre Mensaje y Mensaje Grupal esta en que la valoracion en un caso es a un usuario\r\n"
								+ "y en el otro es a varios usuarios, respectivamente";
						break;
						
					case "Enviar Mensaje Global":
						textoAyuda = //"Texto de ayuda para Enviar Mensaje\r\n
								"Una vez que se haya pulsado en esta opcion aparecera una ventana con un area de texto donde se podra escribir el mensaje\r\n"
								+ "que queramos enviar de manera global y un boton Enviar, con el cual el mensaje se mandará a todo usuario perteneciente a la base de datos";
						break;
						
					case "Gestion de Usuarios":
						textoAyuda = //"Texto de ayuda para Gestion de Usuarios\r\n
								"Hay 3 opciones: Crear, Modificar y Eliminar Uusario";
						break;
						
					case "Crear Usuario":
						textoAyuda = //"Texto de ayuda para Crear Usuario\r\n
								"Aparece una ventana con una serie de campos a rellenar por parte del usuario. Una vez que estén rellenos los campos, se dará a\r\n"
								+ "y se creará el usuario con los datos introducidos";
						break;
						
					case "Modificar Usuario":
						textoAyuda = //"Texto de ayuda para Modificacion\r\n
								"Aparece una ventana con una serie de campos a rellenar por parte del usuario. Una vez que estén rellenados los campos, se dará a\r\n" 
								+ "y se modificará el usuario elegido/buscado en el área de texto en la parte superior de la ventana, con los datos introducidos";
						break;
						
					case "Eliminar Usuario":
						textoAyuda = //"Texto de ayuda para Eliminar Usuario\r\n
								"Se buscará el usuario introducido y se eliminará cuando se pulse sobre el boton de dicha ventana";
						break;
						
					case "Informe":
						textoAyuda = //"Texto de ayuda para Generar Informe\r\n"
								"Como dice el nombre, se generará un informe con las valoraciones recibidas por cada user, se visualizara en la parte izquierda los usuarios de la base\r\n"
								+ "Hay dos botones Recargar Usuarios, por si de primeras los users no aparecen y el boton seleccionar User, con este boton y el usuario seleccionado se\r\n"
								+ "procedera a buscar en la base de datos donde";
						break;
						
					case "Copia Base de Datos":
						textoAyuda = //"Texto de ayuda para Copia Base de Datos\r\n
								"Creación de una copia de la base de datos de MaristMessage, en cuanto se pulsa se genera una copia de base en el ordenador";
						break;
						
					default:
						textoAyuda = "Texto de ayuda para selección errónea. Opcion Incorrecta";
						break;
					}
					txtAyuda.setText(textoAyuda);
				}
			}
		});
	}
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
