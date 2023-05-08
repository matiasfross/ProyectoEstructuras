package TDADiccionario;

/**
 * Entrada con clave y valor
 * @author Matias
 *
 * @param <K> Clave
 * @param <V> Valor
 */
public class Entrada<K , V> implements Entry<K, V> {

	//Atributos de instancia
	
	protected K key;
	protected V value;
	
	//Constructor
	
	/**
	 * Crea una nueva entrada con una clave y un valor 
	 * pasados por parámetro
	 * @param clave Clave
	 * @param valor Valor
	 */
	public Entrada(K clave , V valor) {
		key = clave;
		value = valor;
	}
	
	
	/**
	 * Determina una nueva clave para la entrada
	 * 
	 * @param clave Clave a asignar
	 */
	public void setKey(K clave) {
		key = clave;
	}
	
	
	/**
	 * Determina un nuevo valor para la entrada
	 * @param valor Valor a asignar
	 */
	public void setValue(V valor) {
		value = valor;
	}
	
	//Consultas
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}
}
