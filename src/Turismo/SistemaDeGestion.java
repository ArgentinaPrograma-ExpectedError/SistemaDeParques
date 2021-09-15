package Turismo;

import java.io.IOException;

public class SistemaDeGestion {

	public static void main(String[] args) {
		try {
			Parque tierraMedia = new Parque();
			tierraMedia.iniciarSistema();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}