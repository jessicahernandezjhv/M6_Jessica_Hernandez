package Bateria02_03_AccesoJavaBeans;

public class Prueba {

	public static void main(String[] args) {
		VerProductos ver = new VerProductos();

		System.out.println("Productos\n=============");
		ver.showProductsData();

		System.out.println("Ventas\n=============");
		ver.showSalesData();

		System.out.println("Pedidos\n=============");
		ver.showOrdersData();

		venderPrimerProducto();
		venderSegundoProducto();

		System.out.println("Productos\n=============");
		ver.showProductsData();
		
		System.out.println("Ventas\n=============");
		ver.showSalesData();

		System.out.println("Pedidos\n=============");
		ver.showOrdersData();
	}


	public static void venderPrimerProducto() {
		BaseDatos db = new BaseDatos();
		Producto product = db.getProduct(6);
		db.closeDatabase();

		if (product != null) {
			Pedido order = new Pedido();
			Venta sale = new Venta();

			order.setProducto(product);
			product.addPropertyChangeListener(order);
			product.addPropertyChangeListener(sale);
			product.setVenta(sale);
			product.realizarVenta(4);

			db = new BaseDatos();
			db.updateProduct(product);
			db.closeDatabase();
		} 
	}


	public static void venderSegundoProducto() {
		BaseDatos db = new BaseDatos();
		Producto product = db.getProduct(1);
		db.closeDatabase();

		if (product != null) {
			Pedido order = new Pedido();
			Venta sale = new Venta();

			order.setProducto(product);
			product.addPropertyChangeListener(order);
			product.addPropertyChangeListener(sale);
			product.setVenta(sale);
			product.realizarVenta(8);

			db = new BaseDatos();
			db.updateProduct(product);
			db.closeDatabase();
		} 
	}
}


