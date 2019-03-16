package movimientos;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;

public class VentanaMovimientos extends JDialog {

	private JPanel panelPrincipal;
	/**
	 * Create the frame.
	 */
	public VentanaMovimientos() {
		setTitle("Movimientos Rinku");
		setBounds(100, 100, 500, 350);
		setResizable(false);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JTabbedPane tabsMov = new JTabbedPane(JTabbedPane.TOP);
		tabsMov.setBounds(5, 5, 474, 301);
		panelPrincipal.add(tabsMov);
		
		PanelNuevo panelNuevo = new PanelNuevo();
		tabsMov.addTab("Nuevo", null, panelNuevo, null);
		panelNuevo.setLayout(null);
		
		PanelBuscar panelBuscar = new PanelBuscar();
		tabsMov.addTab("Buscar", null, panelBuscar, null);
		tabsMov.addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent e) {
		            if(tabsMov.getSelectedIndex()==1)
		            {
		            	panelBuscar.llenarLista();
		            }
		        }
		    });
		setModal(true);
	}

}
