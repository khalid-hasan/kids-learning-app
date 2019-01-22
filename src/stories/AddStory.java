/**
 *  PACKAGES
 */
package stories;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStory extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblContent;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	private JButton btnSubmit;
	private Connection conn;
	private static String username;
	private JLabel lblAddStory;

	public AddStory() {}
	
	/**
	 * 
	 * SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public AddStory(String username)
	{
		this.username= username;
		components();
	}
	public void components() {
		/**
		 *  BASIC GUI
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 49, 63));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setForeground(new Color(255, 250, 240));
		lblTitle.setFont(new Font("PT Sans", Font.PLAIN, 14));
		lblTitle.setBounds(48, 58, 70, 28);
		contentPane.add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(48, 89, 507, 28);
		textField.setBorder(null);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblContent = new JLabel("Content:");
		lblContent.setForeground(new Color(255, 250, 240));
		lblContent.setFont(new Font("PT Sans", Font.PLAIN, 14));
		lblContent.setBounds(47, 123, 90, 14);
		lblContent.setBorder(null);
		contentPane.add(lblContent);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 148, 509, 213);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setForeground(new Color(255, 250, 240));
		btnSubmit.setBackground(new Color(30, 139, 195));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					// Database Connection
					Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
				    
				    // Storing TEXT INPUTS
				    String storytitle= textField.getText();
				    String storycontent= textPane.getText();
				    String addedby= getValue();
				    
				    // QUERY
				    PreparedStatement st1=conn.prepareStatement("INSERT INTO story (storytitle,storycontent,addedby) VALUES(?,?,?)");
					st1.setString(1, storytitle);
					st1.setString(2, storycontent);
					st1.setString(3, addedby);
					
					// EXECUTE QUERY
					st1.executeUpdate();  
					
					JOptionPane.showMessageDialog(null,"STORY ADDED.");
					
					conn.close();
					
				}catch(Exception exp)
				{
					System.out.println("Error: "+exp);
				}

			}
		});
		btnSubmit.setBounds(48, 373, 89, 28);
		btnSubmit.setBorder(null);
		btnSubmit.setFocusPainted(false);
		contentPane.add(btnSubmit);
		
		lblAddStory = new JLabel("ADD STORY");
		lblAddStory.setForeground(new Color(255, 250, 240));
		lblAddStory.setFont(new Font("Titillium Web", Font.BOLD, 18));
		lblAddStory.setBounds(293, 11, 233, 46);
		contentPane.add(lblAddStory);
		
		// RETURN ICON
		ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
		JButton returnButton = new JButton(returnIcon);
		returnButton.setBackground(new Color(34, 49, 63));
		returnButton.setBorder(null);
		returnButton.setFocusPainted(false);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame
				setVisible(false);
				homepage.home home= new homepage.home(getValue());
				home.setVisible(true);
			}
		});
		returnButton.setBounds(492, 372, 63, 39);
		contentPane.add(returnButton);
	}
	
	public static void main(String[] args) 
	{
		AddStory story = new AddStory(getValue());
		story.setVisible(true);
	}
}
