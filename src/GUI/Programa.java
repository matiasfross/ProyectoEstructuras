package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import TDALista.ListaDE;
import TDALista.PositionList;
import TDAPar.Par;
import javax.swing.JScrollPane;  
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Programa {
	
	
	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton , backButton;
	private JPanel panelInicio , funcionalidades;
	private String materia;
	private PositionList<Par<String , String>> registro;
	
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
		registro = new ListaDE<Par<String , String>>();
		initializeFrame();
	}

	private void initializeFrame() {
		frame = new JFrame("Registro de notas");
		frame.setBounds(450, 450, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		armarPanelInicio();
		armarBotonRetorno();
		armarPanelFuncionalidades();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void armarPanelInicio() {
		panelInicio = new JPanel();
		panelInicio.setBackground(new Color(0, 255, 255));
		
		
		lblNewLabel = new JLabel("Ingrese el nombre de la materia y presione ingresar");
		lblNewLabel.setBounds(100, 133, 304, 14);
		panelInicio.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(146, 158, 144, 23);
		panelInicio.add(textField);
		textField.setColumns(10);		
		btnNewButton = new JButton("Ingresar");
		btnNewButton.setBounds(171, 192, 104, 28);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materia = textField.getText();
				mostrarPanelFuncionalidades();
			}
		});		
		panelInicio.add(btnNewButton);
		frame.getContentPane().add(panelInicio, BorderLayout.CENTER);
	}
	
	
	
	private void armarPanelFuncionalidades() {

		funcionalidades = new PanelFuncionalidades();
		funcionalidades.setVisible(false);
		frame.getContentPane().add(funcionalidades , BorderLayout.NORTH);
		
		
	}
	
	private void armarBotonRetorno() {
		backButton = new JButton("Crear registro de una nueva materia");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAPanelInicio();
			}
		});
		backButton.setVisible(false);
		frame.getContentPane().add(backButton, BorderLayout.SOUTH);
		
	}
	
	private void mostrarPanelFuncionalidades() {
		frame.setTitle(materia);
		panelInicio.setVisible(false);
		funcionalidades.setVisible(true);
		backButton.setVisible(true);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}
	
	private void volverAPanelInicio() {
		frame.setTitle("Registro de notas");
		panelInicio.setVisible(true);
		backButton.setVisible(false);
		frame.getContentPane().remove(funcionalidades);
		armarPanelFuncionalidades();

	}
}
