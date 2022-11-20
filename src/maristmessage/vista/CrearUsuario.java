package maristmessage.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CrearUsuario extends JFrame {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	static CrearUsuario frame;
	static Principal frame1;
	private JPanel pane;
	private JTextField nombreField;
	private JTextField apellidosField;
	private JComboBox<String> rolSelec;
	private JTextField nombreDeUsuarioField;
	private JPasswordField contrasenyaField;

	private Coordinador coordinadorVentana;

	public CrearUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/CapturaI.PNG"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 628, 532);
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
		pane.setBackground(new Color(79, 98, 155));
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pane);
		pane.setLayout(new MigLayout("", "[][][][grow][][][]", "[][][][][][][][][][][][][][]"));

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("res/Capturaa.PNG"));
		pane.add(logo, "cell 3 0,alignx center,aligny center");
		
		setTitle("MaristMessage - Crear Usuario");
		JLabel lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		pane.add(lblCrearUsuario, "cell 3 1,alignx center");

		JLabel lblNombre = new JLabel("Nombre del usuario:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblNombre, "cell 3 2");

		nombreField = new JTextField();
		pane.add(nombreField, "cell 3 3,growx");
		nombreField.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos del usuario:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblApellidos, "cell 3 4");

		apellidosField = new JTextField();
		apellidosField.setColumns(10);
		pane.add(apellidosField, "cell 3 5,growx");

		JLabel lblRol = new JLabel("Rol del usuario:");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblRol, "cell 3 6");

		rolSelec = new JComboBox<String>();
		rolSelec.setModel(new DefaultComboBoxModel<String>(new String[] { "Profesor", "Jefe de Departamento",
				"Jefe de Estudios Infantil", "Jefe de Estudios Primaria", "Jefe de Estudios Secundaria y Bachillerato",
				"Administración", "Secretaría", "Director", "Subdirector" }));
		pane.add(rolSelec, "cell 3 7,growx");

		JLabel lblNombreDeUsuario = new JLabel("Nombre en la plataforma del usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblNombreDeUsuario, "cell 3 8");

		nombreDeUsuarioField = new JTextField();
		nombreDeUsuarioField.setColumns(10);
		pane.add(nombreDeUsuarioField, "cell 3 9,growx");

		JLabel lblContrasenya = new JLabel("Contraseña del usuario:");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblContrasenya, "cell 3 10");

		contrasenyaField = new JPasswordField();
		contrasenyaField.setColumns(10);
		pane.add(contrasenyaField, "cell 3 11,growx");
		nombreField.setEditable(true);
		apellidosField.setEditable(true);
		nombreDeUsuarioField.setEditable(true);
		contrasenyaField.setEditable(true);

		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});
		pane.add(btnCrear, "cell 3 13,alignx center");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	private void crearUsuario() {
		Usuario usuario = new Usuario();

		usuario.setNombre(nombreField.getText());
		usuario.setApellidos(apellidosField.getText());
		usuario.setRol(rolSelec.getSelectedItem().toString());
		usuario.setNombreUsuario(nombreDeUsuarioField.getText());
		usuario.setContrasenya(String.valueOf(contrasenyaField.getPassword()));
		coordinadorVentana.registrarUsuario(usuario);

		coordinadorVentana.cerrarVentanaCrearAPrincipal();
	}

	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaCrearAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}

}
