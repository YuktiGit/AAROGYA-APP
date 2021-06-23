import java.sql.*; 
import java.lang.*;
import java.util.Scanner;
class Add_record{
   public static void add(){
      Scanner in = new Scanner(System.in);
      try{
         Connection conn = null;
         Statement stmt = null;
         String userName = "root";
         String password = "Jhalkesh@123";
         String url = "jdbc:mysql://localhost:3306/Arogya";
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         conn =DriverManager.getConnection(url,userName,password);
         System.out.println("Database connection established.");
         
         stmt = conn.createStatement(); 
              
         System.out.print("Enter SN : ");
         int sn = in.nextInt();

         in.nextLine();
            
         System.out.print("Enter Patient Aadhar Number : ");
         String a = in.next();
         System.out.println();
         int count = 0;       
         for(int i = 0; i < a.length(); i++){    
                if(a.charAt(i) != ' '){ 
                      count++;
                }    
         }
         if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16."); 
         try{
            Double.parseDouble(a);
         }catch(Exception e){
                 throw new InvalidAadharException("Aadhar number is not numeric.");
           }
           
           
         in.nextLine();
         
         System.out.print("Enter Patient First Name : ");
         String f = in.nextLine();
         boolean b = ((!f.equals("")) && (f != null) && (f.matches("^[a-zA-Z]*$"))); 
         if(b == false) throw new InvalidNameException("First name contains characters other then Alphabets."); 
         
         System.out.println();
         
         System.out.print("Enter Patient Last Name : ");
         String l = in.nextLine();
         boolean b = ((!l.equals("")) && (l != null) && (l.matches("^[a-zA-Z]*$"))); 
         if(b == false) throw new InvalidNameException("Last name contains characters other then Alphabets.");
         
         System.out.println();
         
         System.out.print("Enter Patient Phone Number : ");
         String p = in.next();        
         int count1 = 0;
         for(int i = 0; i < p.length(); i++){    
             if(p.charAt(i) != ' '){ 
                  count1++;
              }    
         } 
         if(count1 != 10) throw new InvalidPhoneNumberException("Length of Phone number is not equals to 10.");
         try{
            Double.parseDouble(p);
          }catch(Exception e){
                throw new InvalidPhoneNumberException("Phone number is not numeric.");
            }
                
         System.out.println();
         
         System.out.print("Enter Patient Age : ");
         int ag = in.nextInt();
         int count2 = 0;
         for(int i = 0; i < ag.length(); i++){    
             if(ag.charAt(i) != ' '){ 
                  count2++;
              }    
         } 
         if(count2 != 2) throw new InvalidPhoneNumberException("Length of Age is not equals to 2 digits.");
         try{
            Double.parseDouble(ag);
          }catch(Exception e){
                throw new InvalidPhoneNumberException("Age is not numeric.");
            }
         
         System.out.println();
         
         Symptoms symp = new Symptoms();
         String sympo = symp.symptoms();
         
         String query = "insert into patient (SN, Aadhar_number, first_name, last_name, Phone_Number, age, Symptoms)"  + " values (?, ?, ?, ?, ?, ?, ?)";
         
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt (1, sn);//sn
         preparedStmt.setString (2, a);
			preparedStmt.setString (3, f);//name
			preparedStmt.setString (4, l);//lastname
		   preparedStmt.setInt(6, ag);//age
         preparedStmt.setString(5, p);//phone
         preparedStmt.setString(7, sympo);//symptom file
		  	preparedStmt.execute();
         
         stmt.execute("SELECT * FROM  patient;");
            
		   ResultSet rs = stmt.getResultSet();
		  	while(rs.next())  
		      	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5) + "  "+rs.getInt(6)+"  "+rs.getString(7));
		   	conn.close();
		   }catch(Exception e){ System.out.println(e);}  
	   }     
}