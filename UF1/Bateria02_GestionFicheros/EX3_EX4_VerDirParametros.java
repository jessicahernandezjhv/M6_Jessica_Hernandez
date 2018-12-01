package Bateria02_GestionFicheros;
/*Ejercicio 3. Realiza un programa Java que utilice el método listFiles() para mostrar la lista de
ficheros de un directorio que se pasará al programa desde los argumentos del main */

/*Ejercicio 4. Añade al programa anterior las instrucciones necesarias para que envíe un mensaje de
error en caso de que el directorio pasado como argumento no exista. */

import java.io.*;
public class EX3_EX4_VerDirParametros {
	public static void main (String[] args) {
		String dir = args[0]; // Directorio introducido por parametros

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

		