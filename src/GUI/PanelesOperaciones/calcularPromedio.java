package GUI.PanelesOperaciones;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import Lógica.Resolvedor;
/**
 * Panel con una etiqueta que muestra el promedio calculado con la nota de todos los alumnos registrados
 * @author valua
 *
 */
public class CalcularPromedio extends JPanel {
	/**
	 * Crea un nuevo panel para la funcionalidad "calcular nota promedio" 
	 * @param r Resolvedor encargado de la lógica del programa
	 */
	public CalcularPromedio(Resolvedor r) {
		float prom;
		try {
			prom = r.promedio();
			JLabel cartelPromedio = new JLabel("El promedio de notas es " + String.format("%.2f",prom));
			add(cartelPromedio);
		} catch(ArithmeticException e) {
			JOptionPane.showMessageDialog(null, "No se puede calcular el promedio ya que no hay alumnos registrados", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
}
