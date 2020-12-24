package project;

import java.awt.Menu;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;


public class AccCMD  {

	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		home();
	}
		
	public static void home() {		
		AccidentDAOJDBC accidentDAO = new AccidentDAOJDBC(); //要把AccidentDAOJDBC當作一個物件new進來，才能使用他的方法

		while(true){

		System.out.println(
				
				"=================================2019年度台北市交通事故調查系統=================================\n\n"+
				"(1)依照日期查詢-----------\n"+
				"(2)依照地區&日期查詢-----\n"+
				"(3)依照地區查詢-----------\n"+
				"(4)日期&地點模糊搜尋-----------\n"+
				"(5)使用編號查詢-----------\n"+
				"(6)月分/時間/天氣統計資料-----------\n"+
				"(7)年分地區統計資料-------\n"+
				"(8)月分地區統計資料-------\n"+
				"(9)新增資料-----------------\n"+
				"(10)更新資料---------------\n"+
				"(11)刪除資料---------------\n"+
				"(12)查詢代碼---------------\n"+
				"(13)離開程式----------------\n\n"+
				"=================================================================================\n");
		
		
		System.out.println("\n"+"請輸入欲使用的服務：");
		
		int inputNum = input.nextInt();
		
		if(inputNum<0 || inputNum >13) {
			System.out.println("\n"+"輸入數字錯誤，請重新輸入");
			String accDateStr = input.next();
			
		}
		
		//============================================================
		 
		if(inputNum == 1) {//依照日期查詢
				System.out.println("請輸入日期(日期格式 2019/MM/dd)：");
				String accDateStr = input.next();
				
				if(accDateStr.matches("\\d{4}"+"/"+"\\d{2}"+"/"+"\\d{2}")) {
				Date accDate = accidentDAO.strToDate(accDateStr);
				accidentDAO.selectByDate(accDate);
				}else {
					System.out.println("日期格式輸入錯誤");
				}

				System.out.println("請輸入任意數字鍵返回主介面");
				int back = input.nextInt();
				
		} 
		//============================================================		
		if(inputNum == 2){	//依照地區&日期查詢
			
			System.out.println(
					
					"=====台北市各行政區代碼========"+"\n"+"\n"+
					 "地區: 01 大同區 || 03 中山區 || 05 中正區 || 07 信義區 || 09 北投區 || 11南港區\n"+
					 "代碼: 02 萬華區 || 04 大安區 || 06 松山區 || 08 士林區 || 10 文山區 || 12內湖區\n\n");
				
			
			System.out.println("請輸入日期(日期格式 2019/MM/dd)：");
			String accDateStr = input.next();
			
			if(accDateStr.matches("\\d{4}"+"/"+"\\d{2}"+"/"+"\\d{2}")) {

				Date accDate = accidentDAO.strToDate(accDateStr);
				
				System.out.println("請輸入地區代碼(兩位數字，ex:1請輸入01)︰");
				
				String location = input.next();
				
				if(location.matches("\\d{2}")) {
					
					int locationNum =  Integer.valueOf(location);
					if(locationNum <= 12 && locationNum >=1) {
						
					accidentDAO.dateAndLocation(accDate,location);
					
					}else {
						System.out.println("數字不在合理範圍內\n");
						
					}
				}else{
					System.out.println("數字格式輸入錯誤\n");
				
				}
			
			}else {
					System.out.println("日期格式輸入錯誤");
				}
			
			System.out.println("請輸入任意數字鍵返回主介面 ");
			int choice = input.nextInt();
		}
			
			
			
			
		//===========================================================================
				
		 if(inputNum == 3) { //依照地區查詢
				
				System.out.println(
						
					"=====台北市各行政區代碼========"+"\n"+"\n"+
					   "地區: 01 大同區 || 03 中山區 || 05 中正區 || 07 信義區 || 09 北投區 || 11南港區"+"\n"+
					   "代碼: 02 萬華區 || 04 大安區 || 06 松山區 || 08 士林區 || 10 文山區 || 12內湖區"+"\n"+"\n");
				
				
				System.out.println("請輸入地區代碼︰");
				
				String locationNO = input.next();
				
				if(locationNO.matches("\\d{2}")) {
					
					int locationNum =  Integer.valueOf(locationNO);
					if(locationNum <= 12 && locationNum >=1) {
					accidentDAO.selectBylocation(locationNO);
					
					}else {
						System.out.println("數字不在合理範圍內，請重新輸入"+"\n");		
					}
				}else{
					System.out.println("數字格式輸入錯誤！請重新輸入"+"\n");
			 	 }

				System.out.println("請輸入任意數字鍵返回主介面 ");
				int choice = input.nextInt();
			}
	
		 
		//============================================================================
		 if(inputNum == 4){//使用日期&地點模糊搜尋
			 System.out.println("請問知道確切日期嗎?\n\n(1)是，使用日期進行搜尋\n(2)否，使用月份進行搜尋");
			 int choice = input.nextInt();
			 if(choice == 1) {
				 
				 System.out.println("請輸入日期(日期格式 2019/MM/dd)：");
					String accDateStr = input.next();
					
					if(accDateStr.matches("\\d{4}"+"/"+"\\d{2}"+"/"+"\\d{2}")) {
						
						 Date accDate = accidentDAO.strToDate(accDateStr);
						 
						 System.out.println("請輸入部分地址(模糊搜尋)：");
						 String address = input.next();
						 
						 accidentDAO.dateAndAddress(accDate,address);
					}else {
						System.out.println("日期格式輸入錯誤");
					}
				 
			 }else if(choice == 2) {
				 System.out.println("請輸入月份:");
				 int month = input.nextInt();
				 
				 if(month>=1 && month<=12) {
					 
					 System.out.println("請輸入部分地址(模糊搜尋)：");
					 String address = input.next();
					 
					
					 accidentDAO.monthAndAddress(month,address);
				 }else {
					 System.out.println("月份輸入錯誤！");
				 }
				 
				 
				
				 
				 
			 }
			 
			 System.out.println("按任意數字鍵回到主介面");
				int back = input.nextInt();
			 
		 }
			
		//===========================================================================
				
		  if(inputNum == 5){//使用編號查詢
			
			    accidentDAO.selectLastNO();
		
				System.out.println("請輸入案件編號：");
				int accno = input.nextInt();
				
				if(accno > 0) {
					
					accidentDAO.selectByAccNO(accno);
					
				}else {
					System.out.println("請輸入正確的數字");
				}
				
				System.out.println("請輸入 1/返回主介面");
				int choice = input.nextInt();
			}
				
		  

			
	  //===========================================================================
		  if(inputNum == 6){ //月分//天氣//時間統計資料
			 System.out.println("請選擇你想觀看的統計資料\n\n(1)台北市整體各月份統計資料\n(2)天氣統計資料\n(3)時間統計資料\n");
			 int choice = input.nextInt();
			 
			 if(choice == 1) {
					System.out.println("=========2019年度地區統計資料=========\n\n");
					accidentDAO.countMonth();
					
			 }else if(choice == 2){
				    System.out.println("=========2019年度天氣統計資料=========\n\n");
					accidentDAO.countWheater();
					
			 }else if(choice == 3){
				    System.out.println("=========2019年度時間統計資料=========\n\n");
					accidentDAO.countTime();
					
			 }			 
			 System.out.println("\n請輸入任意數字鍵返回主介面");
			 int back = input.nextInt();
		  }
	  //==============================================================================
		  if(inputNum == 7) { //年分地區統計資料
				System.out.println("=========年分地區統計資料=========\n\n");
				accidentDAO. countAcc();
				
				 System.out.println("\n請輸入任意數字鍵返回主介面");
				  int choice = input.nextInt();
		  }
	  //==============================================================================
		  if(inputNum == 8){//月分地區統計資料
			  System.out.println("請選擇你想觀看的月份統計資料");
			  int month = input.nextInt();
			  
			  if(month>=1 && month<=12) {
				  accidentDAO.monthLocation(month);
				 }else {
					 System.out.println("月份輸入錯誤！");
				 }
			  
			  System.out.println("\n請輸入任意數字鍵返回主介面");
			  int choice = input.nextInt();
		  }
	 //==============================================================================
		  inner:if(inputNum == 9) { //新增資料
		
				System.out.println(
				
					"==============新增資料================"+"\n"+"\n"+
					"地區: 01 大同區 || 03 中山區 || 05 中正區 || 07 信義區 || 09 北投區 || 11南港區"+"\n"+
					"代碼: 02 萬華區 || 04 大安區 || 06 松山區 || 08 士林區 || 10 文山區 || 12內湖區"+"\n"+
					"------------------------------------------------------"+"\n");
				
				
				
				System.out.println("使否查詢「天氣編號」或「交通工具編號」? \n(1)查詢天氣編號\n(2)查詢交通工具編號\n(3)不必查詢，直接開始新增\n");
			    int choice = input.nextInt();
			    
				if(choice == 1) { //查詢天氣編號
					System.out.println("=========天氣代碼=========");
					accidentDAO.getWheaterTable();
					
				}else if(choice == 2) { //查詢交通工具編號
					System.out.println("================交通工具代碼===================");
					accidentDAO.getCarTable();
					
				}else if(choice == 3){ //直接新增
					
					accidentDAO.selectLastNO();
					
				
				    System.out.println("請輸入案件編號");
					int accNO = input.nextInt();
					
					List<Accident> accidents = accidentDAO.listAccidents();
					for(Accident acc :accidents) {
						  if(acc.getAccNO()==(accNO)){
							  System.out.println("此編號已存在，請重新輸入！");
							  System.out.println("\n請輸入任意數字鍵返回主介面");
							  int back = input.nextInt();	
							  break inner;
						  }
					}
					
					
					System.out.println("請輸入案發月份");
					int month = input.nextInt();
					if(month>12 || month<0) {
						System.out.println("月份格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();	
						break inner;
						
					}
					
					System.out.println("請輸入案時間(小時)");
					int time = input.nextInt();
					if(time>24 || time<0) {
						System.out.println("時間格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();		
						break inner;
					}
					
					System.out.println("請輸入案發日期 (日期格式 :yyyy/MM/dd)");
					String accDateStr = input.next();
					if(!accDateStr.matches("\\d{4}"+"/"+"\\d{2}"+"/"+"\\d{2}")) {
						System.out.println("日期格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();		
						break inner;
					}
					
					Date accDate = accidentDAO.strToDate(accDateStr);
					
					System.out.println("請輸入案發時間 (時間格式:  HH:mm:ss)");
					 String accTimeStr = input.next();
					 if(!accTimeStr.matches("\\d{2}"+":"+"\\d{2}"+":"+"\\d{2}")) {
							System.out.println("時間格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
						}
					 
					 Time accTime =accidentDAO.strToTime(accTimeStr);
					
					System.out.println("請輸入案發地區(請填寫地區代碼 + 地區)");
					String location = input.next();
					if(!location.matches("\\d{2}"+"\\s{3}")) {
						System.out.println("地區格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();		
						break inner;
						
					}
					
					System.out.println("請輸入案發地址");
					String address = input.next();
					
					System.out.println("請輸入當事人交通工具(請依照交通工具代碼填寫)");
					String car = input.next();
					if(!car.matches("\\s{1}"+"\\d{2}")) {
						System.out.println("代碼格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();		
						break inner;
						
					}
					
		
					System.out.println("請輸入案發當時天氣(請依照天氣代碼填寫)");
					int wheater = input.nextInt();
					if(wheater>8){
						System.out.println("代碼格式輸入錯誤，請重新輸入");
						System.out.println("\n請輸入任意數字鍵返回主介面");
						int back = input.nextInt();		
						break inner;
						
					}
					
					System.out.println("請輸入受傷人數");
					int injured = input.nextInt();
									
					
					System.out.println("請輸入死亡人數");
					int dead = input.nextInt();	
					
					Accident accident = new Accident(accNO, month, time, accDate, accTime, location, address, car, wheater, injured, dead); 
					accidentDAO.add(accident);		
					
					}
				
				
				 
				System.out.println("\n請輸入任意數字鍵返回主介面");
				int back = input.nextInt();		
				
		  }
		  
		//==============================================================================
		  inner:if(inputNum == 10) { //更新資料
			  
			  List<Accident> accidents = accidentDAO.listAccidents();
			  
			  System.out.println("請輸入要更新的案件編號：");
			  int accno = input.nextInt();
			  
			  accidentDAO.selectByAccNO(accno);
			  System.out.println("確定更新該筆資料嗎？\n(1)是\n(2)否，返回主頁\n");
			  int choice = input.nextInt();
			  
			  inside:if(choice == 1) {
				  
				  
				  for(Accident acc :accidents) {
					  if(acc.getAccNO()==(accno)){
						  
						System.out.println(
								
							"==================更新資料================\n\n"+
							"地區: 01 大同區 || 03 中山區 || 05 中正區 || 07 信義區 || 09 北投區 || 11南港區\n"+
							"代碼: 02 萬華區 || 04 大安區 || 06 松山區 || 08 士林區 || 10 文山區 || 12內湖區\n"+
							"------------------------------------------------------\n");
						
						
						System.out.println("案件編號 : "+acc.getAccNO());
						
						System.out.println("請輸入案發月份");
						int month = input.nextInt();
						if(month>12 || month<0) {
							System.out.println("月份格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();	
							break inner;
							
						}
						
						System.out.println("請輸入案時間(小時)");
						int time = input.nextInt();
						if(time>24 || time<0) {
							System.out.println("時間格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
						}
						
						System.out.println("請輸入案發日期 (日期格式 :yyyy/MM/dd)");
						String accDateStr = input.next();
						if(!accDateStr.matches("\\d{4}"+"/"+"\\d{2}"+"/"+"\\d{2}")) {
							System.out.println("日期格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
						}
						
						Date accDate = accidentDAO.strToDate(accDateStr);
						
						System.out.println("請輸入案發時間 (時間格式:  HH:mm:ss)");
						 String accTimeStr = input.next();
						 if(!accTimeStr.matches("\\d{2}"+":"+"\\d{2}"+":"+"\\d{2}")) {
								System.out.println("時間格式輸入錯誤，請重新輸入");
								System.out.println("\n請輸入任意數字鍵返回主介面");
								int back = input.nextInt();		
								break inner;
							}
						 
						 Time accTime =accidentDAO.strToTime(accTimeStr);
						
						System.out.println("請輸入案發地區(請填寫地區代碼 + 地區)");
						String location = input.next();
						if(!location.matches("\\d{2}"+"\\s{3}")) {
							System.out.println("地區格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
							
						}
						
						System.out.println("請輸入案發地址");
						String address = input.next();
						
						System.out.println("請輸入當事人交通工具(請依照交通工具代碼填寫)");
						String car = input.next();
						if(!car.matches("\\s{1}"+"\\d{2}")) {
							System.out.println("代碼格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
							
						}
						
			
						System.out.println("請輸入案發當時天氣(請依照天氣代碼填寫)");
						int wheater = input.nextInt();
						if(wheater>8){
							System.out.println("代碼格式輸入錯誤，請重新輸入");
							System.out.println("\n請輸入任意數字鍵返回主介面");
							int back = input.nextInt();		
							break inner;
							
						}
						
						System.out.println("請輸入受傷人數");
						int injured = input.nextInt();
										
						
						System.out.println("請輸入死亡人數");
						int dead = input.nextInt();	
						
						Accident accident = new Accident(acc.getAccNO(), month, time, accDate, accTime, location, address, car, wheater, injured, dead);
						accidentDAO.update(accident);
						
						System.out.println("請輸入任意數字返回主介面");
						int num = input.nextInt();

					  }
				  }
				  
			  }else if(choice == 2) {
				  break inner;	
			  }
 
			  
		  }
		//==============================================================================
		  inner:if(inputNum == 11) { //刪除資料
			  accidentDAO.selectLastNO();
			  System.out.println("\n請輸入要刪除的案件編號：");
			  int accno = input.nextInt();
			  System.out.println("該編號目前資訊：");
			  accidentDAO.selectByAccNO(accno);
			  System.out.println("確定刪除該筆資料嗎？\n(1)是\n(2)否，返回主頁\n");
			  int choice = input.nextInt();
			  if(choice == 1) {
				  accidentDAO.delete(accno);
			  }else if(choice == 2) {
				 break inner;
			  }
			  
			  System.out.println("請輸入任意數字返回主介面");
				int num = input.nextInt();	
	
		  }
		//==============================================================================
		  if(inputNum == 12) { //查詢代碼
			  System.out.println("(1) 查詢交通工具代碼\n(2) 查詢天氣代碼\n\n請輸入代碼：");
			  
			int choice = input.nextInt();
			if( choice == 1 ) {
				System.out.println("================交通工具代碼===================");
				accidentDAO.getCarTable();
				
			}else if(choice == 2) {
				System.out.println("=========天氣代碼=========");
				accidentDAO.getWheaterTable();
				}
			
				System.out.println("請輸入 1/返回主介面");
				int num = input.nextInt();
			
		  }
		  //==============================================================================
		  if(inputNum == 13) { //離開程式
				System.out.println("\n"+"確定要離開程式嗎？\n(1)是\n(2)否\n");
				int choice = input.nextInt();
				if( choice == 1 ) {
					
				}else if(choice == 2) {
					home();
				}
		//==================================================================================
				  break;
		  }
		  
		
		  
		}
	}
		
	}
	



