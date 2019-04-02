package Bateria03_ActualizacionXQuery;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/* EJERCICIO 02. Realiza un programa que añada un departamento nuevo al fichero departamentos.xml.
 * Los datos del nuevo departamento son:
 * a. DEPT_NO → 50
 * b. DNOMBRE → INFORMÁTICA
 * c. LOC → Valencia */

public class Ex02_Ejercicio02 {
	public static void main(String[] args) {
		try {
			// Configuramos conexión como hemos hecho en ocasiones anteriores 
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName","192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password", "austria");	
			XQConnection conn = server.getConnection();
			XQExpression consulta = conn.createExpression();

			// Ejecutamos la expresión XQuery: actualiza el apellido del empleado con EMP_NO=7369 a 1009
			String actual = "update insert"
					+ "<DEP_ROW>\n" + 
					"  <DEPT_NO>50</DEPT_NO>\n" + 
					"  <DNOMBRE>INFORMÁTICA</DNOMBRE>\n" + 
					"  <LOC>VALENCIA</LOC>\n </DEP_ROW> "
					+ "into /departamentos";
			consulta.executeCommand(actual);
			System.out.print("Datos actualizados.");	
		} catch (XQException e) {
			e.printStackTrace();
		}
	}
}

