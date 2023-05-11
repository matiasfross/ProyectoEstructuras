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
public class Resolvedor {

	
	//Atributos de instancia
	
	private PositionList<Par<Integer , Integer>> registroLista;
	
	//Constructor
	
	public Resolvedor() {
		registroLista = new ListaDE<Par<Integer , Integer>>();
		
	}
	
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
	 * @return Promedio
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

	public float obtenerPromedio() /*throws ArithmeticException*/{
		int prom=0;
		int contador=1;
		Iterator<Par<Integer , Integer>> it = registroLista.iterator();
		while(it.hasNext()) {
			Par<Integer , Integer> p = it.next();
			prom+=p.getSecond();
			contador++;
		}
		/*if(contador==0) throw new ArithmeticException("Division por 0 (no hay alumnos agregados)");*/
		return prom/contador;
	}
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
