package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Lógica.Resolvedor;

public class Promedio extends JPanel{
	public Promedio(Resolvedor r){
		setLayout(new BorderLayout(0, 0));
		;
		/*try {
		float prom = r.obtenerPromedio();
		}catch(ArithmeticException e) {
			JOptionPane.showMessageDialog(null, "Debe haber al menos un alumno para calcular el promedio", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}*/
		JLabel lblNewLabel = new JLabel("El promedio de los alumnos es de: "+r.obtenerPromedio());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
	}
}
