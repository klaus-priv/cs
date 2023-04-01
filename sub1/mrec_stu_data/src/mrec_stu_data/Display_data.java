package mrec_stu_data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Display_data {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_data window = new Display_data();
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
	public Display_data() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 529);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ROLL NO.");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 68, 85, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 114, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MARKS");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(25, 164, 85, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		t1 = new JTextField();
		t1.setBounds(150, 67, 86, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(150, 113, 86, 20);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField();
		t3.setBounds(150, 163, 86, 20);
		frame.getContentPane().add(t3);
		t3.setColumns(10);
		
		JButton btnNewButton = new JButton("submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rn=t1.getText();
				int rno=Integer.parseInt(rn);
				String name=t2.getText();
				String m=t3.getText();
				float marks=Float.parseFloat(m);
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrec_db","root","mrec");
					String q="Insert into cs values('"+rno+"','"+name+"','"+m+"')";
					Statement sta= con.createStatement();
		            sta.executeUpdate(q);
					con.close();
					JOptionPane.showMessageDialog(btnNewButton, "Done");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(88, 222, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(322, 50, 347, 237);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("LOAD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mrec_db","root","mrec");
					String q="select * from cs";
					Statement sta=con.createStatement();
					ResultSet rs=sta.executeQuery(q);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colname=new String[cols];
					for(int i=0;i<cols;i++)
					{
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						String s,n,m;
						while(rs.next())
						{
							s=rs.getString(1);
							n=rs.getString(2);
							m=rs.getString(3);
							String[] row= {s,n,m};
							model.addRow(row);
							
						}
						sta.close();
						con.close();
						
						
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnNewButton_1.setBounds(335, 24, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_2.setBounds(441, 24, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
