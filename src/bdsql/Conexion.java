package bdsql;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;

public class Conexion {
	
	  Connection c = null;
	  
	  
	  
	  public Connection getConnection() throws Exception {
		    String urlDatabase =  "jdbc:postgresql://10.44.65.16:5432/tienda.0004";
		    Class.forName("org.postgresql.Driver"); //cargar el driver manualmente
		    
		    //boolean valid = c.isValid(50000);
            //System.out.println(valid ? "TEST OK" : "TEST FAIL");
            return c = DriverManager.getConnection(urlDatabase,  "systienda0004", "b826746ce6270d23b098dc91145208f7");
	  }

}
