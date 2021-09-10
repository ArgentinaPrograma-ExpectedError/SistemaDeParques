package Turismo;

@SuppressWarnings("serial")
public class InvalidIO extends RuntimeException {

	public InvalidIO() {
		super("Se ha presionado una tecla incorrecta");
	}
}
