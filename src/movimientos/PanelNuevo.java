package movimientos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class PanelNuevo extends JPanel {
	private JTextField txtnoemp;
	private JTextField txtNombre;
	private JTextField txtRol;
	private JTextField txtTipo;

	/**
	 * Create the panel.
	 */
	public PanelNuevo() {
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de empleado: ");
		lblNumero.setBounds(24, 27, 105, 14);
		add(lblNumero);
		
		txtnoemp = new JTextField();
		txtnoemp.setToolTipText("Capturar n\u00FAmero de empleado.");
		txtnoemp.setBounds(139, 24, 86, 20);
		add(txtnoemp);
		txtnoemp.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(24, 61, 70, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(104, 61, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(24, 95, 46, 14);
		add(lblRol);
		
		txtRol = new JTextField();
		txtRol.setBounds(104, 90, 86, 20);
		add(txtRol);
		txtRol.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(24, 137, 46, 14);
		add(lblFecha);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(228, 95, 46, 14);
		add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(284, 92, 86, 20);
		add(txtTipo);
		txtTipo.setColumns(10);
		
		JLabel lblCantidadDeEntregas = new JLabel("Cantidad de Entregas");
		lblCantidadDeEntregas.setBounds(24, 191, 134, 14);
		add(lblCantidadDeEntregas);
		
		JCheckBox chckbxCubrioTurno = new JCheckBox("Cubrio turno");
		chckbxCubrioTurno.setBounds(228, 187, 97, 23);
		add(chckbxCubrioTurno);
		
		JSpinner spinEntregas = new JSpinner();
		spinEntregas.setBounds(24, 216, 105, 20);
		add(spinEntregas);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(236, 238, 89, 23);
		add(btnGuardar);
		


	}
}
