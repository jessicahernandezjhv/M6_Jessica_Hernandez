import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

	class Paises {
		private int id;
		private String nombrepais;
		
		public Paises(int id, String nombrepais) {
			super();
			this.id = id;
			this.nombrepais = nombrepais;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombrepais() {
			return nombrepais;
		}

		public void setNombrepais(String nombrepais) {
			this.nombrepais = nombrepais;
		}

		@Override
		public String toString() {
			return nombrepais;
		}
	}
	
	class Jugadores {
		private Paises pais;

		public Jugadores(Paises pais) {
			super();
			this.pais = pais;
		}

		public Paises getPais() {
			return pais;
		}

		public void setPais(Paises pais) {
			this.pais = pais;
		}
	}
	
	
	
	public class EX01_Ejericcio {

	public static void main(String[] args) {
		Paises p1 = new Paises(1,"España");
		Paises p2 = new Paises(2,"Francia");
		Paises p3 = new Paises(3,"Italia");
		Paises p4 = new Paises(4,"Portugal");

		Jugadores j1 = new Jugadores(p1);
		Jugadores j2 = new Jugadores(p2);
		Jugadores j3 = new Jugadores(p3);
		Jugadores j4 = new Jugadores(p4);
		
		ODB odb = ODBFactory.open("EQUPOS.DB");     // Abrir 
		odb.store(p1);
		odb.store(p2);
		odb.store(p3);
		odb.store(p4);
		odb.store(j1);                                    // Almacenamos 
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		
		Objects<Paises> objectsPaises = odb.getObjects(Paises.class);
		Objects<Jugadores> objectsJugadores = odb.getObjects(Jugadores.class);    //recuperamos todos los objetos
		
		
		System.out.printf("%d Paises: %n", objectsPaises.size());
		System.out.printf("%d Jugadores: %n", objectsJugadores.size());

		int i = 1;

		while(objectsJugadores.hasNext()) {                               // visualizar los objetos     
			Jugadores jug = objectsJugadores.next();
			System.out.printf("%d: %s %n", i++, jug.getPais());  
		} 
		
		System.out.print("");
		
		while(objectsPaises.hasNext()) {                               // visualizar los objetos     
			Paises pai = objectsPaises.next();
			System.out.printf("%d: %s %s %n", i++, pai.getId(), pai.getNombrepais());  
		} 
		
		odb.close(); // Cerrar BD      
	}
	}