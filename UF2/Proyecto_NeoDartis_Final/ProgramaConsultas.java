package Proyecto_NeoDartis_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class ProgramaConsultas {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String user;
		int userOption;
		String dataBase = "EMPRESA.DB";
		ODB odb = null;

		try {
			odb = ODBFactory.open(dataBase);
		} catch(Exception e) {
			easyPrint("Error al abrir la base de datos: "+dataBase+".");
			System.exit(0);
		}

		Objects<Emple> listaEmpleados = odb.getObjects(Emple.class);
		Objects<Depart> listaDepartamentos =odb.getObjects(Depart.class);
		Objects<ObjectValues>  objetos = null;

		easyPrint("Gestor de Base de Datos: ");
		do {
			easyPrint("\nSeleccione una opción. \n"
					+ "	1. Visualizar Base de Datos completa\n"
					+ "	2. Insertar en la Base de Datos\n"
					+ "	3. Hacer una consulta\n"
					+ "	4. Migrar Datos\n"
					+ "	5. Modificar Datos\n"
					+ "	0. Salir\n");
			user = reader.readLine();
			userOption = Integer.parseInt(user);

			switch (userOption) {
			case 1: 
				imprimirEmpleados(listaEmpleados, listaDepartamentos, odb);
				imprimirDepartamentos(listaDepartamentos, listaEmpleados, odb);
				odb.close();
				break;
			case 2: 
				System.out.print("Pendiente");
				break;
			case 3: 
				easyPrint(" 1. Mostrar apellido de los empleados del departamento 10\n"
						+ " 2. Mostrar número de empleados del departamento VENTAS\n"
						+ " 3. Mostrar apellido de los empleados cuyo director es FERNÁNDEZ\n"
						+ " 4. Mostrar número de empleados por cada departamento");
				user = reader.readLine();
				int userSubOption = Integer.parseInt(user);

				switch (userSubOption) {
				case 1:
					empleadosEnDepartamentoDiez(odb);
					break;
				case 2:
					empleadosEnVentas(odb);
					break;
				case 3:
					empleadosConDirector(odb);
					break;
				case 4: 
					empleadosPorDepartamento(odb);
					break;
				}

			case 4:
				System.out.print("Pendiente");
				break;
			case 5: 
				System.out.print("Pendiente");
				break;

			default:
				System.exit(0);
			}
		} while (userOption != 0 && userOption > -1);

	}

	public static void empleadosPorDepartamento(ODB odb) {
		Objects<ObjectValues>  objetos = null;
		IQuery query = new ValuesCriteriaQuery(Emple.class).count("count").field("dept.deptNo").groupBy("dept.deptNo"); 
		objetos = odb.getValues((IValuesQuery) query.orderByAsc("dept.deptNo"));

		if (objetos != null) {
			while (objetos.hasNext()) {
				ObjectValues objectValues = (ObjectValues) objetos.next();
				System.out.println("El departamento num. " + objectValues.getByAlias("dept.deptNo") + 
						" tiene " + objectValues.getByAlias("count") + " empleados.");
			}
			odb.close();
			System.exit(0);
		}
	}

	public static void empleadosEnDepartamentoDiez(ODB odb) {
		Objects<Emple> empleList = null;
		IQuery query = new CriteriaQuery(Emple.class, Where.equal("dept.deptNo",10));
		empleList = odb.getObjects(query);

		if (empleList != null) {
			System.out.println("En el departamento 10: ");
			while (empleList.hasNext()) {
				Emple objectValues = empleList.next();
				System.out.println(" - " + objectValues.getApellido());
			}
			odb.close();
			System.exit(0);
		}
	}

	public static void empleadosEnVentas(ODB odb) {
		Objects<Emple> empleList = null;
		IQuery query = new CriteriaQuery(Emple.class, Where.equal("dept.dnombre","VENTAS"));
		empleList = odb.getObjects(query);
		System.out.print("En el departamento de VENTAS hay " + empleList.size() + " trabajadores.");
		odb.close();
		System.exit(0);
	}

	public static void easyPrint(String error){
		System.out.println(error);
	}

	public static void imprimirEmpleados(Objects<Emple> empleList, Objects<Depart> departList, ODB odb) {			
		if (empleList.size() > 0) {
			System.out.println("                    EMPLEADOS                    ");
			System.out.println("_________________________________________________");
			while (empleList.hasNext()) {
				Emple empleado = empleList.next();
				System.out.print(
						"Numero Empleado:\t" + empleado.getEmpNo() + "\n" +
								"Apellido:\t" + empleado.getApellido() + "\n" +
								"Oficio:\t\t" + empleado.getOficio() + "\n" +
								"Director:\t" + empleado.getDir() + "\n" +
								"Alta:\t" + empleado.getFechaAlt() + "\n" +
								"Salario:\t" + empleado.getComision() + "\n" +
								"Departamento:\t" + empleado.getDept() + "\n");
				System.out.println("");
			}
		}
	}

	public static void imprimirDepartamentos(Objects<Depart> departList, Objects<Emple> empleList, ODB odb) {			
		if (departList.size() > 0) {
			System.out.println("                  DEPARTAMENTOS                  ");
			System.out.println("_________________________________________________");
			while (departList.hasNext()) {
				Depart departamento = departList.next();
				System.out.print(
						"Numero Departamento:\t" + departamento.getDeptNo() + "\n" +
								"Nombre Departamento:\t" + departamento.getDnombre() + "\n" +
								"Localización:\t\t" + departamento.getLoc() + "\n");
				System.out.println("");
			}
		}
	}

	public static void addDatos() {}

	public static void empleadosConDirector(ODB odb) {
		ICriterion criterion = Where.like("depart", "VENTAS");
		IQuery query = new CriteriaQuery(Depart.class, criterion);

		Objects<Emple> empleList = odb.getObjects(query);

		while (empleList.hasNext()) {
			Emple empleado = empleList.next();
			System.out.print(
					"Numero Empleado:\t" + empleado.getEmpNo() + "\n" +
							"Apellido:\t" + empleado.getApellido() + "\n" +
							"Oficio:\t\t" + empleado.getOficio() + "\n" +
							"Director:\t" + empleado.getDir() + "\n" +
							"Alta:\t" + empleado.getFechaAlt() + "\n" +
							"Salario:\t" + empleado.getComision() + "\n" +
							"Departamento:\t" + empleado.getDept() + "\n");
			System.out.println("");
		}
		odb.close();
	}
}

