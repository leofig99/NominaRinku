package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import empleados.VentanaEmpleados;

import java.awt.GridLayout;
import javax.swing.JButton;
import movimientos.VentanaMovimientos;
import reportes.Reportes;

public class MainApp extends JFrame implements ActionListener{

	private JPanel panelPrincipal;
	JButton btnEmpleados,btnMovimientos, btnReportes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainApp frame = new MainApp();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.addActionListener(this);
		panelPrincipal.add(btnEmpleados);
		
		btnMovimientos = new JButton("Movimientos");
		btnMovimientos.addActionListener(this);
		panelPrincipal.add(btnMovimientos);
		
		btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(this);
		panelPrincipal.add(btnReportes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnEmpleados){
			VentanaEmpleados frame = new VentanaEmpleados();
			frame.setVisible(true);
		}
		
		if(e.getSource()==btnMovimientos){
			VentanaMovimientos frame = new VentanaMovimientos();
			frame.setVisible(true);
		}
		
		if(e.getSource()==btnReportes){
			Reportes reportes = new Reportes();
			reportes.setVisible(true);
		}
	}
}
