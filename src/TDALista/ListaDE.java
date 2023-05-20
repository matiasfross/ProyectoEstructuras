package TDALista;

import java.util.Iterator;

import Auxiliares.DNodo;
import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;


/**
 * Lista doblemente enlazada
 *
 *
 * @param <E>Tipo de dato en la lista
 */
public class ListaDE<E> implements PositionList<E> {

	
	//Atributos de instancia
	
	protected int size;
	protected DNodo<E> head, trailer;
	
	
	//Constructor
	/**
	 * Crea una lista de nodos doblemente enlazados
	 */
	public ListaDE() {
		size = 0;
		head = new DNodo<E>(null);
		trailer = new DNodo<E>(null);
		head.setNext(trailer);
		trailer.setPrev(head);
	}
	@Override
	//O(1)
	public int size() {
		return size;
	}

	@Override
	//O(1)
	public boolean isEmpty() {
		return head.getNext() == trailer;
	}

	@Override
	//O(1)
	public Position<E> first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("La lista está vacía");
		}
		return head.getNext();
	}

	@Override
	//O(1)
	public Position<E> last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("La lista está vacía");
		}
		return trailer.getPrev();
	}

	@Override
	//O(1)
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> res = checkPosition(p).getNext();
		if(res == trailer) {
			throw new BoundaryViolationException("No se le puede pedir el siguiente al último");
		}
		
		return res;
	}

	@Override
	//O(1)
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> res = checkPosition(p).getPrev();
		if(res == head) {
			throw new BoundaryViolationException("No se le puede pedir el previo al primero");
		}
		return res;
	}

	@Override
	//O(1)
	public void addFirst(E element) {
		if (element == null) {
			throw new IllegalArgumentException("No se pueden insertar elementos nulos a la lista");
		}
		DNodo<E> newNode = new DNodo<E>(element, head.getNext(), head);
		head.getNext().setPrev(newNode);
		head.setNext(newNode);
		size++;
	}

	@Override
	//O(1)
	public void addLast(E element) {
		if (element == null) {
			throw new IllegalArgumentException("No se pueden insertar elementos nulos a la lista");
		}
		DNodo<E> newNode = new DNodo<E>(element, trailer, trailer.getPrev());
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
		size++;
	}

	@Override
	//O(1)
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if (element == null) {
			throw new IllegalArgumentException("No se pueden insertar elementos nulos a la lista");
		}
		DNodo<E> n = checkPosition(p);
		DNodo<E> newNode = new DNodo<E>(element, n.getNext() , n);
		n.getNext().setPrev(newNode);
		n.setNext(newNode);
		size++;
		
	}

	@Override
	//O(1)
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		if (element == null) {
			throw new IllegalArgumentException("No se pueden insertar elementos nulos a la lista");
		}
		DNodo<E> n = checkPosition(p);
		DNodo<E> newNode = new DNodo<E>(element , n , n.getPrev());
		n.getPrev().setNext(newNode);
		n.setPrev(newNode);
		size++;
	}

	@Override
	//O(1)
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		n.getNext().setPrev(n.getPrev());
		n.getPrev().setNext(n.getNext());
		E res = n.element();
		n.setVal(null);
		n.setNext(null);
		n.setPrev(null);
		size--;
		return res;
	}

	@Override
	//O(1)
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if (element == null) {
			throw new IllegalArgumentException("No se pueden insertar elementos nulos a la lista");
		}
		DNodo<E> n = checkPosition(p);
		E res = n.element();
		n.setVal(element);
		return res;
	}

	@Override
	//O(1)
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	//O(n)
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> res = new ListaDE<Position<E>>();
		DNodo<E> p = head.getNext();
		while (p != trailer) {
			res.addLast(p);
			p = p.getNext();
		}
		return res;
	}

	//Método auxiliar
	//O(1)
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		DNodo<E> aRetornar;
		try {
			if (p == null) {
				throw new InvalidPositionException("Posicion nula");
			}
			if (p.element() == null) {
				throw new InvalidPositionException("Posicion eliminada previamente");
			}
			aRetornar = (DNodo<E>)p;
			if (aRetornar.getNext() == null || aRetornar.getPrev() == null) {
				throw new InvalidPositionException("La posición no pertenece a la lista");
			}
		} catch(ClassCastException e) {
			throw new InvalidPositionException("Posición inválida para la lista");
		}
		return aRetornar;
	}
}
