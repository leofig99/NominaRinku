package empleados;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.MainApp;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.WindowConstants;

public class VentanaEmpleados extends JDialog {

	private JPanel panelPrincipal;

	/**
	 * Create the frame.
	 */
	public VentanaEmpleados() {
		
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
		
		PanelEliminar panelEliminar= new PanelEliminar();
		tabsEmp.addTab("Eliminar", null, panelEliminar, null);
		
		setModal(true);
		
	}
}
