package TDADiccionario;

import java.util.Iterator;

import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.PositionList;
import TDALista.ListaDE;
import TDALista.Position;

/**
 * Diccionario con tabla hash de dispersión abierta
 * @author Matias
 *
 * @param <K> Tipo de dato de la clave
 * @param <V> Tipo de dato del valor
 */
public class DiccionarioDA<K, V> implements Dictionary<K , V> {

	//Atributos de instancia
	
	protected int size;
	protected PositionList<Entry<K , V>>[] tabla;
	protected final static float FACTOR = 0.5f;
	
	//Constructores
	
	@SuppressWarnings("unchecked")
	/**
	 * Crea un nuevo diccionario con una tabla de un tamaño dado
	 * @param tableSize Tamaño de la tabla
	 */
	public DiccionarioDA(int tableSize) {
		if (tableSize <= 0) {
			throw new IllegalArgumentException("La tabla de tener al menos una posición");
		}
		
		size = 0;
		tabla = (PositionList<Entry<K , V>>[]) new PositionList[tableSize];
		for (int i = 0; i < tabla.length ; i++) {
			tabla[i] = new ListaDE<Entry <K , V>>();
		}
	}
	
	/**
	 * Crea un nuevo diccionario
	 */
	public DiccionarioDA() {
		this(3);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		int index = checkAndHashKey(key);
		Entry<K , V> res = null;
		Iterator<Entry<K , V>> it = tabla[index].iterator();
		while (it.hasNext() && res == null) {
			Entry<K , V> ent = it.next();
			if (ent.getKey().equals(key)) {
				res = ent;
			}
		}
		return res;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int index = checkAndHashKey(key);
		PositionList<Entry<K , V>> res = new ListaDE<Entry<K , V>>();
		Iterator<Entry<K , V>> it = tabla[index].iterator();
		while (it.hasNext()) {
			Entry<K , V> ent = it.next();
			if (ent.getKey().equals(key)) {
				res.addLast(ent);
			}
		}
		return res;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (value == null) {
			throw new IllegalArgumentException("El valor pasado por parámetro es nulo");
		}
		
		int index = checkAndHashKey(key);
		
		Entry<K , V> nueva = new Entrada<K , V>(key , value);
		tabla[index].addLast(nueva);
		size++;
		
		controlarReHash();
		
		return nueva;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		int index = checkAndHashEntry(e);
		Iterator<Position<Entry<K , V>>> it = tabla[index].positions().iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			Position<Entry<K , V>> ent = it.next();
			if (ent.element() == e) {
				try {
					tabla[index].remove(ent);
				} catch (InvalidPositionException e1) {
					e1.printStackTrace();
				}
				size--;
				encontre = true;
			}
		}
		
		if (!encontre) {
			throw new InvalidEntryException("La entrada pasada por parámetro no se encuentra en el diccionario");
		}
	
		return e;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K , V>> res = new ListaDE<Entry< K , V>>();
		for (int i = 0; i < tabla.length ; i++) {
			for (Entry<K , V> ent : tabla[i]) {
				res.addLast(ent);
			}
		}
		return res;
	}

	//Método auxiliar
	/**
	 * Controla si la clave es válida y devuelve su hash
	 * @param key Clave
	 * @return Hash de la clave
	 * @throws InvalidKeyException Lanzada si la clave es inválida
	 */
	private int checkAndHashKey(K key)throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("La clave pasada por parámetro es nula");
		}
		return Math.abs(key.hashCode() )% tabla.length;
		
	}
	
	/**
	 * Controla si la entrada es válida y devuelve el hash de su clave
	 * @param e Entrada
	 * @return Hash de la clave de la entrada
	 * @throws InvalidEntryException Lanzada si la entrada es inválida
	 */
	private int checkAndHashEntry(Entry<K , V> e) throws InvalidEntryException {
		if (e == null) {
			throw new InvalidEntryException("La entrada pasada por parámetro es nula");
		}
		if (e.getValue() == null) {
			throw new InvalidEntryException("El valor de la entrada es nulo");
		}
		int res;
		try {
			res = checkAndHashKey(e.getKey());
		} catch (InvalidKeyException e1) {
			throw new InvalidEntryException("La clave de la entrada pasada por parámetro es nula");
		}
		return res;
		
	}
	
	//Método auxiliar
	/**
	 * Controla si se debe rehashear . En caso de que se necesite llama a reHash()
	 */
	private void controlarReHash() {
		if(size / tabla.length >= FACTOR) {
			reHash();
		}
	}

	//Método auxiliar
	//O(n)
	/**
	 * Aumenta el tamaño de la tabla
	 */
	@SuppressWarnings("unchecked")
	private void reHash() {
		Iterable<Entry<K , V>> entradas = entries();
		size = 0;
		tabla = (PositionList<Entry<K , V>> [])new PositionList[nextPrimo(tabla.length * 2)];
		for(int i=0;i < tabla.length;i++)
			tabla[i] = new ListaDE<Entry<K,V>>();
		for(Entry<K,V> e: entradas)
			try {
				this.insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
	}
		
	//Método auxiliar
	/**
	 * Da el siguiente primo de n
	 * @param n Número entero
	 * @return Siguiente primo
	 */
	private int nextPrimo(int n) {
		boolean found = false;
		while (!found) {
			n++;
			boolean isPrime = true;
	        for (int i = 2; i <= Math.sqrt(n) && isPrime; i++) {
	            if (n % i == 0) {
	                isPrime = false;
	            }
	        }
	        if (isPrime) {
	        	found = true;
	        }
	    }
		return n;
	}
}
