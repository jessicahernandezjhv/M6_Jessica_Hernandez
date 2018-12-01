package Bateria02_GestionFicheros;

/* Ejercicio 1. Cambia la ruta del ejemplo anterior. 
 * Utiliza una ruta absoluta a tu carpeta de descargas, por ejemplo.
 */

import java.io.*;
public class EX1_VerDirRutaAbsoluta{
	public static void main (String[] args) {
		String dir = "C:\\Users\\Feuer\\Downloads"; // Directorio de descargas (en Windows)

		try {
			File f = new File(dir);
			String[] archivos = f.list();
			System.out.printf("Cantidad de ficheros en el directorio seleccionado: %d %n", archivos.length);

			for (int i = 0; i < archivos.length; i++){
				File f2 = new File(f, archivos[i]);
				System.out.printf("Nombre: %s, es fichero?: %b, es directorio?:%b %n", archivos[i],
						f2.isFile(), f2.isDirectory());
			}
			
		} catch (Exception E) {
			System.out.print("Error salvaje aparecio, el directorio no ha sido encontrado");
		}
	}
}
