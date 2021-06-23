package com.ncu.methods;
import com.ncu.exceptions.*;
import java.sql.*; 
import java.lang.*;
import java.util.Scanner;
class Update{
   public static void main(String args[]){
      Scanner sc = new Scanner(System.in);
      try{
         Connection conn = null;
         Statement stmt = null;
         String userName = "root";
         String password = "";
         String url = "jdbc:mysql://localhost:3306/Arogya";
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         conn =DriverManager.getConnection(url,userName,password);
         System.out.println("Database connection established.");
         stmt = conn.createStatement();
         String CREATE_TABLE_SQL="CREATE TABLE patient (SN INT, Aadhar_number VARCHAR(45), First_Name VARCHAR(45), Last_Name VARCHAR(45),Phone_Number VARCHAR(45), Age INT, Symptoms VARCHAR(45));";
         String query = "insert into patient (SN, Aadhar_number, first_name, last_name, Phone_Number, age, Symptoms)"  + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt (1, 1);//uid
         preparedStmt.setString (2, "1234567891234567");
			preparedStmt.setString (3, "Ram");//name
			preparedStmt.setString (4, "Gopal");//lastname
         preparedStmt.setString (5, "8448172782");
			preparedStmt.setInt(6, 26);//age
         preparedStmt.setString (7, "Dry Cough");
			preparedStmt.execute();
			preparedStmt.setInt (1, 2);//uid
         preparedStmt.setString (2, "1234567891234568");
			preparedStmt.setString (3, "Sanju");//name
			preparedStmt.setString (4, "Samson");//lastname
         preparedStmt.setString (5, "8448172782");
			preparedStmt.setInt(6, 29);//age
         preparedStmt.setString (7, "Dry Cough, Vomit");
			preparedStmt.execute();
         PreparedStatement ps = null;
         int sel = 0;
         int exp = 0;
         do{
            System.out.println("Select which field you want to update: ");
            System.out.println("1  =  Aadhar Number");
            System.out.println("2  =  First Name");
            System.out.println("3  =  Last Name");
            System.out.println("4  =  Phone Number");
            System.out.println("5  =  Age");
            System.out.println("6  =  Symptoms");
            System.out.print("Enter number corresponding to your selection: ");
            sel = sc.nextInt();
            if(sel == 2){
               String query1 = "update patient set First_Name=? where Aadhar_number=? ";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Aadhar number :  ");
                  String aa = sc.next();
                  System.out.print("Enter correct First Name :  ");
                  String fn = sc.next();
                  boolean b = ((!fn.equals("")) && (fn != null) && (fn.matches("^[a-zA-Z]*$"))); 
                  if(b == false) throw new InvalidNameException("First name contains characters other then Alphabets."); 
                  int count = 0;       
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setString(1, fn);
                     ps.setString(2, aa);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else if(sel == 3){
               String query1 = "update patient set Last_Name=? where Aadhar_number=? ";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Aadhar number :  ");
                  String aa = sc.next();
                  System.out.print("Enter correct Last Name :  ");
                  String ln = sc.next();
                  boolean b = ((!ln.equals("")) && (ln != null) && (ln.matches("^[a-zA-Z]*$"))); 
                  if(b == false) throw new InvalidNameException("Last name contains characters other then Alphabets."); 
                  int count = 0;       
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setString(1, ln);
                     ps.setString(2, aa);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else if(sel ==4){
               String query1 = "update patient set Phone_Number=? where Aadhar_number=? ";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Aadhar number :  ");
                  String aa = sc.next();
                  System.out.print("Enter correct Phone Number :  ");
                  String pn = sc.next();
                  int count = 0; 
                  int count1 = 0;
                  for(int i = 0; i < pn.length(); i++){    
                     if(pn.charAt(i) != ' '){ 
                        count1++;
                     }    
                  } 
                  if(count1 != 10) throw new InvalidPhoneNumberException("Length of Phone number is not equals to 10.");
                  try{
                     Double.parseDouble(pn);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidPhoneNumberException("Phone number is not numeric.");
                  }      
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setString(1, pn);
                     ps.setString(2, aa);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else if(sel == 5){
               String query1 = "update patient set Age=? where Aadhar_number=? ";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Aadhar number :  ");
                  String aa = sc.next();
                  System.out.print("Enter correct Age :  ");
                  int age = sc.nextInt();
                  int count = 0;       
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setInt(1, age);
                     ps.setString(2, aa);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else if(sel == 6){
               String query1 = "update patient set Symptoms=? where Aadhar_number=? ";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Aadhar number :  ");
                  String aa = sc.next();
                  System.out.print("Enter symptoms separated by comma :  ");
                  sc.nextLine();
                  String symp = sc.nextLine();
                  int count = 0;       
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setString(1, symp);
                     ps.setString(2, aa);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else if(sel == 1){
               String query1 = "update patient set Aadhar_number=? where Phone_number=? and First_Name=?";
               ps = conn.prepareStatement(query1);
               try{
                  System.out.print("Enter Phone number :  ");
                  String pn = sc.next();
                  System.out.print("Enter First Name :  ");
                  String fn = sc.next();
                  System.out.print("Enter correct Aadhar Number :  ");
                  String aa = sc.next();
                  int count = 0; 
                  int count1 = 0;
                  boolean b = ((!fn.equals("")) && (fn != null) && (fn.matches("^[a-zA-Z]*$"))); 
                  if(b == false) throw new InvalidNameException("First name contains characters other then Alphabets."); 
                  for(int i = 0; i < pn.length(); i++){    
                     if(pn.charAt(i) != ' '){ 
                        count1++;
                     }    
                  } 
                  if(count1 != 10) throw new InvalidPhoneNumberException("Length of Phone number is not equals to 10.");
                  try{
                     Double.parseDouble(pn);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidPhoneNumberException("Phone number is not numeric.");
                  }      
                  for(int i = 0; i < aa.length(); i++){    
                     if(aa.charAt(i) != ' '){ 
                        count++;
                     }    
                  } 
                  if(count != 16) throw new InvalidAadharException("Length of Aadhar number is not equals to 16.");
                  try{
                     Double.parseDouble(aa);
                  }catch(Exception e){
                     exp = 1;
                     throw new InvalidAadharException("Aadhar number is not numeric.");
                  }
                  if(exp == 0){
                     ps.setString(1, aa);
                     ps.setString(2, pn);
                     ps.setString(3, fn);
                  }
               }catch(Exception e){
                  System.out.println(e);
                  exp = 1;
               }
            }
            else System.out.println("Incorrect selection. Select again.");
         }while(sel<1 || sel>6);
         if(exp == 0){
            ps.executeUpdate();
         }
			ResultSet rs = stmt.executeQuery("SELECT * FROM patient;");
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getInt(6)+"  "+rs.getString(7));
			conn.close();
      }catch(Exception e){System.out.println(e);}
   }
}