package TDANodo;

import TDALista.Position;
/**
 * Nodo de doble enlace
 * @author Matias
 *
 * @param <E> Tipo de dato en el nodo
 */
public class DNodo<E> implements Position<E>{
	//Atributos de instancia
	
	protected E val;
	protected DNodo<E> prev , next;
	
	//Constructor
	
	/**
	 * Crea un nuevo nodo doble con el dato dado por parámetro
	 * 
	 * @param val Dato en el nodo
	 */
	public DNodo(E val) {
		this.val = val;
	}
	
	/**
	 * Crea un nuevo nodo doble con el dato dado por parámetro y un nodo siguiente
	 * 
	 * @param val Dato en el nodo
	 * @param next Nodo siguiente
	 */
	public DNodo(E val , DNodo<E> next) {
		this.val = val;
		this.next = next;
	}
	
	/**
	 * Crea un nuevo nodo doble con el dato dado por parámetro, un nodo siguiente y un nodo previo
	 * 
	 * @param val Dato en el nodo
	 * @param next Nodo siguiente
	 * @param prev Nodo previo
	 */
	public DNodo(E val , DNodo<E> next , DNodo<E> prev) {
		this.val = val;
		this.next = next;
		this.prev = prev;
	}

	//Consultas
	
	@Override
	public E element() {
		return val;
	}
	
	/**
	 * Devuelve el nodo siguiente
	 * 
	 * @return Nodo siguiente
	 */
	public DNodo<E> getNext(){
		return next;
	}
	
	/**
	 * Devuelve el nodo previo
	 * 
	 * @return Nodo previo
	 */
	public DNodo<E> getPrev(){
		return prev;
	}
	
	//Comandos
	
	/**
	 * Determina un nuevo dato para el nodo
	 * 
	 * @param val Dato nuevo
	 */
	public void setVal(E val) {
		this.val = val;
	}
	
	/**
	 * Determina un nuevo nodo siguiente
	 * 
	 * @param next Nuevo nodo siguiente
	 */
	public void setNext(DNodo<E> next) {
		this.next = next;
	}
	
	/**
	 * Determina un nuevo nodo previo
	 * 
	 * @param prev Nuevo nodo previo
	 */
	public void setPrev(DNodo<E> prev) {
		this.prev = prev;
	}
}
