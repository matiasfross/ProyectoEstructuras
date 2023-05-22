package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import Excepciones.InvalidGradeException;
import Lógica.Resolvedor;
import TDALista.PositionList;
import TDAPar.Par;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Panel que tiene dos jaulas de texto para recibir el LU y su respectiva nota
 * y un boton que ingresa los datos escritos en las jaulas
 * @author valua
 *
 */
public class AgregarAlumno extends JPanel { 
	private JTextField LU , nota;

	/**
	 * Crea un nuevo panel para la funcionalidad "agregar alumno" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public AgregarAlumno(Resolvedor r) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel dataLabel = new JLabel("Ingrese los datos del alumno");
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(dataLabel, BorderLayout.NORTH);
		
		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int notaAnterior;
				try {
					notaAnterior = r.agregarAlumno(LU.getText() , nota.getText());
					if (notaAnterior == -1) {
						JOptionPane.showMessageDialog(null, "Nota agregada");
					} else {
						JOptionPane.showMessageDialog(null, "Nota actualizada , nota anterior : " + notaAnterior);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "La nota o el LU no es un número", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				} catch (InvalidGradeException e1) {
					JOptionPane.showMessageDialog(null, "La nota no es válida", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		add(saveButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel fileLabel = new JLabel("Número de libreta");
		panel.add(fileLabel);
		
		LU = new JTextField();
		panel.add(LU);
		LU.setColumns(10);
		
		JLabel gradeLabel = new JLabel("Nota");
		panel.add(gradeLabel);
		
		nota = new JTextField();
		panel.add(nota);
		nota.setColumns(10);

	}

}
