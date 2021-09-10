package Turismo;

import java.util.ArrayList;

public interface TodasLasAtracciones {
	public abstract Integer getCosto();

	public abstract ArrayList<Atraccion> getAtraccionesIncluidas();

	public abstract Double getTiempo();

	public abstract TipoDeAtraccion getTipoDeAtraccion();

	public abstract void decrementarCupo();

	public abstract boolean hayCupo();
}
