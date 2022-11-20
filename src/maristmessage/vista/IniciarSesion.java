package maristmessage.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class IniciarSesion extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	static IniciarSesion frame;
	private JPanel pane;
	private JTextField userField;
	private JPasswordField passField;

	private Coordinador coordinadorVentana;

	/**
	 * Create the frame.
	 */
	public IniciarSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/CapturaI.PNG"));
		setTitle("MaristMessage - Inicio de Sesión");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 640, 340);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
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
		
		pane = new JPanel();
		pane.setBackground(new Color(79,98,155));
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pane);
		pane.setLayout(new MigLayout("", "[][][][grow][][][]", "[][][][][][][][]"));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("res/Capturaa.PNG"));
		pane.add(logo, "cell 3 0,alignx center,aligny center");
		
		JLabel lblIniciarSesion = new JLabel("Iniciar sesión");
		lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pane.add(lblIniciarSesion, "cell 3 1,alignx center");
		
		JLabel lblUsuario = new JLabel("Usuario:");
		pane.add(lblUsuario, "cell 3 2");
		
		userField = new JTextField();
		pane.add(userField, "cell 3 3,growx");
		userField.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		pane.add(lblContrasena, "cell 3 4");
		
		passField = new JPasswordField();
		pane.add(passField, "cell 3 5,growx");
		passField.setColumns(10);
		
		JButton btnIniciarSesin = new JButton("Iniciar sesión");
	/*	btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Bienvenido " + userField.getText() + ".");
				setNameParticipante(userField.getText());
				// TODO: se debe utilizar la clase coordinador para crear ventanas
				coordinadorVentana.mostrarVentanaPrincipal();
			}
		});*/
		
			btnIniciarSesin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						iniciarSesion();
					}
				});
		
		pane.add(btnIniciarSesin, "cell 3 6,alignx right");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	private void iniciarSesion() {		
		//Si inicio correcto -> mostrar Principal
		//Si no, pedir de nuevo datos
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(userField.getText());
		usuario.setContrasenya(String.valueOf(passField.getPassword()));
		coordinadorVentana.iniciarSesion(usuario);
		
		// Si ya existe el usuario en el coordinador, el inicio de sesion ha sido correcto
		if(coordinadorVentana.getUsuario()==null) {
			JOptionPane.showMessageDialog(null, "El usuario introducido es incorrecto.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Bienvenido " + coordinadorVentana.getUsuario().getNombre() + ".");
			coordinadorVentana.mostrarVentanaPrincipal();
		}
	}
	
	
	
	public void confirmarExit() {
		// TODO Auto-generated method stub
		int seleccion = JOptionPane.showOptionDialog( null,"¿Esta seguro de que desea salir?",
				  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				  new Object[] { "Si", "No" },"opcion 1");
				 //Valorar
				 if (seleccion == 0){
					System.exit(0);
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
