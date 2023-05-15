package GUI.PanelesOperaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Lógica.Resolvedor;

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
