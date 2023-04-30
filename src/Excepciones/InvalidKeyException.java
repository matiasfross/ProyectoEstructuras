package Excepciones;

/**
 * Lanzada cuando una clave es inválida
 * 
 *
 */
public class InvalidKeyException extends Exception {
	
	/**
	 * Crea una nueva excepción con un mensaje
	 * @param msg Mensaje
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
