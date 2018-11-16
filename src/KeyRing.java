import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class KeyRing {
	static List<Key> keyRing = new ArrayList<Key>();

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		
		int menuFlag = 0;
		int option = 0;
		do {
			//output menu 
			System.out.println("1.) Create Key\n2.) Delete Key\n3.) Encrypt File\n4.) Decrypt File\n5.) Add Digital Signature\n6.) Verify Digital Signature\n7.) Exit"); 
			option = userInput.nextInt();
			userInput.nextLine();
			//need to add exception if input isn't 0<option<4
			
			//option 1 adds a key
			if (option==1) {
				Key key = new Key();
				
				System.out.println("Enter user ID: ");
				key.setUserID(userInput.nextLine());
				
				
				//do while confirms passphrase, then adds the key to the keyring
				boolean passFlag = false;
				do {
					System.out.println("Enter a passphrase: ");
					String passphrase = userInput.nextLine();
					System.out.println("Confirm passphrase: ");
					String confirmPassphrase = userInput.nextLine();
					
					if (passphrase.matches(confirmPassphrase) != true) {
						System.out.println("Passesphases don't match");
					}
					else {
						key.setPassphrase(confirmPassphrase);
						key.createKey();
						keyRing.add(key);
						passFlag = true;
					}
				}while(passFlag == false);
			}
			
			//removes key
			else if (option==2) {
				System.out.println("Enter UserID of key you would like to remove: ");
				String removeUserID = userInput.nextLine();
				Key keyToRemove = findKey(removeUserID);
				boolean passFlag = false;
				do {
					System.out.println("Enter passphrase: ");
					String passphrase = userInput.nextLine();
					
					if (passphrase.matches(keyToRemove.getPassphrase()) == false) {
						System.out.println("Passesphases don't match");
					}
					else {
						keyRing.remove(keyToRemove);
						passFlag = true;
					}
				}while(passFlag);
				
				
			}
			//encrypts file
			else if (option==3) {
				System.out.println("Enter User ID: ");
				String UserID = userInput.nextLine();
				Key keyToUse = findKey(UserID);
				boolean passFlag = false;
				do {
					System.out.println("Enter passphrase: ");
					String passphrase = userInput.nextLine();
					if (passphrase.matches(keyToUse.getPassphrase()) == false) {
						System.out.println("Passesphases don't match");
					}
					else {
						System.out.println("Enter name of file to be encrypted: ");
						String fileName = userInput.nextLine();
						System.out.println("Enter user ID of recepient: ");
						String recepientID = userInput.nextLine();
						Key recepientKey = findKey(recepientID );
						FileEncryption keyEncrypt = new FileEncryption(fileName, recepientKey);
						keyEncrypt.encrypt();
						passFlag = true;
					}
				}while(passFlag==false);
				
				
			}
			//decrypts files with User's private/public key
			else if(option == 4){
				System.out.println("Enter User ID: ");
				String UserID = userInput.nextLine();
				Key keyToUse = findKey(UserID);
				boolean passFlag = false;
				do {
					System.out.println("Enter passphrase: ");
					String passphrase = userInput.nextLine();
					if (passphrase.matches(keyToUse.getPassphrase()) == false) {
						System.out.println("Passesphases don't match");
					}
					else {
						System.out.println("Enter name of file to be decrypted: ");
						String fileName = userInput.nextLine();
						FileDecryption keyDecrypt = new FileDecryption(fileName,keyToUse);
						keyDecrypt.decrypt();
						passFlag = true;
					}
				}while(passFlag==false);
			}
			//adds digital signature to file
			else if(option == 5)
			{
				System.out.println("Enter User ID: ");
				String UserID = userInput.nextLine();
				Key keyToUse = findKey(UserID);
				boolean passFlag = false;
				do {
					System.out.println("Enter passphrase: ");
					String passphrase = userInput.nextLine();
					if (passphrase.matches(keyToUse.getPassphrase()) == false) {
						System.out.println("Passesphases don't match");
					}
					else {
						System.out.println("Enter name of file to add digital signature: ");
						String fileName = userInput.nextLine();
						CreateDS DS = new CreateDS(fileName,keyToUse);
						passFlag = true;
					}
				}while(passFlag==false);
			}
			else if(option == 6)
			{
				System.out.println("Enter User ID: ");
				String UserID = userInput.nextLine();
				Key keyToUse = findKey(UserID);
				boolean passFlag = false;
				do {
					System.out.println("Enter passphrase: ");
					String passphrase = userInput.nextLine();
					if (passphrase.matches(keyToUse.getPassphrase()) == false) {
						System.out.println("Passesphases don't match");
					}
					else {
						System.out.println("Enter name of file to verify digital signature: ");
						String fileName = userInput.nextLine();
						System.out.println("Enter user ID of sender: ");
						String senderID = userInput.nextLine();
						Key senderKey = findKey(senderID);
						VerifyDS DS = new VerifyDS(fileName,senderKey);
						passFlag = true;
					}
				}while(passFlag==false);
			}
			//exit option
			else if(option == 7){
				menuFlag = 1;
			}
		}while(menuFlag == 0);
	
	}
	
	//refactor please
	static Key findKey(String userID) {
		for (Key key : keyRing) {
			if (key.getUserID().matches(userID)) {
				return key;
			}
		}
		System.out.println("Key not found");
		return null;
	}

}