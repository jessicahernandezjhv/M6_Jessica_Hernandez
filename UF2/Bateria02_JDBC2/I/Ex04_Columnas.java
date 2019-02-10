package Bateria02_JDBC2.I;

/* EJERCICIO 4. Busca información sobre la interfaz ResultSetMetaData y
 * realiza un programa utilizando dicha interfaz que obtenga el 
 * número de columnas y el tipo de columnas devueltos por la 
 * consulta “SELECT * FROM DEPARTAMENTOS”. */

import java.sql.*;

public class Ex04_Columnas {
	public static void main (String [] args) {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection conexion = DriverManager.getConnection
	          ("jdbc:mysql://localhost/ejemplo","austria","austria");
	      DatabaseMetaData dbmd = conexion.getMetaData();
	      String sql = "SELECT * from depart";
	      
	      Statement sentencia = conexion.createStatement();
	      ResultSet result = sentencia.executeQuery(sql);
	      ResultSetMetaData rsmd =  result.getMetaData();  
	            
	      int columns = rsmd.getColumnCount();
	      result = dbmd.getColumns(null,"ejemplo","depart",null);

	      System.out.printf("La tabla tiene %d columnas\n",columns);
	      
	      while (result.next()){
	        String typeName = result.getString(6);
	        System.out.printf("type: %s, %n", typeName);
	      }

	      conexion.close();
	    }
	    catch (ClassNotFoundException cn) {cn.printStackTrace();}
	    catch (SQLException e) {e.printStackTrace();}
	  }
	}
