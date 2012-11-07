import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class Lab11 extends JApplet implements ActionListener{

	String username;
	Container contentPane;
	JButton buttonOne;
	JButton buttonTwo;
	JLabel  labelOne;
	
	public void init(){
		contentPane = getContentPane();
		contentPane.setSize(new Dimension(150, 100));
		contentPane.setLayout(new FlowLayout());
		
		labelOne = new JLabel("User Name Input:");
		contentPane.add(labelOne);
		buttonOne = new JButton("Enter User Name");
		buttonOne.addActionListener(this);
		contentPane.add(buttonOne);
		buttonTwo = new JButton("Show User Name");
		buttonTwo.setEnabled(false);
		buttonTwo.addActionListener(this);
		contentPane.add(buttonTwo);
		
		//inputString = JOptionPane.showInputDialog("Please enter your name:");
		

	}

	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Enter User Name")){
			username = JOptionPane.showInputDialog("Enter User Name");
			buttonTwo.setEnabled(true);
		} else if (event.getActionCommand().equals("Show User Name")){
			JOptionPane.showMessageDialog(null, username, "User", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

}
