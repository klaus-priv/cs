package reg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.Statement;

public class Registrationform {

	private JFrame frame;
	private JTextField s;
	private JTextField n;
	private JTextField m;
	private JTextField em;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrationform window = new Registrationform();
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
	public Registrationform() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel.setBounds(145, 22, 205, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(81, 138, 62, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		s = new JTextField();
		s.setBounds(217, 92, 86, 20);
		frame.getContentPane().add(s);
		s.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("S.no");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(81, 93, 62, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		n = new JTextField();
		n.setBounds(217, 137, 86, 20);
		frame.getContentPane().add(n);
		n.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Marks");
		lblNewLabel_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(81, 223, 62, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		m = new JTextField();
		m.setBounds(217, 222, 86, 20);
		frame.getContentPane().add(m);
		m.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sno = s.getText();
				int sn=Integer.parseInt(sno);
				String name = n.getText();
				String email = em.getText();
				String marks = m.getText();
				float ms=Float.parseFloat(marks);
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/regdb","root","mrec");
					String q="Insert into student values('"+sn+"','"+name+"','"+email+"','"+ms+"')";
					Statement sta=con.createStatement();
					sta.executeUpdate(q);
					con.close();
					
					JOptionPane.showMessageDialog(btnNewButton, "Done!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(161, 253, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(81, 187, 62, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		em = new JTextField();
		em.setBounds(217, 186, 86, 20);
		frame.getContentPane().add(em);
		em.setColumns(10);
	}
}
