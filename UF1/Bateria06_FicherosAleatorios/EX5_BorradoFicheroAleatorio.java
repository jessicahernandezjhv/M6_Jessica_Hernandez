package Bateria06_FicherosAleatorios;

/* Crea un programa Java que al ejecutarlo desde la línea de
 * comandos reciba un identificador de empleado y lo borre. 
 * Se hará un borrado lógico marcando el registro con la 
 * siguiente información: el identificador será igual a -1, el
 * apellido será igual al identificador que se borra, y el 
 * departamento y salario serán 0.*/

import java.io.*;

public class EX5_BorradoFicheroAleatorio {
	public static void main(String[] args) throws IOException {
		try {
			int idEmpleado = Integer.parseInt(args[0]);
			leerFicheroAleatorio();
			comprobarId(idEmpleado);
			leerFicheroAleatorio();
			
		} catch  (ArrayIndexOutOfBoundsException exception) {		
			System.out.println("Debes introducir un ID valido por parametro.");
		}
	}


	public static void comprobarId(int inputId) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");

		int id, dep, posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion = 0;

		for ( ; ; ) {
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado

			if (id == inputId) {
				for ( int i = 0; i < apellido.length; i++) {
					aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
					apellido[i]=aux; // en el array apellido
				}

				int apellidos = id;
				dep = 0;
				salario = file.readDouble();
				double nuevoSalario = 0;

				if (id > 0)
					inputId = -1;
					modificarSalarioEmpleado(posicion, inputId, apellidos, dep, nuevoSalario);
				break;
			} else {
				posicion += 36;
				if (posicion == file.length()) {
					System.out.printf("El ID no existe. No se pueden actualizar los datos del empleado.\n\n");
					break; // Si he recorrido todo el fichero, salgo del for
				}
			}
		}

		file.close();
	}

	public static void modificarSalarioEmpleado(int writePosicion, int writeId, int oldApellido, int oldDep, Double writeSalario) throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero , "rw");

		int id = writeId;
		//StringBuffer buffer = null; //Buffer para almacenar apellido
		///buffer = new StringBuffer (oldApellido);
		//buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
		int apellidos = oldApellido;
		int dep = oldDep;
		Double salario = writeSalario;
		//int posicion = (int) file.length();

		file.seek (writePosicion); // Nos posicionamos en posicion
		file.writeInt (id);
		file.writeInt (apellidos);
		file.writeInt(dep);
		file.writeDouble (salario);

		file.close();  // No olvidarse de cerrar el fichero
	}

	public static void leerFicheroAleatorio() throws IOException {
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");

		int id, dep ,posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion = 0;

		for ( ; ; ){
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado

			for ( int i =0; i<apellido.length; i++) {
				aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
				apellido[i]=aux; // en el array apellido
			}

			String apellidos = new String (apellido);
			dep = file.readInt(); //Lectura de departamento y salario
			salario = file.readDouble();

			//if (id >0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep, salario);
			posicion = posicion + 36;

			if (file.getFilePointer() ==file.length()) break; // Si he recorrido todo el fichero, salgo del for
		}

		System.out.println("\n");

		file.close();
	}
}
