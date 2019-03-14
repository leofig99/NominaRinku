package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdsql.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ConfigDlg extends JDialog implements ActionListener{
	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtDatabase;
	private JTextField txtUsuario;
	private JTextField txtPass;
	private JButton btnGuardar;
	private Conexion con = new Conexion();
	public ConfigDlg() {
		setBounds(100, 100, 277, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(30, 21, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(30, 71, 68, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setBounds(30, 103, 88, 14);
		contentPane.add(lblPass);
		
		JLabel lblBaseDeDatos = new JLabel("Base de Datos:");
		lblBaseDeDatos.setBounds(30, 46, 88, 14);
		contentPane.add(lblBaseDeDatos);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(128, 136, 89, 23);
		contentPane.add(btnGuardar);
		
		txtIP = new JTextField();
		txtIP.setBounds(128, 18, 86, 20);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		txtDatabase = new JTextField();
		txtDatabase.setBounds(128, 43, 86, 20);
		contentPane.add(txtDatabase);
		txtDatabase.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(128, 68, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(128, 100, 86, 20);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		setModal(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGuardar) {
			
		    String fileContent = txtIP.getText()+"\n"+txtDatabase.getText()+"\n"+txtUsuario.getText()+"\n"+txtPass.getText();
		    try {
		    	
		    	BufferedWriter writer = new BufferedWriter(new FileWriter("C:/sys/mem/configrinku.txt"));
				writer.write(fileContent);
				 writer.close();

				dispose();
			 
				JOptionPane.showMessageDialog(null, "Se creó el archivo de configuración en la ruta\nC:/sys/mem/configrinku.txt");
			
		    }catch (IOException e1) {
				
			}
		}
	}
}
