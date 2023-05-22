package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Excepciones.EmptyListException;
import Lógica.Resolvedor;
/**
 * Panel que muestra una lista con todos los alumnos aprobados
 * Muestra un mensaje de error si no hay alumnos aprobados en el registro
 * @author valua
 *
 */
public class MostrarAprobados extends JPanel {
	/**
	 * Crea un nuevo panel para la funcionalidad "mostrar aprobados" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public MostrarAprobados(Resolvedor r) {
		JList<String> lista = new JList<String>();
		try {
			lista.setModel(r.mostrarAprobados());
		}catch(EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos aprobados en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		JScrollPane scrollLista = new JScrollPane(lista);
		scrollLista.setBounds(20, 120, 220, 80);				
		add(scrollLista, BorderLayout.CENTER);
	}
}
