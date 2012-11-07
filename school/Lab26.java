import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lab26 extends JApplet implements ItemListener, ActionListener {

	Container contentPane;
	JPanel titlePane = new JPanel();
	JPanel mainPane = new JPanel();
	JPanel pane1 = new JPanel(), pane2 = new JPanel(), pane3 = new JPanel();
	JPanel subpane1 = new JPanel(), subpane2 = new JPanel(),
			subpane3 = new JPanel();
	JPanel bottomPane = new JPanel();
	JButton colorButton;
	JButton resetButton;
	JLabel title = new JLabel("Lab 26: Set Color for Panels");
	JLabel panel1Label = new JLabel("Panel 1");
	JLabel panel2Label = new JLabel("Panel 2");
	JLabel panel3Label = new JLabel("Panel 3");
	JRadioButton radio1 = new JRadioButton();
	JRadioButton radio2 = new JRadioButton();
	JRadioButton radio3 = new JRadioButton();
	int radioSelected = 2;

	public void init() {
		contentPane = getContentPane();
		contentPane.setSize(new Dimension(300, 200));
		contentPane.setLayout(new BorderLayout());

		radio1.addItemListener(this);
		radio2.addItemListener(this);
		radio3.addItemListener(this);

		ButtonGroup radios = new ButtonGroup();
		radios.add(radio1);
		radios.add(radio2);
		radios.add(radio3);
		radio2.setSelected(true);

		titlePane.add(title);
		titlePane.setBackground(Color.yellow);

		subpane1.add(radio1);
		subpane1.add(panel1Label);
		pane1.setBackground(Color.white);
		pane1.add(subpane1);

		subpane2.add(radio2);
		subpane2.add(panel2Label);
		pane2.setBackground(Color.white);
		pane2.add(subpane2);

		subpane3.add(radio3);
		subpane3.add(panel3Label);
		pane3.setBackground(Color.white);
		pane3.add(subpane3);

		mainPane.setLayout(new GridLayout(1, 3));
		mainPane.add(pane1);
		mainPane.add(pane2);
		mainPane.add(pane3);

		contentPane.add(titlePane, BorderLayout.NORTH);
		contentPane.add(mainPane, BorderLayout.CENTER);
		contentPane.add(bottomPane, BorderLayout.SOUTH);

		colorButton = new JButton("Set Color");
		colorButton.addActionListener(this);

		resetButton = new JButton("Reset All");
		resetButton.addActionListener(this);

		bottomPane.add(colorButton);
		bottomPane.add(resetButton);
		bottomPane.setBackground(Color.yellow);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Set Color")) {
			Color colorSelected = JColorChooser.showDialog(contentPane,
					"Choose a Color", Color.white);
			if (colorSelected != null) {
				if (radioSelected == 1) {
					pane1.setBackground(colorSelected);
				} else if (radioSelected == 2) {
					pane2.setBackground(colorSelected);
				} else {
					pane3.setBackground(colorSelected);
				}
			}
		} else if (event.getActionCommand().equals("Reset All")) {
			pane1.setBackground(Color.white);
			pane2.setBackground(Color.white);
			pane3.setBackground(Color.white);
			radio2.setSelected(true);
		}

	}

	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getItemSelectable() == radio1) {
			radioSelected = 1;
		} else if (arg0.getItemSelectable() == radio2) {
			radioSelected = 2;
		} else {
			radioSelected = 3;
		}
	}

}