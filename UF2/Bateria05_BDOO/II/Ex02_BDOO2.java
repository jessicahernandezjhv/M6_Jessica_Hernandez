package Bateria05_BDOO.II;

import Bateria05_BDOO.I.*;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Ex02_BDOO2 {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		String nombre = null;
		ICriterion filter = new And().add(Where.ge("edad", 14)).add(Where.le("edad", 20));
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
								"Pais: " + player.getPais() + "\n");
			}
		} else {
			System.out.println("No hay coincidencias");
		}
	}
}
