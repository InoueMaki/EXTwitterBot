package exTwitter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OnceBean {
	
	private int once_id;
	private String text;
	private Calendar reserve_time;
	private int posted;
	
	void setOnceId(int id){
		this.once_id = id;
	}
	
	void setText(String tw){
		this.text = tw;
	}
	
	void setReserveTime(int year , int month , int date, int hour , int minute){
		this.reserve_time = Calendar.getInstance();
		this.reserve_time.set(year , month , date , hour , minute);
		
	}
	
	void setReserveTime(Timestamp timestamp){
		this.reserve_time = Calendar.getInstance();
		this.reserve_time.setTimeInMillis(timestamp.getTime());
	}
	
	void setPosted(int posted){
		this.posted = posted;
	}
	
	public int getOnceId(){
		return once_id;
	}
	
	public String getText(){
		return text;
	}
	
	public Calendar getReserveTime(){
		return reserve_time;
	}
	
	public String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reserve_time.getTime());
	}
	
	public String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(reserve_time.getTime());
	}
	
	public int getPosted(){
		return posted;
	}
}
