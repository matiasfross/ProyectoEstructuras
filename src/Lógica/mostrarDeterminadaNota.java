package Lógica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Excepciones.InvalidGradeException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class mostrarDeterminadaNota extends JPanel {
	private JTextField textField;
	
	public mostrarDeterminadaNota(Resolvedor r) {
		JLabel msj = new JLabel("Inserte la nota que desea consultar");
		add(msj);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(15);
		
		JButton botonMostrar = new JButton("Mostrar");		
		JList<String> lista = new JList<String>();
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lista.setModel(r.buscarDeterminada(textField.getText()));
				}catch(NumberFormatException | InvalidGradeException e1) {
					//TODO AGREGAR MENSAJE DE ERROR
				}
				JScrollPane scrollLista = new JScrollPane(lista);
				scrollLista.setBounds(20, 120, 220, 80);				
				add(scrollLista, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}
		});	
		add(botonMostrar);
		botonMostrar.setVisible(true);
		
	}
}
