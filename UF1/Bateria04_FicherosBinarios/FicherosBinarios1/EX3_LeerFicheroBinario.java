package Bateria04_FicherosBinarios.FicherosBinarios1;

/* Ejercicio 3. Ahora escribe un programa que permita visualizar los datos grabados
anteriormente en el fichero FicheroDatos.dat. Se deben obtener en el mismo
orden en el que se escribieron, es decir, primero obtenemos el nombre y
luego la edad. */

import java.io.*;

public class EX3_LeerFicheroBinario {
	public static void main (String [] args) throws IOException	{

		File fichero = new File ("FicheroDatos.dat");
		DataInputStream filein = new DataInputStream (new FileInputStream(fichero));
		
		for (int i=0; i<5; i++) {
			System.out.print(filein.readUTF());
			System.out.println(filein.readInt());
		}
		filein.close();
	}
}
