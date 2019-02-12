package Bateria05_BDOO.II;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

public class Ex01_BDOO2 {
	 public static void main (String[] args) { 
		 Jugadores jug1=new Jugadores(); 
		 ODB odb= ODBFactory.open("neodatis.test");
		 Jugadores j1 = new Jugadores("Luis", "voleibol", "Madrid", 14);
		 OID oid= odb.store(j1);
		 jug1 = (Jugadores)odb.getObjectFromId(oid);
		 System.out.printf("%s,%s,%s, %d %n", jug1.getNombre(), jug1.getDeporte(),jug1.getCiudad(),jug1.getEdad()); 
		 odb.close();    
		 }
}
