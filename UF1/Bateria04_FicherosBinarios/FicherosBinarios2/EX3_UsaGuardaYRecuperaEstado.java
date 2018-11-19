package Bateria04_FicherosBinarios.FicherosBinarios2;

/* Ejercicio 3. Crea un programa en java que genere un objeto EstadoPartida, modifique la 
posición de algunas fichas del tablero, guarde en un archivo el estado de la 
partida, recupere el estado desde el archivo y lo muestre por pantalla. */

import java.io.*;

public class EX3_UsaGuardaYRecuperaEstado {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		
		// Iniciamos la partida con unos datos
		int[][] EstadoPartida = {{2,1,0},{0,1,0},{0,2,0}};
		EX1_EstadoPartida partida1 = new EX1_EstadoPartida (EstadoPartida, 1);
		
		System.out.println("Esta es la distribución INICIAL del tablero: ");
		System.out.println(" -------------");
		
		for (int i = 0; i < 3; i++) {
				System.out.print(" | " + partida1.getPosicionPiezas()[i][0]);
				System.out.print(" | " + partida1.getPosicionPiezas()[i][1]);
				System.out.print(" | " + partida1.getPosicionPiezas()[i][2] + " |\n");
				System.out.println(" -------------");
		}
		
		System.out.println("\nEl jugador activo es el número: " + partida1.getJugadorActivo());
		System.out.println("\n------------------------------------------------------\n");

		
		// Modificamos los datos de la partida
		int[][] NuevoEstadoPartida = {{2,1,0},{0,1,1},{2,2,1}};
		partida1.setJugadorActivo(2);
		partida1.setPosicionPiezas(NuevoEstadoPartida);
		
		// Guardamos los datos de partida
		EX2_GuardarYRecuperarEstado.guardarEstado(partida1);	
		
		// Recuperamos los datos de partida
		
		EX1_EstadoPartida partidaFinal = (EX1_EstadoPartida) EX2_GuardarYRecuperarEstado.recuperarEstado();
		
		System.out.println("Esta es la distribución FINAL del tablero: ");
		System.out.println(" -------------");
		
		for (int i = 0; i < 3; i++) {
				System.out.print(" | " + partidaFinal.getPosicionPiezas()[i][0]);
				System.out.print(" | " + partidaFinal.getPosicionPiezas()[i][1]);
				System.out.print(" | " + partidaFinal.getPosicionPiezas()[i][2] + " |\n");
				System.out.println(" -------------");
		}
		
		System.out.println("\nEl jugador activo es el número: " + partidaFinal.getJugadorActivo());
	}
}
