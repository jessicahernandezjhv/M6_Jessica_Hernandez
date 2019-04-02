package Bateria02_XQJ.ParteI;

import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;

/* EJERCICIO 4. Realiza un programa que devuelva todos 
 * los empleados del departamento 10.   */

public class Ex04_Ejercicio04 {
	public static void main(String[] args){
		
		try{
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");

			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;

			consulta = conn.prepareExpression ("for $emp in "
					+ "doc('nueva/empleados.xml')"
					+ "/EMPLEADOS/EMP_ROW[DEPT_NO=10] return $emp");
			resultado = consulta.executeQuery();

			while (resultado.next()) {
				System.out.println(resultado.getItemAsString(null));
			}
			conn.close();

		} catch (XQException ex) {
			System.out.println("Error al operar"+ex.getMessage());
		}
	}
}