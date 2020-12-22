package main.java.api;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;

public class searchWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				searchWindow window = new searchWindow();
				window.frame.setVisible(true);
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public searchWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 643, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(166, 39, 279, 48);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("S\u00F6k Schema");
		lblNewLabel.setBounds(60, 39, 96, 48);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(247, 153, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner.setBounds(176, 99, 113, 20);
		frame.getContentPane().add(spinner);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "2020", "2019", "2018", "2017", "2016", "2015" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(299, 98, 138, 22);
		frame.getContentPane().add(comboBox);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 218, 593, 241);
		frame.getContentPane().add(textArea);

		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setBounds(186, 125, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Year");
		lblNewLabel_2.setBounds(328, 128, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
