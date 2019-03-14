package bdsql;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection ;
import java.sql.DriverManager ;

import principal.ConfigDlg;

public class Conexion {
	
	  Connection c = null;
	  String urlDatabse, sIP, sDatabase, sUsuario, sPass;
	  BufferedReader reader;
	  
	  public Connection getConnection() throws Exception {

			try {
				reader = new BufferedReader(new FileReader("C:/sys/mem/configrinku.txt"));
				String line = reader.readLine();
				sIP = line;
				sDatabase = reader.readLine();
				sUsuario = reader.readLine();
				sPass = reader.readLine();
				reader.close();
				
			} catch (IOException e) {
				ConfigDlg configuracion = new ConfigDlg();
				configuracion.setVisible(true);
			}

		    //String urlDatabase =  "jdbc:postgresql://10.44.65.16:5432/tienda.0004";
		  	String urlDatabase =  "jdbc:postgresql://"+sIP+":5432/"+sDatabase;
		    Class.forName("org.postgresql.Driver"); 
            //return c = DriverManager.getConnection(urlDatabase,  "systienda0004", "b826746ce6270d23b098dc91145208f7");
		    return c = DriverManager.getConnection(urlDatabase,  sUsuario, sPass);
	  }
	  
	  public void initConexion() {
		  
	  }
}
