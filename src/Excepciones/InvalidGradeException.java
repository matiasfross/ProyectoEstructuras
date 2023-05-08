package Excepciones;

/**
 * Lanzada cuando una nota no es válida
 * @author Matias
 *
 */
public class InvalidGradeException extends Exception {
	/**
	 * Crea una nueva excepción con un mensaje
	 * @param msg Mensaje
	 */
	public InvalidGradeException(String msg) {
		super(msg);
	}
}
