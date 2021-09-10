package Turismo;

import java.util.Comparator;

public class Ordenador implements Comparator<TodasLasAtracciones> {

	@Override
	public int compare(TodasLasAtracciones o1, TodasLasAtracciones o2) {
		Integer tipo1 = (Integer) o1.getAtraccionesIncluidas().size();
		Integer tipo2 = (Integer) o2.getAtraccionesIncluidas().size();
		int compareToTipo = tipo1.compareTo(tipo2);
		int compareToCosto = o1.getCosto().compareTo(o2.getCosto());
		int compareToTiempo = o1.getTiempo().compareTo(o2.getTiempo());

		if (compareToTipo == 0) {
			if (compareToCosto == 0) {
				return compareToTiempo * -1;
			} else
				return compareToCosto * -1;
		}
		return compareToTipo * -1;
	}
}