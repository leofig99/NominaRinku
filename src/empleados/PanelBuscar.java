package empleados;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import bdsql.Conexion;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;

public class PanelBuscar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmp;
	private JTable tbBusqueda;
	private JButton btnBuscar;
	private String sNumEmp;
	public Conexion con = new Conexion();
	private JButton btnBaja;
	public String[] tbColumnas = {"No. Empleado","Nombre","Apellido","Rol","Tipo","Status"};
	public PanelBuscar() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de Empleado:");
		lblNumero.setBounds(75, 14, 106, 14);
		add(lblNumero);
		
		txtEmp = new JTextField();
		txtEmp.setBounds(181, 11, 86, 20);
		add(txtEmp);
		
		tbBusqueda = new JTable(null, tbColumnas);
		tbBusqueda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = tbBusqueda.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		      if(tbBusqueda.getSelectedRow()<0) {
			       btnBaja.setEnabled(false);
		      }else {
			      btnBaja.setEnabled(true);
		      }
		    }
		});
		JScrollPane pane = new JScrollPane(tbBusqueda);
		pane.setBounds(29, 50, 435, 175);
		add(pane);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(277, 10, 89, 23);
		add(btnBuscar);
		
		btnBaja = new JButton("Baja");
		btnBaja.addActionListener(this);
		btnBaja.setBounds(277, 236, 89, 23);
		btnBaja.setEnabled(false);
		add(btnBaja);
		
		llenarLista();
	}
	
	public void llenarLista(){
		tbBusqueda.removeAll();
	    DefaultTableModel model = new DefaultTableModel(null,tbColumnas);
	    Statement statement;
	    
		try {
			statement = con.getConnection().createStatement();
		    ResultSet rs = statement.executeQuery("SELECT * FROM empleadosrinku WHERE status=1");
		    ResultSetMetaData metaDatos = rs.getMetaData();
		    while (rs.next())
		    {
		    	int numeroColumnas = metaDatos.getColumnCount();
		    	Object[] etiquetas = new Object[numeroColumnas];
		    	for (int i = 0; i < numeroColumnas; i++)
		    	{
		    	   etiquetas[i] = rs.getObject(i+1);
		    	}
		        model.addRow(etiquetas);
		    }
		    tbBusqueda.setModel(model);
		    rs.close();
		    statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void darBaja() {
		Statement statement;
		sNumEmp=tbBusqueda.getValueAt(tbBusqueda.getSelectedRow(), 0).toString();
		try {
			statement = con.getConnection().createStatement();
			String sql="UPDATE empleadosrinku SET status=0 WHERE numemp ="+sNumEmp;
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBuscar) {

		}
		if(e.getSource()==btnBaja) {
			darBaja();
			llenarLista();
		}
	
	}
}