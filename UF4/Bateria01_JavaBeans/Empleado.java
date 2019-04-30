package Bateria01_JavaBeans;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

public class Empleado implements Serializable {
	private String nif;
	private String nombre;
	private String cargo;
	private int sueldo;
	private VetoableChangeSupport vetoableSupport;

	
	public Empleado() {
		vetoableSupport = new VetoableChangeSupport (this);
		this.cargo = "Junior";
		this.sueldo = 1000;
	}

	public Empleado(String nif, String nombre) {
		this();
		this.nif = nif;
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		if (cargo != null && !cargo.isEmpty()) {
			try {
				vetoableSupport.fireVetoableChange("cargo", this.cargo, cargo);
				this.cargo = cargo;
			} catch (PropertyVetoException e) {
				System.out.println("El cargo no es válido");
				e.printStackTrace();
			}
		}
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		if (sueldo > 0) {
			try {
				vetoableSupport.fireVetoableChange("sueldo", this.sueldo, sueldo);
				this.sueldo = sueldo;
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	public void addVetoableSupport(VetoableChangeListener listener) {
		vetoableSupport.addVetoableChangeListener(listener);
	}

	public void deleteVetoableSupport(VetoableChangeListener listener) {
		vetoableSupport.removeVetoableChangeListener(listener);
	}
}
