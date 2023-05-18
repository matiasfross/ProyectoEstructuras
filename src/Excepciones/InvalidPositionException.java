package Excepciones;

/**
 * Modela una situacion en la que una posición ingresada no es válida
 * 
 *
 */
public class InvalidPositionException extends Exception{
	/**
	 * Crea una nueva instancia de la excepción con un mensaje de error
	 * 
	 * @param msg Mensaje de error
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
