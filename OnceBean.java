package exTwitter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

//テーブル名onceのデータを１つ格納しておくクラス
public class OnceBean {
	
	//フィールド
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
	
	int getOnceId(){
		return once_id;
	}
	
	String getText(){
		return text;
	}
	
	Calendar getReserveTime(){
		return reserve_time;
	}
	
	int getPosted(){
		return posted;
	}
}
