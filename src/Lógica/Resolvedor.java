package Lógica;

import TDADiccionario.Dictionary;
import TDALista.PositionList;
import TDALista.ListaDE;
import TDAPar.Par;

import java.util.Iterator;

import Excepciones.InvalidGradeException;
import TDADiccionario.DiccionarioDA;
public class Resolvedor {

	
	//Atributos de instancia
	
	private PositionList<Par<Integer , Integer>> registroLista;
	
	//Constructor
	
	public Resolvedor() {
		registroLista = new ListaDE<Par<Integer , Integer>>();
		//registroDiccionario = new DiccionarioDA<Integer , Integer>();
	}
	
	public boolean agregarAlumno(String LU, String nota) throws InvalidGradeException , NumberFormatException{
		int lu = toNum(LU);
		int n = toNum(nota);
		if (!esNota(n)) {
			throw new InvalidGradeException("La nota pasada por párametro no es válida");
		}
		
		Iterator<Par<Integer , Integer>> it = registroLista.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			Par<Integer , Integer> p = it.next();
			if (p.getFirst() ==  lu) {
				encontre = true;
			}
		}
		
		if (!encontre) {
			registroLista.addLast(new Par<Integer , Integer>(lu , n));
		}
		
		return !encontre;
	}

	public Iterable<Par<Integer, Integer>> mostrarTodos(){
		return registroLista;
	}
	
	private int toNum(String strNum) throws NumberFormatException {
	    if (strNum == null) {
	        throw new NumberFormatException("La cadena es nula");
	    }
	    int res = Integer.parseInt(strNum);
	    return res;
	}
	
	private boolean esNota(int n) {
		return n >= 0 && n <= 10;
	}
	
	
}
