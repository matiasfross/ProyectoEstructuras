package GUI.PanelesOperaciones;

import javax.swing.JPanel;

import Lógica.Resolvedor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarAlumno extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ConsultarAlumno(Resolvedor r) {
		
		JLabel lblNewLabel = new JLabel("Ingrese el LU del alumno que desea consultar");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int nota = r.consultarNota(textField.getText());
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
		add(btnNewButton);
		
	}

}