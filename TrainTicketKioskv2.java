import java.util.Scanner;
import java.text.DecimalFormat;

public class TrainTicketKioskv2 {
	
	static Scanner input = new Scanner(System.in);
	static DecimalFormat currency = new DecimalFormat("€0.00");
	
	static int totalTicketsSold = 0;
	static double totalTakings = 0;
	static int totalTransactions = 0;
	static int seatsAvailable = 25;
	
	static int pin = 123;
	static double childSingle = 5.50;
	static double childReturn = 10;
	static double adultSingle = 9.50;
	static double adultReturn = 15;
	
	
	public static void main(String[]args) {
		menu();
	}
	
	public static void menu() {
		if(seatsAvailable == 0) { 		// An if statement to reset stats in case of being sold out
			System.out.println("We are fully sold out.");
			System.out.println("Please enter admin PIN code");
			int enteredPin = input.nextInt();
			if(enteredPin == pin) {
				admin();
			}
			else {
				System.out.println("Incorrect pin. Please try again");
				menu();
			}
		}
			
		
		System.out.println("Welcome to Connolly");
		System.out.println("If you would like to purchase ticket(s) to Belfast, please press -");
		System.out.println("1 for a child single");
		System.out.println("2 for an adult single");
		System.out.println("3 for a child return");
		System.out.println("4 for an adult return");
		String stringChoice = input.next();
		int choice = Integer.parseInt(stringChoice);
		
	
		switch(choice) {			//these cases define what the ticket price will be, which we then pass the values of to our selectTicketQty method
		case 1 : {
			selectTicketQty(childSingle, "child single(s)");
			break;
		}
		case 2: {
			selectTicketQty(adultSingle, "adult single(s)");
			break;
		}
		case 3 : {
			selectTicketQty(childReturn, "child return(s)");
			break;
		}
		case 4 : {
			selectTicketQty(adultReturn, "adult return(s)");
			break;
		}
		case 0: {
			adminStats();
			break;
		}
		default: {
			System.out.println("Error. Please select a valid option");
			break;
		}
		}
		menu();  // this method call at the end will loop our program
	}

	private static void selectTicketQty(double ticketPrice, String ticketType) {
		System.out.println("Please select the number of " + ticketType + " tickets you wish to purchase");
		int ticketQty = input.nextInt();
			
		while(ticketQty < 1) {		// User must input a minimum of one in order to break out of the loop
			System.out.println("Please select a valid quantity");
			ticketQty = input.nextInt();
			
			}
		if(ticketQty > seatsAvailable) {		// This line prevents users from buying more tickets than what's available
			System.out.println("We currently only have " + seatsAvailable + " seats available. Apologies for the inconvenience");
			System.out.println("Please select a valid number of tickets");
			ticketQty = input.nextInt();
		}
		
		double transactionPrice = ticketQty * ticketPrice;
		System.out.println("The total cost is " + (currency.format(transactionPrice)));
		
		payment(transactionPrice, ticketQty);
	}
	
	public static void payment(double transactionPrice, int ticketQty) {
		double moneyEntered = input.nextDouble();
		
		while(moneyEntered < transactionPrice) {
			System.out.println("Balance Remaining");
			System.out.println(currency.format(transactionPrice - moneyEntered));
			moneyEntered = moneyEntered + input.nextDouble();
			
			}
		if(moneyEntered > transactionPrice) {
			double change = moneyEntered - transactionPrice;
			System.out.println("Please take your change: " + currency.format(change));
		}
			System.out.println("Thank you. Please take your tickets");
			
			totalTakings = totalTakings + transactionPrice;
			totalTicketsSold = totalTicketsSold + ticketQty;
			seatsAvailable = seatsAvailable - ticketQty;
			totalTransactions ++;
	}

	public static void adminStats() {
		System.out.println("**********************");
		System.out.println("Total Takings: " + currency.format(totalTakings));
		System.out.println("Total number of tickets sold: " + totalTicketsSold);
		System.out.println("Total transactions made: " + totalTransactions);
		System.out.println("Average cash per transaction: " + currency.format(totalTakings / totalTransactions));
		System.out.println("**********************");
		admin();
	}
	
		public static void admin() {
			System.out.println("Press 1 to reset all stats");
			System.out.println("Press 2 to view final stats");
			
			String choice = input.next();
			int intChoice = Integer.parseInt(choice);
			
			switch(intChoice) {
			
			case 1: {
				totalTakings = 0;
				totalTicketsSold = 0;
				seatsAvailable = 25;
				menu();
				break;
			}
			case 2: {
				adminStats();
				break;
			}
			default: {
				System.out.println("Please select valid option");
				admin();
				break;
			}
				
			}
		}
}