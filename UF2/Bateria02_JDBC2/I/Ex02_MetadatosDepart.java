package Bateria1_JDBC.JDBC2;
import java.sql.*;

/* EJERCICIO 2. Genera un programa en Java que muestre el nombre, el tipo, el tamaño y si 
puede ser nulo o no, de las columnas de la tabla departamentos. */

public class Ex02_MetadatosDepart {
	public static void main (String [] args) {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection conexion = DriverManager.getConnection
	          ("jdbc:mysql://localhost/ejemplo","austria","austria");
	      DatabaseMetaData dbmd = conexion.getMetaData();
	      ResultSet result = null;

	      result = dbmd.getColumns(null,"ejemplo","depart",null);
	      
	      while (result.next()){
	        String name = result.getString(4);
	        String typeName = result.getString(6);
	        String size = result.getString(7);
	        String nullType = result.getString(11);
	        
	        if (nullType == "1") {
	        	nullType = "Si";
	        } else {
	        	nullType = "No";
	        }
	        
	        System.out.printf("- Nombre: %s, Tipo de Dato: %s, Tamaño: %s, Puede ser null: %s %n", name, typeName, size, nullType);
	        
	      }
	      conexion.close();
	    }
	    catch (ClassNotFoundException cn) {cn.printStackTrace();}
	    catch (SQLException e) {e.printStackTrace();}
	  }
	}