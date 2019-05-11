package Bateria01_JavaBeans;

public class PruebasFinales {
	public static void main(String[] args) {
		Empleado emple = new Empleado("62473411P", "Popolopoulos");
		PanelEmpleado panelEmple = new PanelEmpleado();
		
		emple.addVetoableSupport(panelEmple);
		
		// Error
		System.out.println("Introduciendo cargo - Granjero");
		emple.setCargo("Granjero");
		
		// Correcto
		System.out.println("Introduciendo cargo - SemiSenior");
		emple.setCargo("SemiSenior");
		
		// Error
		System.out.println("Introduciendo sueldo - 1234");
		emple.setSueldo(1234);
		
		// Correcto
		System.out.println("Introduciendo sueldo - 935");
		emple.setSueldo(935);
		
		// Error
		System.out.println("Introduciendo sueldo - 500");
		emple.setSueldo(500);
		
		// Correcto
		System.out.println("Introduciendo sueldo - 899");
		emple.setSueldo(899);
	}
}
