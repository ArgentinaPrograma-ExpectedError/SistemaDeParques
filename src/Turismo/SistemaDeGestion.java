package Turismo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SistemaDeGestion {

	public static void main(String[] args) throws IOException {
		ArrayList<Usuario> usuarios = cargaDeUsuariosPorArchivo();
		ArrayList<TodasLasAtracciones> todasLasAtracciones = cargaDeTodasLasAtraccionesYPromocionesPorArchivo();
		Collections.sort(todasLasAtracciones, new Ordenador());
		generarSugerenciasParaTodos(usuarios, todasLasAtracciones);
	}

	/**
	 * Metodo que recorre el ArrayList de usuarios llamando a generar sugerencias
	 * para cada uno de ellos.
	 * 
	 * @param PrintWriter salida, Arraylist de usuarios y Arraylist de
	 *                    TodasLasAtracciones ya ordenadas
	 */

	private static void generarSugerenciasParaTodos(ArrayList<Usuario> usuarios,
			ArrayList<TodasLasAtracciones> todasLasAtracciones) {

		for (Usuario u : usuarios) {
			mensajeDeBienvenida(u);
			generarSugerencias(u, todasLasAtracciones);
			agregarAlArchivo(u);
		}
		System.out.println("      *****************************EXIT***********************************");

	}

	static int contadorDeSugerencias = 0; // Inicializa variable local para mostrar sugerencias numeradas al usuario

	/**
	 * Metodo para generar sugerencias al usuario a partir de sus preferencias,
	 * tiempo y dinero
	 * 
	 * @param Usuario usuario y Arraylist de TodasLasAtracciones ya ordenadas
	 */

	private static void generarSugerencias(Usuario usuario, ArrayList<TodasLasAtracciones> todasLasAtracciones) {

		for (TodasLasAtracciones iterador : todasLasAtracciones) {
			if (sonDelGustoDelUsuario(usuario, iterador) && !usuario.yaLaCompro(iterador)) {
				confirmacion(usuario, iterador);
			}
		}

		for (TodasLasAtracciones iterador : todasLasAtracciones) {
			if (!sonDelGustoDelUsuario(usuario, iterador) && !usuario.yaLaCompro(iterador)) {
				confirmacion(usuario, iterador);
			}
		}
		contadorDeSugerencias = 0;
	}

	/**
	 * Metodo para mostrar los datos del usuario por pantalla antes de comenzar a
	 * comprar
	 * 
	 * @param Usuario usuario
	 */
	private static void mensajeDeBienvenida(Usuario usuario) {
		System.out.println(
				"*********************************************************************************************");
		System.out.println("Bienvenido " + usuario.getNombre() + ". Usted cuenta con " + usuario.getDineroDisponible()
				+ " monedas de oro y " + usuario.getTiempoDisponible() + " hs. Su atraccion preferida es "+ usuario.getAtraccionPreferida().getNombreDeTipo());
		System.out.println(" ");
		System.out.println("A continuacion se le presentaran sugerencias para mejorar su estadia en el parque:");
		System.out.println(" ");
	}

	/**
	 * Metodo para preguntar si una Atraccion/Promocion es del gusto del usuario
	 * 
	 * @param Usuario usuario y la Atraccion/Promocion
	 * @return Regresa True si es del gusto del usuario
	 */

	protected static boolean sonDelGustoDelUsuario(Usuario usuario, TodasLasAtracciones iterador) {
		return iterador.getTipoDeAtraccion() == usuario.getAtraccionPreferida();
	}

	/**
	 * Metodo para confirmar la sugerencia del Sistema de Gestion al usuario
	 * 
	 * @param Usuario usuario y la Atraccion/Promocion
	 * @throw crea una excepcion al ingresar una tecla incorrecta por teclado, la
	 *        cual salva dentro de catch llamando nuevamente el metodo
	 */

	@SuppressWarnings("static-access")
	private static void confirmacion(Usuario usuario, TodasLasAtracciones sugerencia) {
		if (usuario.tieneDineroDisponible(sugerencia) && usuario.tieneTiempoDisponible(sugerencia)
				&& sugerencia.hayCupo()) {
			contadorDeSugerencias++;
			System.out.println(contadorDeSugerencias + ") " + sugerencia.toString());
			System.out.println("Desea aceptar la sugerencia? - Presione S/N y enter");
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String caracter = in.nextLine();
			caracter = caracter.toLowerCase();

			try {
				if (caracter.equals("s")) {
					usuario.confirmarSugerencia(sugerencia);
				} else if (!caracter.equals("n")) {
					throw new InvalidIO();
				}
			} catch (InvalidIO e) {
				contadorDeSugerencias--;
				confirmacion(usuario, sugerencia);
			}
		}
	}

	/**
	 * Metodo que agrega al archivo las sugerencias aceptadas de un usuario junto
	 * con el tiempo y dinero a emplear.
	 * 
	 * @param Usuario usuario y PrintWriter linea de archivo de texto
	 * @throws IOException
	 */
	private static void agregarAlArchivo(Usuario usuario) {
		PrintWriter salida = null;
		try {
			FileWriter nombreArchivo = new FileWriter(usuario.getNombre() + "-salida.out");
			salida = new PrintWriter(nombreArchivo);
			salida.println("************************PARQUE DE ATRACCIONES TIERRA MEDIA************************");
			salida.println(" ");
			salida.println(" Sr/a: " + usuario.getNombre());
			salida.println(" ");
			if (!usuario.getSugerenciasConfirmadas().isEmpty()) {
				salida.println(" Su itinerario incluye las siguientes atracciones: ");

				for (TodasLasAtracciones promocion : usuario.getSugerenciasConfirmadas()) {
					salida.println("  · " + ((Atraccion) promocion).getNombreDeAtraccion());
				}
				salida.println(" ");
				salida.println(" El costo total es de " + usuario.getDineroGastado()
						+ " monedas de oro, el tiempo aproximado que pasará es de " + usuario.getTiempoGastado()
						+ " horas.");
				salida.println(" ");
			} else {
				salida.println("El usuario no realizo ninguna compra");

			}
			salida.println("                                                                    by @ExpectedError");

			salida.println("**************************************************************************************");

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			salida.close();
		}

	}

	/**
	 * Metodo que carga desde archivo los usuarios El formato de archivo debe ser:
	 * nombre,tipoPreferido,dinero,tiempo.
	 * 
	 * @return Regresa un Arraylist con todos los usuarios
	 */

	private static ArrayList<Usuario> cargaDeUsuariosPorArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			fr = new FileReader("usuariosTierraMedia.txt");
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				try {
					String[] campos = linea.split(",");
					String nombre = campos[0];
					TipoDeAtraccion tipoPreferido = TipoDeAtraccion.valueOf(campos[1]);
					int dineroDisponible = Integer.parseInt(campos[2]);
					double tiempoDisponible = Double.parseDouble(campos[3]);
					Usuario usuario = new Usuario(nombre, dineroDisponible, tiempoDisponible, tipoPreferido);
					usuarios.add(usuario);
				} catch (NumberFormatException e) {
					System.out.println("Uno de los datos leidos no es un correcto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;

	}

	/**
	 * Metodo que carga desde archivo las Atracciones El formato de archivo debe
	 * ser: "nombre",costo,tiemoi,cupo,tipoDeAtraccion
	 * 
	 * @return Regresa un Arraylist con todas las Atracciones
	 */

	private static ArrayList<Atraccion> cargaDeAtraccionesPorArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		try {
			fr = new FileReader("atraccionesTierraMedia.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] campos = linea.split(",");
					String nombre = campos[0];
					int costo = Integer.parseInt(campos[1]);
					double tiempo = Double.parseDouble(campos[2]);
					int cupo = Integer.parseInt(campos[3]);
					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(campos[4]);
					Atraccion atraccion = new Atraccion(nombre, costo, cupo, tiempo, tipo);
					atracciones.add(atraccion);
				} catch (NumberFormatException e) {
					System.out.println("ATRACCION Uno de los datos leidos no es un double");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return atracciones;
	}

	/**
	 * Metodo que carga desde archivo las promociones y desde otro metodo las
	 * atracciones. El formato de archivo debe ser por linea:
	 * tipoDePromocion,nombreDePromocion,tipoDeAtraccion,descuento(En caso de ser
	 * AxB se ingresa 0),atracciones incluidas separadas por comas. Las atracciones
	 * de una promocion deben ser del mismo tipo y contener al menos una.
	 * 
	 * @return Regresa un Arraylist con todas las Atracciones y Promociones
	 */
	private static ArrayList<TodasLasAtracciones> cargaDeTodasLasAtraccionesYPromocionesPorArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Atraccion> atracciones = cargaDeAtraccionesPorArchivo();
		ArrayList<TodasLasAtracciones> todasLasAtracciones = new ArrayList<TodasLasAtracciones>();

		try {
			fr = new FileReader("promocionesTierraMedia.txt");
			br = new BufferedReader(fr);
			String linea;
			String tipoDePromo = "";
			String nombrePromocion = "";
			Integer porcentajeDeDescuento = null;
			ArrayList<Atraccion> atraccionesParaLaPromo = null;

			while ((linea = br.readLine()) != null) {
				try {
					String[] campos = linea.split(",");
					int cantidadDeAtraccionesEnPromo = campos.length - 4;
					tipoDePromo = campos[0];
					nombrePromocion = campos[1];
					TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.valueOf(campos[2]);
					porcentajeDeDescuento = Integer.parseInt(campos[3]);
					atraccionesParaLaPromo = new ArrayList<Atraccion>();
					if (cantidadDeAtraccionesEnPromo > 0) {
						for (int i = 0; i < cantidadDeAtraccionesEnPromo; i++) {
							for (Atraccion a : atracciones) {
								if (a.getNombreDeAtraccion().contains(campos[i + 4])) {
									atraccionesParaLaPromo.add(a);
								}
							}
						}
						if (todasSonDelMismoTipo(atraccionesParaLaPromo, tipoDeAtraccion)) {
							crearPromocionSegunTipo(todasLasAtracciones, tipoDePromo, nombrePromocion,
									porcentajeDeDescuento, atraccionesParaLaPromo);
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("PROMOCION Uno de los datos leidos no es un double");
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		todasLasAtracciones.addAll(atracciones);
		return todasLasAtracciones;
	}

	/**
	 * Metodo que controla si todas las atracciones de una promo son del mismo tipo
	 * indicado
	 * 
	 * @return True si son todas del mismo tipo
	 */
	private static boolean todasSonDelMismoTipo(ArrayList<Atraccion> atraccionesParaLaPromo,
			TipoDeAtraccion tipoDePromo) {
		for (Atraccion a : atraccionesParaLaPromo) {
			if (!a.getTipoDeAtraccion().equals(tipoDePromo))
				return false;
		}
		return true;
	}

	/**
	 * Metodo que crea la promocion segun el tipo ingresado en el archivo
	 * 
	 */
	private static void crearPromocionSegunTipo(ArrayList<TodasLasAtracciones> todasLasAtracciones, String tipoDePromo,
			String nombrePromocion, Integer porcentajeDeDescuento, ArrayList<Atraccion> atraccionesParaLaPromo) {
		if (tipoDePromo.contains("Absoluta")) {
			Promocion promocion = new PromocionAbsoluta(nombrePromocion, atraccionesParaLaPromo, porcentajeDeDescuento);
			todasLasAtracciones.add(promocion);
		}
		if (tipoDePromo.contains("Porcentual")) {
			Promocion promocion = new PromocionPorcentual(nombrePromocion, atraccionesParaLaPromo,
					porcentajeDeDescuento);
			todasLasAtracciones.add(promocion);
		}
		if (tipoDePromo.contains("AxB")) {
			Promocion promocion = new PromocionAxB(nombrePromocion, atraccionesParaLaPromo);
			todasLasAtracciones.add(promocion);
		}
	}
}