package Excepciones;

/**
 * Lanzada cuando una entrada no es válida
 * @author Matias
 *
 */
public class InvalidEntryException extends Exception{
	/**
	 * Crea una excepción con un mensaje
	 * @param msg Mensaje
	 */
	public InvalidEntryException(String msg) {
		super(msg);
	}
}