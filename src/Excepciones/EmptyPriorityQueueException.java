package Excepciones;

/**
 *  Modela una situacion en la que se intenta accionar sobre una cola con prioridad sin ningun elemento
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
