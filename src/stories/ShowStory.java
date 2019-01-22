/**
 *  PACKAGES
 */
package stories;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowStory extends JFrame {

	private JPanel contentPane;
	private static String username;

	public ShowStory(){}
	
	/**
	 * 
	 * SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public ShowStory(String username)
	{
		this.username= username;
		components();
	}
	public void components() {
		/**
		 *  Basic GUI
		 */
		setTitle("STORY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 62, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 641, 385);
		contentPane.add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		editorPane.setEditable(false);
		
		// RETURN BUTTON
		ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
		JButton returnButton = new JButton(returnIcon);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame
				setVisible(false);
				stories.Story story= new stories.Story(getValue());
				story.setVisible(true);
			}
		});
		returnButton.setBounds(588, 407, 63, 39);
		contentPane.add(returnButton);
	}

	public static void main(String[] args) 
	{
		ShowStory story = new ShowStory(getValue());
		story.setVisible(true);

	}
}
