package Turismo;

import java.util.ArrayList;
import java.util.Collections;

public class PromocionAxB extends Promocion {
	private int descuento;

	public PromocionAxB(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion) {
		super(nombrePromocion, atraccionesDeLaPromocion);
		this.descuento = setDescuento();
	}

	private Integer getDescuento() {
		return this.descuento;
	}

	/**
	 * Metodo para obtener el descuento de la promo
	 * 
	 * @return Regresa el costo de la atraccion mas barata
	 */
	private Integer setDescuento() {
		ArrayList<Atraccion> atracciones = getAtraccionesIncluidas();
		Collections.sort(atracciones);
		return atracciones.get(atracciones.size() - 1).getCosto();
	}
	/*
	 * public Atraccion atraccionGratis() { Atraccion[] atracciones =
	 * getAtraccionesDeLaPromocion(); Arrays.sort(atracciones); return
	 * atracciones[0]; }
	 */

	@Override
	public Integer getCosto() {
		return super.getCosto() - this.getDescuento();
	}

}
