import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class CreateDS {
	
	//takes a file and a key in order to create a digital signature. The signature is then appended to the end of the file
	public CreateDS(String fileName, Key key){
		File file = new File(fileName);
		String tmpFileName = "tmp.txt";
		BufferedReader br = null;
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		try {
			   
		       br = new BufferedReader(new FileReader(file));
		       bw = new BufferedWriter(new FileWriter(tmpFileName));
		       bw1 = new BufferedWriter(new FileWriter("check.txt"));
		       String line;
		       bw.newLine();
		       bw.write("00000000000000000000");//seperator for message and DS
		       bw.newLine();
		       while ((line = br.readLine()) != null) {
		    	  if(line.matches("") == false)
		    	  {
			  		  int hc = line.hashCode();
			  		  if(hc<0)
			  		  {
			  			  hc = hc*-1;
			  		  }
			  		  String message = Integer.toString(hc);
			  		  bw1.write(message);
			  		  bw1.newLine();
			  		  BigInteger m = BigInteger.valueOf(hc);
			    	  BigInteger c = m.modPow(key.getPrivateKey(), key.getPublicKey());
			          bw.write(c.toString());
			          bw.newLine();
		    	  }
		    	  else
		    	  {
		    		  bw.newLine();
		    		  bw1.newLine();
		    	  }
		       }
		       
		    } catch (Exception e) {
		       return;
		    } finally {
		       try {
		          if(br != null)
		             br.close();
		       } catch (IOException e) {
		          //
		       }
		       try {
		          if(bw != null)
		             bw.close();
		       } catch (IOException e) {
		          //
		       }
		       try {
			          if(bw1 != null)
			             bw1.close();
			       } catch (IOException e) {
			          //
			       }
		    }
		try {
			String line;
			br= new BufferedReader(new FileReader(tmpFileName));
			bw = new BufferedWriter(new FileWriter(file,true));
			while ((line = br.readLine()) != null) {
				   if(line.matches("") == false)
				   {
					   bw.write(line);
					   bw.newLine();
				   }
				   else
				   {
					  bw.newLine();
				   }
			}
		    } catch (Exception e) {
		       return;
		    } finally {
		       try {
		          if(br != null)
		             br.close();
		       } catch (IOException e) {
		          //
		       }
		       try {
		          if(bw != null)
		             bw.close();
		       } catch (IOException e) {
		          //
		       }  
		    }
		
			System.out.println("Created DS");
			File tmpFile = new File("tmp.txt");
	    	tmpFile.delete();  
		}
	//Test Function for digital signature
	/*
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Key key = new Key();
		System.out.println("Enter user ID: ");
		key.setUserID(userInput.nextLine());
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
				passFlag = true;
			}
		}while(passFlag == false);
		File file = new File("plaintext.txt");
		CreateDS ds = new CreateDS(file, key);
	}
	*/
}
