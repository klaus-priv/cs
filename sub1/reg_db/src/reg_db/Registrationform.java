package reg_db;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Registrationform {

	private JFrame frame;
	private JTextField n;
	private JTextField em;
	private JPasswordField ps;

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
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 453, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(128, 21, 152, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(78, 93, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EMAIL");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(78, 129, 55, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("GENDER");
		lblNewLabel_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(78, 206, 64, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		n = new JTextField();
		n.setBackground(Color.WHITE);
		n.setBounds(181, 92, 86, 20);
		frame.getContentPane().add(n);
		n.setColumns(10);
		
		em = new JTextField();
		em.setBounds(181, 128, 86, 20);
		frame.getContentPane().add(em);
		em.setColumns(10);
		
		JRadioButton r1 = new JRadioButton("MALE");
		r1.setBackground(Color.GRAY);
		r1.setBounds(170, 204, 80, 23);
		frame.getContentPane().add(r1);
		
		JRadioButton r2 = new JRadioButton("FEMALE");
		r2.setBackground(Color.GRAY);
		r2.setBounds(252, 204, 86, 23);
		frame.getContentPane().add(r2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		JLabel lblNewLabel_4 = new JLabel("PASSWORD");
		lblNewLabel_4.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(78, 167, 86, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		ps = new JPasswordField();
		ps.setBounds(181, 166, 86, 20);
		frame.getContentPane().add(ps);
		
		JLabel lblNewLabel_5 = new JLabel("PROGRAMMING LANGUAGE");
		lblNewLabel_5.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(78, 251, 207, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JComboBox c1 = new JComboBox();
		c1.setModel(new DefaultComboBoxModel(new String[] {"-----", "JAVA", "PYTHON", "SQL", "C", "GO", "R", "C#", "KOTLIN"}));
		c1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		c1.setBounds(320, 247, 89, 24);
		frame.getContentPane().add(c1);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String name=n.getText();
			String email=em.getText();
			@SuppressWarnings("deprecation")
			String password=ps.getText();
			String pl=(String) c1.getSelectedItem();
			String gender ;
			if(r1.isSelected())
			{
				gender="Male";
				
			}
			else if(r2.isSelected())
			{
				gender="Female";
			}
			else
			{
				gender="Invalid";
			}
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/3year","root","mrec");
				String q="Insert into student values('"+name+"','"+email+"','"+password+"','"+gender+"','"+pl+"')";
				Statement sta=con.createStatement();
				sta.executeUpdate(q);
				con.close();
				JOptionPane.showMessageDialog(btnNewButton , "Done!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		btnNewButton.setBounds(161, 290, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
