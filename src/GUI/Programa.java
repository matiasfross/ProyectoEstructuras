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
	private JTextField jaulaTxtMateria;
	private JLabel subjectLabel;
	private JButton enterButton , backButton;
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
	/**
	 * Inicializa el frame y llama a los metodos para construir los paneles y los botones
	 */
	private void initializeFrame() {
		frame = new JFrame("Registro de notas");
		frame.setBounds(450, 450, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		armarPanelInicio();
		armarBotonRetorno();
		armarPanelFuncionalidades();
	}
	/**
	 * Inicializa los contenidos del frame
	 */
	private void armarPanelInicio() {
		panelInicio = new JPanel();
		panelInicio.setBackground(new Color(0, 255, 255));
		
		
		subjectLabel = new JLabel("Ingrese el nombre de la materia y presione ingresar");
		subjectLabel.setBounds(100, 133, 304, 14);
		panelInicio.add(subjectLabel);
		
		jaulaTxtMateria = new JTextField();
		jaulaTxtMateria.setBounds(146, 158, 144, 23);
		panelInicio.add(jaulaTxtMateria);
		jaulaTxtMateria.setColumns(10);		
		enterButton = new JButton("Ingresar");
		enterButton.setBounds(171, 192, 104, 28);
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materia = jaulaTxtMateria.getText();
				mostrarPanelFuncionalidades();
			}
		});		
		panelInicio.add(enterButton);
		frame.getContentPane().add(panelInicio, BorderLayout.CENTER);
	}
	
	
	/**
	 * Inicializa el panel de funcionalidades que contiene el la caja de funciones de la aplicacion
	 */
	private void armarPanelFuncionalidades() {

		funcionalidades = new PanelFuncionalidades();
		funcionalidades.setVisible(false);
		frame.getContentPane().add(funcionalidades , BorderLayout.NORTH);
		
		
	}
	/**
	 * Arma el boton que vuelve al panel de inicio para registrar una materia nueva 
	 */
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
	/**
	 * Hace visible al panel de funcionalidades y al boton de registro de nueva materia luego de asignar sobre que materia se llevara un registro de notas
	 */
	private void mostrarPanelFuncionalidades() {
		frame.setTitle(materia);
		panelInicio.setVisible(false);
		funcionalidades.setVisible(true);
		backButton.setVisible(true);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}
	/**
	 * Hace visible el panel de inicio luego de presionar el  boton de registro de una nueva materia, esconde el boton presionado y borra el panel de funcionalidades viejo para crear uno nuevo
	 */
	private void volverAPanelInicio() {
		frame.setTitle("Registro de notas");
		panelInicio.setVisible(true);
		backButton.setVisible(false);
		frame.getContentPane().remove(funcionalidades);
		armarPanelFuncionalidades();

	}
}
