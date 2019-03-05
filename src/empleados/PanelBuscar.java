package empleados;

import javax.swing.JPanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bdsql.Conexion;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBuscar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmp;
	private JList<String> listaBusqueda;
	private JButton btnBuscar;
	
	Conexion con = new Conexion();
	private JButton btnModificar;
	private JButton btnEliminar;
	
	public PanelBuscar() {
		setLayout(null);
		
		JLabel lblNumero = new JLabel("No. de Empleado:");
		lblNumero.setBounds(75, 14, 106, 14);
		add(lblNumero);
		
		txtEmp = new JTextField();
		txtEmp.setBounds(181, 11, 86, 20);
		add(txtEmp);
		txtEmp.setColumns(10);
		
		listaBusqueda = new JList<String>();
		listaBusqueda.setBounds(29, 50, 388, 206);
		add(listaBusqueda);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(277, 10, 89, 23);
		add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(92, 267, 89, 23);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(277, 267, 89, 23);
		add(btnEliminar);
		
		llenarLista();
			
	}
	
	public void llenarLista(){
		
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    Statement statement;
	    
		try {
			statement = con.getConnection().createStatement();
		    ResultSet resultSet = statement.executeQuery("select * from pruebasenior");
		    while (resultSet.next())
		    {
		       String itemCode = resultSet.getString("nombre");
		        
		        model.addElement(itemCode);
		    }
		    listaBusqueda.setModel(model);
		    resultSet.close();
		    statement.close();
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
	}
}