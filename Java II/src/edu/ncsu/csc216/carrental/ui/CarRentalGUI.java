package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * GUI for managing user interactions with car rental interface.
 * 
 * @author Curtis
 * @author Mike
 */

@SuppressWarnings("serial")
public class CarRentalGUI extends JFrame implements ActionListener {

	NuxCarRental nuxie;
	AddCarDialog newCarDia;
	AddCustomerDialog newCustomerDia;

	/**
	 * width of the frame
	 */
	private final static int FRAME_WIDTH = 1050;
	/**
	 * height of the frame
	 */
	private final static int FRAME_HEIGHT = 350;
	/** Font for titles. */
	private static final Font TITLE_FONT = new Font("Microsoft Sans",
			Font.BOLD, 20);
	/** Generic button font. Garamond is a pretty font. */
	private static final Font NORMAL_FONT = new Font("Garamond", Font.PLAIN, 17);

	// dlms for each list
	private DefaultListModel<String> dlmavailable = new DefaultListModel<String>();
	private DefaultListModel<String> dlmrented = new DefaultListModel<String>();
	private DefaultListModel<String> dlmdetail = new DefaultListModel<String>();
	private DefaultListModel<String> dlmrepair = new DefaultListModel<String>();
	private DefaultListModel<String> dlmcustomers = new DefaultListModel<String>();

	// jlists for each list
	private JList<String> jlavail = new JList<String>(dlmavailable);
	private JList<String> jlrent = new JList<String>(dlmrented);
	private JList<String> jldet = new JList<String>(dlmdetail);
	private JList<String> jlrep = new JList<String>(dlmrepair);
	private JList<String> jlcus = new JList<String>(dlmcustomers);

	// scroll panes for each list
	private JScrollPane availablePane = new JScrollPane(jlavail);
	private JScrollPane rentedPane = new JScrollPane(jlrent);
	private JScrollPane detailPane = new JScrollPane(jldet);
	private JScrollPane repairPane = new JScrollPane(jlrep);
	private JScrollPane customerPane = new JScrollPane(jlcus);

	// labels. So many labels.
	private JLabel lblTitle = new JLabel(
			"Welcome To NuxRental Management System");
	private JLabel lblAva = new JLabel("Avalible Cars:");
	private JLabel lblCussies = new JLabel("Customers:");
	private JLabel lblRentPaid = new JLabel("Rented Cars:");
	private JLabel lblDetailedLabel = new JLabel("Detailing Cars:");
	private JLabel lblRepairing = new JLabel("Repairing Cars:");

	// so many buttons
	private JButton btnCus = new JButton("Add Customer");
	private JButton btnCar = new JButton("Add Car");
	private JButton btnRent = new JButton("Rent Car");
	private JButton btnCusReturn = new JButton("Return Car");
	private JButton btnCmptDtl = new JButton("Complete Detail");
	private JButton btnRptPrblm = new JButton("Report Problem");
	private JButton btnCmptRpr = new JButton("Complete Repair");
	private JButton btnSave = new JButton("Save");

	/**
	 * constructor for gui with string
	 * 
	 * @param sillyString
	 *            the string
	 */
	public CarRentalGUI(String sillyString) {
		fileChooser(sillyString);

		labelFontSetter();

		// 1. Create the frame.
		JFrame frame = new JFrame("Androtech Rent Management System");
		this.addWindowListener(new Listen(frame));
		JPanel pan = new JPanel(new BorderLayout());
		frame.add(pan);
		GridLayout forLists = new GridLayout();
		GridLayout griddlin = new GridLayout();
		GridLayout labelGrid = new GridLayout();
		forLists.setRows(1);
		forLists.setColumns(5);
		forLists.setHgap(5);

		labelGrid.setHgap(5);

		griddlin.setColumns(5);
		griddlin.setRows(1);
		griddlin.setHgap(5);
		JPanel centerLayout = new JPanel(new BorderLayout());
		JPanel iNeedAGridForLabels = new JPanel(labelGrid);
		JPanel japanel = new JPanel(forLists);
		JPanel southLayout = new JPanel(griddlin);

		// 2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Create components and put them in the frame.
		lblTitle.setFont(TITLE_FONT);
		pan.add(lblTitle, BorderLayout.NORTH);
		pan.add(centerLayout, BorderLayout.CENTER);
		pan.add(southLayout, BorderLayout.SOUTH);

		centerLayout.add(iNeedAGridForLabels, BorderLayout.NORTH);
		centerLayout.add(japanel, BorderLayout.CENTER);

		iNeedAGridForLabels.add(lblAva);
		iNeedAGridForLabels.add(lblRentPaid);
		iNeedAGridForLabels.add(lblDetailedLabel);
		iNeedAGridForLabels.add(lblRepairing);
		iNeedAGridForLabels.add(lblCussies);
		japanel.add(availablePane);
		japanel.add(rentedPane);
		japanel.add(detailPane);
		japanel.add(repairPane);
		japanel.add(customerPane);

		listenUp();

		southLayout.add(btnCar);
		southLayout.add(btnRent);
		southLayout.add(btnCusReturn);
		southLayout.add(btnCmptDtl);
		southLayout.add(btnCmptRpr);
		southLayout.add(btnRptPrblm);
		southLayout.add(btnCus);
		southLayout.add(btnSave);
		// 4. Size the frame.
		updateGUI();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// frame.pack();

		// 5. Show it.
		frame.setVisible(true);

	}

	/**
	 * opening file chooser
	 * 
	 * @param fileName
	 *            name of file if ran with command-line arguments.
	 */
	public void fileChooser(String fileName) {

		if (fileName.isEmpty()) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = fc.showOpenDialog(this);
			try {
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					nuxie = new NuxCarRental(new Scanner(fc.getSelectedFile()));
				} else {
					nuxie = new NuxCarRental();
				}
			} catch (Exception e) {
				nuxie = new NuxCarRental();
			}
		} else { // file name is command-line parameter
			try {
				nuxie = new NuxCarRental(new Scanner(new File(fileName)));
			} catch (Exception e) {
				nuxie = new NuxCarRental();
			}
		}
	}

	/**
	 * Adds action listeners to all buttons.
	 */
	public void listenUp() {
		btnCar.addActionListener(this);
		btnCmptDtl.addActionListener(this);
		btnCmptRpr.addActionListener(this);
		btnCus.addActionListener(this);
		btnCusReturn.addActionListener(this);
		btnRent.addActionListener(this);
		btnRptPrblm.addActionListener(this);
		btnSave.addActionListener(this);

	}

	/**
	 * Sets the font of all labels because I think Garamond is pretty.
	 */
	public void labelFontSetter() {
		lblAva.setFont(NORMAL_FONT);
		lblDetailedLabel.setFont(NORMAL_FONT);
		lblCussies.setFont(NORMAL_FONT);
		lblRentPaid.setFont(NORMAL_FONT);
		lblRepairing.setFont(NORMAL_FONT);
	}

	/**
	 * Implements the actionListener interface so the buttons do stuff.
	 * 
	 * @param lightsCameraAction
	 *            action listener associated with this frame.
	 */
	public void actionPerformed(ActionEvent lightsCameraAction) {
		if (lightsCameraAction.getSource().equals(btnCar)) {
			JFrame carAdder = new JFrame("Add A Car");

			newCarDia = new AddCarDialog(carAdder);

			Car newCar = newCarDia.getNewCar();
			if (newCar != null) {
				nuxie.addCar(newCar);
			}

		} else if (lightsCameraAction.getSource().equals(btnCmptDtl)) {
			nuxie.completeDetailing();
		} else if (lightsCameraAction.getSource().equals(btnCmptRpr)) {
			nuxie.completeRepairs();
		} else if (lightsCameraAction.getSource().equals(btnCus)) {
			JFrame cusAdder = new JFrame("Add A Customer");
			newCustomerDia = new AddCustomerDialog(cusAdder);
			Customer newCustomer = newCustomerDia.getNewCustomer();
			if (newCustomer != null) {
				nuxie.addCustomer(newCustomer);
			}
		} else if (lightsCameraAction.getSource().equals(btnCusReturn)) {
			nuxie.returnCar();
		} else if (lightsCameraAction.getSource().equals(btnRent)) {
			nuxie.rentCar();
		} else if (lightsCameraAction.getSource().equals(btnRptPrblm)) {
			nuxie.reportProblem();
		} else if (lightsCameraAction.getSource().equals(btnSave)) {
			saveFile();
		}
		try {
			updateGUI();
		} catch (NullPointerException e) {
			// do nothing, this happens.
			// System.out.println("Caught Nullpointer");
		}

	}

	/**
	 * Updates the various lists and buttons. Called any time the GUI needs to
	 * be updated.
	 */
	private void updateGUI() {
		loadModel(jlavail, dlmavailable, nuxie.availableCars());
		loadModel(jlcus, dlmcustomers, nuxie.customersWaiting());
		loadModel(jldet, dlmdetail, nuxie.detailingCars());
		loadModel(jlrent, dlmrented, nuxie.rentedCars());
		loadModel(jlrep, dlmrepair, nuxie.repairingCars());
		if (nuxie.availableCars() == null || nuxie.availableCars().isEmpty()
				|| nuxie.customersWaiting() == null
				|| nuxie.customersWaiting().equals("")) {
			btnRent.setEnabled(false);
		} else {
			btnRent.setEnabled(true);
		}
		if (nuxie.detailingCars() == null || nuxie.detailingCars().isEmpty()) {
			btnCmptDtl.setEnabled(false);
		} else {
			btnCmptDtl.setEnabled(true);
		}
		if (nuxie.repairingCars() == null || nuxie.repairingCars().isEmpty()) {
			btnCmptRpr.setEnabled(false);
		} else {
			btnCmptRpr.setEnabled(true);
		}
		if (nuxie.rentedCars() == null || nuxie.rentedCars().isEmpty()) {
			btnCusReturn.setEnabled(false);
			btnRptPrblm.setEnabled(false);
		} else {
			btnCusReturn.setEnabled(true);
			btnRptPrblm.setEnabled(true);
		}
	}

	/**
	 * Private Method - loads a list model from a string using newline
	 * tokenizers.
	 * 
	 * @param j
	 *            the JList to refresh
	 * @param m
	 *            the default list model associated with j
	 * @param info
	 *            the String whose tokens initialize the default list model
	 */
	private void loadModel(JList<String> j, DefaultListModel<String> m,
			String info) {
		m.clear();
		if (info == null)
			return;
		StringTokenizer st = new StringTokenizer(info, "\n");
		while (st.hasMoreTokens()) {
			m.addElement(st.nextToken());
		}
		j.ensureIndexIsVisible(0);
	}

	/**
	 * the main method
	 * 
	 * @param args
	 *            command-line arguments
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new CarRentalGUI(args[0]);
		}
		new CarRentalGUI("");
	}

	/**
	 * Opens a save file dialog to save the file. Warns the user that they need
	 * to save in .csv.
	 */
	@SuppressWarnings("static-access")
	private void saveFile() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == fc.APPROVE_OPTION) {
			String fileType = ".csv";
			if (fc.getSelectedFile().getAbsolutePath().endsWith(".csv")) {
				fileType = "";
			}
			try (FileWriter fw = new FileWriter(fc.getSelectedFile() + fileType)) {
				nuxie.writeData(fw);
			} catch (IOException e) {
				JOptionPane fileNoSave = new JOptionPane(
						"File could not Be created!");
				fileNoSave.setVisible(true);
				saveFile();
			}
		}
	}

	/**
	 * Private class with sole purpose of adding the ability to use
	 * WindowListeners. Preserves encapsulation.
	 * 
	 * @author Curtis
	 *
	 */
	private class Listen implements WindowListener {
		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowActivated(WindowEvent winEvent) {
			// do nothing. This is just in the interface

		}

		public Listen(Frame frame) {
			frame.addWindowListener(this);
		}

		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowClosed(WindowEvent winEvent) {

			// do nothing. Needed to satisfy windowListener interface

		}

		/**
		 * If the window is closed, the user is asked if they wish to save.
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowClosing(WindowEvent winEvent) {
			int reply = JOptionPane.showConfirmDialog(null,
					"Would you like to save?", "Save?",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				saveFile();
			}
			System.exit(0);
		}

		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowDeactivated(WindowEvent winEvent) {
			// do nothing. This is just in the interface

		}

		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowDeiconified(WindowEvent winEvent) {
			// do nothing. This is just in the interface

		}

		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowIconified(WindowEvent winEvent) {
			// do nothing. This is just in the interface

		}

		/**
		 * Method needed to satisfy WindowListener Interface
		 * 
		 * @param winEvent
		 *            windowEvent to trigger the method
		 */
		@Override
		public void windowOpened(WindowEvent winEvent) {
			// do nothing. This is just in the interface

		}

	}

}
