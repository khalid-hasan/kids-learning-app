/**
 *  PACKAGES
 */
package stories;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.Color;
import javax.swing.JLabel;

public class Story extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection conn;
	private int storyId;
	private String column[]= {"Story ID", "Story Title", "Added By"};
	private JTextField textField;
	private static String username;

	public Story(){}
	
	/**
	 * 
	 *  SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public Story(String username)
	{
		this.username= username;
		components();
	}
	
	/**
	 *  JCombobox
	 */
	public void getId()
	{
		try
		{
			// Database Connection
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
		    
		    // QUERY
		    PreparedStatement st1=conn.prepareStatement("SELECT storyid from story");
		    
		    // Execute QUERY
		    ResultSet rs= st1.executeQuery();
		    
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(386, 28, 166, 38);
			contentPane.add(comboBox);
			
			JButton btnRead = new JButton("READ");
			btnRead.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					// JFrame UPON CLICKING READ BUTTON
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
					
					// Return Button
					ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
					JButton returnButton = new JButton(returnIcon);
					returnButton.setBackground(new Color(44, 62, 80));
					returnButton.setBorder(null);
					returnButton.setFocusPainted(false);
					returnButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							// SWTICHING JFrame
							setVisible(false);
							stories.Story story= new stories.Story(getValue());
							story.setVisible(true);
						}
					});
					returnButton.setBounds(588, 407, 63, 39);
					contentPane.add(returnButton);
					String storyContent; // To get the story content from Database.
					String idString= comboBox.getSelectedItem().toString(); // To get the selected item from JCombobox
					int id;
					id= Integer.parseInt(idString);
					

					try
					{
						// Database Connection
					    Class.forName("com.mysql.jdbc.Driver");
					    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
					    
					    // QUERY
						PreparedStatement st2=conn.prepareStatement("SELECT storycontent from story where storyid= ?");
						st2.setInt(1,id);
						
						// EXECUTE QUERY and STORE in RESULT SET
						ResultSet rs= st2.executeQuery();
						if(rs.next())
						{
							editorPane.setText(rs.getString(1)); // SETTING TEXT RETRIEVED FROM DATABASE
						}
					}catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			});
			btnRead.setBackground(new Color(220, 20, 60));
			btnRead.setForeground(new Color(255, 250, 240));
			btnRead.setFont(new Font("PT Sans", Font.PLAIN, 13));
			btnRead.setBounds(562, 27, 89, 38);
			btnRead.setBorder(null);
			btnRead.setFocusPainted(false);
			contentPane.add(btnRead);
		    
		    while(rs.next())
		    {
		    	storyId= rs.getInt(1);
		    	comboBox.addItem(storyId); // Fetching Story ID from database and Adding them to JCombobox.
		    }
		    
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	public void components() {
		/*
		 *  Basic GUI
		 */
		setTitle("Stories");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 62, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getId();
		
		JButton btnLoadStories = new JButton("LOAD STORIES");
		btnLoadStories.setForeground(new Color(255, 250, 240));
		btnLoadStories.setBackground(new Color(220, 20, 60));
		btnLoadStories.setBorder(null);
		btnLoadStories.setFocusPainted(false);
		btnLoadStories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					// Database Connection
				    Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
				    int id;
				    String storyTitle;
				    String addedBy;
				    
				    // QUERY
				    PreparedStatement st1=conn.prepareStatement("SELECT storyid, storytitle, addedby from story");
				    
				    // Table Model For Populating JTable
				    DefaultTableModel model = new DefaultTableModel() {
						    public boolean isCellEditable(int row, int column) {
				        return false;
				    }};
				    model.setColumnIdentifiers(column); // SET TABLE COLUMN 
				    
				    // EXECUTE QUERY and STORE in RESLUT SET
				    ResultSet rs= st1.executeQuery();

				    
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(78, 80, 558, 330);
					contentPane.add(scrollPane);
					
					/**
					 *  JTable
					 */
					table = new JTable();
					table.setModel(model); // SET DATA MODEL
					scrollPane.setViewportView(table);
				    
				    while(rs.next())
				    {
				    	id= rs.getInt(1);
				    	storyTitle= rs.getString(2);
				    	addedBy= rs.getString(3);
				    	
				    	model.addRow(new Object[] {Integer.toString(id), storyTitle, addedBy }); // ADDING ROWS TO MODEL
				    }
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		btnLoadStories.setFont(new Font("PT Sans", Font.PLAIN, 16));
		btnLoadStories.setBounds(59, 26, 153, 38);
		contentPane.add(btnLoadStories);
		
		JLabel lblEnterStoryId = new JLabel("SELECT ID TO READ STORY");
		lblEnterStoryId.setFont(new Font("PT Sans", Font.PLAIN, 13));
		lblEnterStoryId.setForeground(new Color(255, 250, 240));
		lblEnterStoryId.setBounds(222, 27, 166, 38);
		contentPane.add(lblEnterStoryId);
		
		// RETURN ICON
		ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
		JButton returnButton = new JButton(returnIcon);
		returnButton.setBackground(new Color(44, 62, 80));
		returnButton.setBorder(null);
		returnButton.setFocusPainted(false);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWTICHING JFrame
				setVisible(false);
				homepage.home home= new homepage.home(getValue());
				home.setVisible(true);
			}
		});
		returnButton.setBounds(561, 442, 63, 39);
		contentPane.add(returnButton);
	}

	public static void main(String[] args) 
	{
		Story story = new Story(getValue());
		story.setVisible(true);
	}
}
