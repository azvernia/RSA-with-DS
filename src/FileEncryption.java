import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class FileEncryption {
	private static File fileEncrypt;
	private static Key key;

	public FileEncryption(String FileName, Key recepientKey){
		fileEncrypt = new File(FileName);
		key = recepientKey;
	}

	public void encrypt() {
		File file = fileEncrypt;
		BigInteger n = key.getPublicKey();
		BigInteger ee = key.getE();
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
		    	  StringBuilder sb = new StringBuilder();
		  		  String message = line;
		  		  char[] letters = message.toCharArray();
		  		  for(char ch: letters){
		  			  
		  			  sb.append((byte)ch);
		  		  }
		  		  message = sb.toString();

		    	  BigInteger m = new BigInteger(message);
		    	  BigInteger c = m.modPow(ee, n);
		          bw.write(c.toString());
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
	    System.out.println(oldFileName + " encrypted!");

	 }
	
}
