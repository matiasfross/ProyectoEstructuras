package Lógica;

import TDALista.PositionList;
import TDALista.ListaDE;
import TDALista.Position;
import TDAPar.Par;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import Excepciones.InvalidGradeException;
import Excepciones.InvalidPositionException;
import GUI.PanelesOperaciones.AgregarAlumno;
import GUI.PanelesOperaciones.ConsultarAlumno;
import GUI.PanelesOperaciones.EliminarAlumno;
import GUI.PanelesOperaciones.MostrarTodos;
import GUI.PanelesOperaciones.calcularPromedio;
import TDADiccionario.DiccionarioDA;
/**
 * Clase que recibe que funcionalidad se desea llevar a cabo y que resulve las operaciones logicas de las mismas
 * tiene un atributo que lleva el registro de los alumnos
 * @author valua
 *
 */
public class Resolvedor {

	
	//Atributos de instancia
	
	private PositionList<Par<Integer , Integer>> registroLista;
	
	//Constructor
	
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
				res = new calcularPromedio(this);
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
	 * Mediante un iterador agrega todos los alumnos del registro a un modelo de lista grafica
	 * @return modelo de lista grafica a mostrar en pantalla
	 */
	public DefaultListModel<String> mostrarTodos() {
		DefaultListModel<String> res = new DefaultListModel<String>();
		Iterator<Par<Integer , Integer>> it = registroLista.iterator();
		while(it.hasNext()) {
			Par<Integer , Integer> p = it.next();
			res.addElement(p.getFirst()+": "+p.getSecond());

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

	//TODO Borrar en un futuro
	public Iterable<Par<Integer, Integer>> obtenerTodos(){
		return registroLista;
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
	
	
}
