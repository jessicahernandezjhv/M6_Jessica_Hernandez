package Bateria02_03_AccesoJavaBeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

public class Venta implements Serializable, PropertyChangeListener {
	int saleID;
	Date saleDate;
	int quantity;
	int productID;
	String comments;

	public Venta() {
	}

	public Venta(int saleNumber, int quantity, int productID, String comments) {
		super();
		this.saleID = saleNumber;
		this.saleDate = new Date(System.currentTimeMillis());
		this.quantity = quantity;
		this.productID = productID;
		this.comments = comments;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("stockactual")) {
			BaseDatos dataBase = new BaseDatos();

			Venta sale = ((Producto)evt.getSource()).getVenta();
			sale.setComments("Pendiente de pedido por falta de stock");
			dataBase.updateSale(sale);
			dataBase.closeDatabase();
		}
	}
	

	public int getSaleNumber() {
		return saleID;
	}

	public void setSaleNumber(int numeroVenta) {
		this.saleID = numeroVenta;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date fechaVenta) {
		this.saleDate = fechaVenta;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int cantidad) {
		this.quantity = cantidad;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int idProducto) {
		this.productID = idProducto;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String observaciones) {
		this.comments = observaciones;
	}
}
