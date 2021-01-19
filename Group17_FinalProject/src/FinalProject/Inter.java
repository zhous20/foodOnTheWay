package FinalProject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("unused")
/**A method related to interface
 * 
 * @author Croup17
 * @see inter
 * @see FinalProject
 * @see inter
 * @see FinalProject.inter
 * @see #main
 */
public class Inter {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application. It is really a long function.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inter window = new Inter();
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
	public Inter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_292754004142095");
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(256, 259, 245, 26);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JPanel panel_1 = new JPanel();
				frame.getContentPane().add(panel_1, "name_294961489147285");
				panel_1.setLayout(null);
				try {
					CSVSort.gen();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "wrong file");
					e1.printStackTrace();
				} finally {

					String in = textField.getText().toLowerCase();

					if (in.isEmpty()) {
						try {
							throw new IOException("input is empty");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Sorry we don't accept empty input. :<");
							e1.printStackTrace();

						}

					} else {
						try {
							setOutput.gettOutput(in);
						} catch (NullPointerException e1) {
							JOptionPane.showMessageDialog(null,
									"Sorry, your input is not correct, please make sure you entered the correct name or we don't have this food in our market yet.:<");
							e1.printStackTrace();

						} finally {
							setOutput.gettOutput(in);

							List<String> out = setOutput.getOutput();

							Object[][] outTable = new Object[out.size()][1];

							for (int i = 0; i < out.size(); i++) {
								outTable[i][0] = out.get(i);
							}

							Object[] columnNames = { "Year, Food, Price" };
							table = new JTable(outTable, columnNames);
							table.setBounds(52, 50, 84, 89);

							JScrollPane Pane = new JScrollPane(table);
							Pane.setLocation(50, 50);
							Pane.setSize(300, 300);

							panel_1.add(Pane);
							panel.setVisible(false);
							panel_1.setVisible(true);
						}
					}
				}
			}

		});
		btnSearch.setBounds(316, 389, 117, 29);
		panel.add(btnSearch);

		JLabel lblNewLabel = new JLabel("Please enter the food name you want to search :");
		lblNewLabel.setBounds(250, 227, 397, 16);
		panel.add(lblNewLabel);

	}
}
