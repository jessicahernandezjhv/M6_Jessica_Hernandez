package Bateria02_XQJ.ParteII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

/* EJERCICIO 01. A partir del documento universidad.xml, haz un programa que muestre los
 * empleados del departamento cuyo tipo es elegido por el usuario. Si no hay empleados o
 * el tipo de departamento aportado por el usuario no existe, se debe de informar al usuario*/

public class EX01_Ejercicio {
	public static void main(String[] args) throws XQException, IOException{
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			XQDataSource server = new ExistXQDataSource();

			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");

			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;

			System.out.print("Escoge un tipo: ");
			String user = reader.readLine();

			consulta = conn.prepareExpression ("for $univ in /universidad\n let $dept:=$univ/departamento[@tipo='" + user + "']\n" + 
					"return $dept\n");
			resultado = consulta.executeQuery();

			while (resultado.next()) {
				if((!user.equals("A") || !user.equals("B")) || (resultado.next() == false)) {
					System.out.println(resultado.getItemAsString(null));
				}
			}
			conn.close();

		} catch (XQException ex) {
			System.out.println("Error al operar"+ex.getMessage());}
	}
}