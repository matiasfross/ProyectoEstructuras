package GUI.PanelesOperaciones;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Excepciones.EmptyListException;
import Lógica.Resolvedor;

public class MostrarDesaprobados extends JPanel {
	
	public MostrarDesaprobados(Resolvedor r) {
		JList<String> lista = new JList<String>();
		try {
			lista.setModel(r.mostrarDesaprobados());
		}catch(EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No se encuentran alumnos desaprobados en el registro", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		JScrollPane scrollLista = new JScrollPane(lista);
		scrollLista.setBounds(20, 120, 220, 80);				
		add(scrollLista, BorderLayout.CENTER);
	}
}
