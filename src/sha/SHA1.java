package sha;

/**
 * Example of the SHA-1 Hashing algorithm.
 * 
 * Message Size | Block Size | Word Size | Message Digest Size | <2^64 | 512 |
 * 32 | 160 | (bits)
 * 
 * @author Calvin A. Williams
 * 
 *         Algorithm from U.S. FIPS PUB 180-4 Secure Hash Standard (SHS)
 *         Category: Computer Security SubCategory: Cryptography
 *         http://csrc.nist.gov/publications/fips/fips180-4/fips-180-4.pdf
 */
public class SHA1 {

	String h0 = "01100111010001010010001100000001";
	String h1 = "11101111110011011010101110001001";
	String h2 = "10011000101110101101110011111110";
	String h3 = "00010000001100100101010001110110";
	String h4 = "11000011110100101110000111110000";

	public SHA1(String message) {
		// TODO: go through methods with string
		for (byte b : asciiArrayToByte(charArrayToASCII(messageToCharArray(message)))) {
			System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
		}
		;
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			new SHA1(args[0]);
		} else {
			// TODO: input message-> get hash.
		}

	}

	/**
	 * First step of sha1 hashing is to take the string and seperate into chars,
	 * I could have avoided this method and just fed the ASCII method a string
	 * and went through each character but I feel a method helps the
	 * understanding.
	 * 
	 * @param message
	 *            The message entered
	 * @return char array of the message
	 */
	public char[] messageToCharArray(String message) {
		char[] c = message.toCharArray();
		return c;
	}

	/**
	 * This is the second step of sha1 hashing and it converts all chars to
	 * there ASCII value.
	 * 
	 * @param c
	 *            Char array of string.
	 * @return int array of ASCII values for the char array.
	 */
	public int[] charArrayToASCII(char[] c) {
		int[] ascii = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			ascii[i] = (int) c[i];
		}
		return ascii;
	}

	/**
	 * This is the third step of sha1 hashing and it converts the ASCII
	 * characters to bytes.
	 * 
	 * @param a
	 *            int array of ASCII values.
	 * @return byte array of character values.
	 */
	public byte[] asciiArrayToByte(int[] a) {
		byte[] bytes = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			String asciibyte = "";
			int value = a[i];
			while (value > 0) {
				String bit = Integer.toString(value % 2);
				asciibyte = bit + asciibyte;
				value = value / 2;
			}
			bytes[i] = Byte.valueOf(asciibyte, 2);
		}
		return bytes;
	}

	/**
	 * Fourth step in sha1. Simply combine all bytes and add one to it.
	 * @param b all the bytes.
	 * @return b+1.
	 */
	public byte[] addToByteArray(byte[] b) {
		for (int i = b.length; i > 0; i--) {
			if (((int) b[i - 1] & 0xFF) == 255) {
				// If the byte is max value
				// ASCII(127 signed, 255
				// unsigned) make this value
				// equal zero and add to the
				// next one. binary adding.
				b[i - 1] = 0;
			} else {
				b[i - 1] += 1;
				return b;
			}
		}

		return b;
	}
	
	public byte[] 

}
