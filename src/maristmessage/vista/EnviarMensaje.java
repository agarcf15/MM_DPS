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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import maristmessage.modelo.vo.Mensaje;

import java.util.ArrayList;

public class EnviarMensaje extends JFrame {

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
	private JScrollPane scrollPane;
	private JTextPane textPane;
	

	/**
	 * Create the frame.
	 */
	public EnviarMensaje() {
		setResizable(false);
		setTitle("MaristMessage - Enviar mensaje");
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
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
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
			/*	if (coordinadorVentana.getGrupo() != null) {
					setTitle("MaristMessage - Enviar mensaje a " +coordinadorVentana.getGrupo().getNombre());
				} else {
					setTitle("MaristMessage - Enviar mensaje a " +coordinadorVentana.getConversacion().getReceiver().getNombreUsuario());
				}*/
				
				//Obtenemos el mensaje
				cad=textField.getText();
				
				//Enviamos el mensaje al coordinador para que lo mande a la logica
				enviarElMensaje(cad);
				
        		//cadf= cad1.concat(cad);
        		//textArea.setText(cadf);
        		//cad1=textArea.getText()+"\n";
        		textField.setText("");
			}
		});
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnEnviar, "cell 0 1");
		
		btnValorar = new JButton("Valorar");
		btnValorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea finalizar la conversación e iniciar la valoracion?");
      				 //Valorar
				if (seleccion == 0){
  					coordinadorVentana.mostrarVentanaValor();
  				 }
  				 //Salir
  				 if (seleccion == 1){
  					//TODO: establecer de alguna forma que el usuario no ha querido hacer la valoracion (penalizacion)
  					coordinadorVentana.cerrarVentanaMensajeIndAPrincipal();
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
		
		// Actualizar mensajes
		ActionListener actualizarMensajes = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (isVisible()) {
                	//Para obtener el nombre del destinatario en la ventana de envío
                	if (coordinadorVentana.getGrupo() != null) {
    					setTitle("MaristMessage - Enviar mensaje a " +coordinadorVentana.getGrupo().getNombre());
    				} else {
    					setTitle("MaristMessage - Enviar mensaje a " +coordinadorVentana.getConversacion().getReceiver().getNombreUsuario());
    				}
                	// A partir de aqui se obtienen los mensajes y se representan
                	textPane.setText("");
                	ArrayList<Mensaje> mensajes = coordinadorVentana.obtenerMensajesConversacion();
                	for(Mensaje e : mensajes) {
                		textPane.setText(textPane.getText()+e.getMessage()+" - "+e.getAuthor().getNombreUsuario()+" "+e.getDate()+"\r\n");
                	}
                	//Para pruebas
                	//textPane.setText(textPane.getText() + "Mensaje de prueba\r\n");
                	
                } else {
                	textPane.setText("");
                }
            }
        };
        Timer timer = new Timer(1000 ,actualizarMensajes); // Cada 5000 ms (se puede cambiar)
        timer.start();

	}
	public void enviarElMensaje(String cadena) {
		coordinadorVentana.enviarMensaje(cadena);
	}
	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaMensajeIndAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
