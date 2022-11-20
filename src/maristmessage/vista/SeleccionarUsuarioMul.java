package maristmessage.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;

public class SeleccionarUsuarioMul extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private String participante1,participante2;
	private JPanel contentPane;
	private ArrayList<String> listaUsu= new ArrayList<String>();
	private Coordinador coordinadorVentana;
	private JTextField txtNombregrupo;



	/**
	 * Create the frame.
	 */
	public SeleccionarUsuarioMul() {
		setResizable(false);
		setTitle("MaristMessage - Selección de usuarios");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 583, 454);
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
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[][][][grow][]"));
		
		JLabel lblNombreDelGrupo = new JLabel("Nombre del grupo");
		contentPane.add(lblNombreDelGrupo, "cell 0 0");
		
		txtNombregrupo = new JTextField();
		contentPane.add(txtNombregrupo, "cell 0 1,growx");
		txtNombregrupo.setColumns(10);
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario:");
		contentPane.add(lblSeleccioneUnUsuario, "cell 0 2");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Seleccione un usuario:");
		contentPane.add(scrollPane, "cell 0 3,grow");
		
		
		JLabel lblUsersSelc = new JLabel("Usuarios Seleccionados:");
		contentPane.add(lblUsersSelc, "cell 1 2");
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setToolTipText("Usuarios seleccionados:");
		contentPane.add(scrollPane1, "cell 1 3,grow");
		
        DefaultListModel<String> lista1 = new DefaultListModel<String>();

        JList<String> list = new JList<String>(lista1);
        list.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		/*Se selecciona un objecto 
        		 * el ususario tiene que pulsar el boton añadir
        		 * El objeto aparecerá en una columna en blanco
        		 * Se comprobará que no hay personas repetidas(A mayores, incluyendo eliminacion de este como en wasap)
        		 * Podrá hacer todas las veces que quiera esta hasta que se pulse el boton correspondiente
        		 */
        		if (e.getClickCount() != 2) {
        			String selectedItem = (String) list.getSelectedValue();
    				participante1 = selectedItem;
        		}
        		else {
        			String selectedItem = (String) list.getSelectedValue();
    				participante2 = selectedItem;
    				System.out.println(participante2);
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
        JButton btnAnadir = new JButton("Añadir");
        contentPane.add(btnAnadir, "flowx,cell 0 4");
		DefaultListModel<String> lista2 = new DefaultListModel<String>();

        btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(listaUsu);
				if (listaUsu.contains(participante1)) {
					JOptionPane.showMessageDialog(null, "Usuario ya añadido en la lista", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					lista2.clear();
					listaUsu.add(participante1);
					for(String e1 : listaUsu) {
						lista2.addElement(e1);
					}
			    }			
			}
		});
        JList<String> list_1 = new JList<String>(lista2);
        scrollPane1.setViewportView(list_1);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (!(listaUsu.contains(participante2))) {
					JOptionPane.showMessageDialog(null, "Usuario no existe en la lista", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
	        		lista2.clear();
					listaUsu.remove(participante2);
					for(String e1 : listaUsu) {
						lista2.addElement(e1);
					}
        		}
        	}
        });
        contentPane.add(btnEliminar, "flowx,cell 1 4");
        
        
        JButton btnCGrupo = new JButton("Crear Grupo");
        contentPane.add(btnCGrupo, "cell 1 4");
        btnCGrupo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//Primero creamos el grupo
	    		coordinadorVentana.crearGrupo(listaUsu, txtNombregrupo.getText());
	    		
	    		// Iniciamos conversacion (o recuperamos).. null significa que es grupo
	    		//coordinadorVentana.iniciarConversacion(null);
	    		
	    		//Abrimos la ventana de envia 
	    		coordinadorVentana.mostrarVentanaEnviarMensaje();
	    	}
    	});
        
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
        contentPane.add(btnRecargar, "cell 0 4");
        btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coordinadorVentana.mostrarVentanaSeleccMultiUser();			
			}
		});
        
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
			coordinadorVentana.cerrarVentanaSeleccionUsersAPrincipal();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}	
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinadorVentana = coordinador;
	}
}
