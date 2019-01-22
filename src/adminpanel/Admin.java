/**
 *  PACKAGES
 */
package adminpanel;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class Admin extends JFrame {

	private JPanel contentPane;
	private Connection conn;
	private static String username;
	private JTextField textField;
	
	public Admin() {}
	
	/**
	 * 
	 *  SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public Admin(String username)
	{
		this.username= username;
		components();
	}
	public void components() {
		/**
		 *  Basic GUI
		 */
		setTitle("ADMIN PANEL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 110, 123));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStory = new JButton("ADD STORY");
		btnAddStory.setBackground(new Color(107, 185, 240));
		btnAddStory.setForeground(new Color(255, 250, 240));
		btnAddStory.setBorder(null);
		btnAddStory.setFocusPainted(false);
		btnAddStory.setFont(new Font("Raleway", Font.BOLD, 16));
		btnAddStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					// SWITCHING JFrame
					adminpanel.AddStory add= new adminpanel.AddStory(getValue());
					setVisible(false);
					add.setVisible(true);
					
				}catch(Exception exp)
				{
					System.out.println("Error: "+exp);
				}
				
			}
		});
		btnAddStory.setBounds(215, 119, 162, 49);
		contentPane.add(btnAddStory);
		
		JButton btnEditStory = new JButton("EDIT STORY");
		btnEditStory.setBorder(null);
		btnEditStory.setFocusPainted(false);
		btnEditStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame
				adminpanel.EditStory edit= new adminpanel.EditStory(getValue());
				setVisible(false);
				edit.setVisible(true);
			}
		});
		btnEditStory.setForeground(new Color(255, 250, 240));
		btnEditStory.setFont(new Font("Raleway", Font.BOLD, 16));
		btnEditStory.setBackground(new Color(107, 185, 240));
		btnEditStory.setBounds(215, 197, 162, 49);
		contentPane.add(btnEditStory);
		
		JButton btnDeleteStory = new JButton("DELETE STORY");
		btnDeleteStory.setBorder(null);
		btnDeleteStory.setFocusPainted(false);
		btnDeleteStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame
				adminpanel.DeleteStory delete= new adminpanel.DeleteStory(getValue());
				setVisible(false);
				delete.setVisible(true);
			}
		});
		btnDeleteStory.setForeground(new Color(255, 250, 240));
		btnDeleteStory.setFont(new Font("Raleway", Font.BOLD, 16));
		btnDeleteStory.setBackground(new Color(107, 185, 240));
		btnDeleteStory.setBounds(215, 278, 162, 49);
		contentPane.add(btnDeleteStory);
		
		JButton btnShowStory = new JButton("SHOW STORY");
		btnShowStory.setBorder(null);
		btnShowStory.setFocusPainted(false);
		btnShowStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame
				setVisible(false);
				adminpanel.ShowStory story= new adminpanel.ShowStory(getValue());
				story.setVisible(true); 
			}
		});
		btnShowStory.setForeground(new Color(255, 250, 240));
		btnShowStory.setFont(new Font("Raleway", Font.BOLD, 16));
		btnShowStory.setBackground(new Color(107, 185, 240));
		btnShowStory.setBounds(215, 35, 162, 49);
		contentPane.add(btnShowStory);

	}
	
	public static void main(String[] args) {
		Admin admin = new Admin(getValue());
		admin.setVisible(true);
	}

}
