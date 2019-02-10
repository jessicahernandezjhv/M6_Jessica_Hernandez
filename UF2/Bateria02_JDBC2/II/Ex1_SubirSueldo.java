package Bateria02_JDBC2.II;

/* EJERCICIO 1. Realiza un programa en Java que suba el 
 * salario a los empleados de un departamento. El programa
 * recibirá el número de departamento y el incremento. */

import java.sql.*;

public class Ex1_SubirSueldo {
	public static void main (String[] args){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		      Connection conexion = DriverManager.getConnection
		          ("jdbc:mysql://localhost/ejemplo","austria","austria");
			
			String departamento = args[0];
			String incremento = args[1];
			
			String sql = String.format("UPDATE emple SET salario = salario + %s where dept_no = %s", incremento, departamento);
			//System.out.println(sql);
			Statement sentencia = conexion.createStatement();
			int filas = sentencia.executeUpdate(sql);
			System.out.printf("Filas afectadas: %d %n", filas);
			
			sentencia.close();
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
	
}
