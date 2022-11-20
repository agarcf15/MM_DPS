package maristmessage.vista;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Grupo;
import maristmessage.modelo.vo.Mensaje;
import maristmessage.modelo.vo.Permiso;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	static IniciarSesion frame;
	private JPanel contentPane;
	private Coordinador coordinadorVentana;
	private ArrayList<String> lista1=new ArrayList<String>();
	//private JMenuItem mntmVerInforme = new JMenuItem("Ver Informe");
	//private JMenuItem mntmGenerarInforme = new JMenuItem("Generar Nuevo Informe");
	private JMenuItem mntmInformes = new JMenuItem("Informes");
	private JMenuItem mntmCopiaDeLa = new JMenuItem("Copia de la Base de Datos");
	private JMenuItem mntmEliminar = new JMenuItem("Eliminar");
	private JMenuItem mntmCrear = new JMenuItem("Crear");
	private JMenuItem mntmModificar = new JMenuItem("Modificar");
	private JMenuItem mntmEnviarMensaje = new JMenuItem("Enviar Mensaje");
	private JMenuItem mntmEnviarMensajeGrupal = new JMenuItem("Enviar Mensaje Grupal");
	private JMenuItem mntmEnviarMensajeGlobal = new JMenuItem("Enviar Mensaje Global");
	private JMenu mntmGestionDeUsuarios = new JMenu("Gestión de Usuarios");
	private JMenu mnOpciones = new JMenu("Opciones");
	private JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
	/**
	 * Create the frame.
	 */
	public Principal() {
		
		setTitle("MaristMessage - Menú Principal"/*+coordinadorVentana.getUsuario().getNombre()*/);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/CapturaI.PNG"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		

		menuBar.add(mnOpciones);
		mnOpciones.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				System.out.print("Cansino");
				visibilidadFunciones();
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				System.out.print("Cansino de");
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				System.out.print("Cansino de verdad");
			}

		});

		mntmEnviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se va a enviar un mensaje privado, por lo que no se va a crear un grupo
				coordinadorVentana.setGrupo(null);
				coordinadorVentana.mostrarVentanaSeleccUser();
			}
		});

		mntmEnviarMensajeGrupal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se va a enviar un mensaje de grupo, por lo que se instancia un grupo
				// Luego se le pondra un nombre
				coordinadorVentana.setGrupo(new Grupo());
				coordinadorVentana.mostrarVentanaSeleccMultiUser();
			}
		});
		
		
		mntmEnviarMensajeGlobal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*dispose();
				EnviarMensajeGlobal vG = new EnviarMensajeGlobal();
				vG.setVisible(true);*/
				coordinadorVentana.mostrarVentanaEnviarMensajeGlobal();
			}
		});
		
		//JSeparator separator = new JSeparator();
		
		
		/*JMenuItem mntmVerMensajesGlobales = new JMenuItem("Ver Mensajes Globales");
		mntmVerMensajesGlobales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnOpciones.add(mntmVerMensajesGlobales);*/
		
		JSeparator separator_1 = new JSeparator();
		mnOpciones.add(separator_1);	
		
		
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinadorVentana.mostrarVentanaCrearUsuario();
			}
		});
		
		
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinadorVentana.mostrarVentanaModificarUsuario();
			}
		});

		
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinadorVentana.mostrarVentanaEliminarUsuario();
			}
		});

		mntmInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinadorVentana.mostrarVentanaInformes();
			}
		});
		
		mntmCopiaDeLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCopiaBase();
			}
		});

		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showConfirmDialog(null, "¿Quiere volver a iniciar sesión?");
				
				cerrarSesion(seleccion);
			}
		});	
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic(KeyEvent.VK_F1);
		menuBar.add(mnAyuda);
		
		JMenuItem mntmMostrarAyuda = new JMenuItem("Mostrar ayuda");
		mntmMostrarAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coordinadorVentana.mostrarVentanaAyuda();
			}
		});
		mnAyuda.add(mntmMostrarAyuda);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79,98,155));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][]", "[][grow][]"));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("res/Capturaa.PNG"));
		contentPane.add(label, "cell 3 0,alignx center,aligny center");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 3 1,grow");
		
		JScrollPane panelGrupos = new JScrollPane();
		tabbedPane.addTab("Ver grupos", null, panelGrupos, null);
		
		DefaultListModel<String> listaGrupos = new DefaultListModel<String>();
		JList<String> listGrupos = new JList<String>(listaGrupos);
		listGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Doble click, iniciar conversacion de grupo
				if (arg0.getClickCount() == 2) {
					// Guardamos id de grupo
					Grupo grupo = new Grupo();
					grupo.setNombre(listGrupos.getSelectedValue().split(": ")[1]);
					coordinadorVentana.setGrupo(grupo);
					// Iniciamos conversacion (o recuperamos).. null significa que es grupo
		    		coordinadorVentana.iniciarConversacion(null);
		    		
		    		//Abrimos la ventana de envia 
		    		coordinadorVentana.mostrarVentanaEnviarMensaje();
				}
			}
		});
		panelGrupos.setViewportView(listGrupos);
		
		JScrollPane panelMsjGlobal = new JScrollPane();
		tabbedPane.addTab("Ver mensajes globales", null, panelMsjGlobal, null);
		
		DefaultListModel<String> listaMsjG = new DefaultListModel<String>();
		JList<String> listMsjGlobal = new JList<String>(listaMsjG);
		panelMsjGlobal.setViewportView(listMsjGlobal);
		
		JButton btnActualizarPestaas = new JButton("Actualizar pestañas");
		btnActualizarPestaas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaGrupos.clear();
				HashMap<Integer, String> grupos = coordinadorVentana.verGrupos();
				for (Entry<Integer, String> grupo : grupos.entrySet()) {
					listaGrupos.addElement(grupo.getKey() + ": " + grupo.getValue());
				} 
				
				listaMsjG.clear();
				ArrayList<Mensaje> mensajes = coordinadorVentana.obtenerMensajesGlobales();
				for (Mensaje mensaje : mensajes) {
					listaMsjG.addElement(mensaje.getMessage());
				}
			}
		});
		contentPane.add(btnActualizarPestaas, "cell 3 2,growx");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	protected void crearCopiaBase() {
		// TODO Auto-generated method stub
		coordinadorVentana.crearCopiaBase();
	}

	public void visibilidadFunciones() {
		ArrayList<Permiso> lista=coordinadorVentana.getUsuario().getRol().getPermissions();
		for(Permiso e : lista) {
			lista1.add(e.getName());
		}
		//Se puede borrar esta solo para probar
		System.out.println(lista1);
		for(String e:lista1) {
			if(e.matches("Enviar Mensaje")) {
				mnOpciones.add(mntmEnviarMensaje);
			}
			else if(e.matches("Enviar Mensaje Global")) {
				mnOpciones.add(mntmEnviarMensajeGlobal);
			}
			else if(e.matches("Crear Grupo")) {
				mnOpciones.add(mntmEnviarMensajeGrupal);
			}
			else if(e.matches("Gestion de Usuarios")) {
				mnOpciones.add(mntmGestionDeUsuarios);
				mntmGestionDeUsuarios.add(mntmCrear);
				mntmGestionDeUsuarios.add(mntmModificar);
				mntmGestionDeUsuarios.add(mntmEliminar);	
			}
			else if(e.matches("Informes")) {
				//mnOpciones.add(mntmVerInforme);
				//mnOpciones.add(mntmGenerarInforme);
				mnOpciones.add(mntmInformes);
			}
			else if(e.matches("Base de datos")) {
				mnOpciones.add(mntmCopiaDeLa);
			}
		}
		mnOpciones.add(mntmCerrarSesion);
	}
	
	public void cerrarSesion(int seleccion) {
		// Valorar
		if (seleccion == 0) {
			coordinadorVentana.cerrarSesion();
			coordinadorVentana.cerrarVentanaPrincipalAIniciarSesion();
		}
		// Salir
		if (seleccion == 1) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
	}
	public void confirmarExit() {
		// TODO Auto-generated method stub
		int seleccion = JOptionPane.showOptionDialog( null,"¿Esta seguro de que desea salir e iniciar sesion de nuevo?",
				  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				  new Object[] { "Si", "No" },"opcion 1");
				 //Valorar
				 if (seleccion == 0){
					coordinadorVentana.cerrarVentanaPrincipalAIniciarSesion();
				 }
				 //Salir
				 if (seleccion == 1){
					 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				 }	
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
