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
	
	
	public MostrarTodos(Resolvedor r) {
		JList<String> lista = new JList<String>();
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		r.mostrarTodos(modelo);
		
		lista.setModel(modelo);
		JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(20, 120, 220, 80);		
		scrollLista.setViewportView(lista);		
		add(scrollLista);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		panel.add(scrollLista);
	}
}
