package Bateria03_Streams.Streams3;

/* Ejercicio 1. Escribe un programa en java que muestre por pantalla un fichero de texto
que le pasamos como argumento (o utilizando scanner) utilizando la clase
BufferedReader */

import java.io.*;

public class EX1_LeerFicheroBufferedReader {
	public static void main (String [] args) {
		String dir = args[0]; // Directorio introducido por parametros

		try {
			BufferedReader fichero = new BufferedReader (new FileReader (dir));
			String linea;

			while ((linea = fichero.readLine()) != null) { // leemos de l�nea en l�nea
				System.out.println (linea);
			}

			fichero.close();
		}

		catch (FileNotFoundException fn) {
			System.out.println ("No se encuentra el fichero"); 
		}

		catch (IOException io) {
			System.out.println ("Error de E/S"); 
		}
	}
}
