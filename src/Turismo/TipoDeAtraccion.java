package Turismo;

public enum TipoDeAtraccion {

	AVENTURA("Aventura"), PAISAJE("Paisaje"), DEGUSTACION("Degustacion");

	private String nombreDeTipo;

	private TipoDeAtraccion(String nombreDeTipo) {
		this.nombreDeTipo = nombreDeTipo;
	}

	public String getNombreDeTipo() {
		return nombreDeTipo;
	}

}
