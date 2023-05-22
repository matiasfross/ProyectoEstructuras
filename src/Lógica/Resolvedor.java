package Lógica;

import TDALista.PositionList;

import TDALista.ListaDE;
import TDAPar.Pair;
import TDAPar.Par;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import Auxiliares.Entry;
import Auxiliares.Position;
import Auxiliares.Entrada;
import Excepciones.EmptyListException;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidGradeException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import GUI.PanelesOperaciones.AgregarAlumno;
import GUI.PanelesOperaciones.ConsultarAlumno;
import GUI.PanelesOperaciones.EliminarAlumno;
import GUI.PanelesOperaciones.MostrarAprobados;
import GUI.PanelesOperaciones.MostrarDesaprobados;
import GUI.PanelesOperaciones.MostrarTodos;
import GUI.PanelesOperaciones.NotaMinima;
import GUI.PanelesOperaciones.CalcularPromedio;
import GUI.PanelesOperaciones.MostrarDescendente;
import GUI.PanelesOperaciones.MostrarDeterminadaNota;
import TDADiccionario.DiccionarioDA;
import TDADiccionario.Dictionary;
import TDAColaCP.PriorityQueue;
import TDAColaCP.CCPConHeap;
import TDAColaCP.DefaultComparator;
import TDAColaCP.DecreasingComparator;
/**
 * Clase que recibe que funcionalidad se desea llevar a cabo y que resulve las operaciones logicas de las mismas
 * tiene un atributo que lleva el registro de los alumnos
 * @author valua
 *
 */
public class Resolvedor {

	
	//Atributos de instancia
	
	private PositionList<Par<Integer , Integer>> registroLista;
	private static final int NOTA_APROBACION = 6;
	//Constructor
	
	/**
	 * Crea un nuevo resolvedor que se encarga de la lógica de la aplicación
	 */
	public Resolvedor() {
		registroLista = new ListaDE<Par<Integer , Integer>>();
	}
	/**
	 * Crea un panel de acuerdo a la funcion que se quiera utilizar
	 * @param n referencia a la opcion elegida en la caja de opciones
	 * @return retorna un panel que realiza la funcion solicitada
	 */
	public JPanel crearPanelFunción(int n) {
		JPanel res = null;
		switch(n) {
			case 0:
				res = new AgregarAlumno(this);
				break;
			case 1:
				res = new ConsultarAlumno(this);
				break;
			case 2:
				res = new EliminarAlumno(this);
				break;
			case 3:
				res = new MostrarTodos(this);
				break;
			case 4:
				res = new CalcularPromedio(this);
				break;
			case 5:
				res = new NotaMinima(this);
				break;
			case 6:
				res = new MostrarDescendente(this);
				break;
			case 7:
				res = new MostrarDeterminadaNota(this);
				break;
			case 8:
				res = new MostrarAprobados(this);
				break;
			case 9:
				res = new MostrarDesaprobados(this);
				break;
		}
		return res;
	}
	/**
	 * Agrega un alumno al registro
	 * @param LU numero de legajo que identifica al alumno
	 * @param nota nota obtenida por el alumno
	 * @return retorna -1 si no estaba en el registro, o la nota anterior si ya estaba registrado anteriormente
	 * @throws InvalidGradeException  si la nota nota pasada por paramentro no es valida
	 * @throws NumberFormatException si la nota pasada por parametro no es un numero
	 */
	public int agregarAlumno(String LU, String nota) throws InvalidGradeException , NumberFormatException{
		int lu = toNum(LU);
		int n = toNum(nota);
		if (!esNota(n)) {
			throw new InvalidGradeException("La nota pasada por párametro no es válida");
		}
		
		Iterator<Par<Integer , Integer>> it = registroLista.iterator();
		int res = -1;
		while (it.hasNext() && res == -1) {
			Par<Integer , Integer> p = it.next();
			if (p.getFirst() ==  lu) {
				res = p.getSecond();
				p.setSecond(n);
			}
		}		
		if (res == -1) {
			registroLista.addLast(new Par<Integer , Integer>(lu , n));
		}		
		return res;
	}
	/**
	 * Metodo que recibe un LU y lo busca en el registro para retornar su respectiva nota
	 * @param LU numero de legajo del alumno a a consultar
	 * @return nota asociada al alumno con el LU pasado por parametro
	 * @throws NumberFormatException si el LU pasado por parametro no representa un numero
	 */
	public int consultarNota(String LU)throws NumberFormatException {
		int lu = toNum(LU);
		Iterator<Par<Integer , Integer>> it = registroLista.iterator();
		int res = -1;
		while (it.hasNext() && res == -1) {
			Par<Integer , Integer> p = it.next();
			if (p.getFirst() == lu) {
				res = p.getSecond();
			}
		}
		
		return res;
	}
	/**
	 * Recibe un LU por parametro, lo busca en el registro y elimina al alumno registrado con ese LU
	 * @param LU numero de legajo del alumno a eliminar
	 * @return retorna verdadero si encontro al alumno asociado a LU en el registro, falso en caso contrario
	 * @throws NumberFormatException si el LU pasado por parametro no representa a un numero
	 */
	public boolean eliminarNota(String LU)throws NumberFormatException{
		int lu = toNum(LU);
		Iterator<Position<Par<Integer , Integer>>> it = registroLista.positions().iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			Position<Par<Integer , Integer>> p = it.next();
			if (p.element().getFirst() == lu) {
				try {
					registroLista.remove(p);
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
				encontre = true;
			}
		}
		
		return encontre;
	}
	

	/**
	 * Devuelve un iterable con todos los pares LU - Nota del registro
	 * El primer elemento del par representa el LU y el segundo la nota del alumno
	 * @return Iterable con todos los alumnos
	 */
	public Iterable<Pair<Integer , Integer>> mostrarTodos(){
		PositionList<Pair<Integer , Integer>> res = new ListaDE<Pair<Integer , Integer>>();
		for (Pair<Integer, Integer> alumno : registroLista) {
			res.addLast(alumno);
		}
		return res;
	}
	
	/**
	 * Calcula el promedio de las notas de los alumnos en el registro
	 * @return Promedio entre todas las notas registradas
	 * @throws ArithmeticException lanzada cuando no hay alumnos
	 */
	public float promedio() throws ArithmeticException {
		int totalNotas = 0;
		int totalAlumnos = 0;
		for (Par<Integer , Integer> p : registroLista) {
			totalNotas += p.getSecond();
			totalAlumnos++;
		}
		if (totalAlumnos == 0) {
			throw new ArithmeticException("División entre 0 , no hay alumnos registrados");
		}
		return (float)totalNotas / totalAlumnos;
	}
	
	/**
	 * Devuelve un iterable con los datos de los alumnos aprobados
	 * El primer elemento del par representa el LU y el segundo la nota del alumno
	 * @return Iterable con datos de alumnos aprobados
	 */
	public Iterable<Pair<Integer , Integer>> mostrarAprobados(){
		PositionList<Pair<Integer , Integer>> res = new ListaDE<Pair<Integer , Integer>>();
		for (Par<Integer , Integer> alumno : registroLista) {
			if (alumno.getSecond() >= NOTA_APROBACION) {
				res.addLast(alumno);
			}
		}
		return res;
	}
	
	/**
	 * Devuelve un iterable con los datos de los alumnos desaprobados
	 * El primer elemento del par representa el LU y el segundo la nota del alumno
	 * @return Iterable con datos de alumnos desaprobados
	 */
	public Iterable<Pair<Integer , Integer>> mostrarDesaprobados(){		
		PositionList<Pair<Integer , Integer>> res = new ListaDE<Pair<Integer , Integer>>();
		for (Par<Integer , Integer> alumno : registroLista) {
			if (alumno.getSecond() < NOTA_APROBACION) {
				res.addLast(alumno);
			}
		}
		return res;
	}
	
	
	/**
	 * Retorna la mínima nota en el registro buscándola con una cola con prioridad.
	 * Si no hay alumnos en el registro , retorna -1.
	 * @return Nota mínima del registro
	 */
	public int consultarMinima() {
		int res = -1;
		PriorityQueue<Integer , Integer> ccpNotas = new CCPConHeap<Integer , Integer>(new DefaultComparator<Integer>()); 
		for (Par<Integer , Integer> alum : registroLista) {
			try {
				ccpNotas.insert(alum.getSecond(), alum.getFirst());
			} catch (InvalidKeyException e) {			
				e.printStackTrace();
			}
		}
		if (!ccpNotas.isEmpty()) {
			try {
				res = ccpNotas.min().getKey();
			} catch (EmptyPriorityQueueException e) {
				e.printStackTrace();
			}
		}
		return res;
		
	}
	
	/**
	 * Devuelve un iterable con los datos de los alumnos ordenados según la nota de mayor a menor
	 * El primer elemento del par representa el LU y el segundo la nota del alumno
	 * @return Iterable con los alumnos ordenados
	 */
	public Iterable<Pair<Integer , Integer>> construirDescendente() {
		
		PriorityQueue<Integer , Pair<Integer , Integer>> ccpNotas = new CCPConHeap<Integer , Pair<Integer , Integer>>(new DecreasingComparator<Integer>());
		PositionList<Pair<Integer , Integer>> res = new ListaDE<Pair<Integer , Integer>>();
	
		for(Pair<Integer , Integer> alumno : registroLista) {
			try {
				ccpNotas.insert(alumno.getSecond() , alumno);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		try {
			while(!ccpNotas.isEmpty()) {
				res.addLast(ccpNotas.removeMin().getValue());
			}
		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Controla si una cadena representa un número y devuelve dicho número
	 * @param strNum Cadena a controlar
	 * @return Valor entero de la cadena
	 * @throws NumberFormatException si la cadena no representa un número
	 */
	private int toNum(String strNum) throws NumberFormatException {
	    if (strNum == null) {
	        throw new NumberFormatException("La cadena es nula");
	    }
	    int res = Integer.parseInt(strNum);
	    return res;
	}
	
	/**
	 * Controla si el valor n es válido como nota
	 * @param n Nota a controlar
	 * @return Verdadero si es n es válido como nota
	 */
	private boolean esNota(int n) {
		return n >= 0 && n <= 10;
	}
	
	/**
	 * Devuelve un iterable con los datos de los alumnos con la nota buscada
	 * El primer elemento del par representa el LU y el segundo la nota del alumno
	 * @param text Nota dada
	 * @return Iterable con los alumnos buscado
	 * @throws InvalidGradeException si la nota pasada por parámetro no es válida
	 * @throws NumberFormatException si la cadena no representa un número
	 */
	public Iterable<Pair<Integer , Integer>> buscarDeterminada(String text) throws InvalidGradeException , NumberFormatException{
		int n = toNum(text);
		if(!esNota(n))throw new InvalidGradeException("La nota pasada por párametro no es válida");
		Dictionary<Integer , Pair<Integer , Integer>> registroNotas = new DiccionarioDA<Integer , Pair<Integer , Integer>>();
		PositionList<Pair<Integer , Integer>> res = new ListaDE<Pair<Integer , Integer>>();
		for(Pair<Integer , Integer> alumno : registroLista) {
			try {
				registroNotas.insert(alumno.getSecond() , alumno);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		try {
			for (Entry< Integer , Pair<Integer , Integer>> alumno : registroNotas.findAll(n)) {
				res.addLast(alumno.getValue());
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
