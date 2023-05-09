/*
 * Student Name: Duy Duc Minh Vo
 * Lab Professor: Professor Fedor Ilitchev
 * Due Date: Mar 19, 2023
 * Description: Lab 4
 */
package encryption.ciphers;

import encryption.Encryptable;

/**
 * An Encryptable class that uses a Caesar cipher to encrypt/decrypt strings
 * @author Duy Duc Minh Vo
 */
public class CaesarCipher implements Encryptable {
	
	/**
	 * The amount of shift between encrypted and decrypted characters
	 */
	private int key;
	
	/**
	 * create Caesar cipher with the given shift
	 * @param shift The amount of shift between encrypted and decrypted characters
	 */
	public CaesarCipher(int shift) {
		this.key = shift;
	}
	
	/**
	 * Encryption based on Caesar cipher
	 * Encrypt input string by adding the shift value to each character
	 * Must handle wraparound to/from the two ends of the character range
	 * @param input user's selected String to encrypt
	 * @return the Encrypted version of the given string
	 */
	@Override
	public String encrypt(String input) {
		char[] ch = input.toCharArray();
		for (int i = 0; i < input.length(); i++) {
			ch[i] += key;
			while (ch[i]>'z'||ch[i]<' ') { 
				if(ch[i]  > 'z') ch[i] = (char)(ch[i] - Encryptable.RANGE - 32);
				else if (ch[i] < ' ') ch[i] = (char)(ch[i] + Encryptable.RANGE );
			}    
			 ch[i] = (char)ch[i];
	}
		return new String(ch);
}
	@Override
	/**
	 * Decryption based on Caesar cipher
	 * Decrypt input string by subtracting the shift value from each character
	 * Must handle wraparound to/from the two ends of the character range
	 * @param input user's selected String to decrypt
	 * @return the Decrypted version of the given string
	 */
	public String decrypt (String input) {
		char[] ch = input.toCharArray();
		for (int i = 0; i < input.length(); i++) {
			ch[i] -= key;
			while (ch[i]>'z'||ch[i]<' ') { 
				if(ch[i]  > 'z') ch[i] = (char)(ch[i] - Encryptable.RANGE );
				else if (ch[i] < ' ') ch[i] = (char)(ch[i] + Encryptable.RANGE + 32 );
			}    
			 ch[i] = (char)ch[i];
		}
		return new String(ch);
	}

	
}