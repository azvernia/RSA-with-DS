import java.math.BigInteger;
public class Key {

		private String userID;
		private String passphrase;  //the passphrase used for your private key
		private BigInteger PrivateKey;
		private BigInteger PublicKey;
		private BigInteger e;
		
		public Key() {
			this.userID = "";
			this.passphrase = "";
			this.PrivateKey = BigInteger.ZERO;
			this.PublicKey = BigInteger.ZERO;
			this.e = BigInteger.ZERO;
		}
		
		public Key(String userID, String passphrase, int bits) {
			this.userID = userID;
			this.passphrase = passphrase;
		}

		public String getUserID() {
			return userID;
		}

		public void setUserID(String userID) {
			this.userID = userID;
		}

		public String getPassphrase() {
			return passphrase;
		}

		public void setPassphrase(String passphrase) {
			int pass = passphrase.hashCode();
			passphrase = Integer.toString(pass);
			this.passphrase = passphrase;
		}
		
		public BigInteger getPublicKey(){
			return PublicKey;
		}
		
		public BigInteger getPrivateKey(){
			return PrivateKey;
		}
		
		public BigInteger getE(){
			return e;
		}
		public void createKey(){
			KeyGeneration key = new KeyGeneration();
			PrivateKey = key.getPrivateKey();
			PublicKey = key.getPublicKey();
			e = key.getE();
		}
		
}
