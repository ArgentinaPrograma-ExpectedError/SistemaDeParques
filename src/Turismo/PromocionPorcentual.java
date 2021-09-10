package Turismo;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {
	private double descuento;

	public PromocionPorcentual(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion,
			double descuento) {
		super(nombrePromocion, atraccionesDeLaPromocion);
		this.descuento = setDescuento(descuento);// se deberia controlar antes de crear el metodo y lanzar una excepcion
	}

	private Double getDescuento() {
		return this.descuento;
	}

	private double setDescuento(double descuento) {
		if (descuento > 0 && descuento < 100)
			return descuento;
		else
			return 1;
	}

	@Override
	public Integer getCosto() {
		return (int) (super.getCosto() * (100 - this.getDescuento()) / 100);
	}

}
