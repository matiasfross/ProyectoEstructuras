package TDAColaCP;

import java.util.Comparator;

public class DefaultComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E a, E b) {
		return a.compareTo(b);
	}

}
