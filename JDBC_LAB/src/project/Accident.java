package project;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.management.loading.PrivateMLet;

import oracle.net.jdbc.TNSAddress.Address;

public class Accident {
	
	private int accNO;
	
	private int month;
	private int time;
	private Date accDate;
	private Time accTime;
	private String location;
	private String address;
	private String car;
	private int wheater;
	private int injured;
	private int dead;
	
	
	public int getAccNO() {
		return accNO;
	}
	public void setAccNO(int accNO) {
		this.accNO = accNO;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public int getWheater() {
		return wheater;
	}
	public void setWheater(int wheater) {
		this.wheater = wheater;
	}
	public int getInjured() {
		return injured;
	}
	public void setInjured(int injured) {
		this.injured = injured;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	public Time getAccTime() {
		return accTime;
	}
	public void setAccTime(Time accTime) {
		this.accTime = accTime;
	}
	
	public Accident() {
		
	}
	
	public Accident(int accNO,int month,int time,Date accDate,Time accTime,
			String location,String address,String car,int wheater,int injured,int dead)
	{
		this.accNO=accNO;
		this.month=month;
		this.time=time;
		this.accDate=accDate;
		this.accTime=accTime;
		this.location=location;
		this.address=address;
		this.car=car;
		this.wheater=wheater;
		this.injured=injured;
		this.dead=dead;
		
	}
	
	
	
	
	
	

}
