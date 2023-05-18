package Excepciones;

/**
 * Modela una situacion en la que una entrada ingresada no es valida
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