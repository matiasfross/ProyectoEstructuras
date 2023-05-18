package GUI;

import javax.swing.JPanel;

import Lógica.Resolvedor;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
/**
 * Panel cuya accion principal es contener la caja que emite cada una de las funcionalidades del programa
 * @author valua
 *
 */
public class PanelFuncionalidades extends JPanel {
	
	private final JLabel functionLabel = new JLabel("Seleccione la funcionalidad que desea usar");
	private Resolvedor resolvedor;
	private JPanel panelOperacion , contenedorOperacion;
	/**
	 * Create the panel.
	 */
	public PanelFuncionalidades() {
		resolvedor = new Resolvedor();
		setLayout(new BorderLayout(0, 0));
		contenedorOperacion = new JPanel();
		add(contenedorOperacion, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(functionLabel);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Agregar nuevo alumno", "Consultar alumno", "Eliminar alumno", "Mostrar todos los alumnos", "Promedio de notas del registro", "Nota mínima del registro", "Mostrar notas de mayor a menor", "Mostrar alumnos con determinada nota", "Mostrar aprobados", "Mostrar desaprobados"}));
		panel.add(comboBox);
		
		
		
		ActionListener cbFuncionalidad = new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (panelOperacion != null) {
					contenedorOperacion.remove(panelOperacion);
				}
				
				int s =  comboBox.getSelectedIndex();
				panelOperacion = resolvedor.crearPanelFunción(s);
				contenedorOperacion.add(panelOperacion);
				
				contenedorOperacion.repaint();
				contenedorOperacion.revalidate();
			}
			
		};
		comboBox.addActionListener(cbFuncionalidad);
		
		
		
		
		
		
	

	}

}
