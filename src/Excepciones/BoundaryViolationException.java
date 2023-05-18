package Excepciones;
/** 
 * Modela una situacion en la que se traspasan los límites de una estructura  
 *
 */
public class BoundaryViolationException extends Exception{

	/**
	 * Crea una nueva excepción con un mensaje de error
	 * @param string Mensaje de error
	 */
	public BoundaryViolationException(String string) {
		super(string);
	}
	
}
