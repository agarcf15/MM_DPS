package maristmessage.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class EnviarMensajeGlobal extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Coordinador coordinadorVentana;
	
	
	/**
	 * Create the frame.
	 */
	public EnviarMensajeGlobal() {
		setTitle("MaristMessage - Enviar mensaje global");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 733, 307);
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
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JLabel lblIntroduzcaElMensaje = new JLabel("Introduzca el mensaje global a continuación:");
		lblIntroduzcaElMensaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblIntroduzcaElMensaje, "cell 0 0");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1,grow");
		
		textArea = new JTextArea(11,0);
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Principal ventanaUsuario= new Principal();
                ventanaUsuario.setVisible(true);
                dispose();*/
				//Enviamos el mensaje al coordinador para que lo mande a la logica
				enviarElMensaje(textArea.getText());
				coordinadorVentana.mostrarVentanaPrincipal();
			}
		});
		contentPane.add(btnEnviar, "cell 0 2,alignx center");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	public void enviarElMensaje(String cadena) {
		coordinadorVentana.enviarMensajeGlobal(cadena);
	}

	public void confirmarExit() {
		// TODO Auto-generated method stub
		int seleccion = JOptionPane.showOptionDialog( null,"¿Esta seguro de que no desea continuar con la operación?",
				  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				  new Object[] { "Si", "No" },"opcion 1");
				 //Valorar
				 if (seleccion == 0){
					coordinadorVentana.cerrarVentanaMensajeGlobalAPrincipal();
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
