package Turismo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {
	Usuario usuario1;
	Usuario usuario2;
	
	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	Atraccion atraccion4;

	Promocion promocion1;
	Promocion promocion2;
	Promocion promocion3;

	@Before
	public void setup() {
		usuario1 = new Usuario("Juan Carlos", 23, 160, TipoDeAtraccion.PAISAJE);
		usuario2 = new Usuario("Juana De Arco", 12, 150, TipoDeAtraccion.DEGUSTACION);

		atraccion1 = new Atraccion("Desierto", 5, 2, 10, TipoDeAtraccion.PAISAJE);
		atraccion2 = new Atraccion("Fuego", 13, 44, 7, TipoDeAtraccion.DEGUSTACION);
		atraccion3 = new Atraccion("Agua", 8, 1, 20, TipoDeAtraccion.DEGUSTACION);
		atraccion4 = new Atraccion("Aire", 11, 5, 20, TipoDeAtraccion.DEGUSTACION);
		ArrayList<Atraccion> a1 = new ArrayList<Atraccion>();
		a1.add(atraccion1);
		a1.add(atraccion2);
		ArrayList<Atraccion> a2 = new ArrayList<Atraccion>();
		a2.add(atraccion4);
		a2.add(atraccion2);
        ArrayList<Atraccion> a3 = new ArrayList<Atraccion>();
		a3.add(atraccion4);
		a3.add(atraccion3);
		a3.add(atraccion1);
		promocion1 = new PromocionAbsoluta("Aventura",a1, 2);
		promocion2 = new PromocionPorcentual("Paisajes ", a2, 50);
		promocion3 = new PromocionAxB("Degustacion",a3);
	}

	@Test
	public void inicializacionTest() {
		assertNotNull(usuario1);
		assertNotNull(atraccion1);
		assertNotNull(promocion1);
		assertNotNull(promocion2);
		assertNotNull(promocion3);
	}

	@Test
	public void metodosDelUsuarioTest() {
		assertEquals(23, usuario1.getDineroDisponible(), 0);
		assertEquals(TipoDeAtraccion.PAISAJE, usuario1.getAtraccionPreferida());
		assertTrue(usuario1.tieneDineroDisponible(atraccion1));
		usuario1.disminuirDineroDisponible(23);
		assertFalse(usuario1.tieneDineroDisponible(atraccion1));
		assertFalse(usuario2.tieneDineroDisponible(atraccion2));
	}

	@Test
	public void metodosDeAtraccionesTest() {
		assertEquals(5, atraccion1.getCosto(), 0);
		assertTrue(atraccion1.hayCupo());
		atraccion1.decrementarCupo();
		atraccion1.decrementarCupo();
		assertFalse(atraccion1.hayCupo());
		assertEquals(7, atraccion2.getTiempo(), 0);
	}

	@Test
	public void metodosDePromocionesTest() {
		assertEquals(16, promocion1.getCosto(), 0);
		assertEquals(12, promocion2.getCosto(), 0);
		assertEquals(19, promocion3.getCosto(), 0);
		assertEquals(27, promocion2.calcularTiempoDePromocion(), 0);
		assertTrue(promocion3.getAtraccionesIncluidas().get(1).hayCupo());
		promocion3.decrementarCupo();
		assertFalse(promocion3.getAtraccionesIncluidas().get(1).hayCupo());
	}

	@Test
	public void metodosDeGestionTest() {
		usuario1.confirmarSugerencia(atraccion1);
		assertTrue(usuario1.getSugerenciasConfirmadas().contains(atraccion1));
		usuario2.confirmarSugerencia(promocion2);
		assertTrue(usuario2.getSugerenciasConfirmadas().contains(atraccion4));
		usuario2.confirmarSugerencia(atraccion3);
		assertFalse(usuario2.yaLaCompro(atraccion1));
		assertTrue(SistemaDeGestion.sonDelGustoDelUsuario(usuario2, atraccion3));	
	}


}
