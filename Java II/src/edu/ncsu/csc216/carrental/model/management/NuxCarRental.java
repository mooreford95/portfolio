package edu.ncsu.csc216.carrental.model.management;



import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.InvalidIDException;
import edu.ncsu.csc216.carrental.model.state.Available;
import edu.ncsu.csc216.carrental.model.state.OutForDetail;
import edu.ncsu.csc216.carrental.model.state.OutForRepair;
import edu.ncsu.csc216.carrental.model.state.RentalStateManager;
import edu.ncsu.csc216.carrental.model.state.Rented;
import edu.ncsu.csc216.carrental.util.Queue;
import edu.ncsu.csc216.carrental.util.SimpleQueue;
import edu.ncsu.csc216.carrental.util.SimpleStack;
import edu.ncsu.csc216.carrental.util.Stack;

/**
 * nux car rental class
 * 
 * @author mmackrell, cfmoore2
 *
 */
public class NuxCarRental implements RentalLocation, RentalStateManager {

	/**
	 * queue of customers
	 */
	private SimpleQueue<Customer> customer;

	/**
	 * queue of cars in the detail shop
	 */
	private SimpleQueue<Car> detailShop;
	/**
	 * queue of cars in the repair shop
	 */
	private SimpleQueue<Car> repairShop;

	/**
	 * detail shop state
	 */
	private OutForDetail detShop = new OutForDetail();
	/**
	 * repair shop state
	 */
	private OutForRepair repShop = new OutForRepair();
	/**
	 * available shop state
	 */
	private Available avail = new Available();
	/**
	 * rent shop state
	 */
	private Rented whereIsMyRent = new Rented(null);

	/**
	 * queue of the available cars
	 */
	private SimpleStack<Car> availableCars;

	/**
	 * rented cars queue
	 */
	private SimpleQueue<Car> rentedCars;

	/**
	 * constructor for class
	 */
	public NuxCarRental() {
		detailShop = new Queue<Car>();
		repairShop = new Queue<Car>();
		customer = new Queue<Customer>();
		availableCars = new Stack<Car>();
		rentedCars = new Queue<Car>();
	}

	/**
	 * constructor that uses data from a scanner
	 * 
	 * @param scandal
	 *            scanner that data comes from
	 */
	public NuxCarRental(Scanner scandal) {
		this(); 
		if (scandal != null && scandal.hasNextLine()) {
			String fleetNumber = "";
			String carMake = "";
			String carModel = "";
			String carColor = "";
			String stat = "";
			while (scandal.hasNextLine()) {
	
				String line = scandal.nextLine();
				Scanner smallScandal = new Scanner(line);
				smallScandal.useDelimiter(",");

				carMake = smallScandal.next();
				carModel = smallScandal.next();

				if (smallScandal.hasNext()) {
					carColor = smallScandal.next();
				}
				if (smallScandal.hasNext()) {
					fleetNumber = smallScandal.next();
				}
				if (smallScandal.hasNext()) {
					stat = smallScandal.next();
				}
			
				if (stat.equalsIgnoreCase("R") && smallScandal.hasNext()) {
					String cusName = smallScandal.next();
					String cusID = smallScandal.next();
					try {
					
						Car car = new Car(fleetNumber, carMake, carModel,
								carColor);

						Scanner splitter = new Scanner(cusName);
						String firstName = splitter.next();
						String lastName = splitter.next();
						splitter.close();
						whereIsMyRent = new Rented(new Customer(firstName,
								lastName, cusID));
						car.setState(whereIsMyRent); 
						rentedCars.add(car);

					}

					catch (InvalidIDException e) {
						smallScandal.close();
						return;
					}

				} else if (stat.equalsIgnoreCase("A")) {
					try {
						Car car = new Car(fleetNumber, carMake, carModel,
								carColor);
						car.setState(avail);
						addCar(car);
					} catch (InvalidIDException e) {
						smallScandal.close();
						return;
					}

				} else if (stat.equalsIgnoreCase("D")) {
					try {
						Stack<Car> qGadgets = new Stack<Car>();
						Car car = new Car(fleetNumber, carMake, carModel,
								carColor);
						boolean broken = false;
						while (!(detailShop.isEmpty())) {
							Car nextCar = detailShop.peek();
							if (nextCar.equals(car)) {
								while (!(qGadgets.isEmpty())) {
									Car moveMe = qGadgets.pop();
									detailShop.add(moveMe);
								}
								broken = true;
								break;
							} else {
								detailShop.remove();
								qGadgets.push(nextCar);
							}
						}
						while (!(qGadgets.isEmpty())) {
							Car moveMe = qGadgets.pop();
							detailShop.add(moveMe);
						}

						if (!broken) {
							car.setState(detShop);
							detailShop.add(car);

						}

					} catch (InvalidIDException e) {
						smallScandal.close();
						return;
					}
				} else if (stat.equalsIgnoreCase("S")) {
					try {
						Stack<Car> qGadgets = new Stack<Car>();
						Car car = new Car(fleetNumber, carMake, carModel,
								carColor);
						boolean broken = false;
						while (!(repairShop.isEmpty())) {
							Car nextCar = repairShop.peek();
							if (nextCar.equals(car)) {
								while (!(qGadgets.isEmpty())) {
									Car moveMe = qGadgets.pop();
									repairShop.add(moveMe);
								}
								broken = true;
								break;
							} else {
								repairShop.remove();
								qGadgets.push(nextCar);
							}
						}

						while (!(qGadgets.isEmpty())) {
							Car moveMe = qGadgets.pop();
							repairShop.add(moveMe);
						}

						if (!broken) {

							car.setState(repShop);
							repairShop.add(car);

						}

					} catch (InvalidIDException e) {
						smallScandal.close();
						return;
					}
				}
				smallScandal.close();
			}

		}
		// same as the one in p2. get from file then add at end

	}

	/**
	 * method for renting a car Moves car from available cars to rented cars
	 */
	public void rentCar() {

		if (!(availableCars.isEmpty()) && !(customer.isEmpty())) {
			Car moveMe = availableCars.pop();
			Customer renter = customer.remove();
			Rented rentACar = new Rented(renter);
			moveMe.setState(rentACar);
			rentedCars.add(moveMe);

		}

	}

	/**
	 * method for returning a car
	 */
	public void returnCar() {

		if (!(rentedCars.isEmpty())) {
			Car returnMe = rentedCars.remove();
			returnMe.setState(detShop);
			detailShop.add(returnMe);
		}

	} 

	/**
	 * method for reporting a problem with the car
	 */
	public void reportProblem() {

		if (!(rentedCars.isEmpty())) {
			Car reportMe = rentedCars.remove();
			reportMe.setState(repShop);
			repairShop.add(reportMe);

		}

	}

	/**
	 * method for detailing a car. takes a car from the detail shop and puts in
	 * back on available cars
	 */
	public void completeDetailing() {

		if (!(detailShop.isEmpty())) {
			Car detailedCar = detailShop.remove();
			detailedCar.setState(avail);
			availableCars.push(detailedCar);
		}

	}

	/**
	 * method for repairing a car
	 */
	public void completeRepairs() {
		if (!(repairShop.isEmpty())) {
			Car repairedCar = repairShop.remove();

			repairedCar.setState(detShop);
			detailShop.add(repairedCar);

		}

	}

	/**
	 * list of all the available cars in the center
	 * 
	 * @return the list of cars
	 */
	public String availableCars() {
		Stack<Car> tempStack = new Stack<Car>();
		String printMe = "";
		while (!(availableCars.isEmpty())) {
			Car moveMe = availableCars.pop();
			printMe += moveMe.toString() + "\n";
			tempStack.push(moveMe);
		}

		while (!(tempStack.isEmpty())) {
			Car moveMe = tempStack.pop();
			availableCars.push(moveMe);
		}

		return printMe;

	}

	/**
	 * list of all the rented cars in the center
	 * 
	 * @return the list of rented cars
	 */
	public String rentedCars() {
		Stack<Car> rentals = new Stack<Car>();

		String printer = "";
		while (!(rentedCars.isEmpty())) {

			Car madison = rentedCars.remove();
			if (whereIsMyRent != null) {
				printer += madison.toString() + " "
						+ madison.getState().rentalInfo() + "\n";
			}
			rentals.push(madison);
		}
		while (!(rentals.isEmpty())) {
			Car madison = rentals.pop();
			rentedCars.add(madison);
		}

		return printer;
	}

	/**
	 * list of all the cars awaiting detailing
	 * 
	 * @return the list of cars waiting detailing
	 */
	public String detailingCars() {
		Stack<Car> qGadgets = new Stack<Car>();

		String printThis = "";
		while (!(detailShop.isEmpty())) {

			Car inevitable = detailShop.remove();

			printThis += inevitable.toString() + "\n";
			qGadgets.push(inevitable);
		}
		while (!(qGadgets.isEmpty())) {
			Car inevitable = qGadgets.pop();
			detailShop.add(inevitable);
		}

		return printThis;

	}

	/**
	 * list of all the cars awaiting repairs
	 * 
	 * @return the list of cars waiting on repairs
	 */
	public String repairingCars() {
		Stack<Car> repStack = new Stack<Car>();

		String printThis = "";
		while (!(repairShop.isEmpty())) {
			Car inevitable = repairShop.remove();

			printThis += inevitable.toString() + "\n";
			repStack.push(inevitable);
		}
		while (!(repStack.isEmpty())) {
			Car inevitable = repStack.pop();
			repairShop.add(inevitable);
		}
		return printThis;

	}

	/**
	 * list of all the customers waiting
	 * 
	 * @return the list of the customers
	 */
	public String customersWaiting() {
		Stack<Customer> custStack = new Stack<Customer>();

		String print = "";
		while (!(customer.isEmpty())) {
			Customer janessa = customer.remove();
			print += janessa.toString() + "\n";
			custStack.push(janessa);
		}
		while (!(custStack.isEmpty())) {
			Customer janessa = custStack.pop();
			customer.add(janessa);
		}

		return print;
	}

	/**
	 * checks to see if there are cars in the rented cars list
	 * 
	 * @return true or false
	 */
	public boolean hasRentedCars() {
		return (!rentedCars.isEmpty());

	}

	/**
	 * checks to see if there are cars in the available car list
	 * 
	 * @return if it is empty
	 */
	public boolean hasAvailableCars() {
		return (!availableCars.isEmpty());
	}

	/**
	 * checks to see if there are cars in the detailing cars list
	 * 
	 * @return true or false
	 */
	public boolean hasDetailingCars() {
		return (!detailShop.isEmpty());

	}

	/**
	 * checks to see if there are cars in the repairing cars list
	 * 
	 * @return true or false
	 */
	public boolean hasRepairingCars() {
		return (!repairShop.isEmpty());
	}

	/**
	 * checks to see if there are people in the waiting customers list
	 * 
	 * @return true or false
	 */
	public boolean hasWaitingCustomers() {
		return (!customer.isEmpty());
	}

	/**
	 * processes the rental of a car
	 */
	public void processRental() {
		rentCar();
	}

	/**
	 * processes the return of a car
	 * @param yesOrNo
	 *            what I pass in
	 */
	public void processReturn(boolean yesOrNo) {
		if (yesOrNo) {
			reportProblem(); 
		} else if (!yesOrNo) {
			returnCar();
		}
	}

	/**
	 * processes the detailing of a car
	 */
	public void processDetailed() {
		completeDetailing();
	}

	/**
	 * processes the repairing of a car
	 */
	public void processRepaired() {
		completeRepairs();
	}

	/**
	 * processes new cars
	 * 
	 * @param number
	 *            number of car
	 * @param make
	 *            make of car
	 * @param model
	 *            model of car
	 * @param color
	 *            color of car
	 */
	public void processNewCar(String number, String make, String model,
			String color) {
		Car thisCar = new Car(number, make, model, color); 
		addCar(thisCar);
	}

	/**
	 * adds a customer
	 * 
	 * @param curtis
	 *            the customer that gets added
	 * @return true or false
	 */
	public boolean addCustomer(Customer curtis) {
		Stack<Customer> cusStack = new Stack<Customer>();
		Stack<Car> allMyRentedCars = new Stack<Car>();
		while (!(customer.isEmpty())) {
			Customer nextCus = customer.peek();
			if (nextCus.equals(curtis)) {
				while (!(cusStack.isEmpty())) {
					Customer moveMe = cusStack.pop();
					customer.add(moveMe);
				}
				return false;
			} else {
				customer.remove();
				cusStack.push(nextCus);
			}
		}
		while (!(cusStack.isEmpty())) {
			Customer moveMe = cusStack.pop();
			customer.add(moveMe);
		}
		String s = "";
		while (!(rentedCars.isEmpty())) {
			Car rentedCar = rentedCars.peek();
			char one = curtis.getFirstName().charAt(0);
			s = "(" + one + " " + curtis.getLastName() + ")";
			if (rentedCar.getState().rentalInfo().equals(s)) {
				while (!(allMyRentedCars.isEmpty())) {
					Car move = allMyRentedCars.pop();
					rentedCars.add(move);
				}
				return false;
			} else {
				rentedCars.remove();
				allMyRentedCars.push(rentedCar);
			}
		}
		while (!(allMyRentedCars.isEmpty())) {
			Car moveThis = allMyRentedCars.pop();
			rentedCars.add(moveThis);
		}

		customer.add(curtis);

		return true;
	}

	/**
	 * adds a car
	 * 
	 * @param vroomVroom
	 *            the car that gets added
	 * @return true or false
	 */
	public boolean addCar(Car vroomVroom) {
		Stack<Car> carStack = new Stack<Car>();
		while (!(availableCars.isEmpty())) {
			Car nextCar = availableCars.peek();

			if (nextCar.equals(vroomVroom)) {
				while (!(carStack.isEmpty())) {
					Car moveMe = carStack.pop();
					availableCars.push(moveMe);
				}
				return false;

			}

			else {
				availableCars.pop();
				carStack.push(nextCar);
			}

		}
		while (!(carStack.isEmpty())) {
			Car moveMe = carStack.pop();
			availableCars.push(moveMe);
		}
		vroomVroom.setState(avail);
		availableCars.push(vroomVroom);

		return true;
	} 

	/**
	 * writes data
	 * 
	 * @param hemmingway
	 *            writer that is used
	 */
	public void writeData(Writer hemmingway) throws IOException {
		Stack<Car> tempStack = new Stack<Car>();
		String printMe = "";
		while (!(availableCars.isEmpty())) {
			Car moveMe = availableCars.pop();
			String state = stateChecker(moveMe);
			printMe = moveMe.getMake() + "," + moveMe.getModel() + ","
					+ moveMe.getColor() + "," + moveMe.getFleetNum() + ","
					+ state + "\n";
			tempStack.push(moveMe);
			
			hemmingway.append(printMe);
		}

		while (!(tempStack.isEmpty())) {
			Car moveMe = tempStack.pop();
			availableCars.push(moveMe);
		}

		Stack<Car> rentals = new Stack<Car>();

		String printer = "";
		while (!(rentedCars.isEmpty())) {

			Car madison = rentedCars.remove();
			String state = stateChecker(madison);
			if (whereIsMyRent != null) {
				printer = madison.getMake() + "," + madison.getModel() + ","
						+ madison.getColor() + "," + madison.getFleetNum()
						+ "," + state + ","
						+ ((Rented) madison.getState()).getCustomer().getFirstName() + " "
						+ ((Rented) madison.getState()).getCustomer().getLastName() + ","
						+ ((Rented) madison.getState()).getCustomer().getId() + "\n";
			}

			rentals.push(madison);
			hemmingway.append(printer);
		}
		while (!(rentals.isEmpty())) {
			Car madison = rentals.pop();
			rentedCars.add(madison);
		}
		
		Stack<Car> qGadgets = new Stack<Car>();

		String printerer = "";
		while (!(detailShop.isEmpty())) {

			Car inevitable = detailShop.remove();
			String state = stateChecker(inevitable);
			printerer = inevitable.getMake() + "," + inevitable.getModel() + ","
					+ inevitable.getColor() + "," + inevitable.getFleetNum() + ","
					+ state + "\n";
			qGadgets.push(inevitable);
			hemmingway.append(printerer);
		}
		while (!(qGadgets.isEmpty())) {
			Car inevitable = qGadgets.pop();
			detailShop.add(inevitable);
		}
		
		Stack<Car> repStack = new Stack<Car>();

		String printere = "";
		while (!(repairShop.isEmpty())) {
			Car fasterDude = repairShop.remove();
			String state = stateChecker(fasterDude);
			printere = fasterDude.getMake() + "," + fasterDude.getModel() + ","
					+ fasterDude.getColor() + "," + fasterDude.getFleetNum() + ","
					+ state + "\n";
			repStack.push(fasterDude);
			hemmingway.append(printere);
		}
		while (!(repStack.isEmpty())) {
			Car inevitable = repStack.pop();
			repairShop.add(inevitable);
		}
		
		hemmingway.close();
	}

	private String stateChecker(Car car) {
		if (car != null && car.getState() == avail) {
			return "A";
		} else if (car != null && car.getState() == detShop) {
			return "D";
		} else if (car != null && car.getState() == repShop) {
			return "S";
		} else {
			return "R";
		}
	}

}
