package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Programa {
	
	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JPanel panel_1;
	private String materia;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Programa window = new Programa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Programa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 450, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Ingrese el nombre de la materia y presione ingresar");
		//lblNewLabel.
		lblNewLabel.setBounds(100, 133, 304, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(146, 158, 144, 23);
		panel.add(textField);
		textField.setColumns(10);		
		btnNewButton = new JButton("Ingresar");
		btnNewButton.setBounds(171, 192, 104, 28);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materia = textField.getText();
				armarPanel();
			}
		});		
		panel.add(btnNewButton);
		
		
	}
	public void armarPanel() {
		frame.getContentPane().removeAll();		
		frame.setTitle(materia);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
		
	}
	
}
