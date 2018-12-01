package Bateria02_GestionFicheros;

/* Ejercicio 2. Ahora haz los cambios necesarios para que el programa anterior muestre los ficheros
del directorio introducido desde línea de comandos al ejecutar el programa */

import java.io.*;
public class EX2_VerDirUserInput{
	public static void main (String[] args) throws IOException {
		
		System.out.print("Introduce el directorio a listar: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String dir = userInput; 

		try {
			File f = new File(dir);
			String[] archivos = f.list();
			System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);

			for (int i=0; i<archivos.length; i++){
				File f2 = new File(f, archivos[i]);
				System.out.printf("Nombre: %s, es fichero?: %b, es directorio?:%b %n", archivos[i],
						f2.isFile(), f2.isDirectory());
			}
		} catch (Exception E) {
			System.out.print("Error, el directorio no existe o no es correcto");
		}
	}
}