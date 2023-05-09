/*
 * Student Name: Duy Duc Minh Vo
 * Lab Professor: Professor Fedor Ilitchev
 * Due Date: Mar 19, 2023
 * Description: Lab 4
 */
import java.util.Scanner;
import java.util.ArrayList;
import encryption.ciphers.CaesarCipher;
import encryption.ciphers.VigenereCipher;
import encryption.Encryptable;

/**
 * The program that tests the encryption methods
 * @author Duy Duc Minh Vo
 */
public class Lab4Tests {
	/**
	 * The one Scanner shared by all input methods
	 */
	Scanner input = new Scanner(System.in);
	
	
	/**
	 * Using a vararg of menu options have user select one and return option number
	 * Must handle all invalid user input and only return when a valid selection has been made
	 * @param options a vararg of menu's options
	 * @return selected option number
	 */
	public int getMenuItem(String... options) {
		for(String x: options) {
			System.out.println(x);
		}
		return getInt("Select action:", 1, 4);
	}
	
	/**
	 * Get a string from the user
	 * Display prompt then get user input
	 * User input string may contain spaces
	 * @param prompt message or title to be displayed
	 * @return user input
	 */
	public String getString(String prompt) {
		System.out.println(prompt);
		return input.nextLine();
	}
	
	
	/**
	 * Get an integer from the user
	 * Display prompt then get user input
	 * The input integer must not be less than the “min” or greater than the “max” specified
	 * Must handle all invalid user input and only return when a valid integer entered
	 * @param prompt message or title to be displayed
	 * @param min the minimum accepted input
	 * @param max the maximum accepted input
	 * @return user's selected integer
	 */
	public int getInt(String prompt, int min, int max) {
		System.out.println(prompt);
		boolean isInputBad = true;
		boolean hasNextInt;
		int selection = 0;
		
		while(isInputBad) {
			hasNextInt = input.hasNextInt();
			if(hasNextInt) {
				int input1 = input.nextInt();
				if ( input1 < min || input1 > max) { 
					System.out.println("Please select a number in the range");
				}
				else {
				selection = input1;
				isInputBad = false;
				}// break out of loop
			}
			else {
				System.out.print("Please select an integer: ");
			}
			input.nextLine(); //clean up input stream
		}
		return selection;
	}
	
	
	/**
	 * Get the encryption method from the user
	 * 		Display title
	 * 		Request encryption method selection
	 * 		Request construction parameter
	 * 		Construct and return encryption method
	 * Must handle all invalid user input and only return when a valid Encryptable has been selected
	 * @return the encryption method from the user
	 */
	public Encryptable getMethod() {	
		System.out.println("Encryption method: ");
		System.out.println("	1 - Caesar: ");
		System.out.println("	2 - Vigenere: ");
		if (getInt("Select action: ", 1, 2) == 1) {
			int shiftValue = getInt("Shift value: ", 0 , 100);
			CaesarCipher caesar = new CaesarCipher(shiftValue);
			return caesar;
		}
		else {
			VigenereCipher vigenere = new VigenereCipher(getString("Password: "));
			return vigenere;
		}
		
	}
	
	
	/**
	 * Application main. The following are the basic steps (error handling not included):
	 * 		Display title
	 * 		Create "encrypteddText" an ArrayList of Strings
	 * 		Create a Lab4Test
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Encryption tester: ");
		int selection;
		ArrayList<String> stringList = new ArrayList<String>();
		Lab4Tests lab4 = new Lab4Tests();
		
		/**
		 * Loop until user selects “exit” (option 4)
		 */
		do {
			/**
			 * Call “getMenuItem” method with main menu options
			 */
			selection =lab4.getMenuItem("1 - Encrypt text",
											"2 - Decrypt text",
											"3 - Display encrypted text",
											"4 - Exit");
			
			/**
			 * Encrypt text:
			 * Get text to encrypt
			 * Get encryption method
			 * Encrypt text using encryption method
			 * Add encrypted text to EncryptedText
			 * Display decrypted text message
			 */
			if (selection == 1) {
				System.out.println("Please enter text to be encryptted: ");
				String userInputText = input.nextLine();
				String encryptedText = lab4.getMethod().encrypt(userInputText);
				stringList.add(encryptedText);
				System.out.println("Encrypted value is: " + encryptedText );	
				System.out.println("");
			}
			
			/**
			 * Decrypt text:
			 * Select text to decrypt from encryptedText
			 * Get encryption method
			 * Decrypt text using encryption method
			 * Display decrypted text message
			 */
			else if (selection == 2) {
				if (stringList.size() == 0)  System.out.println("Nothing to decrypt");
				else {
				System.out.println("----------");
				for (String x: stringList) {
					System.out.println("#" + (stringList.indexOf(x) + 1) + ": " + x);
				}
				System.out.println("----------");
				int decryptedSelection = lab4.getInt("Message number you want to decrypt: ",1, stringList.size());
				String decryptedText = lab4.getMethod().decrypt(stringList.get(decryptedSelection - 1));
				System.out.println("decrypted value is: " + decryptedText);
				System.out.println("");
			}
			}
			
			
			/**
			 * Display encrypted list:
			 * Display all the encrypted text in encryptedText
			 */
			else if (selection == 3) {
				if (stringList.size() == 0) System.out.println("Nothing to display");
				else {
					System.out.println("----------");
				for (String x: stringList) {
					System.out.println("#" + (stringList.indexOf(x) + 1) + ": " + x);
				}
				System.out.println("----------");
				System.out.println("");
				}
			}
		} while (selection != 4);
		System.out.println("Goodbye");
	}
}
