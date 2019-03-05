package empleados;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import bdsql.Conexion;

public class PanelNuevo extends JPanel implements ActionListener {
	private JTextField txtNumero;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnGuardar;
	/**
	 * Create the panel.
	 */
	public PanelNuevo() {
		setLayout(null);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setBounds(10, 14, 65, 14);
		add(lblNumero);
		
		txtNumero = new JTextField(8);
		txtNumero.setBounds(85, 11, 86, 20);
		add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 39, 65, 14);
		add(lblNombre);
		
		txtNombre = new JTextField(20);
		txtNombre.setBounds(85, 36, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(210, 39, 65, 14);
		add(lblApellido);
		
		txtApellido = new JTextField(20);
		txtApellido.setBounds(285, 36, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		JPanel panelRol = new JPanel();
		panelRol.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelRol.setBounds(25, 83, 185, 95);
		add(panelRol);
		panelRol.setLayout(null);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 11, 46, 14);
		panelRol.add(lblRol);
		
		JRadioButton rbChofer = new JRadioButton("Chofer",true);
		rbChofer.setBounds(44, 7, 109, 23);
		panelRol.add(rbChofer);
		
		JRadioButton rbCargador = new JRadioButton("Cargador");
		rbCargador.setBounds(44, 32, 109, 23);
		panelRol.add(rbCargador);
		
		JRadioButton rbAuxiliar = new JRadioButton("Auxiliar");
		rbAuxiliar.setBounds(44, 58, 109, 23);
		panelRol.add(rbAuxiliar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(236, 83, 185, 95);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 46, 14);
		panel.add(lblTipo);
		
		JRadioButton rbInterno = new JRadioButton("Interno",true);
		rbInterno.setBounds(53, 7, 109, 23);
		panel.add(rbInterno);
		
		JRadioButton rbExterno = new JRadioButton("Externo");
		rbExterno.setBounds(53, 35, 109, 23);
		panel.add(rbExterno);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(254, 204, 89, 23);
		add(btnGuardar);

		ButtonGroup  grpRol = new ButtonGroup();
		grpRol.add(rbChofer);
		grpRol.add(rbCargador);
		grpRol.add(rbAuxiliar);
		
		ButtonGroup  grpTipo = new ButtonGroup();
		grpTipo.add(rbInterno);
		grpTipo.add(rbExterno);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGuardar){
			Conexion conexion = new Conexion(); 
			try {
				conexion.getConnection();
			} catch (Exception e1) {
			}
		}
	}
}
