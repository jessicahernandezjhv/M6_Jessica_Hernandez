package Bateria05_BDOO.II;

import Bateria05_BDOO.I.*;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Ex03_BDOO2 {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Objects<Jugador> objects = odb.getObjects(Jugador.class);
		while(objects.hasNext()){  
			Jugador player = objects.next();
			player.setEdad(player.getEdad() + 1);
			odb.store(player);
		}
		
		odb.commit();
		odb.close(); 
	}
}
