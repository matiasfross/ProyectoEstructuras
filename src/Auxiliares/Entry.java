package Auxiliares;

/**
 * Entrada con una clave y un valor
 * @author Matias
 *
 * @param <K> Tipo de dato de la clave
 * @param <V> Tipo de dato del valor
 */
public interface Entry <K , V>{
	
	/**
	 * Devuelve la clave de la entrada
	 * 
	 * @return Clave de la entrada
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada
	 * 
	 * @return Valor en la entrada
	 */
	public V getValue();
}
