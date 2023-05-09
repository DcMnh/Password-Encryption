/*
 * Student Name: Duy Duc Minh Vo
 * Lab Professor: Professor Fedor Ilitchev
 * Due Date: Mar 19, 2023
 * Description: Lab 4
 */
package encryption;

/**
 * The required methods for all the encryption classes
 * @author Duy Duc Minh Vo
 */
public interface Encryptable {
		/**
		 * The first valid character for the range of string characters (i.e. space character)
		 */
		public static final int START_CHAR = 32;
		/**
		 * The last valid character for the range of string values (i.e. ‘z’)
		 */
		public static final int END_CHAR = 122;
		/**
		 *  The number of valid string values (difference between the two previous values plus one)
		 */
		public static final int RANGE = 91;
		
		/**
		 * @param input user's selected String to encrypt
		 * @return the Encrypted version of the given string
		 */
		public abstract String encrypt(String input); 
		/**
		 * @param input user's selected String to decrypt
		 * @return the Decrypted version of the given string
		 */
		public abstract String decrypt (String input);
	}


