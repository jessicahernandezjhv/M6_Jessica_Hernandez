package Bateria02_XQJ.ParteI;

import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;

/* EJERCICIO 3. Realiza un programa que devuelva el numero de 
 * productos con precio mayor a 50. */


public class Ex03_Ejercicio03 {
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

			consulta = conn.prepareExpression ("for $prod in /productos\n"
					+ "let $numProd := /$prod/produc[precio > 50]\n"
					+ "return <num_productos>{count($numProd)}</num_productos>");
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
