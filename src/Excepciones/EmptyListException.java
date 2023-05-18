package Excepciones;
/**
 * Modela una situacion en la que se intenta accionar sobre una lista sin ningun elemento
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
