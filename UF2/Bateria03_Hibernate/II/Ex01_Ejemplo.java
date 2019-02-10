package Bateria03_Hibernate.II;

import primeroo.Depart;
import primeroo.HibernateUtil;

import primeroo.*;

/* EJERCICIO 1. Realiza el ejemplo expuesto anteriormente. 
 * Comprueba que añade una nueva fila en depart */

public class Ex01_Ejemplo {
	public static void main(String[] args){
		//En primer lugar se obtiene la sesión creada por el Singleton
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos sesión e iniciamos una transacción
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("Inserto una fila en depart");
		//Creamos un nuevo objeto Depart y damos valor a sus atributos
		Depart dep = new Depart();
		dep.setDeptNo((byte) 45);
		dep.setDnombre("LIMPIEZA");
		dep.setLoc("MARSELLA");
		//Guardamos en la base de datos y comprometemos la información
		session.save(dep);
		tx.commit();
		session.close();
		System.exit(0);
	}

}
