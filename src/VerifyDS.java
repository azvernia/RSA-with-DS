import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class VerifyDS {
	public VerifyDS(String fileName, Key key){
		BigInteger n = key.getPublicKey();
		BigInteger ee = key.getE();
	    String tmpFileName = "tmp.txt";
	    BufferedReader br = null;
	    BufferedReader br1 = null;
	    BufferedWriter bw = null;
	    BufferedWriter bw1 = null;
	    
	    //Separate message from signature
	    try {
	       br = new BufferedReader(new FileReader(fileName));
	       bw = new BufferedWriter(new FileWriter(tmpFileName));
	       bw1 = new BufferedWriter(new FileWriter("DS.txt"));
	       String line;
	       
	       boolean dsFlag = false;
	       while ((line = br.readLine()) != null) {
	    	  if(line.matches("00000000000000000000") == true)
		    		  dsFlag = true; 
	    	  else if(dsFlag == false)
	    	  {
	    		  
	    		  bw.write(line);
	    		  bw.newLine();
	    	  }
	    	  else 
	    	  {
	    		  bw1.write(line);
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
	          {
	        	 bw1.close(); 
	             bw.close();
	          }
	       } catch (IOException e) {
	          //
	       }
	    }
	    System.out.println("Seperated message from DS");
	    //Decrypt signature
	    try {
		       br = new BufferedReader(new FileReader("DS.txt"));
		       bw = new BufferedWriter(new FileWriter("DS1.txt"));
		       String line;
		       //Separate message from signature
		       while ((line = br.readLine()) != null) {
		    	   if(line.matches("") == false)
			    	  {
				    	  BigInteger m = new BigInteger(line);
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
	    System.out.println("Decrypted DS");
	    //compute hash of message
	    try {
		       br = new BufferedReader(new FileReader("tmp.txt"));
		       bw = new BufferedWriter(new FileWriter("DS2.txt"));
		       String line;
		       while ((line = br.readLine()) != null) {
		    	   if(line.matches("") == false)
			    	  {
		    		      int hc = line.hashCode();
		    		      if(hc<0)
				  		  {
				  			  hc = hc*-1;
				  		  }
				    	  String message = Integer.toString(hc);
				    	  bw.write(message);
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
	    System.out.println("Computed Hash of message");
	    // verify hashes
	    boolean match = true;
	    try {
		       br = new BufferedReader(new FileReader("DS1.txt"));
		       br1 = new BufferedReader(new FileReader("DS2.txt"));
		       String line;
		       String line1;
		       while ((line = br.readLine()) != null && (line1 = br1.readLine()) != null) {
		    	   if(line.matches(line1) == false)
			    	  {
		    		      System.out.println("Hashes do not match");
		    		      match = false;
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
		          if(br1 != null)
		             br1.close();
		       } catch (IOException e) {
		          //
		       }
		    }
	    
	    //if hashes match, save message
	    if(match == true)
	    {
	    	System.out.println("hashes match");
	    	try {
	 	       br = new BufferedReader(new FileReader(tmpFileName));
	 	       bw = new BufferedWriter(new FileWriter(fileName));
	 	       String line;
	 	       
	 	       while ((line = br.readLine()) != null) {
	 	    	   bw.write(line);
	 	    	   bw.newLine();  
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
	 	          {
	 	             bw.close();
	 	          }
	 	       } catch (IOException e) {
	 	          //
	 	       }
	 	    }
	    }
	    // Once everything is complete, delete old file..
	    File oldFile1 = new File("DS.txt");
	    File oldFile2 = new File("tmp.txt");
	    File oldFile3 = new File("DS1.txt");
	    File oldFile4 = new File("DS2.txt");
	    oldFile1.delete();
	    oldFile2.delete();
	    oldFile3.delete();
	    oldFile4.delete();
	    

	}

}


