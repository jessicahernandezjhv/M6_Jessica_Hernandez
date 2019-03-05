package Proyecto_NeoDartis_Final;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Constructor {
	public static void main(String[] args) {
		Depart depart1 = new Depart(1,"GUION","Bilbao");
		Depart depart2 = new Depart(2,"ATREZZO","Valencia");
		Depart depart3 = new Depart(3,"INTERPRETACIÓN","Barcelona");
		Depart depart4 = new Depart(4,"DIRECCION","Aragon");
		
		java.sql.Date insertDate = new java.sql.Date(System.currentTimeMillis());
		
		Emple director = new Emple(1, "Perez", "Director", null, insertDate, (float)8347.67, depart4);
		director = new Emple(1, "Perez", "Director", director, insertDate, (float)8347.67, depart4);
		
		Emple emple1 = new Emple(2, "Banisher", "Decorador", director, insertDate, (float)4764.34, depart2);
		Emple emple2 = new Emple(3, "Lifson", "Estilista", emple1, insertDate, (float)2796.74, depart2);
		
		Emple emple3 = new Emple(4, "Mambru", "Guionista", director, insertDate, (float)3170.43, depart1);
		Emple emple4 = new Emple(5, "Shrek", "Guionista principal", emple3, insertDate, (float)2791.00, depart1);
		
		Emple emple5 = new Emple(6, "Al Paccino", "Actor principal", director, insertDate, (float)2004.37, depart3);
		Emple emple6 = new Emple(7, "Mel Gibson", "Actor secundario", emple5, insertDate, (float)1976.22, depart3);
		
		ODB odb = ODBFactory.open("EMPRESA.DB");
		
		odb.store(director);
		odb.store(emple1);
		odb.store(emple2);
		odb.store(emple3);
		odb.store(emple4);
		odb.store(emple5);
		odb.store(emple6);
		System.out.print("Datos guardados correctamente");
		
		odb.close();
	}
}

