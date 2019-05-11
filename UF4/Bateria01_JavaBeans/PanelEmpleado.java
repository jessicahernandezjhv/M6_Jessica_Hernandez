package Bateria01_JavaBeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.Arrays;

public class PanelEmpleado implements Serializable, VetoableChangeListener {
	int limiteVariacionSueldo;
	private String[] listaDeCargos = {"Junior", "SemiSenior", "Analista", "CEO"};

	public PanelEmpleado() {
		this.limiteVariacionSueldo = 10;
	}

	public PanelEmpleado(int limiteVariacionSueldo) {
		if (this.limiteVariacionSueldo >= 10 && this.limiteVariacionSueldo <= 50) {
			this.limiteVariacionSueldo = limiteVariacionSueldo;
		}
	}

	@Override
	public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
		if (event.getPropertyName().equals("sueldo")) {
			float variacion = Math.abs((int)event.getNewValue()-(int)event.getOldValue());
			float porcentajeVariacion = ((variacion/(float)(int)event.getOldValue()) * 100);
			
			if (porcentajeVariacion > this.limiteVariacionSueldo) {
				throw new PropertyVetoException("Se ha sobrepasado la variación de sueldo: " + this.limiteVariacionSueldo, event);
			}
			
		} else if (event.getPropertyName().equals("cargo")) {
			if (!Arrays.stream(this.listaDeCargos).anyMatch(((String)event.getNewValue())::equals)) {
				throw new PropertyVetoException("El cargo no es válido", event);
			}
		}
		
	}	
}


