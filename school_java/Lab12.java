import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Lab12 extends JApplet implements ItemListener, ActionListener {

	Container contentPane;
	JLabel makeLabel;
	JLabel modelLabel;
	JLabel engineLabel;
	JLabel optionsLabel;
	JTextField makeText;
	JTextField modelText;
	JButton submit;
	JButton reset;
	JRadioButton fourteen;
	JRadioButton v6;
	JRadioButton v8;
	JCheckBox ac;
	JCheckBox sunroof;
	String make = "";
	String model = "";
	String engine = "";
	String extra1 = "";
	String extra2 = "";

	public void init() {
		/*
		 * Set contentPane Preferences
		 */
		contentPane = getContentPane();
		contentPane.setSize(200, 200);
		contentPane.setLayout(new FlowLayout());

		/*
		 * Define variables
		 */
		makeLabel = new JLabel("Make of Car:");
		modelLabel = new JLabel("Model of Car:");
		engineLabel = new JLabel("Engine:");
		optionsLabel = new JLabel("Options:");

		makeText = new JTextField(15);
		modelText = new JTextField(15);

		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setActionCommand("submitButton");
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setActionCommand("resetButton");

		ButtonGroup group = new ButtonGroup();
		fourteen = new JRadioButton("14");
		fourteen.addItemListener(this);
		group.add(fourteen);
		v6 = new JRadioButton("V6");
		v6.addItemListener(this);
		group.add(v6);
		v8 = new JRadioButton("V8");
		v8.addItemListener(this);
		group.add(v8);

		ac = new JCheckBox("A/C");
		ac.addItemListener(this);
		sunroof = new JCheckBox("SunRoof");
		sunroof.addItemListener(this);

		this.activate(false);

		contentPane.add(makeLabel);
		contentPane.add(makeText);
		contentPane.add(modelLabel);
		contentPane.add(modelText);
		contentPane.add(engineLabel);
		contentPane.add(fourteen);
		contentPane.add(v6);
		contentPane.add(v8);
		contentPane.add(optionsLabel);
		contentPane.add(ac);
		contentPane.add(sunroof);
		contentPane.add(submit);
		contentPane.add(reset);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getItemSelectable() == ac){
			if (ac.isSelected())
				extra1 = " has A/C";
			else
				extra1 = "";
		} else if (arg0.getItemSelectable() == sunroof) {
			if (sunroof.isSelected())
				extra2 = " has SunRoof";
			else
				extra2 = "";
		}
			
		if (arg0.getItemSelectable() == fourteen) {
			engine = "14";
			this.activate(true);
		} else if (arg0.getItemSelectable() == v6) {
			engine = "V6";
			this.activate(true);
		} else if (arg0.getItemSelectable() == v8) {
			engine = "V8";
			this.activate(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("submitButton")) {
			make = makeText.getText();
			model = modelText.getText();
			JOptionPane.showMessageDialog(null, make + " " + model + " with "
					+ engine + " " + extra1 + extra2, "",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			makeText.setText("");
			modelText.setText("");
			this.activate(false);
		}

	}

	public void activate(boolean set) {
		ac.setEnabled(set);
		ac.setSelected(false);
		sunroof.setEnabled(set);
		sunroof.setSelected(false);
		submit.setEnabled(set);
		reset.setEnabled(set);
	}

}
