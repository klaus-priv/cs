package reg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;



public class checkmarks {

	private JFrame frame;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkmarks window = new checkmarks();
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
	public checkmarks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter S.no");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel.setBounds(51, 42, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		t1 = new JTextField();
		t1.setBounds(182, 37, 86, 28);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lb1 = new JLabel("Name");
		lb1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lb1.setBounds(91, 116, 64, 14);
		frame.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Marks");
		lb2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lb2.setBounds(91, 166, 70, 14);
		frame.getContentPane().add(lb2);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sno = t1.getText();
				int sn= Integer.parseInt(sno);
				
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/regdb","root","mrec");
					String q= "select name , marks from student where sno =? ";
					PreparedStatement ps=con.prepareStatement(q);
					ps.setInt(1, sn);
					ResultSet rs=ps.executeQuery();
					rs.next();
					
					lb1.setText("Name:"+  rs.getString(1));
					lb2.setText("Marks:"+   rs.getInt(2));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(156, 201, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
