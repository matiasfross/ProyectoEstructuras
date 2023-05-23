package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Lógica.Resolvedor;
import TDAPar.Pair;
/**
 * Panel que muestra una lista con todos los alumnos desaprobados
 * Muestra un mensaje de error si no hay alumnos desaprobados en el registro
 * @author valua
 *
 */
public class MostrarDesaprobados extends JPanel {
	
	/**
	 * Crea un nuevo panel para la funcionalidad "mostrar desaprobados" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public MostrarDesaprobados(Resolvedor r) {
		JList<String> lista = new JList<String>();
		DefaultListModel<String> desaprobados = new DefaultListModel<String>();
		for (Pair<Integer , Integer> alumno : r.mostrarDesaprobados()) {
			desaprobados.addElement(alumno.getFirst() + ": " + alumno.getSecond());
		}
			
		if (desaprobados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos desaprobados en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		} else {
			lista.setModel(desaprobados);
			JScrollPane scrollLista = new JScrollPane(lista);
			scrollLista.setBounds(20, 120, 220, 80);				
			add(scrollLista, BorderLayout.CENTER);

		}
	}
}
