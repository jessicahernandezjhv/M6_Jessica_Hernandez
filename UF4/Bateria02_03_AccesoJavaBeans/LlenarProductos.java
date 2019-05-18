package Bateria02_03_AccesoJavaBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LlenarProductos {

	public static void main(String[] args) {
		
		BaseDatos db = new BaseDatos();
		
		db.insertProduct(new Producto(1, "Duruss Cobalt", 10, 3, 220));
		db.insertProduct(new Producto(2, "Varlion Avant Carbon", 5, 2, 176));
		db.insertProduct(new Producto(3, "Star Vie Pyramid R50", 20, 5, 193));
		db.insertProduct(new Producto(4, "Dunlop Titan", 8, 3, 85));
		db.insertProduct(new Producto(5, "Vision King", 7, 1, 159));
		db.insertProduct(new Producto(6, "Slazenger Reflex Pro", 5, 2, 80));

		db.closeDatabase();
	}
}
