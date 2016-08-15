import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ComputeSHA {
	public static void main(String[] args) throws IOException { 
    	FileReader file = new FileReader(args[0]);
       BufferedReader bf = new BufferedReader(file);
       StringBuffer sb = new StringBuffer();
       String m="";
        	  int line = 0;
		if(args[0]==null)
		System.out.println("File not received");
        	 
        	  while((line =bf.read())!=-1){
        	 //sb.append((char)line+"");
        		  m=m+(char)line;
        	  }
        	   //System.out.println(m);
        try { 
            System.out.println(SHA1convert(m));
        } catch (NoSuchAlgorithmException e) { 
           e.printStackTrace();
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();
        } 
    } 


public static String Hexadecimal(byte[] input) { 
	
	StringBuffer buf = new StringBuffer();
    for (int i = 0; i < input.length; i++) { 
        int halfbyte = (input[i] >>> 4) & 0x0F;
        int two_halfs = 0;
        do { 
            if ((0 <= halfbyte) && (halfbyte <= 9)) 
                buf.append((char) ('0' + halfbyte));
            else 
                buf.append((char) ('a' + (halfbyte - 10)));
            halfbyte = input[i] & 0x0F;
        } while(two_halfs++ < 1);
    } 
    return buf.toString();

	}


public static String SHA1convert(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest md;
	    md = MessageDigest.getInstance("SHA-1");
	    md.reset();
	    //byte[] manseeSHA1 = new byte[40];
	    md.update(text.getBytes("UTF-8"));
	    //manseeSHA1 = md.digest();
	    return Hexadecimal(md.digest());
	    } 
}
