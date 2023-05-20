package TDAColaCP;

import java.util.Comparator;

import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;
import Auxiliares.Entrada;
import Auxiliares.Entry;

/**
 * Cola con prioridad con heap
 * 
 *
 * @param <K> Tipo de dato de la clave
 * @param <V> Tipo de dato del valor
 */
public class CCPConHeap<K, V> implements PriorityQueue<K , V>{
	
	//Atributos de instancia
	protected Entrada<K ,V>[] heap;
	protected int size;
	protected Comparator<K> comp;
	
	//Constructor
	/**
	 * Crea una nueva cola con prioridad con heap
	 * @param maxElems Cantidad máxima de elementos en la estructura
	 * @param comp Comparador para las claves de los elementos
	 */
	@SuppressWarnings("unchecked")
	public CCPConHeap(int maxElems , Comparator<K> comp) {
		if (maxElems <= 1) {
			throw new IllegalArgumentException("La cola con prioridad poder tener más de dos elementos");
		}
		if (comp == null) {
			throw new IllegalArgumentException("La referencia al comparador no puede ser nula");
		}
		size = 0;
		heap = (Entrada<K,V> []) new Entrada[maxElems];
		this.comp = comp;
	}
	
	/**
	 * Crea una nueva cola con prioridad con heap
	 * @param comp Comparador para las claves de los elementos
	 */
	public CCPConHeap(Comparator<K> comp) {
		this(100 , comp);
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("La CCP está vacía");
		}
		
		return heap[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("La referencia a la clave es nula");
		}
		
		if (value == null) {
			throw new IllegalArgumentException("La referencia al valor es nula");
		}
		Entrada<K , V> res = new Entrada<K , V>(key , value);
		heap[++size] = res;
		
		int i = size;
		boolean encontre = false;
		while(i > 1 && !encontre) {
			int padre = i / 2;
			if (comp.compare(heap[i].getKey(), heap[padre].getKey())< 0) {
				swap(i , padre);
				i = padre;
			} else {
				encontre = true;
			}
		}
		
		if (size + 1 == heap.length) {
			extenderHeap();
		}
		
		return res;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K , V> res = min();
		if (size == 1) {
			heap[1] = null;
			size = 0;
		} else {
			heap[1] = heap[size];
			heap[size] = null;
			size--;
			int i = 1;
			boolean encontre = false;
			while (!encontre) {
				int l = 2 * i;
				int r = 2 * i + 1;
				boolean hasLeft = l <= size;
				boolean hasRight = r <= size;
				if (hasLeft) {
					int m;
					if (hasRight) {
						if (comp.compare(heap[l].getKey(), heap[r].getKey())< 0) {
							m = l;
						} else {
							m = r;
						}
					} else {
						m = l;
					}
					if (comp.compare(heap[i].getKey(), heap[m].getKey())> 0) {
						swap(i , m);
						i = m;
					} else {
						encontre = true;
					}
				} else {
					encontre = true;
				}
			}
		}
		return res;
	}

	/**
	 * Intercambia dos elementos en el arreglo heap
	 * @param a índice del primer elemento 
	 * @param b índice del segundo elemento
	 */
	//O(1)
	private void swap(int a , int b) {
		Entrada<K , V> aux = heap[a];
		heap[a] = heap[b];
		heap[b] = aux;
	}
	
	/**
	 * Extiende el tamaño del arreglo heap
	 */
	//O(n)
	private void extenderHeap() {
		Entrada<K ,V>[] aux = (Entrada<K ,V>[])new Entrada[heap.length * 2];
		for(int i = 0; i < heap.length ; i++) {
			aux[i] = heap[i];
		}
		heap = aux;
	}
}
