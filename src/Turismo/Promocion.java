package Turismo;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Promocion implements TodasLasAtracciones, Comparable<Promocion> {

	protected String nombrePromocion;
	protected double tiempoPromocion;
	private double descuentoPromocion;
	private TipoDeAtraccion tipoDeAtraccion;
	protected ArrayList<Atraccion> atraccionesIncluidas;

	public Promocion(String nombrePromocion, ArrayList<Atraccion> atraccionesDeLaPromocion) {
		this.nombrePromocion = nombrePromocion;
		this.atraccionesIncluidas = atraccionesDeLaPromocion;
		this.tiempoPromocion = calcularTiempoDePromocion();
		this.tipoDeAtraccion = atraccionesDeLaPromocion.get(0).getTipoDeAtraccion();
	}

	@Override
	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double calcularTiempoDePromocion() {
		double tiempo = 0;
		for (Atraccion atraccion : atraccionesIncluidas) {
			tiempo += atraccion.getTiempo();
		}
		return tiempo;
	}

	public Double getTiempo() {
		return tiempoPromocion;
	}

	public Integer getCosto() {
		int costoTotal = 0;
		for (Atraccion atraccion : atraccionesIncluidas) {
			costoTotal += atraccion.getCosto();
		}
		return costoTotal;
	}

	public void decrementarCupo() {
		for (Atraccion atraccion : atraccionesIncluidas) {
			atraccion.decrementarCupo();
		}
	}

	public boolean hayCupo() {
		for (Atraccion it : atraccionesIncluidas) {
			if (!it.hayCupo())
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesIncluidas, descuentoPromocion, nombrePromocion, tiempoPromocion,
				tipoDeAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas)
				&& Double.doubleToLongBits(descuentoPromocion) == Double.doubleToLongBits(other.descuentoPromocion)
				&& Objects.equals(nombrePromocion, other.nombrePromocion)
				&& Double.doubleToLongBits(tiempoPromocion) == Double.doubleToLongBits(other.tiempoPromocion)
				&& tipoDeAtraccion == other.tipoDeAtraccion;
	}

	@Override
	public int compareTo(Promocion o) {
		if (this.getCosto().compareTo(o.getCosto()) == 0) {
			return this.getTiempo().compareTo(o.getTiempo()) * -1;
		} else
			return this.getCosto().compareTo(o.getCosto()) * -1;
	}

	@Override
	public String toString() {
		return nombrePromocion + " - " + tipoDeAtraccion.getNombreDeTipo().toUpperCase() + "\n"
				+ "- Atracciones incluidas: " + toStringAtracciones(atraccionesIncluidas) + "\n" + "- Precio: "
				+ this.getCosto() + " monedas de oro\n- Duración: " + this.getTiempo() + " horas";
	}

	protected String toStringAtracciones(ArrayList<Atraccion> atraccionesDeLaPromocion) {
		String s = "";
		for (Atraccion a : atraccionesDeLaPromocion) {
			s += a.getNombreDeAtraccion() + ", ";
		}
		s = s.substring(0, s.length() - 2);
		return s;
	}
}
