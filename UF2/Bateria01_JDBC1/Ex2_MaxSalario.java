package Bateria01_JDBC1;

/* EJERCICIO 2. Realiza otro programa Java utilizando la base de 
 * datos “ejemplo” que visualice el APELLIDO del empleado con 
 * máximo salario, visualiza también su SALARIO y el nombre del 
 * departamento. */

import java.sql.*;

public class Ex2_MaxSalario {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","austria","austria");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * from emple join depart using(dept_no) where salario = (select MAX(salario) from emple);";
			ResultSet result = sentencia.executeQuery(sql);

			while (result.next()) {
				System.out.printf("%s, %s, %d, %n",
						result.getString("apellido"),
						result.getString("oficio"),
						result.getInt("salario"));

				System.out.println("");
				//System.out.printf("%s,%n", result.getString("apellido"));
				//System.out.printf("%s,%n", result.getString("oficio"));
				//System.out.printf("%d,%n \n", result.getInt("salario"));
			}

			result.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
