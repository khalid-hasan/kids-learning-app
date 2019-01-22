/**
 *  PACKAGES
 */
package numbers;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NumberLayout extends JFrame
{
    private JLabel positionLabel;
    private JButton returnButton;
    private JButton resetButton;
    private static int gridSizeRow = 4;
    private static int gridSizeCol = 3;
    private int firstCharCode = 48;
    private final int totalButton = 10;
	private static final String VOICENAME= "kevin16"; 
    private String username;
    
    public String getValue()
    {
    	return username;
    }
    public NumberLayout(){}
    public NumberLayout(String username)
    {
    	this.username= username;
    	components();
    }

    public void components()
    {   
    	/**
    	 *  BASIC GUI
    	 */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Number Keyboard");
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        /**
         * Default Icon / Image
         */
        ImageIcon defaultIcon = new ImageIcon(".\\.\\images\\index.jpe");
        JLabel imgLabel = new JLabel("", defaultIcon, JLabel.CENTER);
        
        JPanel leftPanel = new JPanel();
        leftPanel.add(imgLabel, BorderLayout.CENTER);

        contentPane.add(leftPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(gridSizeRow, gridSizeCol, 10, 10));
        
        /**
         *  CREATING NUMBER BUTTONS
         */
        int buttonCount = 0;
        for (int i = 0; i < gridSizeRow; i++)
        {
            for (int j = 0; j < gridSizeCol; j++)
            {
                char [] buttonTextTmpCharArray = new char[]{(char)firstCharCode++};
                String  buttonText = new String(buttonTextTmpCharArray);
                if(buttonCount++ >= totalButton){
                    break;
                } 
                
                /**
                 *  NUMBER BUTTONS GUI
                 */
                JButton button = new JButton(buttonText);
                button.setBackground(new Color(51, 110, 123));
                button.setForeground(new Color(242, 241, 239));
                button.setFont(new Font("PT Sans", Font.BOLD , 16));
                button.setBorder(null);
                button.setFocusPainted(false);
                button.addActionListener(new ActionListener()
                {
                    
                    public void actionPerformed(ActionEvent ae)
                    {
                    	/**
                    	 *  Properties for VOICE OUTPUT
                    	 */
                        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                        Voice voice=VoiceManager.getInstance().getVoice(VOICENAME);
                        voice.allocate();
                        JButton alphabateBtn = (JButton) ae.getSource();
                        String currentAlphabetText = alphabateBtn.getText();
                        /**
                         * For TEXT to SPEECH
                         * Corresponding with Alphabet
                         */
                        try
                        {                            
                            voice.speak(currentAlphabetText);
                        }catch(Exception exc){
                                System.out.println("Error:" +exc);
                        }
                        /**
                         * For IMAGE show
                         * Corresponding with Alphabet
                         */
                        ImageIcon newIcon = new ImageIcon(".\\.\\images\\"+currentAlphabetText+".jpg");
                        imgLabel.setIcon(newIcon);
                    }
                });
                buttonPanel.add(button);
            }
        }
        
        /**
         * RETURN BUTTON
         */
        ImageIcon returnIcon = new ImageIcon(".\\.\\images\\return-button.png");
        returnButton= new JButton(returnIcon);
        returnButton.setBackground(new Color(51, 110, 123));
        returnButton.setFocusPainted(false);
        buttonPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				homepage.home home= new homepage.home(getValue());
				home.setVisible(true);
				
			}
		});
        contentPane.add(buttonPanel);

        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);        
        setVisible(true);
    }

    public static void main(String[] args)
    {
    	NumberLayout number= new NumberLayout();
    	number.components();

    }
}