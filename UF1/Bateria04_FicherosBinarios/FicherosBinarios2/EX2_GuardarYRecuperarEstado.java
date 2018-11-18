package Bateria04_FicherosBinarios.FicherosBinarios2;

/* Ejercicio 2. Diseña una clase que llamarás GuardarYRecuperarEstado que disponga de
dos métodos: guardarEstado que permitirá guardar en un archivo el estado
de la partida y otro, recuperarEstado, que permitirá recuperar el estado de
una partida desde archivo. */

import java.io.*;

// TODO: Solucionar crear objeto de tipo EstadoPartida

public class EX2_GuardarYRecuperarEstado {
	public static void main (String[] args) throws IOException {
		File fichero = new File ("EstadoPartida.dat");
		FileOutputStream fileout = new FileOutputStream (fichero);
		ObjectOutputStream dataOS = new ObjectOutputStream (fileout);
		EX1_EstadoPartida partida1 = new EX1_EstadoPartida ({0,1,1}, 1);
		dataOS.writeObject (partida1);
		dataOS.close();
	}
}
