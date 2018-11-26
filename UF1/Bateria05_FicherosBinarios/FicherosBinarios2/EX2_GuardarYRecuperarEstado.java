package Bateria05_FicherosBinarios.FicherosBinarios2;

/* Ejercicio 2. Diseña una clase que llamarás GuardarYRecuperarEstado que disponga de
dos métodos: guardarEstado que permitirá guardar en un archivo el estado
de la partida y otro, recuperarEstado, que permitirá recuperar el estado de
una partida desde archivo. */

import java.io.*;

public class EX2_GuardarYRecuperarEstado {
	public static File fichero = new File ("EstadoPartida.dat");
	
	public static void guardarEstado(EX1_EstadoPartida partida) throws IOException {
		FileOutputStream fileout = new FileOutputStream (fichero);
		ObjectOutputStream dataOut = new ObjectOutputStream (fileout);
		
		dataOut.writeObject (partida);
		dataOut.close();
	}
	
	public static EX1_EstadoPartida recuperarEstado() throws IOException, ClassNotFoundException {
		FileInputStream filein = new FileInputStream (fichero);
		ObjectInputStream dataIn = new ObjectInputStream (filein);
		
		EX1_EstadoPartida partida = (EX1_EstadoPartida) dataIn.readObject();
		//dataIn.readObject();
		dataIn.close();
		return partida;
	}
}
