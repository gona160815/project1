package project;

import java.io.*;
import java.sql.*;


public class AccEx {	
	public static void main(String[] args) {
		try {Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1",
             "project", "project");
			 System.out.println("與資料庫連線");
			 
			 
			 Statement statement = conn.createStatement(); 
	           ResultSet rs = statement.executeQuery("select * from accident order by 1 desc");
	           
	           BufferedWriter output = new BufferedWriter
	                   (new OutputStreamWriter(new FileOutputStream("D://ACCIDENT_output.csv"), "Big5"));

	           output.write("ACCNO,MONTH,TIME,ACCDATE,ACCTIME,LOCATION,ADDRESS,CAR,WHEATER,DEAD,INJURED");
	           
	           while (rs.next()) {
	                int ACCNO = rs.getInt("ACCNO");
	                int MONTH = rs.getInt("MONTH");
	                int TIME = rs.getInt("TIME");
	                Date ACCDATE = rs.getDate("ACCDATE");
	                Time ACCTIME = rs.getTime("ACCTIME");
	                String LOCATION = rs.getString("LOCATION");
	                String ADDRESS = rs.getString("ADDRESS");
	                String CAR = rs.getString("CAR");
	                int WHEATER = rs.getInt("WHEATER");
	                int DEAD = rs.getInt("DEAD");
	                int INJURED = rs.getInt("INJURED");
	                
	    
	                String result = String.format("%d,%d,%d,%s,%s,%s,%s,%s,%d,%d,%d",
	                		ACCNO,MONTH,TIME,ACCDATE,ACCTIME,LOCATION,ADDRESS,CAR,WHEATER,DEAD,INJURED);
	                
	                output.newLine();
	                output.write( result);            
	            }
	           		statement.close();
	           		output.close();
	             
	        } catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}catch (IOException e) {					
				
				e.printStackTrace();
			}catch (SQLException e){
				
				e.printStackTrace();
			}
			
		}
	
}
	  