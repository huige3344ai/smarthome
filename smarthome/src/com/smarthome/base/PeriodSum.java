package com.smarthome.base;

import java.util.Calendar;
import java.util.Date;

import com.smarthome.util.Constans;

public class PeriodSum {
	public static Short getPeriod(Date birthday,int age){
		Short period = 0;
		Calendar now = Calendar.getInstance();
		Calendar born = Calendar.getInstance();
		now.setTime(new Date());
		born.setTime(birthday);
		int nowMonth = now.get(Calendar.MONTH)+1;
		int bornMonth = born.get(Calendar.MONTH)+1;
		int nowDay = now.get(Calendar.DATE);
		int bornDay= now.get(Calendar.DATE);
		
		int monthNum = nowMonth-bornMonth;
		int dayNum = nowDay-bornDay;
		
		if(age<=1){
			if(monthNum==0){//新生儿
				period=Constans.ONE;
			}else if(monthNum==1){
				if(dayNum<=14){//0.12(约4周大)
					period=Constans.TWO;
				}//0.24(约6周大)
			}else if(1<monthNum&&monthNum<4){//0.33(约四个月大)
				period=Constans.THREE;
			}else if(4<=monthNum&&monthNum<6){//0.55(约6个月大)
				period=Constans.FOUR;
			}else if(6<=monthNum&&monthNum<9){//0.75(约9个月大)
				period=Constans.FIVE;
			}
			
		}else if(1<age&&age<4){
			if(1<age&&age<2){//1~1.5岁
				period=Constans.SIX;
				if(monthNum>=6){//大于1.5岁
					period=Constans.SEVEN;
				}
				
			}else if(age==2){//2岁
				period=Constans.EIGHT;
			}else if(age==3){//3岁
				period=Constans.NINE;
			}
		}else if(4<=age&&age<5){//ELEVEN
			period=Constans.TEN;
		}else if(5<=age&&age<15){//TWELVE
			period=Constans.ELEVEN;
		}else if(15<=age&&age<21){//THIRTEEN
			period=Constans.TWELVE;
		}else if(21<=age&&age<30){//FOURTEEN
			period=Constans.FOURTEEN;
		}else if(30<=age&&age<60){//FIFTEEN
			period=Constans.FIFTEEN;
		}else{//SIXTEEN
			period=Constans.SIXTEEN;
		}
		
		return period;
		
	}
	
	
}
