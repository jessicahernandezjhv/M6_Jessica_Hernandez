package Bateria02_JDBC2.II;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* EJERCICIO 3. Crea un programa Java que inserte un empleado
 * en la tabla emple, el programa recibe del usuario los 
 * valores a insertar. Los argumentos que recibe son: EMP_NO, 
 * APELLIDO, OFICIO, DIR, SALARIO, COMISION, DEPT_NO. */

public class Ex3_InsertarEmple {
	public static void main(String[] args) {
		String emp_no = "", apellido = "", oficio = "", dir = "", salario = "", comision = "", dept_no = "";

		try {
			emp_no = args[0];
			apellido = args[1];
			oficio = args[2];
			dir = args[3];
			salario = args[4];
			comision = args[5];
			dept_no = args[6];

		} catch ( IndexOutOfBoundsException e ) {
			System.out.println("Introducir los parametros indicados:\n"
					+ "emp_no, apellido, oficio, dir, salario, comision, dept_no");
			System.exit(0);
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/ejemplo","austria","austria");			
			Statement statement = connexion.createStatement();
			String sql = "SELECT * FROM depart WHERE dept_no = " + dept_no;
			ResultSet result = statement.executeQuery(sql);
			
			if ( result.next() == false ) {
				System.out.println("El departamente existe.");
				System.exit(0);
			}

			sql = "SELECT * FROM emple WHERE emp_no = " + emp_no;
			result = statement.executeQuery(sql);
			if (result.next() != false) {
				System.out.println("El ID de empleado está registrado.");
				System.exit(0);
			}
			if (0 > Integer.parseInt(salario)) {
				System.out.println("El sueldo debe ser un número positivo mayor que 0.");
				System.exit(0);
			}

			sql = "SELECT * FROM emple WHERE emp_no = " + dir;
			result = statement.executeQuery(sql);
			if  (result.next() == false) {
				System.out.println("No hay ningun empleado con ese director.");
				System.exit(0);
			}
			if (apellido.equals("") || apellido == null) {
				System.out.println("El apellido no puede ser null.");
				System.exit(0);
			}
			if (oficio.equals("") || oficio == null) {
				System.out.println("El oficio no puede ser null.");
				System.exit(0);
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			sql = "INSERT INTO emple (emp_no, apellido, oficio, dir, salario, comision, dept_no) "
					+ "VALUES(" + emp_no + ",'" + apellido + "','" + oficio + "'," 
					+ dir + "," + salario + "," + comision + "," + dept_no + ")";
			statement.executeUpdate(sql);
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
