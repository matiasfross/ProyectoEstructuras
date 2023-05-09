package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;

import Lógica.Resolvedor;

public class MostrarTodos extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MostrarTodos(Resolvedor r) {
		JPanel panel = new JPanel();
		JList<String> lista = new JList<String>();
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		r.mostrarTodos(modelo);		
		lista.setModel(modelo);
		lista.setVisibleRowCount(5);
		JScrollPane scrollLista = new JScrollPane(lista);
		scrollLista.setBounds(20, 120, 220, 80);										
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));		
		panel.add(scrollLista);				
	}
}
