package maristmessage.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
//TODO: eliminar clase
public class MensajeGrupal extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnEnviar;
	private JButton btnValorar;
	String cad, cadf, cad1="";
	private Coordinador coordinadorVentana;
	

	/**
	 * Create the frame.
	 */
	public MensajeGrupal() {
		setResizable(false);
		setTitle("MaristMessage - Enviar mensaje grupal");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(79,98,155));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		contentPane.add(textArea, "cell 0 0,grow");
		
		textField = new JTextField();
		contentPane.add(textField, "flowx,cell 0 1,growx");
		textField.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Revisar, quiero que se pueda hacer un scroll en el area de texto
        		/* HOLA
        		 * QUE TAL? 
        		 * ESTAS BIEN?
        		 * ....
        		 * 
        		 * Y usar el scroll para ver 
        		 */
				//Para obtener el nombre del destinatario en la ventana de envío
				if (getTitle()!="MaristMessage - Enviar mensaje grupal "+coordinadorVentana.getGrupo().getNombre()) {
					setTitle("MaristMessage - Enviar mensaje grupal " +coordinadorVentana.getGrupo().getNombre());
				}
				
				//Obtenemos el mensaje
				cad=textField.getText();
				
				//Enviamos el mensaje al coordinador para que lo mande a la logica
				enviarElMensaje(cad);
				
        		cadf= cad1.concat(cad);
        		textArea.setText(cadf);
        		cad1=textArea.getText()+"\n";
        		textField.setText("");
			}
		});
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnEnviar, "cell 0 1");
		
		btnValorar = new JButton("Valorar");
		btnValorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showOptionDialog( null,"¿Esta seguro de que no desea continuar con la operación?",
      				  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
      				   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
      				  new Object[] { "Si", "No" },"opcion 1");
      				 //Valorar
      				 if (seleccion == 0){
      					coordinadorVentana.mostrarVentanaEnviarMensajeGrupal();
      				 }
      				 //Salir
      				 if (seleccion == 1){
      					 
      				 }	
				
			}
		});
		btnValorar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnValorar, "cell 0 1");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	
	public void enviarElMensaje(String cadena) {
		coordinadorVentana.enviarMensaje(cadena);
	}
	
	public void confirmarExit() {
		// TODO Auto-generated method stub
		int seleccion = JOptionPane.showOptionDialog( null,"¿Esta seguro de que desea salir?",
				  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
				   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
				  new Object[] { "Si", "No" },"opcion 1");
				 //Valorar
				 if (seleccion == 0){
					coordinadorVentana.cerrarVentanaMensajeGrupAPrincipal();
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