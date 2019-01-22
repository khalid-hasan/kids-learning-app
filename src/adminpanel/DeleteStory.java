/**
 *  PACKAGES
 */
package adminpanel;
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
import javax.swing.JOptionPane;

public class DeleteStory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection conn;
	private int storyId;
	private String column[]= {"Story ID", "Story Title", "Added By"};
	private JTextField textField;
	private int id;
	private static String username;
	private String storyContent;

	public DeleteStory() {}
	/**
	 * 
	 * SETTER-GETTER
	 */
	public static String getValue()
	{
		return username;
	}
	public DeleteStory(String username)
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
			
			JButton btnDelete = new JButton("DELETE");
			btnDelete.setBorder(null);
			btnDelete.setFocusPainted(false);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					// JFrame UPON CLICKING READ BUTTON
					setTitle("DELETE");
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
					editorPane.setEditable(true);
					
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
							DeleteStory delete= new DeleteStory(getValue());
							delete.setVisible(true);
						}
					});
					returnButton.setBounds(588, 407, 63, 39);
					contentPane.add(returnButton);
					

					String idString= comboBox.getSelectedItem().toString(); // To get the selected item from JCombobox
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
							editorPane.setText(rs.getString(1)); // SETTING TEXT RETRIVED FROM DATABASE
						}
					}catch(Exception exp)
					{
						exp.printStackTrace();
					}
					JButton btnNewButton = new JButton("DELETE");
					btnNewButton.setBorder(null);
					btnNewButton.setFocusPainted(false);
					idString= comboBox.getSelectedItem().toString(); // To get the selected item from JCombobox
					id= Integer.parseInt(idString);
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							try
							{
								// Database Connection
							    Class.forName("com.mysql.jdbc.Driver");
							    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
							    
							    // QUERY
								PreparedStatement st2=conn.prepareStatement("DELETE from story where storyid= ?");
								st2.setInt(1,id);
								
								// EXECUTE QUERY and STORE in RESULT SET
								st2.executeUpdate();
								JOptionPane.showMessageDialog(null, "STORY DELETED");
							}catch(Exception exp)
							{
								exp.printStackTrace();
							}
						}
					});
					btnNewButton.setForeground(new Color(255, 250, 240));
					btnNewButton.setBackground(new Color(30, 139, 195));
					btnNewButton.setFont(new Font("PT Sans", Font.PLAIN, 14));
					btnNewButton.setBounds(450, 407, 120, 39);
					contentPane.add(btnNewButton);
				}
			});
			btnDelete.setBackground(new Color(220, 20, 60));
			btnDelete.setForeground(new Color(255, 250, 240));
			btnDelete.setFont(new Font("PT Sans", Font.PLAIN, 13));
			btnDelete.setBounds(562, 27, 89, 38);
			contentPane.add(btnDelete);
		    
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
		/**
		 *  BASIC GUI
		 */
		setTitle("DELETE STORY");
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
				    
				    // EXECUTE QUERY
				    ResultSet rs= st1.executeQuery();
				    
				    // Table Model For Populating JTable
				    DefaultTableModel model = new DefaultTableModel() {
						    public boolean isCellEditable(int row, int column) {
				        return false;
				    }};
				    model.setColumnIdentifiers(column);  // SETTING TABLE COLUMN

				    
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(78, 80, 558, 330);
					contentPane.add(scrollPane);
					
					/**
					 *  JTable
					 */
					table = new JTable();
					table.setModel(model);
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
		
		JLabel lblEnterStoryId = new JLabel("SELECT ID TO DELETE STORY");
		lblEnterStoryId.setFont(new Font("PT Sans", Font.PLAIN, 13));
		lblEnterStoryId.setForeground(new Color(255, 250, 240));
		lblEnterStoryId.setBounds(216, 27, 188, 38);
		contentPane.add(lblEnterStoryId);
		
		//RETURN ICON
		ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
		JButton returnButton = new JButton(returnIcon);
		returnButton.setBackground(new Color(44, 62, 80));
		returnButton.setBorder(null);
		returnButton.setFocusPainted(false);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// SWITCHING JFrame 
				setVisible(false);
				adminpanel.Admin admin= new adminpanel.Admin(getValue());
				admin.setVisible(true);
			}
		});
		returnButton.setBounds(561, 442, 63, 39);
		contentPane.add(returnButton);
	}

	
	public static void main(String[] args) {
		DeleteStory delete = new DeleteStory(getValue());
		delete.setVisible(true);
	}
}
