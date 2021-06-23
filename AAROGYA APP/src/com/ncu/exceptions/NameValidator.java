package com.ncu.validators;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.ncu.exceptions.*;
import java.io.*;
import java.util.*;


public class NameValidator {
	public boolean nameValidator(String fileName, String fileType) {
		System.out.println("File name is : " + fileName + " and its type is : " + fileType);
		boolean b = true;  
	    b = emptyFileName(fileName);
		if (b == true) {
		  	try {
			
	        throw new EmptyNameException("EmptyName");
            }catch(EmptyNameException e){System.out.println(e);} 
		}		
        else {	
        	b = missingDot(fileName); 
        	if (b == true){
        	   try { 
			   
			      throw new EmptyExtensionException("EmptyExtension");
               }catch(EmptyExtensionException e){System.out.println(e);} 
            } 
		    else {
		  	   b = fileFormat(fileName);   
		       if (b == true) {
		         try { 
			      
			      throw new MissingExtensionException("MissingExtension");
		  	     }catch(MissingExtensionException e){System.out.println(e);} 
		  	  }
		       else {
		       	  b = fileLength(fileName);  
		          if (b == true) {
		            try {  
			       
			         throw new FileNameLengthException("FileNameLength");
		           }catch(FileNameLengthException e){System.out.println(e);}
		          }  
		          else { 
		       	     b = specialCharacter(fileName);
		       	     if (b == true) {
		       	     try {
			            
			         throw new SpecialCharacterException("SpecialCharacter");
		       	     }catch(SpecialCharacterException e){System.out.println(e);}
		       	     }         
		             else {
		       	        b = fileExists(fileName);
		       	        if (b == true) {
		       	        try {
			              
			              throw new FileAlreadyExists("FileAlreadyExists");
		       	        } catch(FileAlreadyExists e){System.out.println(e);} 
		       	        }   			              
		                else {
		               	    b = fileNotAvailable(fileName);   
		               	    if (b == true) {
		               	     try { 
			                   
			                   throw new FileNotAvailable("FileNotAvailable");
		       	            } catch(FileNotAvailable e){System.out.println(e);}
		       	            }  
		                    else {	
		       	               b = checkValidation(fileName, fileType);
		       	               if (b == true){
		       	               try {
		       	               try{
			                       
			                       throw new InvalidExtensionException("InvalidExtension");
		       	               } catch(InvalidExtensionException e){System.out.println(e);} 
		       	                   throw new NotJsonFileException("NotJsonFile");
		       	               } catch(NotJsonFileException e){System.out.println(e);}
		       	               }         
		                       else {
		 	                       return true;
		       	               } 
		       	            }
		       	         }
		       	      }
		           }
		        }
	       	}
	    }    
        return b;
   }           

    boolean emptyFileName(String fileName) {
	    if(fileName == "") 
		   return true;
		else
			return false;		
   	 }

	 boolean missingDot(String fileName) {
        Pattern costPattern = Pattern.compile("[.]");
		Matcher costMatcher = costPattern.matcher(fileName);
		boolean value = costMatcher.find();
		if (value == true) 
			return false;
		else 
			return true;
	}

    boolean fileFormat(String fileName) {
    	String [] haveExtenstion= fileName.split("\\.");
		if (haveExtenstion.length <= 1)
			return false;
		else 
			return true;
	}
	  	
	boolean fileLength(String fileName) {
        int fileLength = 25;
		String namelength = fileName.split("\\.")[0];
		if(fileName.length() > fileLength) 
			return true;	
		else 
			return false; 
	}

    boolean specialCharacter(String fileName) {
    	fileName = fileName.split("\\.")[0];
		Pattern  patternGet = Pattern.compile("[@#$%^&(,)_]");
		Matcher check = patternGet.matcher(fileName);
		boolean finalValue = check.find();
		if (finalValue == true) 
			return true;
		else
			return false;
    	
    }
	
	boolean fileExists(String fileName) {
		File f = new File(fileName);
		if(f.exists())
		  return true;
		else 
		   return false;  
	}

	boolean fileNotAvailable(String fileName) {
		File f = new File(fileName);
		if(f.exists())
		  return true;
		else 
		   return false;  
	}
	
    boolean checkValidation(String fileName, String fileType) {
    	boolean c;
    	boolean d = true;
		if(fileType == "csv"){
	     c = csvOnly(fileName);
	     if (c == false)
		    return false;
         else {
	        c = jsonOnly(fileName);
            if(c == false)
               return d = false;
            else 
               return d = true;   
          }   
        }
        return d;
	}


    boolean csvOnly(String fileName) {
    	String[] name = fileName.split("\\.");		
		if(name[1].equals("csv"))
			return false;
		else 
			return true;
    }
    
    boolean jsonOnly(String fileName) {
    	String[] name = fileName.split("\\.");		
		if(name[1].equals("json")) 
			return false;
		else 
			return true;
    }
}	

