package Excepciones;

/**
 * Lanzada cuando una cola con prioridad está vacía
 * @author Matias
 *
 */
public class EmptyPriorityQueueException extends Exception {
	/**
	 * Crea una excepción con un mensaje
	 * @param msg Mensaje
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}
}
