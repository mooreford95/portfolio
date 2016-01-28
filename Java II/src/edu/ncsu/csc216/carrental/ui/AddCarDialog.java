package edu.ncsu.csc216.carrental.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * Creates a dialog that prompts the user for the fields reqired to make a new
 * car.
 * 
 * 
 * @author Curtis
 *
 */
@SuppressWarnings("serial")
public class AddCarDialog extends JDialog {
	/** Generic button font */
	private static final Font NORMAL_FONT = new Font("Garamond", Font.PLAIN, 17);

	private Car createdCar;

	JPanel pain = new JPanel();

	JLabel lblMake = new JLabel("Make: ");
	JLabel lblModel = new JLabel("Model: ");
	JLabel lblFleetID = new JLabel("Fleet ID: ");
	JLabel lblColor = new JLabel("Color: ");
	JLabel blank = new JLabel("          ");

	JTextField bxMake = new JTextField();
	JTextField bxModel = new JTextField();
	JTextField bxFleetID = new JTextField();
	JTextField bxColor = new JTextField();

	JButton btnAddCar = new JButton("Add Car");

	/**
	 * Creates the dialog box
	 * 
	 * @param frame
	 *            parent class of this JDialog
	 */
	public AddCarDialog(Frame frame) {
		super(frame, "Add Car", true);
		// this.frame = frame;
		GridLayout gridPrime = new GridLayout();
		gridPrime.setColumns(2);
		gridPrime.setRows(5);
		pain.setLayout(gridPrime);
		add(pain);

		setFonts();
		addActionListener();

		pain.add(lblMake);
		pain.add(bxMake);
		pain.add(lblModel);
		pain.add(bxModel);
		pain.add(lblFleetID);
		pain.add(bxFleetID);
		pain.add(lblColor);
		pain.add(bxColor);
		pain.add(blank);
		pain.add(btnAddCar);

		pack();
		setVisible(true);

	}

	/**
	 * Custom set of action listeners for this jDialog
	 */
	private void addActionListener() {
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Try creating a car out of the user input. With success, close
				// the dialog.
				try {
					String inMake = bxMake.getText();
					String inModel = bxModel.getText();
					String inColor = bxColor.getText();
					String inFleetID = bxFleetID.getText();
					if (inMake.isEmpty() || inModel.isEmpty()
							|| inColor.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Fields Cannot Be Blank");
						return;
					}
					try {
						createdCar = new Car(inFleetID, inMake, inModel,
								inColor);
						dispose();

					} catch (InvalidIDException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					setVisible(false);
					dispose();
				} catch (InvalidIDException e) {
					// Without success, show a message and let the user correct
					// the errors.
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Sets label fonts
	 */
	private void setFonts() {
		lblColor.setFont(NORMAL_FONT);
		lblFleetID.setFont(NORMAL_FONT);
		lblMake.setFont(NORMAL_FONT);
		lblModel.setFont(NORMAL_FONT);
	}

	/**
	 * returns the created object
	 * 
	 * @return the car created by this dialog
	 */
	public Car getNewCar() {
		return createdCar;
	}
}
