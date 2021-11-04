package com.techelevator.view;


import com.techelevator.tenmo.model.Account;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

	public void displayOtherUsers(List<Account> users, String username) {
		out.println("-------------------------------------------");
		out.println("Users");
		out.printf("%-30s", "ID");
		out.printf("%-10s", "Name");
		out.println();
		out.println("-------------------------------------------");

		for (int i = 0; i < users.size(); i++) {
			if (!users.get(i).getUsername().equals(username)) {
				out.printf("%-30s", users.get(i).getUserId());
				out.printf("%-10s ", users.get(i).getUsername());
				out.println();
			}

		}
		out.println("-------------------------------------------");
		out.flush();

	}
	public long getTransferUserId(){
		out.println("Id of user you want to send to");
		long userIdChoice = Long.parseLong(in.nextLine());
		out.flush();
		return userIdChoice;
	}

	public BigDecimal getTransferAmount(){
		out.println("Enter Amount: ");
		Double transferAmount = Double.parseDouble(in.nextLine());
		BigDecimal amount = new BigDecimal(transferAmount);
		out.flush();
		return amount;
	}
	public void insufficientFundInput(){
		out.println();
		out.println(" *** Not enough funds available. Please try again! *** ");
		out.flush();
	}

	public void incorrectUserId() {
		out.println();
		out.println(" *** User ID does not exist! *** ");
		out.flush();
	}
}
