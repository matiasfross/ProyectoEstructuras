package GUI.PanelesOperaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Lógica.Resolvedor;
/**
 * Panel que contiene una etiqueta con la nota minima en el regitro
 * Si no hay alumnos en el registro muestra un mensaje de error
 * @author valua
 *
 */
public class NotaMinima extends JPanel {
	public NotaMinima(Resolvedor r) {
		int min;
		
		min = r.consultarMinima();
		if(min != -1) {
			JLabel cartelMinima = new JLabel("La mínima nota en el registro es " + min);
			add(cartelMinima);
		} else {
			JOptionPane.showMessageDialog(null, "No se puede calcular la nota mínima ya que no hay alumnos registrados", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
}
