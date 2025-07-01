package 健身房管理系统;

import 健身房管理系统.Main.MemberLevel;

public class Calculator {
     private int money;
     private MemberLevel level;
     private Course course;
    public Calculator(){
    	money=0;
    	level=MemberLevel.基础会员;
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
		if(level.equals(MemberLevel.基础会员)){
			money-=course.getPriceForBasicMember();
		}
		if(level.equals(MemberLevel.月度会员)){
			money-=course.getPriceForMonthlyMember();
		}
		if(level.equals(MemberLevel.季度会员)){
			money-=course.getPriceForQuarterlyMember();
		}
		if(level.equals(MemberLevel.年度会员)){
			money-=course.getPriceForAnnualMember();
		}
		return money;
	}
	public int payment(){
		if(level.equals(MemberLevel.基础会员)){
			money+=course.getPriceForBasicMember();
		}
		if(level.equals(MemberLevel.月度会员)){
			money+=course.getPriceForMonthlyMember();
		}
		if(level.equals(MemberLevel.季度会员)){
			money+=course.getPriceForQuarterlyMember();
		}
		if(level.equals(MemberLevel.年度会员)){
			money+=course.getPriceForAnnualMember();
		}
		return money;
	}
}
