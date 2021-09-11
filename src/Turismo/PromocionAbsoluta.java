package Turismo;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private int costo;

	public PromocionAbsoluta(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion, int precio) {
		super(nombrePromocion, atraccionesDeLaPromocion);
		this.costo = setCosto(precio);// se deberia controlar antes de crear el metodo y lanzar una excepcion
	}

	/**
	 * Metodo que valida que el descuento sea menor que el costo
	 * 
	 * @param costo a realizar
	 * @return Regresa el descuento si es valido, y 1 en caso de no ser valido
	 */
	private int setCosto(int costo) {
		if (costo < super.getCosto())
			return costo;
		else
			return 1;
	}

	@Override
	public Integer getCosto() {
		return this.costo;
	}
}
