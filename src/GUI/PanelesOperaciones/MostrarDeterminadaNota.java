package GUI.PanelesOperaciones;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Excepciones.EmptyListException;
import Excepciones.InvalidGradeException;
import Lógica.Resolvedor;
import TDAPar.Pair;
import TDAPar.Par;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
/**
 * Panel con un boton, una etiqueta y una jaula de texto que pregunta la nota que se desea consultar
 * Luego muestra una lista grafica que contiene a los alumnos que tienen la nota pasada por la jaula de texto
 * Segun lo que se escriba en la jaula de texto puede enviar tres mensajes de error(nota invalida o nota no registrada)
 * @author valua
 *
 */
public class MostrarDeterminadaNota extends JPanel {
	private JTextField gradeTextField;
	private JScrollPane scrollLista;
	
	/**
	 * Crea un nuevo panel para la funcionalidad "mostrar alumno con una nota determinada" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public MostrarDeterminadaNota(Resolvedor r) {
		
		JLabel gradeLabel = new JLabel("Inserte la nota que desea consultar");
		scrollLista = null;
		add(gradeLabel);
		
		gradeTextField = new JTextField();
		add(gradeTextField);
		gradeTextField.setColumns(15);
		
		JButton botonMostrar = new JButton("Mostrar");		
		JList<String> lista = new JList<String>();
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DefaultListModel<String> alumnosEncontrados = new DefaultListModel<String>();
					if (scrollLista != null) {
						remove(scrollLista);
					}
					for (Pair<Integer , Integer> alumno : r.buscarDeterminada(gradeTextField.getText())) {
						alumnosEncontrados.addElement(alumno.getFirst()+ ": " + alumno.getSecond());
					}
					
					if (alumnosEncontrados.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay alumnos en el registro con la nota solicitada", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
					} else {
						lista.setModel(alumnosEncontrados);
						scrollLista = new JScrollPane(lista);
						scrollLista.setBounds(20, 120, 220, 80);				
						add(scrollLista, BorderLayout.SOUTH);
					}
					
				}catch(NumberFormatException e1) {					
					JOptionPane.showMessageDialog(null, "Ingrese una nota valida", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}catch(InvalidGradeException  e2) {
					JOptionPane.showMessageDialog(null, "Ingrese una nota valida", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
				
				repaint();
				revalidate();
			}
		});	
		add(botonMostrar);
		botonMostrar.setVisible(true);
		
	}
}
