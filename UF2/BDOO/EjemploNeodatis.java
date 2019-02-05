//
//import org.neodatis.odb.ODB;
//import org.neodatis.odb.ODBFactory;
//import org.neodatis.odb.Objects;
//
//class Jugadores { //Clase Jugadores
//	private String nombre;
//	private String deporte;
//	private String ciudad;
//	private int edad;
//
//	public Jugadores() {}
//	public Jugadores(String nombre, String deporte,String ciudad, int edad) {
//		this.nombre = nombre;
//		this.deporte = deporte;
//		this.ciudad = ciudad;
//		this.edad = edad;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setDeporte(String deporte) {
//		this.deporte = deporte;
//	}
//
//	public String getDeporte() {
//		return deporte;
//	}
//
//	public void setCiudad(String ciudad) {
//		this.ciudad = ciudad;
//	}
//
//	public String getCiudad () {
//		return ciudad;
//	}
//
//	public void setEdad(int edad) {
//		this.edad = edad;
//	}
//
//	public int getEdad() {
//		return edad;
//	}
//}
//
//public class EjemploNeodatis {
//	public static void main(String[] args) {
//		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14);  // Crear instancias para almacenar en BD
//		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
//		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);
//		ODB odb = ODBFactory.open("neodatis.test");     // Abrir 
//		odb.store(j1);                                    // Almacenamos 
//		odb.store(j2);
//		odb.store(j3);
//		odb.store(j4);
//		Objects<Jugadores> objects = odb.getObjects(Jugadores.class);    //recuperamos todos los objetos
//		System.out.printf("%d Jugadores: %n", objects.size());
//
//		int i = 1;
//
//		while(objects.hasNext()) {                               // visualizar los objetos     
//			Jugadores jug = objects.next();
//			System.out.printf("%d: %s, %s, %s %n",i++, jug.getNombre(), jug.getDeporte(),jug.getCiudad(), jug.getEdad());  
//		}   
//		odb.close(); // Cerrar BD      
//	}
//}
//
//
