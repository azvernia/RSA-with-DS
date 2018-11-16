import java.math.BigInteger;
import java.util.Random;
public class KeyGeneration {
	private BigInteger PublicKey, PrivateKey;
	private BigInteger e;
	
	public KeyGeneration(){
		
			//Choose random prime numbers of bit length 512
			Random rnd = new Random();
			BigInteger p = BigInteger.probablePrime(512, rnd);
			BigInteger q = BigInteger.probablePrime(512, rnd);
			
			//Calculate n for public key
			BigInteger n = p.multiply(q);
			
			//Calculate p-1 and q-1
			BigInteger p1 = p.subtract(BigInteger.ONE);
			BigInteger q1 = q.subtract(BigInteger.ONE);
			
			//Compute totient
			BigInteger totient = p1.multiply(q1);
			
			//Random e greater than 1 and less than totient
			this.e = nextRandomBigInteger(totient);
			
			//Make sure e relatively prime to totient
			while(gcd(e,totient) == false){
				e = nextRandomBigInteger(totient);
			}
			
			//Compute private key 
			BigInteger d = e.modInverse(totient);
			this.PublicKey = n;
			this.PrivateKey= d;
	}
	
	//returns public key n
	public BigInteger getPublicKey(){
		return this.PublicKey;
	}
	
	//returns value of e
	public BigInteger getE(){
		return this.e;
	}
	
	//returns private key
	public BigInteger getPrivateKey(){
		return this.PrivateKey;
	}
	
	//This function returns a random BigInteger greater than 1 and less than input n
	public BigInteger nextRandomBigInteger(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 && result.compareTo(BigInteger.ONE)<= 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
	
	//determine if relatively prime inputs
	public boolean gcd(BigInteger p, BigInteger q){
		BigInteger t;
		while(q != BigInteger.ZERO)
		{
			t = p;
			p = q;
			q = t.mod(q);
		}
		if(p.compareTo(BigInteger.ONE)== 0)
		{
			return true;
		}
		return false;
	}
	
	//test function to encrypt/decrypt message
	/*
	public static void main(String[] args) {
		KeyGeneration keys = new KeyGeneration();
		StringBuilder sb = new StringBuilder();
		String message ="the oscars turn  on sunday which seems about right after this long strange";
		char[] letters = message.toCharArray();
		for(char ch: letters){
			sb.append((byte)ch);
		}
		message = sb.toString();
		BigInteger m = new BigInteger(message);
		System.out.println(m.bitCount());
		BigInteger c = encrypt(message,keys);
		message = c.toString();
		System.out.println(c.bitCount());
		BigInteger d = decrypt(message, keys);
		System.out.println(d.bitCount());
		String p = d.toString();
		char[] plaintext = p.toCharArray();
		for(int counter = 0; counter < plaintext.length; counter = counter+2){
			StringBuilder sd = new StringBuilder();
			sd.append(plaintext[counter]);
			sd.append(plaintext[counter+1]);
			if(Integer.parseInt(sd.toString())<32){
				sd.append(plaintext[counter+2]);
				counter++;
			}
			System.out.print((char)Integer.parseInt(sd.toString()));
		}
	}
	
	public static BigInteger encrypt(String message, KeyGeneration keys)
	{
		BigInteger m = new BigInteger(message);
		BigInteger c = m.modPow(keys.getE(), keys.getPublicKey());
		return c;
	}
	
	public static BigInteger decrypt(String message, KeyGeneration keys){
		BigInteger c = new BigInteger(message);
		BigInteger d = c.modPow(keys.getPrivateKey(), keys.getPublicKey());
		return d;
	}
	*/
	
}
