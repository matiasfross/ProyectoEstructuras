package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Excepciones.EmptyListException;
import Lógica.Resolvedor;
import TDAPar.Pair;
/**
 * Panel qcon una lista grafica que muestra a los alumnos de forma descendente segun sus notas
 * Muestra un mensaje de error si no hay alumnos en el registro
 * @author valua
 *
 */
public class MostrarDescendente extends JPanel {

	/**
	 * Crea un nuevo panel para la funcionalidad "mostrar alumnos según su nota de mayor a menor" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public MostrarDescendente(Resolvedor r) {
		JList<String> lista = new JList<String>();
		DefaultListModel<String> alumnosOrdenados = new DefaultListModel<String>();
		for (Pair<Integer , Integer> alumno : r.construirDescendente()) {
			alumnosOrdenados.addElement(alumno.getFirst()+ ": " + alumno.getSecond());
		}
		if (alumnosOrdenados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		} else {
			lista.setModel(alumnosOrdenados);
			JScrollPane scrollLista = new JScrollPane(lista);
			scrollLista.setBounds(20, 120, 220, 80);				
			add(scrollLista, BorderLayout.CENTER);
		}
			
	}
}
