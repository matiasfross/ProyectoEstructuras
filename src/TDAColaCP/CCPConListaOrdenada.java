package TDAColaCP;

import java.util.Comparator;

import java.util.Iterator;

import Auxiliares.Entrada;
import Auxiliares.Entry;
import Auxiliares.Position;
import Excepciones.EmptyListException;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.PositionList;
import TDALista.ListaDE;

/**
 * Cola con prioridad con una lista ordenada
 * @author Matias
 *
 * @param <K> Tipo de dato de la clave
 * @param <V> Tipo de dato del valor
 */
public class CCPConListaOrdenada<K , V> implements PriorityQueue<K, V> {

	//Atributos de instancia
	protected PositionList<Entry<K , V>> lista;
	protected Comparator<K> comp;
	//Constructor
	
	/**
	 * Crea una nueva CCP
	 * @param comp Comparador para la prioridad
	 */
	public CCPConListaOrdenada(Comparator<K> comp) {
		if (comp == null) {
			throw new IllegalArgumentException("La referencia al comparador no puede ser nula");
		}
		lista = new ListaDE<Entry<K , V>>();
		this.comp = comp;
	}
	
	@Override
	//O(1)
	public int size() {
		return lista.size();
	}

	@Override
	//O(1)
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("La CCP está vacía");
		}
		Entry<K , V> res = null;
		try {
			res = lista.first().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("La referencia a la clave es nula");
		}
		
		if (value == null) {
			throw new IllegalArgumentException("La referencia al valor es nula");
		}
		
		Entry<K , V> res = null;
		Iterator<Position<Entry<K , V>>> it = lista.positions().iterator();
		while (it.hasNext() && res == null) {
			Position<Entry<K , V>> pos = it.next();
			if (comp.compare(key, pos.element().getKey()) <= 0) {
				res = new Entrada<K , V>(key , value);
				try {
					
					lista.addBefore(pos, res);
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
			}
		}
		if (res == null){
			res = new Entrada<K , V>( key , value);
			lista.addLast(res);
		}
		
		return res;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("La CCP está vacía");
		}
		Entry<K , V> res = null;
		try {
			res = lista.remove(lista.first());
		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	

}
