/*
 * Student Name: Duy Duc Minh Vo
 * Lab Professor: Professor Fedor Ilitchev
 * Due Date: Mar 19, 2023
 * Description: Lab 4
 */
package encryption.ciphers;

import encryption.Encryptable;

/**
 * An Encryptable class that uses a Vigenere cipher to encrypt/decrypt strings
 * @author Duy Duc Minh Vo
 */
public class VigenereCipher implements Encryptable {
	/**
	 * The password used to encrypt and decrypt the text
	 */
	private String key;
	/**
	 * The number of characters in the password
	 */
	private int keyLength;
	
	/**
	 * Create Vigenere cipher with the given password
	 * @param password the password used to encrypt and decrypt
	 */
	public VigenereCipher (String password) {
		this.key = password;
		this.keyLength = password.length();
	}

	/**
	 * Encryption based on Vigenere cipher
	 * Encrypt input string by adding the key values to each character
	 * Must handle wraparound to/from the two ends of the character range
	 * Must handle key that is not the same length as the input string
	 * @param input user's selected String to encrypt
	 * @return the Encrypted version of the given string
	 */
	@Override
	public String encrypt(String input) {
		char[] ch = input.toCharArray();
		char[] ch_key = key.toCharArray();
		
		for (int i = 0; i < input.length(); i++) {
			int input_ascii = (int)ch[i];
			int key_ascii = (int)ch_key[i % keyLength];
			input_ascii += key_ascii;
			while (input_ascii > Encryptable.END_CHAR || input_ascii < Encryptable.START_CHAR) {
				if (input_ascii > Encryptable.END_CHAR) input_ascii = input_ascii - Encryptable.RANGE -32;
				else if ( input_ascii < Encryptable.START_CHAR) input_ascii = input_ascii + Encryptable.RANGE  ; 
			}
			ch[i] = (char)input_ascii;
		}
		return new String(ch);
		
	}
	
	/**
	 * Decryption based on Vigenere cipher
	 * Decrypt input string by subtracting the key values to each character
	 * Must handle wraparound to/from the two ends of the character range
	 * Must handle key that is not the same length as the input string
	 * @param input user's selected String to decrypt
	 * @return the Decrypted version of the given string
	 */
	 
	@Override
	public String decrypt (String input) {
		char[] ch = input.toCharArray();
		char[] ch_key = key.toCharArray();
		for (int i = 0; i < input.length(); i++) {
			int input_ascii = (int)ch[i];
			int key_ascii = (int)ch_key[i % keyLength];
			input_ascii -= key_ascii;
			while (input_ascii > Encryptable.END_CHAR || input_ascii < Encryptable.START_CHAR) {
				if (input_ascii > Encryptable.END_CHAR) input_ascii = input_ascii - Encryptable.RANGE;
				else if ( input_ascii < Encryptable.START_CHAR) input_ascii = input_ascii + Encryptable.RANGE + 32;
			}
			ch[i] = (char)input_ascii;
		}
		return new String(ch);
		
	}
}

