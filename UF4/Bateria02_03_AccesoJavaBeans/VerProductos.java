package Bateria02_03_AccesoJavaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.neodatis.odb.Objects;

public class VerProductos implements Serializable {

	public VerProductos() {}

	public void showProductsData() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("ID", "Decription", "Current Stock", "Min. Stock", "Price");
		rows.add(headers);
		
		for (Producto producto : db.getProducts()) {
			rows.add(Arrays.asList(String.valueOf(producto.getIdproducto()),
					producto.getDescripcion(),
					String.valueOf(producto.getStockactual()),
					String.valueOf(producto.getStockminimo()),
					String.valueOf(producto.getPvp()+"€")));
		}
		
		System.out.println(formatAsTable(rows));
		db.closeDatabase();
	}

	public void showOrdersData() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("Order num.", "Product", "Date", "Quantity");
		rows.add(headers);
		
		for(Pedido pedido : db.getOrders()) {
			rows.add(Arrays.asList(String.valueOf(pedido.getNumeroPedido()), 
					String.valueOf(pedido.getProducto()), 
					String.valueOf(pedido.getFecha()), 
					String.valueOf(pedido.getCantidad())));
		}
		
		System.out.println(formatAsTable(rows));
		db.closeDatabase();
	}
	
	public void showSalesData() {
		BaseDatos db = new BaseDatos();		
		List<List<String>> rows = new ArrayList<>();
		List<String> headers = Arrays.asList("Sale num.", "Date", "Quantity", "Product", "Comment");
		rows.add(headers);
		
		for(Venta venta : db.getSales()) {
			rows.add(Arrays.asList(String.valueOf(venta.getSaleNumber()),
					String.valueOf(venta.getSaleDate()), 
					String.valueOf(venta.getQuantity()), 
					db.getProduct(venta.getProductID()).getDescripcion(), 
					venta.getComments()));
		}
		
		System.out.println(formatAsTable(rows));
		db.closeDatabase();
	}
	
	public void showAll() {
		showProductsData();
		System.out.println("___________________________________________________________________________________________________________________________________________\n");
		showOrdersData();
		System.out.println("___________________________________________________________________________________________________________________________________________\n");
		showSalesData();
	}

	
	// METODO REUTILIZADO
	public static String formatAsTable(List<List<String>> rows) {
	    int[] maxLengths = new int[rows.get(0).size()];
	    
	    for (List<String> row : rows) {
	        for (int i = 0; i < row.size(); i++) {
	            maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
	        }
	    }

	    StringBuilder formatBuilder = new StringBuilder();
	    for (int maxLength : maxLengths) {
	        formatBuilder.append("%-").append(maxLength + 12).append("s");
	    }
	    
	    String format = formatBuilder.toString();
	    StringBuilder result = new StringBuilder();
	    
	    for (List<String> row : rows) {
	        result.append(String.format(format, row.toArray(new String[0]))).append("\n");
	    }
	    
	    return result.toString();
	}
}
