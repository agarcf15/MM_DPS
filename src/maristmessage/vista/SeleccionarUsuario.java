package maristmessage.vista;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Usuario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SeleccionarUsuario extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private String participante1;
	private JPanel contentPane;
	private Coordinador coordinadorVentana;
	private static String nameParticipante;



	/**
	 * Create the frame.
	 */
	public SeleccionarUsuario() {
		setResizable(false);
		setTitle("MaristMessage - Selección de usuario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 320, 240);
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
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario:");
		contentPane.add(lblSeleccioneUnUsuario, "cell 0 0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Seleccione un usuario:");
		contentPane.add(scrollPane, "cell 0 1,grow");
		
        DefaultListModel<String> lista1 = new DefaultListModel<String>();
        JList<String> list = new JList<String>(lista1);
        list.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//if(e.getClickCount()==2) {
        			/*
        			 * Como idea, poner el nombre de Usuario que se seleccione en la ventana que se abra a partir de esta
        			 * para que el Usuario sepa que de verdad esta hablando con la persona que ha seleccionado
        			 * Un inicio sería lo de abajo para ir sabiendo cual es el user elegido, que habría que pasarselo de 
        			 * alguna manera a la siguiente clase
        			 */
        		//}
        		if (e.getClickCount() != 2) {
        			String selectedItem = (String) list.getSelectedValue();
    				participante1 = selectedItem;
        			setNameParticipante(participante1);
        			coordinadorVentana.mostrarVentanaSeleccUser();
        		}
        	}
        });
        list.addListSelectionListener(e ->{
        	if(!e.getValueIsAdjusting()) {
        		String name= (String) list.getSelectedValue();
        		Font font= new Font(name, Font.PLAIN, 12);
        		JLabel label = new JLabel();
        		label.setFont(font);
        	}
        });
        scrollPane.setViewportView(list);
        
        JButton btnRecargar = new JButton("Recargar");
        btnRecargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		lista1.removeAllElements();
        		ArrayList<Usuario> lista = coordinadorVentana.obtenerListaUsuarios();
                for(Usuario e : lista) {
                	lista1.addElement(e.getNombreUsuario());
                }
        	}
        });
        contentPane.add(btnRecargar, "flowx,cell 0 2");
        
        //Metodo para dar paso a enviar mensaje
        JButton btnStartConv = new JButton("Empezar Conversación");
        btnStartConv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(getNameParticipante()!=null) {
	        		// coordinadorVentana.crearGrupoDos(getNameParticipante());
        			Usuario usuarioReceptor = new Usuario();
        			usuarioReceptor.setNombreUsuario(getNameParticipante());
        			coordinadorVentana.iniciarConversacion(usuarioReceptor);
	        		coordinadorVentana.mostrarVentanaEnviarMensaje();
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para iniciar la conversación", "Info",
        					JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
        contentPane.add(btnStartConv, "cell 0 2");
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
	}
	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaSeleccionUserAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}	
	}
	
	public void setNameParticipante(String participante1) {
		nameParticipante=participante1;
	}
	public static String getNameParticipante() {
		return nameParticipante;
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
