package maristmessage.vista;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;

import maristmessage.controlador.Coordinador;
import maristmessage.modelo.vo.Usuario;

import javax.swing.event.ListSelectionEvent;

public class Informes extends JDialog {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador coordinadorVentana;
	private JTextField textFieldBuscar;
	private DefaultListModel<String> lista1=new DefaultListModel<>();
	private String participante ;
	private static String nameParticipante;
	/**
	 * Array con los elementos de la ayuda
	 */
	static ArrayList<String> elementos;

	/* JButton btnRecargar = new JButton("Recargar");
     btnRecargar.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     		lista1.removeAllElements();
     		ArrayList<Usuario> lista = coordinadorVentana.obtenerListaUsuarios();
             for(Usuario e : lista) {
             	lista1.addElement(e.getNombreUsuario());
             }
     	}
     });*/
	
	/**
	 * Create the dialog.
	 */
	public Informes() {
		setTitle("MaristMessage - Informes");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][grow][]"));

		JLabel lblBsqueda = new JLabel("Búsqueda:");
		getContentPane().add(lblBsqueda, "cell 0 0");

		JScrollPane scrollPaneBuscar = new JScrollPane();
		getContentPane().add(scrollPaneBuscar, "cell 0 1,grow");

		JList<String> listAyudas = new JList<String>();
		lista1.addElement("Pedro");
		DefaultListModel<String> lista = new DefaultListModel<String>();
		/*for (String e : elementos) {
			lista.addElement(e);
		}*/
        listAyudas.setModel(lista1);
        lista1.removeElement("Pedro");
		listAyudas.setBackground(new Color(79, 98, 155));
		scrollPaneBuscar.setViewportView(listAyudas);

		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
 			
		textFieldBuscar = new JTextField();
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				lista1.removeAllElements();
				for (Usuario e : listaUsuarios) {
					if (e.getNombreUsuario().toLowerCase().contains(textFieldBuscar.getText().toLowerCase())) {
						lista.addElement(e.getNombreUsuario());
					}
				}
			}
		});
		scrollPaneBuscar.setColumnHeaderView(textFieldBuscar);
		textFieldBuscar.setColumns(10);

		JScrollPane scrollPaneAyuda = new JScrollPane();
		getContentPane().add(scrollPaneAyuda, "cell 1 1,grow");

		JTextPane txtUsuario = new JTextPane();
		txtUsuario.setBackground(Color.LIGHT_GRAY);
		txtUsuario.setText(
				"Bienvenido a los informes de MaristMessage.\r\nPuedes elegir un usuario concreto o buscarlo en el menu de la izquierda.");
		scrollPaneAyuda.setViewportView(txtUsuario);
		
		JButton btnRecargarUsuarios = new JButton("Recargar usuarios");
		btnRecargarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista1.isEmpty()) {
					//NO hace nada
				}
				else {
				lista1.removeAllElements();
				}
				ArrayList<Usuario> listaUsuarios = coordinadorVentana.obtenerListaUsuarios();
		        for(Usuario e : listaUsuarios) {
		        	lista1.addElement(e.getNombreUsuario());
		        }
		        System.out.println(lista1);
			}
		});
		getContentPane().add(btnRecargarUsuarios, "flowx,cell 0 2,growx");
		
		//Boton que tienes que retocar Raúl
		JButton btnSeleccionarUser = new JButton("Obtener informe");
		btnSeleccionarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getNameParticipante()!=null) {
	        		// coordinadorVentana.crearGrupoDos(getNameParticipante());
        			Usuario usuarioInforme = new Usuario();
        			usuarioInforme.setNombreUsuario(getNameParticipante());
        			usuarioInforme = coordinadorVentana.buscarUsuario(usuarioInforme);
        			ArrayList<Integer> valoracionesArrayList = coordinadorVentana.obtenerValoracionesUsuario(usuarioInforme);
        			String textoUsuario = "El usuario "+usuarioInforme.getNombre()+" "+usuarioInforme.getApellidos()+" ha obtenido las siguientes valoraciones: \r\n";
        			double contadorValoraciones=0.0;
        			double suma=0.0;
        			double media=0;
        			for(Integer valoracion: valoracionesArrayList) {
        				textoUsuario=textoUsuario.concat(valoracion+"\r\n");
        				suma=suma+valoracion;
        				contadorValoraciones=contadorValoraciones+1.0;
        			}
        			//System.out.print("antesmedia "+textoUsuario);
        		//	System.out.print(contadorValoraciones);
        			media=suma/contadorValoraciones;
        			//System.out.print("media "+media);
        			textoUsuario=textoUsuario.concat("La media de sus valoraciones es: " +String.format("%.2f", media)+"\r\n");
        			//System.out.print(textoUsuario);
        			
        			String textoComportamiento = "";
        			
        			if(media<=1.5) {
        				textoComportamiento = "Este usuario muestra un mal comportamiento y se muestra poco amistoso en sus mensajes.\r\n"
        						+"Tiene tendencia a no aportar contenido o a hablar de otros temas. Interactúa de forma incorrecta con sus compañeros\r\n"
        						+"Sería necesario darle un toque de atención para que mejorase o de lo contrario tomar medidas drásticas.\r\n";
        				textoUsuario=textoUsuario.concat(textoComportamiento);
        			}
        			else if(media>1.5&&media<=3.0) {
        				textoComportamiento = "Este usuario no suele aportar mucho en sus mensajes. No es mal compañero, pero a veces se sale del tema.\r\n"
        						+"Sus compañeros no consideran que su comportamiento sea malo, pero podría ser mejor\r\n"
        						+"Sería necesario no perderle de vista por si acaso.\r\n";
        				textoUsuario=textoUsuario.concat(textoComportamiento);
        			}
        			else if(media>3.0&&media<=4.0) {
        				textoComportamiento = "Este usuario suele aportar con sus mensajes. Es un buen compañero en líneas generales.\r\n"
        						+"Sus compañeros consideran que su comportamiento es bueno y les gusta hablar con él\r\n"
        						+"No suele salirse del tema y su actitud es positiva.\r\n";
        				textoUsuario=textoUsuario.concat(textoComportamiento);
        			}
        			else if(media>4.0) {
        				textoComportamiento = "Este usuario suele aportar mucho con sus mensajes. Es muy buen compañero.\r\n"
        						+"Sus compañeros consideran que su comportamiento es muy bueno y les gusta hablar con él porque siempre aporta\r\n"
        						+"No se sale del tema y crea soluciones y vías para solucionar posibles conflictos.\r\n"
        						+"Merecería ser premiado por su actitud.\r\n";
        				textoUsuario=textoUsuario.concat(textoComportamiento);
        			}
        			txtUsuario.setText(textoUsuario);
        			
        			coordinadorVentana.insertarInforme(usuarioInforme, textoUsuario);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para obtener el informe", "Info",
        					JOptionPane.WARNING_MESSAGE);
        		}
			}
		});
		getContentPane().add(btnSeleccionarUser, "cell 0 2");

		listAyudas.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				participante = listAyudas.getSelectedValue();
				setNameParticipante(participante);
				System.out.println(participante);
			}

		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				confirmarExit();
			}
		});
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
	public void confirmarExit() {
		int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que no desea continuar con la operación?");
		// Salida
		if (seleccion == 0) {
			coordinadorVentana.cerrarVentanaInformeAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
}
