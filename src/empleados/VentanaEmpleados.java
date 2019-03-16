package empleados;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import principal.MainApp;

import javax.swing.JTabbedPane;

public class VentanaEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	public VentanaEmpleados() {
		setTitle("Empleados Rinku");
		
		setBounds(100, 100, 500, 350);
		panelPrincipal = new JPanel();
		setResizable(false);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabsEmp = new JTabbedPane(JTabbedPane.TOP);
		panelPrincipal.add(tabsEmp);
		
		PanelNuevo panelNuevo = new PanelNuevo();
		tabsEmp.addTab("Nuevo", null, panelNuevo, null);
		panelNuevo.setLayout(null);
		
		PanelBuscar panelBuscar = new PanelBuscar();
		tabsEmp.addTab("Buscar", null, panelBuscar, null);
		
		PanelModificar panelModificar = new PanelModificar();
		tabsEmp.addTab("Modificar", null, panelModificar, null);
		
		tabsEmp.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if(tabsEmp.getSelectedIndex()==1)
	            {
	            	panelBuscar.llenarLista();
	            }
	        }
	    });
	    
		setModal(true);
		
	}
}
