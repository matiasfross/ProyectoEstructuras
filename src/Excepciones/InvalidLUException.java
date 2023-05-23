package Excepciones;

/**
 * Modela la situación en la que el LU ingresado no es válido
 * @author Matias
 *
 */
public class InvalidLUException extends Exception {
	/**
	 * Crea una nueva excepción con un mensaje
	 * @param msg Mensaje
	 */
	public InvalidLUException(String msg) {
		super(msg);
	}
}
