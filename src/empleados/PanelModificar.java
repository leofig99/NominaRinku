package empleados;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

import bdsql.Conexion;

import javax.swing.border.BevelBorder;

public class PanelModificar extends JPanel implements ActionListener {
	
	private JTextField txtEmp;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JRadioButton rbChofer,rbCargador,rbAuxiliar,rbInterno,rbExterno;
	private JButton btnGuardar;
	private JButton btnConsultar;
	Conexion con = new Conexion();
	private String sNombre,sApellido,sRol,sTipo;
	
	
	public PanelModificar() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de Empleado:");
		lblNumero.setBounds(84, 33, 94, 14);
		add(lblNumero);
		
		txtEmp = new JTextField();
		txtEmp.setBounds(188, 30, 86, 20);
		txtEmp.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c)||txtEmp.getText().length()==8){
	                e.consume();
	            }
	        }
	    });
		add(txtEmp);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(24, 82, 65, 14);
		add(lblNombre);
		
		txtNombre = new JTextField(20);
		txtNombre.setEnabled(false);
		txtNombre.setBounds(84, 79, 86, 20);
		txtNombre.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (Character.isDigit(c)||txtNombre.getText().length()==18) {
	                e.consume();
	            }
	        }
	    });
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(209, 82, 65, 14);
		add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setBounds(284, 79, 86, 20);
		txtApellido.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (Character.isDigit(c)||txtApellido.getText().length()==18) {
	                e.consume();
	            }
	        }
	    });
		add(txtApellido);
		
		JPanel panelRol = new JPanel();
		panelRol.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelRol.setBounds(24, 126, 185, 95);
		add(panelRol);
		panelRol.setLayout(null);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 11, 46, 14);
		panelRol.add(lblRol);
		
		rbChofer = new JRadioButton("Chofer",true);
		rbChofer.setEnabled(false);
		rbChofer.setBounds(44, 7, 109, 23);
		panelRol.add(rbChofer);
		
		rbCargador = new JRadioButton("Cargador");
		rbCargador.setEnabled(false);
		rbCargador.setBounds(44, 32, 109, 23);
		panelRol.add(rbCargador);
		
		rbAuxiliar = new JRadioButton("Auxiliar");
		rbAuxiliar.setEnabled(false);
		rbAuxiliar.setBounds(44, 58, 109, 23);
		panelRol.add(rbAuxiliar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(235, 126, 185, 95);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 46, 14);
		panel.add(lblTipo);
		
		rbInterno = new JRadioButton("Interno",true);
		rbInterno.setEnabled(false);
		rbInterno.setBounds(53, 7, 109, 23);
		panel.add(rbInterno);
		
		rbExterno = new JRadioButton("Externo");
		rbExterno.setEnabled(false);
		rbExterno.setBounds(53, 35, 109, 23);
		panel.add(rbExterno);
		
		ButtonGroup  grpRol = new ButtonGroup();
		grpRol.add(rbChofer);
		grpRol.add(rbCargador);
		grpRol.add(rbAuxiliar);
		
		ButtonGroup  grpTipo = new ButtonGroup();
		grpTipo.add(rbInterno);
		grpTipo.add(rbExterno);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(277, 236, 89, 23);
		add(btnGuardar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(284, 29, 89, 23);
		add(btnConsultar);
		
	}

	
	public void limpiarCampos() {
	    txtNombre.setText("");	    
	    txtApellido.setText("");
	    rbChofer.setSelected(true);
	    rbInterno.setSelected(true);
	    btnGuardar.setEnabled(false);
	}
	
	public void cargarDatos() {

		   Statement statement;
		    
			limpiarCampos();
				try {
					statement = con.getConnection().createStatement();
				    ResultSet rs = statement.executeQuery("SELECT * FROM empleadosrinku WHERE numemp="+txtEmp.getText()); 
				    rs.next();
				
				    sNombre=rs.getObject(2).toString();
				    sApellido=rs.getObject(3).toString();
				    sTipo=rs.getObject(5).toString().trim();
				    sRol=rs.getObject(4).toString().trim();
				    //JOptionPane.showMessageDialog(null, sNombre+" "+sApellido+" "+sRol+" "+sTipo);
				    
				    
				    txtNombre.setText(sNombre);
				    
				    txtNombre.setEnabled(true);
				    
				    txtApellido.setText(sApellido);
				    txtApellido.setEnabled(true);
				    
				    rbChofer.setEnabled(true);
				    rbCargador.setEnabled(true);
				    rbAuxiliar.setEnabled(true);
				    
				    rbInterno.setEnabled(true);
				    rbExterno.setEnabled(true);
				    
				    btnGuardar.setEnabled(true);
				    
				    switch(sRol) {
				    case "Chofer": rbChofer.setSelected(true);
				    	break;
				    case "Cargador": rbCargador.setSelected(true);
				    	break;
				    case "Auxiliar":rbAuxiliar.setSelected(true);
				    	break;
				    default: rbChofer.setSelected(true);
				    }
				    
				    switch(sTipo){
				    case "Interno": rbInterno.setSelected(true);
				    	break;
				    case "Externo": rbExterno.setSelected(true);
				    	break;
				    default: rbInterno.setSelected(true);
				    }
				    
				} catch (SQLException e) {
			    	JOptionPane.showMessageDialog(null,"No se encontro el empleado.", "ERROR",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	public void modificarEmp() {
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
			String sql="UPDATE empleadosrinku set nombre='"+txtNombre.getText()+"', apellido='"+txtApellido.getText()+"', rol='"+sRol+"', tipo='"+sTipo+"' WHERE numemp="+txtEmp.getText();
			statement.executeUpdate(sql); 
			//JOptionPane.showMessageDialog(null, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnConsultar) {
			if(!txtEmp.getText().isEmpty()) {
				cargarDatos();
				}else {
			    	JOptionPane.showMessageDialog(null,"Favor de ingresar No. de empleado.", "Buscar",JOptionPane.INFORMATION_MESSAGE);
				}
		}
	}

}
