package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;

import Lógica.Resolvedor;

public class MostrarTodos extends JPanel {
	
	
	public MostrarTodos(Resolvedor r) {
		JList<String> lista = new JList<String>();
		lista.setModel(r.mostrarTodos());

		JScrollPane scrollLista = new JScrollPane(lista);
		scrollLista.setBounds(20, 120, 220, 80);				
		add(scrollLista, BorderLayout.CENTER);
		

	}
}
