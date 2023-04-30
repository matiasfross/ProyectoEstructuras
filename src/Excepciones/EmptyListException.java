package Excepciones;
/**
 * Lanzada cuando una lista está vacía
 * 
 *
 */
public class EmptyListException extends Exception {

	/**
	 * Crea una nueva excepción con un mensaje
	 * @param string Mensaje a mostrar
	 */
	public EmptyListException(String string) {
		super(string);
	}
	
}
