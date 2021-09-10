package Turismo;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private int descuento;

	public PromocionAbsoluta(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion, int descuento) {
		super(nombrePromocion, atraccionesDeLaPromocion);
		this.descuento = setDescuento(descuento);// se deberia controlar antes de crear el metodo y lanzar una excepcion
	}

	/**
	 * Metodo que valida que el descuento sea menor que el costo
	 * 
	 * @param descuento a realizar
	 * @return Regresa el descuento si es valido, y 1 en caso de no ser valido
	 */
	private int setDescuento(int descuento) {
		if (descuento < super.getCosto())
			return descuento;
		else
			return 1;
	}

	private int getDescuento() {
		return this.descuento;
	}

	@Override
	public Integer getCosto() {
		return super.getCosto() - this.getDescuento();
	}
}
