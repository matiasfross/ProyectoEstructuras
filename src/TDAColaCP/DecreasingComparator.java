package TDAColaCP;

import java.util.Comparator;

/**
 * 
 * Comparador invertido
 *
 * @param <E> Tipo de dato a comparar
 */
public class DecreasingComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E a, E b) {
		
		return b.compareTo(a);
	}

}
