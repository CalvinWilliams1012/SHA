package sha;

import java.util.Arrays;

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
	int messageLength;

	public SHA1(String message) {
		// TODO: go through methods with string
		int count = 0;
		for (byte[][] arr2 : wordChunk(chunkMessage(appendMessageLength(appendZeroPadding(appendOne(asciiArrayToByte(charArrayToASCII(messageToCharArray(message))))))))) {
			for(byte[] arr : arr2){
				for(byte b : arr){
					count++;
					System.out.print(count + ":");
					System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
				}
				count = 0;
			}

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
	 * Fourth step in sha1. Simply combine all bytes and append one to it.(-128 = 10000000, which adds one to the end. the zeros are fine.)
	 * @param b all the bytes.
	 * @return b append 1.
	 */
	public byte[] appendOne(byte[] b) {
		messageLength = b.length*8;//This is needed for the 64 bit message length append step.
		byte[] temp = new byte[messageLength+1];
		for(int i =0;i<(messageLength/8);i++){
			temp[i] = b[i];
		}
		temp[b.length] = -128;//IN BINARY =1000 0000
		return temp;
	}
	/**
	 * append 0's until congruent to 448 mod 512.
	 * @param b byte array passed
	 * @return
	 */
	public byte[] appendZeroPadding(byte[] b) {
		int count = b.length;
		while((count*8)%512!=448){
			count++;
		}
		byte[] temp = new byte[count];
		for(int i = 0; i< b.length;i++){
			temp[i] = b[i];
		}
		for(int i = b.length;i<count;i++){
			temp[i] = 0;
		}
		return temp;
	}
	
	/**
	 * Append the original message length in binary to the end of the binary we currently have.
	 * @param b byte array to append to.
	 * @return appended byte array b with message length.
	 */
	public byte[] appendMessageLength(byte[] b){
		byte[] temp = new byte[b.length+8];
		for(int i = 0; i<b.length;i++){
			temp[i] = b[i];
		}
		//Gross line of code needed, turns an integer into a 64 bit string representation with 0 padding and splits into string array of 8 chars each.
		String[] s = String.format("%64s", Integer.toBinaryString(messageLength)).replace(' ', '0').split("(?<=\\G........)");
		int count = 0;
		for(int j = (b.length);j<temp.length;j++){
			temp[j] = Byte.parseByte(s[count],2);
			count++;
		}
		return temp;
	}

	/**
	 * Break the message into chunks of 512 bits or 64 bytes.
	 * @param b message.
	 * @return array of chunk arrays.
	 */
	public byte[][] chunkMessage(byte[] b){
		byte[][] temp = new byte[(b.length/64)][64];
		
		int chunker = 0;
		 for(int i =0;i<temp.length;i++){
			 temp[i] = Arrays.copyOfRange(b,chunker, chunker + 64);
			 chunker+=64;
		 }
		 
		 return temp;
	}
	
	public byte[][][] wordChunk(byte[][] b){
		byte[][][] temp = new byte[b.length][16][4];
		
		int wordStart = 0;
		for(int i = 0; i<temp.length;i++){
			for(int j = 0;j<temp[i].length;j++){
				temp[i][j] = Arrays.copyOfRange(b[i], wordStart, wordStart+4);
				wordStart+=4;
			}
		}
		return temp;
	}
}
