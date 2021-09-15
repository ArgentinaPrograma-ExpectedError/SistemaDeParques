package Turismo;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private int costo;

	public PromocionAbsoluta(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion, int precio) {
		super(nombrePromocion, atraccionesDeLaPromocion);
		this.costo = precio;
	}

	@Override
	public Integer getCosto() {
		return this.costo;
	}
}
