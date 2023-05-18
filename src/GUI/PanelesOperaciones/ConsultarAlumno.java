package GUI.PanelesOperaciones;

import javax.swing.JPanel;

import Lógica.Resolvedor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Panel que tiene una etiqueta, una jaula de texto y un boton para preguntarle al usuario que alumno quiere consultar
 * y muestra 3 mensajes distintos segun lo que se ingrese en la jaula de texto 
 * @author valua
 *
 */
public class ConsultarAlumno extends JPanel {
	private JTextField fileTextField;

	/**
	 * Create the panel.
	 */
	public ConsultarAlumno(Resolvedor r) {
		
		JLabel fileLabel = new JLabel("Ingrese el LU del alumno que desea consultar");
		add(fileLabel);
		
		fileTextField = new JTextField();
		add(fileTextField);
		fileTextField.setColumns(10);
		
		
		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int nota = r.consultarNota(fileTextField.getText());
					if (nota == -1) {
						JOptionPane.showMessageDialog(null, "Este LU no se encuentra en el registro", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "La nota de este alumno es : " + nota);
					}
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El LU ingresado no es un número", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(searchButton);
		
	}

}