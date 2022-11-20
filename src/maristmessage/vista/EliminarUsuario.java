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

public class EliminarUsuario extends JFrame{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	// TODO: frame para realizar dispose
	static EliminarUsuario frame;
	private JPanel pane;
	private JTextField nombreUsuarioField;
	private Coordinador coordinadorVentana;



	/**
	 * Create the frame.
	 */
	public EliminarUsuario() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("res/CapturaI.PNG"));
		setTitle("MaristMessage - Eliminar Usuario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 630, 300);
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
		pane.setLayout(new MigLayout("", "[][][][grow][][][]", "[][][][][][]"));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("res/Capturaa.PNG"));
		pane.add(logo, "cell 3 0,alignx center,aligny center");
		
		JLabel lblEliminar = new JLabel("Eliminar Usuario");
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 18));
		pane.add(lblEliminar, "cell 3 1,alignx center");
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario que desea eliminar:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pane.add(lblNombreUsuario, "cell 3 2");
		
		nombreUsuarioField = new JTextField();
		pane.add(nombreUsuarioField, "cell 3 3,growx");
		nombreUsuarioField.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarUsuario();
			}
		});
		pane.add(btnEliminar, "cell 3 5,alignx center");
	}
	private void eliminarUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNombreUsuario(nombreUsuarioField.getText());
		coordinadorVentana.eliminarUsuario(usuario);
		
		coordinadorVentana.cerrarVentanaEliminaAPrincipal();
	}
	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaEliminaAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
