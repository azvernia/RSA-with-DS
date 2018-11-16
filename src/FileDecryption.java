import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class FileDecryption {
	private static File fileDecrypt;
	private static Key key;
	
	public FileDecryption(String FileName, Key recepientKey){
		fileDecrypt = new File(FileName);
		key = recepientKey;
	}

	public void decrypt() {
		File file = fileDecrypt;
		BigInteger n = key.getPublicKey();
		BigInteger d = key.getPrivateKey();
	    String oldFileName = file.toString();
	    String tmpFileName = "tmp.txt";
	    BufferedReader br = null;
	    BufferedWriter bw = null;
	    try {
	       br = new BufferedReader(new FileReader(oldFileName));
	       bw = new BufferedWriter(new FileWriter(tmpFileName));
	       String line;
	       while ((line = br.readLine()) != null) {
	    	  if(line.matches("") == false)
	    	  {
		    	  BigInteger m = new BigInteger(line);
		    	  BigInteger c = m.modPow(d, n);
		    	  
		    	  //bw.write(c+ "\n");
		          bw.write(asciiToString(c));
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
	    // Once everything is complete, delete old file..
	    File oldFile = new File(oldFileName);
	    oldFile.delete();
	    
	    // And rename tmp file's name to old file name
	    File newFile = new File(tmpFileName);
	    newFile.renameTo(oldFile);
	    System.out.println(oldFileName + " decrypted!");
	 }
	
	 public String asciiToString(BigInteger c){
		  StringBuilder sb = new StringBuilder();
		  String p = c.toString();
 		  char[] plaintext = p.toCharArray();
 		  for(int counter = 0; counter < plaintext.length; counter = counter+2){
 			  StringBuilder s = new StringBuilder();
 			  s.append(plaintext[counter]);
 			  s.append(plaintext[counter+1]);
 			  if(Integer.parseInt(s.toString())<32){
 				  s.append(plaintext[counter+2]);
 				  counter++;
 			  }
 			  sb.append((char)Integer.parseInt(s.toString()));
 		  }
		 return(sb.toString());
	 }
}
