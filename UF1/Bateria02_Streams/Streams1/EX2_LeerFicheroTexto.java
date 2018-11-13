package Bateria02_Streams.Streams1;

/* Ejercicio 2. Modifica el código anterior para que el programa vaya 
 * leyendo caracteres de 20 en 20. */

import java.io.*;

public class EX2_LeerFicheroTexto {
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("./././UF1/Bateria02_Streams/Streams1/EX1_LeerFicheroTexto.java");  // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero

		char[] buf = new char[20];

		while ((flu.read(buf)) != -1) {     //Vamos leyendo de 20 caracteres en 20 caracteres
			System.out.println (buf);	 //Imprimimos los caracteres leidos
		}
		
		flu.close();
	}
}
