package Bateria02_GestionFicheros;
/* Ejercicio 7. Realiza un programa que elimine el directorio creado en el punto anterior. Para ello
habrás de eliminar todos los archivos que se encuentren dentro del directorio. */

import java.io.*;

public class EX7_EliminarDirectorio {
	public static void main(String[] args) {
		File dir = new File(".\\NuevoDir");

		try {
			if (dir.exists()) {
				for (File file : dir.listFiles()) {
					file.delete();
				}

				dir.delete();
				System.out.println("El direcotio se ha eliminado correctamente");
			} else {
				System.out.println("El directorio no existe");
			}
		} catch (Exception E ) {
			System.out.println("Error. Algo no funcionó correctamente");
		}
	}
}

