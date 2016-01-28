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

import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * Prompts the user for all fields related to creating a new customer, then
 * creates one.
 * 
 * @author Curtis
 *
 */
@SuppressWarnings("serial")
public class AddCustomerDialog extends JDialog {
	/** Generic button font */
	private static final Font NORMAL_FONT = new Font("Garamond", Font.PLAIN, 17);

	private Customer createdCustomer;

	JPanel pain = new JPanel();

	JLabel lblFirst = new JLabel("First: ");
	JLabel lblLast = new JLabel("Last: ");
	JLabel lblCusID = new JLabel("Customer ID: ");
	JLabel blank = new JLabel("          ");

	JTextField bxFirst = new JTextField();
	JTextField bxLast = new JTextField();
	JTextField bxCusID = new JTextField();

	JButton btnAddCus = new JButton("Add Customer");

	/**
	 * Creates a dialog to ask user for fields to make a new customer
	 * 
	 * @param frame
	 *            frame from main panel to make this a child class.
	 */
	public AddCustomerDialog(Frame frame) {
		super(frame, "Add Customer", true);

		GridLayout gridPrime = new GridLayout();
		gridPrime.setColumns(2);
		gridPrime.setRows(4);
		pain.setLayout(gridPrime);
		add(pain);

		setFonts();
		addActionListener();

		pain.add(lblFirst);
		pain.add(bxFirst);
		pain.add(lblLast);
		pain.add(bxLast);
		pain.add(lblCusID);
		pain.add(bxCusID);
		pain.add(blank);
		pain.add(btnAddCus);

		pack();
		setVisible(true);

	}

	/**
	 * Custom set of action listeners for this jDialog
	 */
	private void addActionListener() {
		btnAddCus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Try creating a car out of the user input. With success, close
				// the dialog.
				try {
					String inLast = bxLast.getText();
					String inFirst = bxFirst.getText();
					String inID = bxCusID.getText();
					if (inLast.isEmpty() || inFirst.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Fields Cannot Be Blank");
						return;
					}
					try {
						createdCustomer = new Customer(inFirst, inLast, inID);
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
		lblCusID.setFont(NORMAL_FONT);
		lblFirst.setFont(NORMAL_FONT);
		lblLast.setFont(NORMAL_FONT);
	}

	/**
	 * returns the created object
	 * 
	 * @return created customer
	 */
	public Customer getNewCustomer() {
		return createdCustomer;
	}
}
