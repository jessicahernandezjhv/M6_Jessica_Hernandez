package Bateria02_XQJ.ParteII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

/* EJERCICIO 02. A partir de los documentos productos.xml y zonas.xml, haz un programa que reciba
 * un n�mero de zona por par�metro y genere un documento con nombre zonaXX.xml donde XX es la zona
 * solicitada. El documento debe contener los productos de esta zona y las siguientes etiquetas: 
 * <cod_prod>, <denominaci�n>, <precio>, <nombre_zona>, <director> y <stock>. Donde el stock se
 * calcula restando el stock actual y el stock m�nimo.*/


public class EX02_Ejercicio {
	public static void main(String[] args) throws XQException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");

			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;

			System.out.println("Introduce el número de la zona que deseas exportar: ");
			String user = reader.readLine();

			consulta = conn.prepareExpression(
					"let $prod := /productos/produc[cod_zona="+user+"]"
							+ " let $numerozona := /zonas/zona[cod_zona="+user+"]/nombre"
							+ " let $dirZona := /zonas/zona[cod_zona="+user+"]/director"
							+ " return"
							+ " <productos>{"
							+ " for $prod in $prod"
							+ " let $stock:=$prod/(stock_actual - stock_minimo)"
							+ " return"
							+ " <producto>"
							+ " <cod_prod>{data($prod/cod_prod)}</cod_prod>"
							+ " <denominacion>{data($producto/denominacion)}</denominacion>"
							+ " <precio>{data($prod/precio)}</precio>"
							+ " <nombre_zona>{data($numerozona)}</nombre_zona>"
							+ " <director>{data($dirZona)}</director>"
							+ " <stock>{data($stock)}</stock>"
							+ " </producto>"
							+ " }"
							+ " </productos>");

			resultado = consulta.executeQuery();

			String archivo = "zona"+user+".xml";
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

			writer.write("<?xml version='1.0' encoding='UTF-8'?>");
			writer.newLine();

			while (resultado.next()) {
				String line = resultado.getItemAsString(null);
				writer.write(line);
				writer.newLine();
			}

			conn.close();
			writer.close();
			System.out.print("Se generado el fichero correctamente.");

		} catch (XQException ex) {
			System.out.println("Error al operar"+ex.getMessage());
		}
	}
}
