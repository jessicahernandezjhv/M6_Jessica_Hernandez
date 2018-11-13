package Bateria02_Streams.Streams1;

/* Ejercicio 3. Modifica el código anterior para que se le puede
 * pasar el nombre del fichero al programa.*/

import java.io.*;

public class EX3_LeerFicheroTextoNombre {
	public static void main ( String [] args) throws IOException {
		System.out.print("Introduce la ruta del fichero a leer: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		
		File fichero = new File (userInput);  // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero

		char[] buf = new char[20];

		while ((flu.read(buf)) != -1) {     //Vamos leyendo de 20 caracteres en 20 caracteres
			System.out.println (buf);	 //Imprimimos los caracteres leidos
		}
		
		flu.close();
	}

}
