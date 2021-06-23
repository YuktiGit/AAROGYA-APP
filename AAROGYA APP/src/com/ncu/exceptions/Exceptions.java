import com.ncu.exceptions.*;
import com.ncu.validators.*;
import java.util.*;
    
class Exceptions {
  void exceptionProperties(){
    try{	
		input = new FileInputStream("exceptions.properties");
		prop.load(input);
		emptyFileName(filename);
	}
	catch(EmptyFileNameException e){
		String s =prop.getProperty("emptyNameMessage");
		System.out.println(s);
	}
	catch(Exception e){
		System.out.println(e);
	}
  }
}