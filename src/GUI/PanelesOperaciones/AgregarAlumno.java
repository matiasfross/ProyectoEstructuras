package GUI.PanelesOperaciones;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import Excepciones.InvalidGradeException;
import Lógica.Resolvedor;
import TDALista.PositionList;
import TDAPar.Par;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarAlumno extends JPanel { 
	private JTextField LU , nota;

	/**
	 * Create the panel.
	 */
	public AgregarAlumno(Resolvedor r) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos del alumno");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.agregarAlumno(LU.getText() , nota.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidGradeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Nota agregada");
			}
		});
		add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Número de libreta");
		panel.add(lblNewLabel_1);
		
		LU = new JTextField();
		panel.add(LU);
		LU.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nota");
		panel.add(lblNewLabel_2);
		
		nota = new JTextField();
		panel.add(nota);
		nota.setColumns(10);

	}

}
