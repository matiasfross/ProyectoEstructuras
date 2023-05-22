package TDAPar;

/**
 * Pareja de elementos
 * @author Matias
 *
 * @param <A> Tipo de dato del primer elemento
 * @param <B> Tipo de dato del segundo elemento
 */
public interface Pair<A , B> {

	/**
	 * Devuelve el primer elemento del par
	 * @return Primer elemento
	 */
	public A getFirst();
	
	/**
	 * Devuelve el segundo elemento del par
	 * @return Segundo elemento
	 */
	public B getSecond();
}
