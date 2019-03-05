package Bateria05_BDOO.I;

/* EJERCICIO 1.
 * 1. Crea la clase Paises con dos atributos y sus getter y 
 * setter. Los atributos son: private int id; private String nombrepais;
 * 2. Añade también el método toString() para que devuelva 
 * el nombre del país: public String toString() {return nombrepais;}
 * 3. Crea la clase Jugadores (como el ejemplo anterior) y 
 * añade el siguiente atributo con sus getter y setter: private Paises pais;
 * 4. Crea una clase Java (con el método main()) que cree
 * una base de datos de nombre EQUIPOS.DB e inserte países y los jugadores de esos países.
 * 5. Añade otra clase Java para visualizar los países y
 * los jugadores que hay en la base de datos. */

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Ex01_Ejericcio {
	public static void main(String[] args) {
		Pais p1 = new Pais(1,"España");
		Pais p2 = new Pais(2,"Francia");
		Pais p3 = new Pais(3,"Italia");
		Pais p4 = new Pais(4,"Portugal");
		Jugador j1 = new Jugador("Pepito Grillo", "Charranca", "Salamanca", 18, p1);
		Jugador j2 = new Jugador("Waldo Geraldo", "Parchis", "Marsella", 25, p2);
		Jugador j3 = new Jugador("Pepper Pots", "Pica-Paret", "Genova", 21, p3);
		Jugador j4 = new Jugador("Steve Stramge", "Salto a pata coja", "Oporto", 27, p4);

		ODB odb = ODBFactory.open("EQUPOS.DB");
		odb.store(p1);
		odb.store(p2);
		odb.store(p3);
		odb.store(p4);
		odb.store(j1);      
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);

		Objects<Pais> objectsPaises = odb.getObjects(Pais.class);
		Objects<Jugador> objectsJugadores = odb.getObjects(Jugador.class); 
		System.out.printf("%d Paises: %n", objectsPaises.size());
		System.out.printf("%d Jugadores: %n", objectsJugadores.size());

		int i = 1;
		while(objectsJugadores.hasNext()) {                      
			Jugador jug = objectsJugadores.next();
			System.out.printf("%d: %s %n", i++, jug.getPais());  
		} 
		System.out.print("");

		while(objectsPaises.hasNext()) {                     
			Pais pai = objectsPaises.next();
			System.out.printf("%d: %s %s %n", i++, pai.getId(), pai.getNombrepais());  
		} 
		odb.close();      
	}
}