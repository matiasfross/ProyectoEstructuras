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

import javax.swing.JLabel;
import javax.swing.JButton;
/**
 * Panel con un boton, una etiqueta y una jaula de texto que pregunta la nota que se desea consultar
 * Luego muestra una lista grafica que contiene a los alumnos que tienen la nota pasada por la jaula de texto
 * Segun lo que se escriba en la jaula de texto puede enviar tres mensajes de error(nota invalida o nota no registrada)
 * @author valua
 *
 */
public class mostrarDeterminadaNota extends JPanel {
	private JTextField textField;
	private JScrollPane scrollLista;
	
	public mostrarDeterminadaNota(Resolvedor r) {
		
		JLabel msj = new JLabel("Inserte la nota que desea consultar");
		scrollLista = null;
		add(msj);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(15);
		
		JButton botonMostrar = new JButton("Mostrar");		
		JList<String> lista = new JList<String>();
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (scrollLista != null) {
						remove(scrollLista);
					}
					lista.setModel(r.buscarDeterminada(textField.getText()));
					scrollLista = new JScrollPane(lista);
					scrollLista.setBounds(20, 120, 220, 80);				
					add(scrollLista, BorderLayout.SOUTH);
				}catch(NumberFormatException e1) {
					//TODO AGREGAR MENSAJE DE ERROR
					JOptionPane.showMessageDialog(null, "Ingrese una nota valida", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}catch(InvalidGradeException  e2) {
					JOptionPane.showMessageDialog(null, "Ingrese una nota valida", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}catch(EmptyListException e3) {
					JOptionPane.showMessageDialog(null, "No hay alumnos en el registro con la nota solicitada", "Dialog",
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
