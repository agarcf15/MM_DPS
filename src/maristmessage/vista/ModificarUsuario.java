package maristmessage.vista;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class ModificarUsuario extends JFrame {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	static ModificarUsuario frame;
	static Principal frame1;
	private JPanel pane;
	private JTextField nombreUsuarioField;
	private JTextField apellidosField;
	private JComboBox<String> rolSelec;
	private JPasswordField contrasenyaField;
	private Coordinador coordinadorVentana;
	private JTextField nombreField;
	private JButton btnModificar;


	public ModificarUsuario() {
		// setIconImage(Toolkit.getDefaultToolkit().getImage("res/CapturaI.PNG"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 628, 530);
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
		pane.setLayout(new MigLayout("", "[][][][grow][][][]", "[][][][][][][][][][][][][][][][][]"));

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("res/Capturaa.PNG"));
		pane.add(logo, "cell 3 0,alignx center,aligny center");
		
		setTitle("MaristMessage - Modificar Usuario");
		JLabel lblModificar = new JLabel("Modificar Usuario");
		lblModificar.setFont(new Font("Tahoma", Font.BOLD, 18));
		pane.add(lblModificar, "cell 3 1,alignx center");

		JLabel lblNombreUsuario = new JLabel("Nombre de usuario que desea modificar:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblNombreUsuario, "cell 3 2");

		nombreUsuarioField = new JTextField();
		pane.add(nombreUsuarioField, "cell 3 3,growx");
		nombreUsuarioField.setColumns(10);
		
		JButton btnBuscarUsuario = new JButton("Buscar usuario");
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarUsuario();
			}
		});
		btnBuscarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		pane.add(btnBuscarUsuario, "cell 3 4,alignx center");
		
		JLabel lblNombre = new JLabel("Nombre del usuario:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblNombre, "cell 3 5");
		
		nombreField = new JTextField();
		nombreField.setEditable(false);
		pane.add(nombreField, "cell 3 6,growx");
		nombreField.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos del usuario:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblApellidos, "cell 3 7");

		apellidosField = new JTextField();
		apellidosField.setColumns(10);
		pane.add(apellidosField, "cell 3 8,growx");

		JLabel lblRol = new JLabel("Rol del usuario:");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblRol, "cell 3 9");

		rolSelec = new JComboBox<String>();
		rolSelec.setEnabled(false);
		rolSelec.setModel(new DefaultComboBoxModel<String>(new String[] { "Profesor", "Jefe de Departamento",
				"Jefe de Estudios Infantil", "Jefe de Estudios Primaria", "Jefe de Estudios Secundaria y Bachillerato",
				"Administración", "Secretaría", "Director", "Subdirector" }));
		pane.add(rolSelec, "cell 3 10,growx");
		
				JLabel lblContrasenya = new JLabel("Contraseña del usuario:");
				lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 15));
				pane.add(lblContrasenya, "cell 3 11");
		nombreUsuarioField.setEditable(true);
		apellidosField.setEditable(false);
		rolSelec.setEditable(false);

				contrasenyaField = new JPasswordField();
				contrasenyaField.setColumns(10);
				pane.add(contrasenyaField, "cell 3 12,growx");
				contrasenyaField.setEditable(false);
		btnModificar = new JButton("Cambiar");
		btnModificar.setEnabled(false);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarUsuario();
			}
		});
		pane.add(btnModificar, "cell 3 14,alignx center");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	private void buscarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombreUsuarioField.getText());
		usuario = coordinadorVentana.buscarUsuario(usuario);
		if (usuario.getNombre()!=null) {
			nombreField.setText(usuario.getNombre());
			nombreField.setEditable(true);
			apellidosField.setText(usuario.getApellidos());
			apellidosField.setEditable(true);
			rolSelec.setSelectedItem(usuario.getRol().getName());
			rolSelec.setEnabled(true);
			contrasenyaField.setText(usuario.getContrasenya());
			contrasenyaField.setEditable(true);
			btnModificar.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(null,"El usuario introducido no existe","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void modificarUsuario() {
		Usuario usuario = new Usuario();

		usuario.setNombreUsuario(nombreUsuarioField.getText());
		usuario.setNombre(nombreField.getText());
		usuario.setApellidos(apellidosField.getText());
		usuario.setRol(rolSelec.getSelectedItem().toString());
		usuario.setContrasenya(String.valueOf(contrasenyaField.getPassword()));
		coordinadorVentana.modificarUsuario(usuario);
		
		JOptionPane.showMessageDialog(null, "Usuario " + nombreUsuarioField.getText() + " cambiado correctamente.");
		
		coordinadorVentana.cerrarVentanaModificaAPrincipal();
	}

	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaModificaAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}

}
