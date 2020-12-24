package project;

import java.sql.Date;
import java.util.List;

import orm.Employee;

public interface AccidentDAO {
	
	//List<Accident>
	public List<Accident> listAccidents();
	
	//1.查詢個別資料
	public void selectByAccNO(int accno);
	
	//2.依照地區查詢
	public void selectBylocation(String locationNO);
	
	//2.更新
	public void update(Accident accident);
	
	//3.刪除
	public void delete(int accno);
	
	//4.增加
	public void add(Accident acc);
	
	//使用日期搜尋
	public void selectByDate(Date accDate);
	
	//使用日期跟地點搜尋
	public void dateAndLocation(Date accDate,String location);
	
	//使用日期做地址模糊搜尋
	public void dateAndAddress(Date date,String address);
	
	//使用月份做地址模糊搜尋
	public void monthAndAddress(int month,String address);
	
	//顯示目前最大資料
	public void selectLastNO();
	
	//年度天氣統計資料
	public void countWheater();
	
	//年度時間統計資料
	public void countTime();
	
	//年度月分統計資料
	public void countMonth();
	
	//年度車禍統計資料
	public void countAcc();
	
	//地區月份統計資料
	public void monthLocation(int month);
	
	//查詢交通代碼
	public void getCarTable();
	
	//查詢天氣代碼
	public void getWheaterTable();
	
	//轉換字串格式到日期
	public java.sql.Date strToDate(String strDate);
	
	//轉換字串格式到時間
	 public java.sql.Time strToTime(String strDate);


}
