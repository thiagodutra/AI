package br.edu.uepb.eightpuzzle.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PuzzleGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleGUI window = new PuzzleGUI();
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
	public PuzzleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnResolver = new JButton("Resolver");
		panel.add(btnResolver);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JRadioButton rdbtnBFS = new JRadioButton("BFS");
		rdbtnBFS.setVerticalAlignment(SwingConstants.TOP);
		panel_1.add(rdbtnBFS);
		
		JRadioButton rdbtnDFS = new JRadioButton("DFS");
		rdbtnDFS.setVerticalAlignment(SwingConstants.CENTER);
		panel_1.add(rdbtnDFS);
		
		JRadioButton rdbtnIDDF = new JRadioButton("IDDF");
		rdbtnIDDF.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(rdbtnIDDF);
	}

}
