package Bateria01_JDBC1;

/* EJERCICIO 1. Tomando como base el programa que ilustra los pasos 
 * del funcionamiento de JDBC obtén el APELLIDO, OFICIO y SALARIO de
 * los empleados del departamento 10 */

import java.sql.*;

public class Ex1_DatosEmple {
	public static void main(String[] args) {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection 
					("jdbc:mysql://localhost/ejemplo","austria","austria");
			Statement sentencia =conexion.createStatement();
			String sql = "SELECT * from emple where dept_no = 10";
			ResultSet result = sentencia.executeQuery(sql);

			while (result.next()){
				System.out.printf("%s, %s, %d, %n",
						result.getString(2),
						result.getString(3),
						result.getInt(6));
			}

			result.close();
			sentencia.close();
			conexion.close();
			
		} catch (ClassNotFoundException cn) { cn.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
	}
}
