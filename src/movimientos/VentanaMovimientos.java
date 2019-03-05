package movimientos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import empleados.PanelBuscar;
import empleados.PanelEliminar;
import empleados.PanelModificar;

import javax.swing.JTabbedPane;

public class VentanaMovimientos extends JDialog {

	private JPanel panelPrincipal;
	/**
	 * Create the frame.
	 */
	public VentanaMovimientos() {
		setBounds(100, 100, 500, 350);
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
		
		PanelModificar panelModificar = new PanelModificar();
		tabsMov.addTab("Modificar", null, panelModificar, null);
		
		PanelEliminar panelEliminar= new PanelEliminar();
		tabsMov.addTab("Eliminar", null, panelEliminar, null);
		
		setModal(true);
	}

}
