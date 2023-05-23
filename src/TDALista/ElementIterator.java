package TDALista;

import java.util.Iterator;

import java.util.NoSuchElementException;

import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

/**
 * Iterador para lista
 * 
 *
 * @param <E>Tipo de dato en la lista
 */
public class ElementIterator<E> implements Iterator<E> {

	//Atributos de instancia
	protected Position<E> p;
	protected PositionList<E> l;
	
	//Constructor
	/**
	 * Crea un nuevo iterador para una lista
	 * 
	 * @param l Lista a iterar
	 */
	public ElementIterator(PositionList<E> l){
		if (l == null) {
			throw new IllegalArgumentException("La referencia a la lista es nula");
		}
		
		this.l = l;
		if (!l.isEmpty()) {
			try {
				p = l.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
		} else {
			p = null;
		}
		
	}
	
	@Override
	
	public boolean hasNext() {
		return p != null;
	}

	@Override
	
	public E next() {
		
		if (!hasNext()) {
			throw new NoSuchElementException("No hay elemento siguiente para iterar");
		} 
			
		E res = p.element();
		try {
			p = p != l.last() ? l.next(p) : null;
		} catch ( InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			e.printStackTrace();
		}
	
		return res;
	}

}