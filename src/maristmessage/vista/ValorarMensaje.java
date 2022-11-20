package maristmessage.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Usuario;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ValorarMensaje extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String[] valoraciones;
	private Coordinador coordinadorVentana;


	/**
	 * Create the frame.
	 */
	public ValorarMensaje() {
		setTitle("MaristMessage - Valorar mensaje");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
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
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79,98,155));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
		JButton btnObtenerUsuarioDe = new JButton("Obtener usuarios de la conversación");
		contentPane.add(btnObtenerUsuarioDe, "cell 0 0");
		
		JLabel lblValorarAlUsuario = new JLabel("Valorar al usuario:");
		contentPane.add(lblValorarAlUsuario, "cell 0 1");
		
		JComboBox<String> comboBoxUsuarios = new JComboBox<String>();
		contentPane.add(comboBoxUsuarios, "cell 0 2,growx");
		
		JLabel lblEligeUnaValoracin = new JLabel("con una valoración de:");
		contentPane.add(lblEligeUnaValoracin, "cell 0 3");
		
		valoraciones = new String[] {"★★★★★", "★★★★☆", "★★★☆☆", "★★☆☆☆", "★☆☆☆☆", "☆☆☆☆☆", "No valorar"};
		JComboBox<String> comboBoxValoracion = new JComboBox<>();
		comboBoxValoracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int it = 5;
				for (String valoracion: valoraciones) {
					if (comboBoxValoracion.getSelectedItem().toString().equals(valoracion)) {
						int valoracionMensaje = it;
						System.out.println("Valoración: " + valoracionMensaje);
						break;
					} else if (comboBoxValoracion.getSelectedItem().toString().equals("No valorar")) {
						it = -1;
						break;
					}
					it--;
				}

				valorarMensaje(comboBoxUsuarios.getSelectedItem().toString(), it);
			}
		});
		comboBoxValoracion.setModel(new DefaultComboBoxModel<String>(valoraciones));
		contentPane.add(comboBoxValoracion, "cell 0 4,growx");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
		btnObtenerUsuarioDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Usuario> usuariosAValorar = coordinadorVentana.getListaUsuariosSinValorar();
				DefaultComboBoxModel<String> listaUsuarios = new DefaultComboBoxModel<String>();
				for (Usuario usuario : usuariosAValorar) {
					listaUsuarios.addElement(usuario.getNombreUsuario());
				}
				comboBoxUsuarios.setModel(listaUsuarios);
			}
		});
	}
	private void valorarMensaje(String nombreDeUsuario, int it) {
		Usuario usuarioValorado = new Usuario();
		usuarioValorado.setNombreUsuario(nombreDeUsuario);
		coordinadorVentana.valorarMensaje(coordinadorVentana.buscarUsuario(usuarioValorado), it);
	}
	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación? Si no desea valorar la conversacion, seleccione \"No Valorar\"");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaCrearAPrincipal();
			this.setVisible(false);
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}	
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
