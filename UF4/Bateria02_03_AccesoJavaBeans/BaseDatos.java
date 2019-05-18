package Bateria02_03_AccesoJavaBeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class BaseDatos {
	private ODB dataBase;

	public BaseDatos() {
		
		dataBase = ODBFactory.open("Producto_Ped.DB");
	}

	public void insertProduct(Producto product) {
		
		dataBase.store(product);
		dataBase.commit();
	}

	public Producto getProduct(int idProduct) {
		
		ICriterion rule = Where.equal("idproducto", idProduct);
		CriteriaQuery query = new CriteriaQuery(Producto.class, rule);
		Objects<Producto> product = dataBase.getObjects(query);

		if (product.hasNext()) {
			return product.getFirst();
		} 
		
		return null;
	}

	public void updateProduct(Producto productToUpdate) {
		Producto updatedProduct = getProduct(productToUpdate.getIdproducto());

		if (updatedProduct != null) {
			updatedProduct.setDescripcion(productToUpdate.getDescripcion());
			updatedProduct.setStockactual(productToUpdate.getStockactual());
			updatedProduct.setStockminimo(productToUpdate.getStockminimo());
			dataBase.store(updatedProduct);
			dataBase.commit();
		}
	}
	
	public int getNewProductID() {
		IValuesQuery query = new ValuesCriteriaQuery(Producto.class).max("idproducto");
		Values values = dataBase.getValues(query);
		return ((BigDecimal)values.next().getByAlias("idproducto")).intValue() + 1;
	}
	
	public void insertOrder(Pedido order) {
		Producto soldProduct = getProduct(order.getProducto().getIdproducto());
		order.setProducto(soldProduct);

		dataBase.store(order);
		dataBase.commit();
	}

	public Producto getOrder(int orderID) {
		ICriterion rule = Where.equal("numeroPedido", orderID);
		CriteriaQuery query = new CriteriaQuery(Pedido.class, rule);
		Objects<Producto> product = dataBase.getObjects(query);

		if (product.hasNext()) {
			return product.getFirst();
		}
		
		return null;
	}

	public int getNewOrderID() {
		IValuesQuery query = new ValuesCriteriaQuery(Pedido.class).max("numeroPedido");
		Values values = dataBase.getValues(query);
		return ((BigDecimal)values.next().getByAlias("numeroPedido")).intValue() + 1;
	}

	public Venta insertSale(int productID, int quantity) {
		Venta newSale = new Venta(getNewSaleID(), quantity, productID, "");
		dataBase.store(newSale);
		dataBase.commit();
		return newSale;
	}
		
	public Venta getSale(int sellID) {
		ICriterion rule = Where.equal("numeroVenta", sellID);
		CriteriaQuery query = new CriteriaQuery(Venta.class, rule);
		Objects<Venta> sales = dataBase.getObjects(query);

		if (sales.hasNext()) {
			return sales.getFirst();
		} 
		
		return null;
	}

	public void updateSale(Venta venta) {
		Venta updatedSale = getSale(venta.getSaleNumber());

		if (updatedSale != null) {
			updatedSale.setQuantity(venta.getQuantity());
			updatedSale.setSaleDate(venta.getSaleDate());
			updatedSale.setProductID(venta.getProductID());
			updatedSale.setComments(venta.getComments());

			dataBase.store(updatedSale);
			dataBase.commit();
		}
	}

	public int getNewSaleID() {
		IValuesQuery query = new ValuesCriteriaQuery(Venta.class).max("numeroVenta");
		Values values = dataBase.getValues(query);

		return ((BigDecimal)values.next().getByAlias("numeroVenta")).intValue() + 1;
	}

	public Objects<Producto> getProducts() {
		return dataBase.getObjects(Producto.class);
	}
	
	public Objects<Pedido> getOrders() {
		return dataBase.getObjects(Pedido.class);
		
	}
	
	public Objects<Venta> getSales() {
		return dataBase.getObjects(Venta.class);
	}

	public void closeDatabase() {
		dataBase.close();
	}
}
