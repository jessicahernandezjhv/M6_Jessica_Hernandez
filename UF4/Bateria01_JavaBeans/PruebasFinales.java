package Bateria01_JavaBeans;

public class PruebasFinales {
	public static void main(String[] args) {
		Empleado emple = new Empleado("62473411P", "Popolopoulos");
		PanelEmpleado panelEmple = new PanelEmpleado();
		
		emple.addVetoableSupport(panelEmple);
		
		// PRUEBAS QUE PASAN EL FILTRO
		emple.setSueldo(900);
		emple.setCargo("CEO");
		emple.setCargo("SemiSenior");

		
		// PRUEBAS QUE NO PASAN EL FILTRO POR EL SUELDO
		//emple.setSueldo(-20);
		emple.setCargo("Artista");
		//emple.setSueldo(850);
		
	}
}
