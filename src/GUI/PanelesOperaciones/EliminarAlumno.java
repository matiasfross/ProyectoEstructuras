package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Lógica.Resolvedor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Panel que contiene una etiqueta, una jaula de texto y un boton que le preguntan al usuario que alumno quiere eliminar
 * y muestra 3 mensajes distintos segun lo que se ingrese en la jaula de texto
 * @author valua
 *
 */
public class EliminarAlumno extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EliminarAlumno(Resolvedor r) {
		
		JLabel lblNewLabel = new JLabel("Ingrese el LU del alumno que desea eliminar del registro");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String LU = textField.getText();
					boolean eliminado = r.eliminarNota(textField.getText());
					if (eliminado) {
						JOptionPane.showMessageDialog(null, "El alumno de LU " + LU + " ha sido eliminado del registro");
					} else {
						JOptionPane.showMessageDialog(null, "El LU ingresado no corresponde a ningún alumno en el registro", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El LU dado no es un número", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnNewButton);

	}

}
