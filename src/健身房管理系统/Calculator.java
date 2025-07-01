package ��������ϵͳ;

import ��������ϵͳ.Main.MemberLevel;

public class Calculator {
     private int money;
     private MemberLevel level;
     private Course course;
    public Calculator(){
    	money=0;
    	level=MemberLevel.������Ա;
    	course=new Course();
    }
    public Calculator(int mo,MemberLevel l,Course c){
    	money=mo;
    	level=l;
    	course=new Course(c);
    }
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public MemberLevel getLevel() {
		return level;
	}
	public void setLevel(MemberLevel level) {
		this.level = level;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int paymentdel(){
		if(level.equals(MemberLevel.������Ա)){
			money-=course.getPriceForBasicMember();
		}
		if(level.equals(MemberLevel.�¶Ȼ�Ա)){
			money-=course.getPriceForMonthlyMember();
		}
		if(level.equals(MemberLevel.���Ȼ�Ա)){
			money-=course.getPriceForQuarterlyMember();
		}
		if(level.equals(MemberLevel.��Ȼ�Ա)){
			money-=course.getPriceForAnnualMember();
		}
		return money;
	}
	public int payment(){
		if(level.equals(MemberLevel.������Ա)){
			money+=course.getPriceForBasicMember();
		}
		if(level.equals(MemberLevel.�¶Ȼ�Ա)){
			money+=course.getPriceForMonthlyMember();
		}
		if(level.equals(MemberLevel.���Ȼ�Ա)){
			money+=course.getPriceForQuarterlyMember();
		}
		if(level.equals(MemberLevel.��Ȼ�Ա)){
			money+=course.getPriceForAnnualMember();
		}
		return money;
	}
}
