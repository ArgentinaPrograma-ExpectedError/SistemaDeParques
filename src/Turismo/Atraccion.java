package Turismo;

import java.util.ArrayList;
import java.util.Objects;

public class Atraccion implements TodasLasAtracciones, Comparable<Atraccion> {
	private String nombreDeAtraccion;
	private int costoDevisita;
	private int cupoDePersonasDiario;
	private double tiempoPromedioDeUso;
	private TipoDeAtraccion tipoDeAtraccion;

	public Atraccion(String nombreDeAtraccion, int costoDeVisita, int cupoDePersonasDiario, double tiempoPromedioDeUso,
			TipoDeAtraccion tipoDeAtraccion) {
		this.nombreDeAtraccion = nombreDeAtraccion;
		this.costoDevisita = costoDeVisita;
		this.cupoDePersonasDiario = cupoDePersonasDiario;
		this.tiempoPromedioDeUso = tiempoPromedioDeUso;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	public Double getTiempo() {
		return this.tiempoPromedioDeUso;
	}

	public int getCupoDePersonasDiario() {
		return this.cupoDePersonasDiario;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public void decrementarCupo() {
		this.cupoDePersonasDiario--;
	}

	public boolean hayCupo() {
		return (this.getCupoDePersonasDiario() > 0);
	}

	@Override
	public Integer getCosto() {
		return this.costoDevisita;
	}

	public String getNombreDeAtraccion() {
		return nombreDeAtraccion;
	}

	@Override
	public int compareTo(Atraccion o) {
		if (this.getCosto().compareTo(o.getCosto()) == 0) {
			return this.getTiempo().compareTo(o.getTiempo()) * -1;
		} else
			return this.getCosto().compareTo(o.getCosto()) * -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDevisita, cupoDePersonasDiario, nombreDeAtraccion, tiempoPromedioDeUso,
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
		Atraccion other = (Atraccion) obj;
		return costoDevisita == other.costoDevisita && cupoDePersonasDiario == other.cupoDePersonasDiario
				&& Objects.equals(nombreDeAtraccion, other.nombreDeAtraccion)
				&& Double.doubleToLongBits(tiempoPromedioDeUso) == Double.doubleToLongBits(other.tiempoPromedioDeUso)
				&& tipoDeAtraccion == other.tipoDeAtraccion;
	}

	public String toStringParaPromo() {
		return nombreDeAtraccion + ". ";
	}

	@Override
	public String toString() {
		return nombreDeAtraccion + " - " + tipoDeAtraccion.getNombreDeTipo() + "\n- Precio: " + costoDevisita
				+ " monedas de oro \n- Duración: " + tiempoPromedioDeUso + " horas";
	}

	@Override
	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		ArrayList<Atraccion> arrayList = new ArrayList<Atraccion>();
		arrayList.add(this);
		return arrayList;
	}

}
