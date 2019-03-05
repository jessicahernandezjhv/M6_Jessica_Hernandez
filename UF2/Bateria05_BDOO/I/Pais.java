package Bateria05_BDOO.I;

public class Pais {
	private int id;
	private String nombrePais;
	
	public Pais() {
		super();
	}

	public Pais(int id, String nombrepais) {
		super();
		this.id = id;
		this.nombrePais = nombrepais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrepais() {
		return nombrePais;
	}

	public void setNombrepais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	@Override
	public String toString() {
		return nombrePais;
	}
}
