package Turismo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario {

	private String nombre;
	private int dineroDisponible;
	private double tiempoDisponible;
	TipoDeAtraccion atraccionPreferida;
	private ArrayList<TodasLasAtracciones> sugerenciasConfirmadas;
	private int dineroGastado = 0;
	private double tiempoGastado = 0;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionPreferida) {
		this.nombre = nombre;
		this.dineroDisponible = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
		this.sugerenciasConfirmadas = new ArrayList<TodasLasAtracciones>();
	}

	public int getDineroDisponible() {
		return dineroDisponible;
	}

	public void disminuirDineroDisponible(int dineroDisponible) {
		this.dineroDisponible -= dineroDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void disminuirTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible -= tiempoDisponible;
	}

	public ArrayList<TodasLasAtracciones> getSugerenciasConfirmadas() {
		return sugerenciasConfirmadas;
	}

	public int getDineroGastado() {
		return dineroGastado;
	}

	public void aumentarDineroGastado(int dineroGastado) {
		this.dineroGastado += dineroGastado;
	}

	public double getTiempoGastado() {
		return tiempoGastado;
	}

	public void aumentarTiempoGastado(double tiempoGastado) {
		this.tiempoGastado += tiempoGastado;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoDeAtraccion getAtraccionPreferida() {
		return atraccionPreferida;
	}
	
	public void setSugerenciasConfirmadas(TodasLasAtracciones confirmada) {
		this.sugerenciasConfirmadas.addAll(confirmada.getAtraccionesIncluidas());
	}

	public void confirmarSugerencia(TodasLasAtracciones confirmada) {
		setSugerenciasConfirmadas(confirmada);
		confirmada.decrementarCupo();
		setTiempoYDinero(confirmada);
	}

	private void setTiempoYDinero(TodasLasAtracciones confirmada) {
		disminuirDineroDisponible(confirmada.getCosto());
		aumentarDineroGastado(confirmada.getCosto());
		aumentarTiempoGastado(confirmada.getTiempo());
		disminuirTiempoDisponible(confirmada.getTiempo());
	}

	public boolean yaLaCompro(TodasLasAtracciones sugerencia) {
		boolean resultado = false;
		resultado = sugerenciasConfirmadas.contains(sugerencia);
		if (!resultado) {
			for (TodasLasAtracciones s : sugerencia.getAtraccionesIncluidas()) {
				resultado = sugerenciasConfirmadas.contains(s);
			}
		}
		return resultado;
	}
	
	public boolean tieneDineroDisponible(TodasLasAtracciones sugerencia) {
		return getDineroDisponible()>=sugerencia.getCosto();
	}

	public boolean tieneTiempoDisponible(TodasLasAtracciones sugerencia) {
		return getTiempoDisponible()>=sugerencia.getTiempo();
	}

	
}
