package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Excepciones.EmptyListException;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;

import Lógica.Resolvedor;
import TDAPar.Pair;
import TDAPar.Par;
/**
 * Panel que muestra una lista con todos los alumnos y su respectiva nota 
 * y una barra para scrollear en caso de que los alumnos no entren en la pantalla
 * @author valua
 *
 */
public class MostrarTodos extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Crea un nuevo panel para la funcionalidad "mostrar todos los alumnos en el registro" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public MostrarTodos(Resolvedor r) {		
		JList<String> lista = new JList<String>();
		DefaultListModel<String> todos = new DefaultListModel<String>();
		for (Pair<Integer , Integer> alumno : r.mostrarTodos()) {
			todos.addElement(alumno.getFirst() + ": " + alumno.getSecond());
		}
		if(todos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		} else {
			lista.setModel(todos);
			JScrollPane scrollLista = new JScrollPane(lista);
			scrollLista.setBounds(20, 120, 220, 80);				
			add(scrollLista, BorderLayout.CENTER);		
		}
				
	}
}
