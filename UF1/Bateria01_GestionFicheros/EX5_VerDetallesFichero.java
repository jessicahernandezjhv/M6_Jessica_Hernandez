package Bateria01_GestionFicheros;
/* Ejercicio 5. Realiza un programa Java que muestre la siguiente información de un fichero
cualquiera: Nombre, ruta relativa, ruta absoluta, permisos y tamaño. */

import java.io.*;
public class EX5_VerDetallesFichero {
	public static void main (String[] args) throws IOException {
		
		System.out.print("Arrastra un archivo aquí o introduce su ruta manualmente: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = reader.readLine();
		String dir = userInput; // Directorio introducido por el usuario

		try {			
			File f = new File(dir);
			String fileName = f.getName();
			String relativePath = f.getPath();
			String absolutePath = f.getAbsolutePath();
			Boolean readPermits = f.canRead();
			Boolean writePermits = f.canWrite();
			Boolean executePermits = f.canExecute();
			Long fileSize = f.length();

			System.out.println("El fichero '" + fileName + "' se encuentra en la ruta:\n\tRUTA RELATIVA: "+ relativePath+ "\n\tRUTA ABSOLUTA: "+ absolutePath);
			System.out.println("\nLos permisos del fichero son:\n\tLECTURA: "+ readPermits + "\n\tESCRITURA: "+ writePermits + "\n\tEJECUCION: "+ executePermits);
			System.out.println("\nEl tama�o total del fichero es de : " + fileSize + "bytes");
		} catch (Exception E) {
			System.out.print("Error, el directorio no existe o no es correcto");
		}
	}
}

//TODO: La ruta relativa parece que no la muestra