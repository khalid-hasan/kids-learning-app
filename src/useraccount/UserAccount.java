/**
 *  PACKAGES
 */
package useraccount;
import alphabets.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.sql.*;

public class UserAccount extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField nameSU;
	private JTextField userNameSU;
	private JTextField userNameLogin;
	private JPasswordField passwordSU;
	private JPasswordField passwordLogin;
	private String username;
    private Connection conn;
	private String checkUser;
	
	public UserAccount()
	{
		components();
	}

	/**
	 * 
	 * SETTER-GETTER
	 */
	public void setUsername(String username)
	{
		this.username= username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * 
	 * LOGIN VALIDATION
	 * 
	 */
	private boolean validateLogin(String username,String password)
	{
		   try{ 
			   // Database Connection
		       Class.forName("com.mysql.jdbc.Driver");
		       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");    
		       
		       // QUERY
		       PreparedStatement pst = conn.prepareStatement("select * from loginaccount where username= ? and password= ?");
		       pst.setString(1, username); 
		       pst.setString(2, password);
		       
		       // Executing QUERY and STORING in RESULT SET
		       ResultSet rs = pst.executeQuery();   
		       
		       // CONDITION CHECKING
		       if(rs.next())            
		           return true;    
		       else
		           return false;            
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }       
	}
	
	/**
	 * 
	 * CHECK FOR DUPLICATE USERNAME
	 * 
	 */
	private boolean checkDuplicateEntry(String username)
	{
		try
		{   // Database Connection
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
		    
		    // QUERY
			PreparedStatement st=conn.prepareStatement("select username from loginaccount where username= ?");
			st.setString(1, username);
			
			// Executing QUERY and STORING in RESULT SET
			ResultSet rs= st.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else 
			{
				return false;
			}
			
		}catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }   
	}
	public void components() {
		/**
		 *  BASIC GUI
		 */
		setTitle("Kids Learning Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User? Sign Up");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Titillium Web", Font.BOLD, 17));
		lblNewLabel.setBounds(68, 37, 153, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 250, 240));
		lblName.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 13));
		lblName.setBounds(27, 105, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 250, 240));
		lblUsername.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 13));
		lblUsername.setBounds(27, 140, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 250, 240));
		lblPassword.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 13));
		lblPassword.setBounds(27, 175, 65, 14);
		contentPane.add(lblPassword);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(255, 250, 240));
		btnSignUp.setBackground(new Color(44, 62, 80));
		btnSignUp.setBorder(null);
		btnSignUp.setFont(new Font("PT Sans", Font.PLAIN, 14));
		btnSignUp.setBounds(157, 210, 98, 31);
		contentPane.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					// Database Connection
				    Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
				     
				    String name;
				    String username= "";
				    char[] password= new char[] {}; 
				    
				    // Storing TEXT INPUTS 
				    name= nameSU.getText();
					username = userNameSU.getText().trim();
					password = passwordSU.getPassword();
					String passwordString= new String(password);
					
					// CONDITION CHECKING
					if (name.equals("") || username.equals("") || passwordString.equals(""))
					{
						JOptionPane.showMessageDialog(null,"PLEASE ENTER PROPER USERNAME AND PASSWORD.");
					}
					else if(checkDuplicateEntry(username))
					{
							JOptionPane.showMessageDialog(null,"Username Already Exists");
					}

					else 
					{
						// QUERY
						PreparedStatement st1=conn.prepareStatement("INSERT INTO loginaccount (name,username,password) VALUES(?,?,?)");
						st1.setString(1, name);
						st1.setString(2, username);
						st1.setString(3, passwordString);
						
						// Executing QUERY
						st1.executeUpdate();  
						
						JOptionPane.showMessageDialog(null,"Account Created Successfully.");
						
						conn.close();
					}
				}catch (Exception ex) 
				{
				     ex.printStackTrace();
				}
			}
		});
		
		// BASIC GUI
		nameSU = new JTextField();
		nameSU.setFont(new Font("Open Sans", Font.PLAIN, 11));
		nameSU.setBounds(102, 98, 153, 31);
		nameSU.setBorder(null);
		contentPane.add(nameSU);
		nameSU.setColumns(10);
		
		userNameSU = new JTextField();
		userNameSU.setFont(new Font("Open Sans", Font.PLAIN, 11));
		userNameSU.setBounds(102, 133, 153, 31);
		userNameSU.setBorder(null);
		contentPane.add(userNameSU);
		userNameSU.setColumns(10);
		
		userNameLogin = new JTextField();
		userNameLogin.setFont(new Font("Open Sans", Font.PLAIN, 11));
		userNameLogin.setColumns(10);
		userNameLogin.setBounds(379, 133, 153, 31);
		userNameLogin.setBorder(null);
		contentPane.add(userNameLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 250, 240));
		btnLogin.setBackground(new Color(44, 62, 80));
		btnLogin.setBorder(null);
		btnLogin.setFont(new Font("PT Sans", Font.PLAIN, 14));
		btnLogin.setBounds(434, 220, 98, 31);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					// Database Connection
				    Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
				    
				    // Storing TEXT INPUTS 
				    username= userNameLogin.getText();
				    char[] password = passwordLogin.getPassword();
					String passwordString= new String(password);
					
					// CONDITION CHECKING
					if(username.length()==0 || passwordString.length()==0)
					{
						JOptionPane.showMessageDialog(null, "Fields are Empty.");
					}
					else
					{
						if(validateLogin(username,passwordString))
						{
							if(username.equals("admin"))
							{
								setUsername(username);
								JOptionPane.showMessageDialog(null, "Login Successful. Welcome "+getUsername());
								
								// SWITCHING JFrame
								adminpanel.Admin admin= new adminpanel.Admin(getUsername());
								setVisible(false);
								admin.setVisible(true);
								
							}
							else
							{
								setUsername(username);
								JOptionPane.showMessageDialog(null, "Login Successful. Welcome "+getUsername());
								
								// SWITCHIN JFrame
								homepage.home home1= new homepage.home(getUsername());
								setVisible(false);
								home1.setVisible(true);
							}

						}
						else
						{
							JOptionPane.showMessageDialog(null, "Login Unuccessful");
						}
					}
						
					conn.close();
				}catch (Exception ex) 
				{
				     ex.printStackTrace();
				}
			}
		});
		
		JLabel label = new JLabel("Username");
		label.setForeground(new Color(255, 250, 240));
		label.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(304, 140, 65, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(new Color(255, 250, 240));
		label_1.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 13));
		label_1.setBounds(304, 175, 65, 14);
		contentPane.add(label_1);
		
		JLabel lblLoginToYour = new JLabel("Login To Your Account");
		lblLoginToYour.setForeground(Color.WHITE);
		lblLoginToYour.setFont(new Font("Titillium Web", Font.BOLD, 17));
		lblLoginToYour.setBounds(349, 74, 177, 50);
		contentPane.add(lblLoginToYour);
		
		passwordSU = new JPasswordField();
		passwordSU.setFont(new Font("Open Sans", Font.PLAIN, 11));
		passwordSU.setBounds(102, 168, 153, 31);
		passwordSU.setBorder(null);
		contentPane.add(passwordSU);
		
		passwordLogin = new JPasswordField();
		passwordLogin.setFont(new Font("Open Sans", Font.PLAIN, 11));
		passwordLogin.setBounds(379, 173, 153, 31);
		passwordLogin.setBorder(null);
		contentPane.add(passwordLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37, 116, 169));
		panel.setBounds(0, 0, 287, 303);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(103, 65, 114));
		panel_1.setBounds(282, 0, 277, 303);
		contentPane.add(panel_1);
	}
	
	public static void main(String[] args) 
	{
		UserAccount ua = new UserAccount();
		ua.setVisible(true);
	}
}
