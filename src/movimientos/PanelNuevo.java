package movimientos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.border.SoftBevelBorder;

import com.toedter.calendar.JDateChooser;

import bdsql.Conexion;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

public class PanelNuevo extends JPanel implements ActionListener {
	private JTextField txtnoemp;
	private JTextField txtNombre;
	private JButton btnGuardar;
	private Conexion con = new Conexion();
	private JComboBox<String> cbRol,cbTipo,cbTurno;
	private JSpinner spinEntregas;
	private int iTurno=0;
	private JCheckBox chTurno;
	private JDateChooser pickFecha;
	private SimpleDateFormat fechaformato;
	private String sFecha,sql;
	private Statement statement;
	private Date fecha = new Date();
	
	public PanelNuevo() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de empleado: ");
		lblNumero.setBounds(24, 27, 105, 14);
		add(lblNumero);
		
		txtnoemp = new JTextField();
		txtnoemp.setToolTipText("Capturar n\u00FAmero de empleado.");
		txtnoemp.setBounds(139, 24, 86, 20);
		
		txtnoemp.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c)||txtnoemp.getText().length()==8) 
	            {
	                e.consume();
	            }
	            
	            if(e.getKeyChar()==KeyEvent.VK_ENTER) {
	            	if(!txtnoemp.getText().isEmpty()) {
	            		consultarEmpleado();
	    				}else {
	    			    	JOptionPane.showMessageDialog(null,"Favor de ingresar No. de empleado.", "Buscar",JOptionPane.INFORMATION_MESSAGE);
	    				}
	            }
	        }
	    });
		
		add(txtnoemp);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(24, 61, 70, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(104, 61, 86, 20);
		txtNombre.setEnabled(false);
		add(txtNombre);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(24, 95, 46, 14);
		add(lblRol);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(24, 137, 46, 14);
		add(lblFecha);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(228, 95, 46, 14);
		add(lblTipo);
		
		JLabel lblCantidadDeEntregas = new JLabel("Cantidad de Entregas");
		lblCantidadDeEntregas.setBounds(24, 179, 134, 14);
		add(lblCantidadDeEntregas);
		
		chTurno = new JCheckBox("Cubrio turno");
		chTurno.setEnabled(false);
		chTurno.setBounds(228, 175, 97, 23);
		add(chTurno);
		
		spinEntregas = new JSpinner();
		spinEntregas.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinEntregas.setBounds(24, 204, 105, 20);
		add(spinEntregas);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(236, 238, 89, 23);
		add(btnGuardar);
		
		cbRol = new JComboBox<String>();
		cbRol.setBounds(66, 92, 92, 20);
		cbRol.addItem("Chofer");
		cbRol.addItem("Cargador");
		cbRol.addItem("Auxiliar");
		cbRol.setEnabled(false);
		add(cbRol);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(284, 92, 92, 20);
		cbTipo.addItem("Interno");
		cbTipo.addItem("Externo");
		cbTipo.setEnabled(false);
		add(cbTipo);
		
		pickFecha = new JDateChooser();
		pickFecha.setDateFormatString("dd/MM/yyyy");
		fechaformato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		
		pickFecha.setMaxSelectableDate(fecha);
		pickFecha.setDate(fecha);
		pickFecha.setBounds(72, 134, 86, 20);
		
		add(pickFecha);
		
		cbTurno = new JComboBox<String>();
		cbTurno.setBounds(228, 205, 97, 20);
		cbTurno.addItem("Chofer");
		cbTurno.addItem("Cargador");
		cbTurno.setEnabled(false);
		chTurno.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	cbTurno.setEnabled(true);
		        } else {
		            cbTurno.setEnabled(false);
		        }
		    }

		});
		add(cbTurno);
		
		//JOptionPane.showMessageDialog(null, "busqueda");
	}
	
	private void consultarEmpleado(){
		
		try {
			statement = con.getConnection().createStatement();
			sql = "SELECT * FROM empleadosrinku WHERE numemp="+txtnoemp.getText();
		    ResultSet rs = statement.executeQuery(sql); 
		    rs.next();
		    
		    switch(rs.getObject(4).toString().trim()) {
		    case "Chofer": cbRol.setSelectedIndex(0);;
	    	break;
		    case "Cargador": cbRol.setSelectedIndex(1);
		    	break;
		    case "Auxiliar":cbRol.setSelectedIndex(2);
		    				chTurno.setEnabled(true);
		    	break;
		    default: cbRol.setSelectedIndex(0);
		    }
		    
		    switch(rs.getObject(5).toString().trim())
		    {
		    case "Interno": cbTipo.setSelectedIndex(0);
	    	break;
		    case "Externo": cbTipo.setSelectedIndex(1);
		    	break;
	    	default:
	    		break;
		    }
		    
		    txtNombre.setText(rs.getObject(2).toString());
		    
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se encontro el empleado!.", "Busqueda",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void NuevoMovto() {
		if(!(validarFechaMovto()>0)) {
			
			Statement statement;
			String sql;
		
			if(!chTurno.isSelected()){
				iTurno=0;
			}else{
				if(cbTurno.getSelectedItem().toString()=="Chofer") {
					iTurno=1;
				}else {
					iTurno=2;
				}
			}
			
			sFecha=fechaformato.format(pickFecha.getDate());
			
			try {
				statement = con.getConnection().createStatement();
				
				sql="INSERT INTO movimientosrinku VALUES ("+txtnoemp.getText().trim()+",'"+txtNombre.getText().trim()+"','"+cbRol.getSelectedItem().toString()+"','"+cbTipo.getSelectedItem().toString()+"',TO_DATE('"+sFecha+"','DD/MM/YY'),"+(Integer)spinEntregas.getValue()+","+iTurno+")";
				statement.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(null,"Movimiento guardado correctamente!", "Exito",JOptionPane.INFORMATION_MESSAGE);									//TO_DATE('13/03/2019','DD/MM/YYYY')

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Hubo un error al guardar el movimiento.", "ERROR",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Ya existe un movimiento con ese día.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void limpiarCampos() {
		txtnoemp.setText("");
		txtNombre.setText("");
		spinEntregas.setValue(0);
		chTurno.setSelected(false);
		chTurno.setEnabled(false);
		pickFecha.setDate(fecha);
		cbTurno.setEnabled(false);
	}

	public int validarFechaMovto() {
		int resultado=0;
		sFecha=fechaformato.format(pickFecha.getDate());
		try {
			statement = con.getConnection().createStatement();
			sql = "SELECT COUNT(*) FROM movimientosrinku WHERE numemp=123456 AND fecha=TO_DATE('"+sFecha+"','DD/MM/YY')";
		    ResultSet rs = statement.executeQuery(sql); 
		    rs.next();
		    resultado=Integer.parseInt(rs.getObject(1).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGuardar) {
			if(!txtNombre.getText().isEmpty()) {
			NuevoMovto();
			limpiarCampos();
			}else {
				JOptionPane.showMessageDialog(null, "Favor de consultar un empleado primero");
			}
		}
	}

}
