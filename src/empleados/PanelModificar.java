package empleados;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PanelModificar extends JPanel {
	private JTextField txtEmp;

	/**
	 * Create the panel.
	 */
	public PanelModificar() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de Empleado:");
		lblNumero.setBounds(130, 14, 94, 14);
		add(lblNumero);
		
		txtEmp = new JTextField();
		txtEmp.setBounds(234, 11, 86, 20);
		add(txtEmp);
		txtEmp.setColumns(10);

	}

}
