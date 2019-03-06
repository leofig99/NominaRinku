package empleados;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

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
	private Conexion con = new Conexion();
	private String sTipo,sRol;
	private JRadioButton rbChofer,rbCargador,rbAuxiliar,rbInterno,rbExterno;
	private JLabel lblNumero,lblNombre,lblApellido;
	public PanelNuevo() {
		setLayout(null);
		
		lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setBounds(10, 14, 65, 14);
		add(lblNumero);
		
		txtNumero = new JTextField(8);
		txtNumero.setBounds(85, 11, 86, 20);
		add(txtNumero);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 39, 65, 14);
		add(lblNombre);
		
		txtNombre = new JTextField(20);
		txtNombre.setBounds(85, 36, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(210, 39, 65, 14);
		add(lblApellido);
		
		txtApellido = new JTextField(20);
		txtApellido.setBounds(285, 36, 86, 20);
		add(txtApellido);
		
		JPanel panelRol = new JPanel();
		panelRol.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelRol.setBounds(25, 83, 185, 95);
		add(panelRol);
		panelRol.setLayout(null);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 11, 46, 14);
		panelRol.add(lblRol);
		
		rbChofer = new JRadioButton("Chofer",true);
		rbChofer.setBounds(44, 7, 109, 23);
		panelRol.add(rbChofer);
		
		rbCargador = new JRadioButton("Cargador");
		rbCargador.setBounds(44, 32, 109, 23);
		panelRol.add(rbCargador);
		
		rbAuxiliar = new JRadioButton("Auxiliar");
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
		
		rbInterno = new JRadioButton("Interno",true);
		rbInterno.setBounds(53, 7, 109, 23);
		panel.add(rbInterno);
		
		rbExterno = new JRadioButton("Externo");
		rbExterno.setBounds(53, 35, 109, 23);
		panel.add(rbExterno);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(277, 236, 89, 23);
		add(btnGuardar);

		ButtonGroup  grpRol = new ButtonGroup();
		grpRol.add(rbChofer);
		grpRol.add(rbCargador);
		grpRol.add(rbAuxiliar);
		
		ButtonGroup  grpTipo = new ButtonGroup();
		grpTipo.add(rbInterno);
		grpTipo.add(rbExterno);
		
	}

public void NuevoEmpleado() {
	Statement statement;
	
	if(rbChofer.isSelected()) {
		sRol="Chofer";
	}else if(rbCargador.isSelected()) {
		sRol="Cargador";
	}else {
		sRol="Auxiliar";
	}
	
	if(rbInterno.isSelected()) {
		sTipo="Interno";
	}else {
		sTipo="Externo";
	}
	
	try {
		statement = con.getConnection().createStatement();
		String sql="insert into empleadosrinku values ("+txtNumero.getText().trim()+",'"+txtNombre.getText().trim()+"','"+txtApellido.getText().trim()+"','"+sRol+"','"+sTipo+"')";
		//JOptionPane.showMessageDialog(null, sql);
		statement.executeUpdate(sql);
	} catch (SQLException e) {
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnGuardar){
				NuevoEmpleado();
		}
	}
}
