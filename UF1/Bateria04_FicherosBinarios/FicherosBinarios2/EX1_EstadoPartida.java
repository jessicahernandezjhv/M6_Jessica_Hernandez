package Bateria04_FicherosBinarios.FicherosBinarios2;

/* Ejercicio 1. Dise�a una clase que llamar�s EstadoPartida para gestionar el estado de
una partida de 3 en raya. Debe incluir: posici�n de las piezas (ser� una matriz
3x3) y a qui�n le toca tirar (jugador1 o jugador2). */

import java.io.*;

public class EX1_EstadoPartida implements Serializable {
	private int [] posicionPiezas = new int[3];
	private int jugadorActivo;
	
	public EX1_EstadoPartida(int [] posicionPiezas, int jugadorActivo) {
		super();
		this.posicionPiezas = posicionPiezas;
		this.jugadorActivo = jugadorActivo;
	}

	public int [] getPosicionPiezas() {
		return posicionPiezas;
	}

	public void setPosicionPiezas(int [] posicionPiezas) {
		this.posicionPiezas = posicionPiezas;
	}

	public int getJugadorActivo() {
		return jugadorActivo;
	}

	public void setJugadorActivo(int jugadorActivo) {
		this.jugadorActivo = jugadorActivo;
	}
}

