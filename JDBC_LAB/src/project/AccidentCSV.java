package project;

import java.io.*;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AccidentCSV {
		public static void main(String[] args) {
			try {Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1",
	             "project", "project");
			System.out.println("與資料庫連線");
			
			FileInputStream fr = new FileInputStream("C:\\Users\\user\\Desktop\\Exam01\\Accident.csv.csv"); //讀取csv檔
			BufferedReader lineReader = new BufferedReader(new InputStreamReader(fr, "Big5")); //把fr的資料搬過去
			
			PreparedStatement pstmt = conn.prepareStatement("Insert into Accident(ACCNO,MONTH,TIME,ACCDATE,ACCTIME,LOCATION,ADDRESS,CAR,WHEATER,DEAD,INJURED) values(?,?,?,?,?,?,?,?,?,?,?)"
					);
			
			String result = "";
			int lineNumberHdr=0;
			
			while((result = lineReader.readLine())!=null) {
				
				lineNumberHdr++;
				if(lineNumberHdr==1)continue;
				String[] data = result.split(",");
				
				
				pstmt.setInt(1, Integer.valueOf(data[0])); //ACCNO
				pstmt.setInt(2, Integer.valueOf(data[1])); //MONTH
				pstmt.setInt(3, Integer.valueOf(data[3]));//TIME
				 
				Date sqlDate=strToDate(data[5]);
				pstmt.setDate(4,sqlDate);//ACCDATE
				
				Time sqlTime=strToTime(data[6]);
				pstmt.setTime(5,sqlTime);//ACCTIME
				
				
				//Timestamp sqlDate = Timestamp.valueOf(data[5]);
				
				
				//Timestamp sqlTime =Timestamp.valueOf(data[6]);
				
				
				pstmt.setString(6,data[7]);	//LOCATION
				pstmt.setString(7,data[8]);//ADDRESS
				pstmt.setString(8,data[9]);//CAR
				pstmt.setString(9,data[10]);//WHEATER
				pstmt.setInt(10, Integer.valueOf(data[11]));//DEAD
				pstmt.setInt(11, Integer.valueOf(data[12]));//INJURED
				
				pstmt.execute();
				
			};
			
			}
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}catch (IOException e) {					
				
				e.printStackTrace();
			}catch (SQLException e){
				
				e.printStackTrace();
			}
			
			}
		
	

	public static java.sql.Date strToDate(String strDate) {
    String str = strDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date d = null;
    try {
        d = format.parse(str);
    } catch (Exception e) {
        e.printStackTrace();
    }
    java.sql.Date date = new java.sql.Date(d.getTime());
    return date;
	}
	
	
	
    public static java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }
}

