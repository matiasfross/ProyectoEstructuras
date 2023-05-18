package Excepciones;

/**
 * Modela una situacion en la que una nota ingresada no es valida
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
