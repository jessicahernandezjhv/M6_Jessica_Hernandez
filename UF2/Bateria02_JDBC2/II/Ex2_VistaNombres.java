package Bateria02_JDBC2.II;

/* EJERCICIO 2. Realiza un programa que cree una vista 
 * (de nombre “totales”) que contenga por cada departamento
 * el número de departamento, el nombre, el número de empleados
 * que tiene y el salario medio. */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex2_VistaNombres {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		      Connection conexion = DriverManager.getConnection
		          ("jdbc:mysql://localhost/ejemplo","austria","austria");			
			Statement sentencia = conexion.createStatement();
			String instruccionSQL = String.format("create view totales as (select e.dept_no, d.dnombre,"
					+ "count(e.emp_no) 'num_empleados',avg(e.salario) from emple e inner join depart "
					+ "d on e.dept_no=d.dept_no group by e.dept_no)");
			String instruccionSQL2 = String.format("select * from totales");

			System.out.println(instruccionSQL);
			sentencia.executeUpdate(instruccionSQL);
			ResultSet result2 = sentencia.executeQuery(instruccionSQL2);

			while (result2.next()) {
				System.out.printf("%s, %s, %s, %s %n", result2.getString(1), result2.getString(2), result2.getString(3), result2.getString(4));
			}

			sentencia.close();
			conexion.close();
			
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
