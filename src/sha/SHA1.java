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

	public SHA1(String message){
		//TODO: go through methods with string
	}
	
	public static void main(String[] args) {
		if(args.length > 0){
			new SHA1(args[0]);
		}else{
			//TODO: input message-> get hash.
		}
		
	}
	
	public char[] messageToCharArray(String message){
		char[] c = message.toCharArray();
		return c;
	}
	
	public int[] charArrayToASCII(char[] c){
		int[] ascii = new int[c.length];
		for(int i = 0; i < c.length;i++){
			ascii[i] = (int) c[i];
		}
		return ascii;
	}

}
