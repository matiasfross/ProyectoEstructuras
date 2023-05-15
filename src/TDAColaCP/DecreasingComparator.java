package TDAColaCP;

import java.util.Comparator;

public class DecreasingComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E a, E b) {
		
		return b.compareTo(a);
	}

}
