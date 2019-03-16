package reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;
import bdsql.Conexion;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Reportes extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbReporte;
	private Conexion con = new Conexion();
	public String[] tbColumnas = {"Nombre","Rol","Cantidad de Entregas","Cubrio Turno","Sueldo Mensual"};
	private JDateChooser pickerFecha, pickerFecha2;
	private JButton btnGenerar;
	private SimpleDateFormat fechaformato;
	private Date fecha = new Date();
	private String sFecha, sFecha2;
	private JTextField txtNumemp;
	public Reportes() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbReporte = new JTable();
		tbReporte.setBounds(5, 51, 424, 184);
		
		JScrollPane pane = new JScrollPane(tbReporte,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tbReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane.setBounds(29, 75, 380, 175);
		getContentPane().add(pane);
		
		pickerFecha = new JDateChooser();
		pickerFecha.setBounds(32, 44, 86, 20);
		fechaformato = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		pickerFecha.setDate(fecha);
		pickerFecha.setMaxSelectableDate(fecha);
		contentPane.add(pickerFecha);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(294, 41, 89, 23);
		btnGenerar.addActionListener(this);
		contentPane.add(btnGenerar);
		
		pickerFecha2 = new JDateChooser();
		pickerFecha2.setBounds(155, 44, 86, 20);
		pickerFecha2.setDate(fecha);
		pickerFecha2.setMaxSelectableDate(fecha);
		contentPane.add(pickerFecha2);
		
		txtNumemp = new JTextField();
		txtNumemp.setBounds(155, 13, 86, 20);
		contentPane.add(txtNumemp);
		txtNumemp.setColumns(10);
		
		JLabel lblNoDeEmpleado = new JLabel("No. de Empleado:");
		lblNoDeEmpleado.setBounds(29, 19, 99, 14);
		contentPane.add(lblNoDeEmpleado);
		setModal(true);
	}
	
	public void llenarSueldos()
	{
		
		int iSueldoBase=0, iBono,iBonoCubrio, iEntregas,iCubrioTurno, iHorasTrabajadas;
		double iSueldoTotal=0, dSueldoISR, dVales;
		String sRol;
		
		tbReporte.removeAll();
	    DefaultTableModel model = new DefaultTableModel(null,tbColumnas);
	    Statement statement;
	    sFecha=fechaformato.format(pickerFecha.getDate());
	    sFecha2=fechaformato.format(pickerFecha2.getDate());
		try {
		
			statement = con.getConnection().createStatement();
			System.out.println("SELECT count(*) FROM movimientosrinku WHERE fecha between TO_DATE('"+sFecha+"','DD/MM/YY') and TO_DATE('"+sFecha2+"','DD/MM/YY')");
		    ResultSet count = statement.executeQuery("SELECT count(*) FROM movimientosrinku WHERE fecha between TO_DATE('"+sFecha+"','DD/MM/YY') and TO_DATE('"+sFecha2+"','DD/MM/YY') and numemp ="+txtNumemp.getText());
		    count.next();
		    iHorasTrabajadas = Integer.parseInt(count.getObject(1).toString());
		
		    System.out.println("SELECT sum(cantidadentregas) FROM movimientosrinku WHERE fecha between TO_DATE('"+sFecha+"','DD/MM/YY') and TO_DATE('"+sFecha2+"','DD/MM/YY')");
		    ResultSet horastrb = statement.executeQuery("SELECT sum(cantidadentregas) FROM movimientosrinku WHERE fecha between TO_DATE('"+sFecha+"','DD/MM/YY') and TO_DATE('"+sFecha2+"','DD/MM/YY') and numemp = "+txtNumemp.getText());
		    horastrb.next();
		    iEntregas= Integer.parseInt(horastrb.getObject(1).toString());
		    
		    System.out.println("SELECT nombre,rol,cantidadentregas,cubrioturno FROM movimientosrinku WHERE fecha between TO_DATE('\"+sFecha+\"','DD/MM/YY') and TO_DATE('\"+sFecha2+\"','DD/MM/YY')");
		    ResultSet rs = statement.executeQuery("SELECT nombre,rol,cantidadentregas,cubrioturno FROM movimientosrinku WHERE fecha between TO_DATE('"+sFecha+"','DD/MM/YY') and TO_DATE('"+sFecha2+"','DD/MM/YY') and numemp = "+txtNumemp.getText());
		
		    while (rs.next())
		    {
		    	
		    	Object[] filas = new Object[4];
		    	for (int i = 0; i < 4; i++)
		    	{
		    	   filas[i] = rs.getObject(i+1);
		    	}
		    	
		        model.addRow(filas);
		        //iEntregas= Integer.parseInt(model.getValueAt(model.getRowCount()-1,2).toString());
		        iCubrioTurno = Integer.parseInt(model.getValueAt(model.getRowCount()-1,3).toString());
		        sRol = model.getValueAt(model.getRowCount()-1,3).toString();
		        
		        switch(sRol)
		        {
		        case "Chofer":	iBono=10*8;
		        	break;
		        case "Cargador": iBono=10*5;
		        	break;
		        default: iBono=0;
		        	break;
		        }
		        
		        switch(iCubrioTurno) 
		        {
		        case 1:iBonoCubrio=10*8;
		        break;
		        case 2: iBonoCubrio=10*5;
		        break;
		        default: iBonoCubrio=0;
		        break;
		        }
		        iSueldoBase=iHorasTrabajadas*30;
		        iSueldoTotal = iSueldoBase +(iEntregas*5)+iBono+iBonoCubrio;
		        dVales = iSueldoTotal*0.04;
		        
		        iSueldoTotal+=dVales;
		        
		        if(iSueldoTotal>16000) {
		        	dSueldoISR=iSueldoTotal-(iSueldoTotal*0.12);
		        }else {
		        	dSueldoISR=iSueldoTotal-(iSueldoTotal*0.09);
		        }
		        model.setValueAt(String.format("$%,.2f", dSueldoISR), model.getRowCount()-1, 4);
		        //JOptionPane.showMessageDialog(null, dSueldoISR);
		    }
		    
		    tbReporte.setModel(model);
		    
		    if(tbReporte.getRowCount()==0) {
		    	JOptionPane.showMessageDialog(null,"No se encontro ningun registro.", "Buscar",JOptionPane.INFORMATION_MESSAGE);
		    }else {
		    	
		    	toExcel(tbReporte);
		    }
		    
		    rs.close();
		    statement.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar reporte");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar reporte");
		}
	}

	public void toExcel(JTable table){
	    try{
	    	File file = new File("C:\\sys\\mem\\NominaRinku\\ReporteRinku"+sFecha+".xls");
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter(file);

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }

	        excel.close();
	        JOptionPane.showMessageDialog(null, "Archivo generado exitosamente");
	    }catch(IOException e){ 
	    		e.printStackTrace();
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGenerar) {
			if(txtNumemp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Ingrese numero de empleado");
			}else {
				llenarSueldos();
			}
		}
	}
}
