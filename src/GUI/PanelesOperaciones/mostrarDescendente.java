package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Excepciones.EmptyListException;
import Lógica.Resolvedor;
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
		try {
			lista.setModel(r.construirDescendente());
		}catch(EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		JScrollPane scrollLista = new JScrollPane(lista);
		scrollLista.setBounds(20, 120, 220, 80);				
		add(scrollLista, BorderLayout.CENTER);	
	}
}
