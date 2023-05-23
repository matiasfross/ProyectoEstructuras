package GUI;

import javax.swing.JPanel;

import GUI.PanelesOperaciones.AgregarAlumno;
import GUI.PanelesOperaciones.CalcularPromedio;
import GUI.PanelesOperaciones.ConsultarAlumno;
import GUI.PanelesOperaciones.EliminarAlumno;
import GUI.PanelesOperaciones.MostrarAprobados;
import GUI.PanelesOperaciones.MostrarDesaprobados;
import GUI.PanelesOperaciones.MostrarDescendente;
import GUI.PanelesOperaciones.MostrarDeterminadaNota;
import GUI.PanelesOperaciones.MostrarTodos;
import GUI.PanelesOperaciones.NotaMinima;
import Lógica.Resolvedor;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridLayout;
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
	private JLabel cartelMateria;
	private Resolvedor resolvedor;
	private JPanel panelOperacion , contenedorOperacion;
	/**
	 * Create the panel.
	 */
	public PanelFuncionalidades(Resolvedor r) {
		resolvedor = r;
		setLayout(new BorderLayout(0, 0));
		contenedorOperacion = new JPanel();
		add(contenedorOperacion, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		panel.setLayout(new GridLayout(3, 1));
		cartelMateria = new JLabel();
		panel.add(cartelMateria);
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
				switch(s) {
				case 0:
					panelOperacion = new AgregarAlumno(r);
					break;
				case 1:
					panelOperacion = new ConsultarAlumno(r);
					break;
				case 2:
					panelOperacion = new EliminarAlumno(r);
					break;
				case 3:
					panelOperacion = new MostrarTodos(r);
					break;
				case 4:
					panelOperacion = new CalcularPromedio(r);
					break;
				case 5:
					panelOperacion = new NotaMinima(r);
					break;
				case 6:
					panelOperacion = new MostrarDescendente(r);
					break;
				case 7:
					panelOperacion = new MostrarDeterminadaNota(r);
					break;
				case 8:
					panelOperacion = new MostrarAprobados(r);
					break;
				case 9:
					panelOperacion = new MostrarDesaprobados(r);
					break;
			}
				contenedorOperacion.add(panelOperacion);
				
				contenedorOperacion.repaint();
				contenedorOperacion.revalidate();
			}
			
		};
		comboBox.addActionListener(cbFuncionalidad);

		ComponentAdapter panelVisible = new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e )
	        {
	        	resolvedor.reiniciarRegistro();
	            actualizarMateria();
	        }
	    };
	    addComponentListener(panelVisible);
	}
	
	/**
	 * Actualiza el nombre de la materia
	 */
	private void actualizarMateria() {
		cartelMateria.setText(resolvedor.getMateria());
	}

}
