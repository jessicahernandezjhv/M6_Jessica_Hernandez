package Bateria05_BDOO.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import Bateria05_BDOO.I.*;

/* 1. Realiza un programa que realice una búsqueda por nombre
 * en la base de datos “EQUIPOS.DB”. Pedirá un nombre al usuario
 * y devolverá los datos del jugador que responda a dicho nombre
 * o un mensaje del estilo “no hay ningún jugador que tenga ese 
 * nombre en la base de datos” */

public class Ex01_BDOO2 {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String nombre = null;
		try {
			System.out.print("Inserte el nombre que quiera buscar: ");
			nombre = reader.readLine();
		} catch ( IOException e ) {
			e.printStackTrace();
			System.exit(0);
		}


		ICriterion filter = Where.equal("nombre", nombre);
		CriteriaQuery query = new CriteriaQuery (Jugador.class, filter);
		Objects<Jugador> objects = odb.getObjects(query);

		if (objects.size() > 0) {
			while (objects.hasNext()) {
				Jugador player = objects.next();
				System.out.println(
						"Nombre: " + player.getNombre() + "\n" +
								"Deporte: " + player.getDeporte() + "\n" +
								"Ciudad: " + player.getCiudad() + "\n" + 
								"Edad: " + player.getEdad() + "\n" +
								"Pais: " + player.getPais() + "\n");}
		} else {
			System.out.println("No hay coincidencias");
		}
	}
}