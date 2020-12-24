package project;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class AccidentDAOJDBC implements AccidentDAO{
		
	//創造連線=======================================================================
		
		public DataSource dataSource;
		
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		public DataSource getDataSource() {
			if(dataSource==null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("project");
			ds.setPassword("project");
			ds.setMaxTotal(50);
			ds.setMaxIdle(50);
			
			dataSource = ds;
			}
			return dataSource;			
		}
	//依照日期查詢=======================================================================
		
		@Override
		public void selectByDate(Date accDate) {
			try(Connection conn = getDataSource().getConnection();)
			
			{
				PreparedStatement pstmt = conn.prepareStatement(
				"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a, car c , wheater w where a.car = c.car and a.wheater = w.wheater and accdate = ?");
			pstmt.setDate(1,accDate);
			int result = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {

				System.out.println(
						
					"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
					"案件日期："+rs.getDate("ACCDATE")+"\n"+
					"案件時間："+rs.getTime("ACCTIME")+"\n"+
				    "案件地點："+rs.getString("ADDRESS")+"\n"+
					"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
				    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
					"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
					"當事人交通工具："+rs.getString("CARSTR")+"\n\n"+
					"==================================================== ");
				
		
		}
		
			if(result == 0) {
				System.out.println("查無資料");
			};
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			
		}
	}
	//依照日期與地區查詢=================================================================
		
		@Override
		public void dateAndLocation(Date accDate,String location) {
			try(Connection conn = getDataSource().getConnection();)
			
			{
				PreparedStatement pstmt = conn.prepareStatement(
				"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a, car c , wheater w where a.car = c.car and a.wheater = w.wheater and accdate = ? and a.location like ?");
			pstmt.setDate(1,accDate);
			pstmt.setString(2,location+"%");
			int result = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {

				System.out.println(
						
					"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
					"案件日期："+rs.getDate("ACCDATE")+"\n"+
					"案件時間："+rs.getTime("ACCTIME")+"\n"+
				    "案件地點："+rs.getString("ADDRESS")+"\n"+
					"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
				    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
					"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
					"當事人交通工具："+rs.getString("CARSTR")+"\n\n"+
					"==================================================== ");
				
		
		}

			if(result == 0) {
				System.out.println("查無資料");
			};
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			
		}
	}
		
	//依照地區查詢=======================================================================
		
		@Override
		public void selectBylocation(String locationNO) {
			try(Connection conn = getDataSource().getConnection();)
			
			{
				PreparedStatement pstmt = conn.prepareStatement(
				"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a, car c , wheater w where a.car = c.car and a.wheater = w.wheater and a.location like ?");
			pstmt.setString(1,locationNO+"%");
			int result = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {

				System.out.println(
						
					"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
					"案件日期："+rs.getDate("ACCDATE")+"\n"+
					"案件時間："+rs.getTime("ACCTIME")+"\n"+
				    "案件地點："+rs.getString("ADDRESS")+"\n"+
					"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
				    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
					"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
					"當事人交通工具："+rs.getString("CARSTR")+"\n\n"+
					"==================================================== ");
				
		
		}
			if(result == 0) {
				System.out.println("查無資料");
			};
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			
		}
	}
	//使用日期&地點模糊搜尋============================================================
		
		@Override
		public void dateAndAddress(Date accDate,String address) {
			try(Connection conn = getDataSource().getConnection();)
			
			{
				PreparedStatement pstmt = conn.prepareStatement(
						"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a, car c , wheater w where a.car = c.car and a.wheater = w.wheater and accdate = ? and a.address like ?");
				pstmt.setDate(1,accDate);
				pstmt.setString(2,"%"+address+"%");
				int result = pstmt.executeUpdate();
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {

					System.out.println(
							
						"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
						"案件日期："+rs.getDate("ACCDATE")+"\n"+
						"案件時間："+rs.getTime("ACCTIME")+"\n"+
					    "案件地點："+rs.getString("ADDRESS")+"\n"+
						"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
					    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
						"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
						"當事人交通工具："+rs.getString("CARSTR")+"\n\n"+
						"==================================================== ");
					
			
			}
				if(result == 0) {
					System.out.println("查無資料");
				};
				
				}catch (SQLException e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				
			}
		}
		
	//使用月份&地點模糊搜尋==============================================================
		
		@Override
		public void monthAndAddress(int month,String address) {
			try(Connection conn = getDataSource().getConnection();)
			
			{
				PreparedStatement pstmt = conn.prepareStatement(
						"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a, car c , wheater w where a.car = c.car and a.wheater = w.wheater and month = ? and a.address like ?");
				pstmt.setInt(1,month);
				pstmt.setString(2,"%"+address+"%");
				int result = pstmt.executeUpdate();
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {

					System.out.println(
							
						"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
						"案件日期："+rs.getDate("ACCDATE")+"\n"+
						"案件時間："+rs.getTime("ACCTIME")+"\n"+
					    "案件地點："+rs.getString("ADDRESS")+"\n"+
						"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
					    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
						"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
						"當事人交通工具："+rs.getString("CARSTR")+"\n\n"+
						"==================================================== ");
					
			
			}
				if(result == 0) {
					System.out.println("查無資料");
				};
				
				}catch (SQLException e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				
			}
		}
		
	//個別編號查詢=======================================================================
		
		@Override
		public void selectByAccNO(int accno){
			try(Connection conn = getDataSource().getConnection();)
			{
			PreparedStatement pstmt = conn.prepareStatement(
				"select a.accno,a.accdate,a.acctime,a.address,a.injured,a.dead,c.carstr,w.wheaterstr from accident a , car c ,wheater w where c.car = a.car and a.wheater = w.wheater and a.accno = ?" );
			pstmt.setInt(1,accno);
			int result = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				System.out.println(
						
						"\n"+"案件編號："+rs.getInt("ACCNO")+"\n"+
						"案件日期："+rs.getDate("ACCDATE")+"\n"+
						"案件時間："+rs.getTime("ACCTIME")+"\n"+
					    "案件地點："+rs.getString("ADDRESS")+"\n"+
						"受傷人數："+rs.getInt("INJURED")+"人"+"\n"+
					    "死亡人數："+rs.getInt("DEAD")+"人"+"\n"+
						"當時天氣："+rs.getString("WHEATERSTR")+"\n"+
						"當事人交通工具："+rs.getString("CARSTR")+"\n"+"\n"+
						"==================================================== ");
			
			} 
				if(result == 0) {
					System.out.println("查無資料");
				};
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
	//查詢目前編號的最大值
	//=========================================================================================
		
		@Override
		public void selectLastNO() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select accno from Accident where ROWNUM = 1 order by accno desc");
				
					while(rs.next()) {
						System.out.println("目前事故編號最大值為：" + rs.getInt("ACCNO"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
		
		
	//ACCList
	//=========================================================================================
	@Override 
	public List<Accident> listAccidents(){
		
		List<Accident> acclist = new ArrayList<>();
		try(Connection conn = getDataSource().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
			   "select * from accident ");
		){
			while(rs.next()) {
				Accident acc = new Accident();
				acc.setAccNO(rs.getInt("ACCNO"));
				acc.setMonth(rs.getInt("MONTH"));
				acc.setTime(rs.getInt("TIME"));
				acc.setAccDate(rs.getDate("ACCDATE"));
				acc.setAccTime(rs.getTime("ACCTIME"));
				acc.setLocation(rs.getString("LOCATION"));
				acc.setAddress(rs.getString("ADDRESS"));
				acc.setInjured(rs.getInt("INJURED"));
				acc.setDead(rs.getInt("DEAD"));
				acc.setCar(rs.getString("CAR"));
				acc.setWheater(rs.getInt("WHEATER"));
				acclist.add(acc);
				
		}
	}catch(SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
		return acclist;
	}	
	
	//更新資料
	//=========================================================================================
		@Override 
		public void update(Accident acc) {
			try(Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
					"update Accident set month=?,time=?,accDate=?,accTime=?,location=?,address=?,car=?,wheater=?,injured=?,dead=? where accNO=?")
		){ 
				pstmt.setInt(1,acc.getMonth());
				pstmt.setInt(2,acc.getTime());
				pstmt.setDate(3,acc.getAccDate());
				pstmt.setTime(4, acc.getAccTime());
				pstmt.setString(5,acc.getLocation());
				pstmt.setString(6,acc.getAddress());
				pstmt.setString(7,acc.getCar());
				pstmt.setInt(8,acc.getWheater());
				pstmt.setInt(9,acc.getInjured());
				pstmt.setInt(10,acc.getDead());
				pstmt.setInt(11,acc.getAccNO());
				
				int result = pstmt.executeUpdate();
				if(result == 0) {
					throw new RuntimeException("更新失敗");
				}else {
					System.out.println("成功更新"+result+"筆資料");
				}		
			
		}catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
		
		
	//新增一筆資料
	//=========================================================================================
		@Override 
		public void add(Accident acc) {
			try(Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into Accident(accNO,month,time,accDate,accTime,location,address,car,wheater,injured,dead)values(?,?,?,?,?,?,?,?,?,?,?)")
		){ 
				pstmt.setInt(1,acc.getAccNO());
				pstmt.setInt(2,acc.getMonth());
				pstmt.setInt(3,acc.getTime());
				pstmt.setDate(4,acc.getAccDate());
				pstmt.setTime(5, acc.getAccTime());
				pstmt.setString(6,acc.getLocation());
				pstmt.setString(7,acc.getAddress());
				pstmt.setString(8,acc.getCar());
				pstmt.setInt(9,acc.getWheater());
				pstmt.setInt(10,acc.getInjured());
				pstmt.setInt(11,acc.getDead());
				
				int result = pstmt.executeUpdate();
				
				if(result == 0) {
					throw new RuntimeException("更新失敗");
				}else {
					System.out.println("成功新增"+result+"筆資料");
				}	
				
			
		}catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
		
	//刪除一筆資料
	//==============================================================================
		@Override
		public void delete(int accno) {
			try(Connection conn = getDataSource().getConnection();)	
				{
					PreparedStatement pstmt = conn.prepareStatement(
					"delete from accident where accno = ?" );
				pstmt.setInt(1,accno);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("刪除資料成功");
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
	//台北市年度月份統計資料
	//==================================================================================================	
		@Override
		public void countMonth() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select month,count(month) as count from (select month,location,address,accdate,acctime from accident group by month,location,address,accdate,acctime ) group by month order by count desc");
				
					while(rs.next()) {
						System.out.println(
								rs.getString("month")+"月　車禍總計："+rs.getInt("count"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
	//台北市天氣統計資料
	//==================================================================================================
		@Override
		public void countWheater() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select wheaterstr,count(wheater) as count from (select a.location,a.address,a.accdate,a.acctime,w.wheater,w.wheaterstr from accident a,wheater w where a.wheater = w.wheater group by a.location,a.address,a.accdate,a.acctime ,w.wheater,w.wheaterstr ) group by wheaterstr order by count desc");
				
				while(rs.next()) {
					System.out.println(
							rs.getString("WHEATERSTR")+"  車禍總計："+rs.getInt("COUNT"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
		
	//年度時間統計資料
	//==================================================================================================
		@Override
		public void countTime() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select time,count(time) as count from (select time,address,accdate,acctime from accident group by time,address,accdate,acctime) group by time order by count desc");
				
				while(rs.next()) {
					System.out.println(
							rs.getInt("time")+"點  車禍總計："+rs.getInt("COUNT"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
		
	//各地區月份統計資料
	//==================================================================================================
		@Override
		public void monthLocation(int month) {
			try(Connection conn = getDataSource().getConnection();)	
			{
				PreparedStatement pstmt = conn.prepareStatement(
				"select month ,location ,count(location) as count from (select month,location,address,accdate,acctime from accident group by month,location,address,accdate,acctime) group by month ,location having  month = ? order by count desc" );
				
				pstmt.setInt(1,month);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(							
 							rs.getInt("month")+"月　"+rs.getString("location")+"　車禍總計：　"+rs.getInt("count"));
			}
				
		  }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);	
	   }
   }
		
	//年度車禍統計資料
	//===================================================================================================
		@Override
		public void countAcc() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select location ,count(location) as count from (select month,location,address,accdate,acctime from accident group by month,location,address,accdate,acctime ) group by location order by count desc ");
				
					while(rs.next()) {
						System.out.println(
								rs.getString("location")+"　車禍總計：　"+rs.getInt("count"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
		
	//查詢車輛代碼
	//========================================================================================
		@Override
		public void getCarTable() {
			try(Connection conn = getDataSource().getConnection();)
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Car  order by car");
				
					while(rs.next()) {
						System.out.println(
								rs.getString("CAR")+" "+rs.getString("CARSTR"));
				}
					rs.close();
					stmt.close();
			
			}catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);	
		}
	}
		
		
	//查詢天氣代碼
	//=======================================================================================
			@Override
			public void getWheaterTable() {
				try(Connection conn = getDataSource().getConnection();)
				{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from Wheater  order by wheater");
					
						while(rs.next()) {
							System.out.println(
									rs.getString("WHEATER")+" "+rs.getString("WHEATERSTR"));
					}
						rs.close();
						stmt.close();
				
				}catch (SQLException e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);	
			}
		}
		
	//轉換字串格式到日期
	//=======================================================================================
	@Override
		public java.sql.Date strToDate(String strDate) {
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
	
	//轉換字串格式到時間
	//============================================================================		
	@Override
	 public  java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time;
    }
	
	
}	
		



