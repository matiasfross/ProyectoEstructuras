package TDAPar;

/**
 * Pareja de elementos
 * @author Matias
 *
 * @param <A>Tipo de dato del primer elemento
 * @param <B>Tipo de dato del segundo elemento
 */
public class Par<A , B> {
	
	//Atributos de instancia
	
	protected A elem1;
	protected B elem2;
	
	//Constructores
	
	/**
	 * Crea una nueva pareja
	 */
	public Par() {
		elem1 = null;
		elem2 = null;
	}
	
	
	/**
	 * Crea una nueva pareja con el primer elemento
	 * @param elem1 Primer elemento
	 */
	public Par(A elem1) {
		this.elem1 = elem1;
		elem2 = null;
	}
	
	/**
	 * Crea una nueva pareja con ambos elementos
	 * @param elem1 Primer elemento
	 * @param elem2 Segundo elemento
	 */
	public Par(A elem1 , B elem2) {
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	//Comandos
	
	/**
	 * Determina un nuevo primer elemento
	 * @param elem1 Primer elemento
	 */
	public void setFirst(A elem1) {
		this.elem1 = elem1;
	}
	
	/**
	 * Determina un nuevo segundo elemento
	 * @param elem2 Segundo elemento
	 */
	public void setSecond(B elem2) {
		this.elem2 = elem2;
	}
	
	//Consultas
	
	/**
	 * Devuelve el primer elemento
	 * @return Primer elemento
	 */
	public A getFirst() {
		return elem1;
	}
	
	/**
	 * Devuelve el segundo elemento
	 * @return Segundo elemento
	 */
	public B getSecond() {
		return elem2;
	}
	
	@Override
	public String toString() {
		return "("  + elem1 + " , " + elem2 + ")" ;
	}
}
