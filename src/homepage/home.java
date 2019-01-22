/**
 *  PACKAGES
 */
package homepage;
import useraccount.UserAccount;

import adminpanel.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class home extends JFrame {

	private JPanel contentPane;
	private static String username;

	public home() {}
	
	/**
	 * 
	 *  SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public home(String username)
	{
		this.username=username;
		components();
		
		
	}
	public void components() {
		/**
		 *  BASIC GUI
		 */
		setTitle("Kids Learning Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**
		 *  HOMEPAGE IMAGE
		 */
		ImageIcon newIcon = new ImageIcon(".\\.\\images\\home.png");
		JLabel lblNewLabel = new JLabel("", newIcon, JLabel.CENTER);
		lblNewLabel.setBounds(50, 34, 220, 232);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PLAY WITH ALPHABETS");
		btnNewButton.setBorder(null);
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING Jframe
				setVisible(false);
				alphabets.AlphabetLayout alpha= new alphabets.AlphabetLayout(getValue());	
			}
		});
		btnNewButton.setFont(new Font("Segoe Marker", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(255, 250, 240));
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setBounds(323, 34, 198, 47);
		contentPane.add(btnNewButton);
		
		JButton btnReadStories = new JButton("READ STORIES");
		btnReadStories.setBorder(null);
		btnReadStories.setFocusPainted(false);
		btnReadStories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING Jframe
				stories.Story story= new stories.Story(getValue());
				setVisible(false);
				story.setVisible(true);
				
			}
		});
		btnReadStories.setForeground(new Color(255, 250, 240));
		btnReadStories.setFont(new Font("Segoe Marker", Font.BOLD, 16));
		btnReadStories.setBackground(new Color(30, 144, 255));
		btnReadStories.setBounds(323, 150, 198, 47);
		contentPane.add(btnReadStories);
		
		JButton btnPlayWithNumbers = new JButton("PLAY WITH NUMBERS");
		btnPlayWithNumbers.setBorder(null);
		btnPlayWithNumbers.setFocusPainted(false);
		btnPlayWithNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING Jframe
				setVisible(false);
				numbers.NumberLayout number= new numbers.NumberLayout(getValue());
			}
		});
		btnPlayWithNumbers.setForeground(new Color(255, 250, 240));
		btnPlayWithNumbers.setFont(new Font("Segoe Marker", Font.BOLD, 16));
		btnPlayWithNumbers.setBackground(new Color(255, 140, 0));
		btnPlayWithNumbers.setBounds(323, 92, 198, 47);
		contentPane.add(btnPlayWithNumbers);
		
		JButton btnSubmitStories = new JButton("SUBMIT STORIES");
		btnSubmitStories.setBorder(null);
		btnSubmitStories.setFocusPainted(false);
		btnSubmitStories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING Jframe
				stories.AddStory story= new stories.AddStory(getValue());
				setVisible(false);
				story.setVisible(true);
			}
		});
		btnSubmitStories.setForeground(new Color(255, 250, 240));
		btnSubmitStories.setFont(new Font("Segoe Marker", Font.BOLD, 16));
		btnSubmitStories.setBackground(new Color(239, 72, 54));
		btnSubmitStories.setBounds(323, 208, 198, 47);
		contentPane.add(btnSubmitStories);

	}

	public static void main(String[] args) 
	{
		home frame = new home(getValue());
		frame.setVisible(true);
	}
}
