package Bateria06_AccesoDOM;

/*Ejercicio 2.1 - Vas a realizar exactamente lo mismo que en el programa anterior pero en vez 
de utilizar un fichero de acceso aleatorio (como era el caso de 
AleatorioEmpleado.dat) vas a utilizar un fichero de texto con los datos de los 
empleados. */

import java.io.*;

public class EX2_CrearTxtEmpleados {
		
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("Empleados.txt");  // declaración fichero
		String [] empleado = {"1:Fernandez:10:1000.45",
				"2:Gil:20:2400.60",
				"3:Lopez:10:3000.10",
				"4:Ramos:10:1520.35",
				"5:Pepito:50:1234.56",
				"6:Juanito:26:2345.50",
				"7:Wombat:37:2647.12"};

		if (fichero.exists()) {
			fichero.delete();
		} 
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

		for (int i = 0; i < empleado.length; i++) {
			bw.append(empleado[i]+"\n");
		}

		bw.close();
	}
}


