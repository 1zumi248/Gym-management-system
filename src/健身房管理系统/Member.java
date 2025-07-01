package 健身房管理系统;

import 健身房管理系统.Main.MemberLevel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
	
    private String memberId;
    private String password;
    private int change;
    private MemberLevel level; // 枚举类型或引用另一个类
    private List<Course> MemberCourse;
    private int charge;

    //初始化
    public Member(){
        memberId=" ";
        change =0;
        password=" ";
        charge=0;
        level=MemberLevel.基础会员;
        MemberCourse =new ArrayList<>();
    }
    public Member(String name,String pass){
    	memberId=name;
    	password=pass;
    	change=0;
    	 charge=0;
    	 level=MemberLevel.基础会员;
    	 MemberCourse =new ArrayList<>();
    }
    public Member(String name,String pass,Course course){
    	memberId=name;
    	password=pass;
    	change=0;
    	charge=0;
    	level=MemberLevel.基础会员;
    	MemberCourse =new ArrayList<>();
    	MemberCourse.add(course);
    }
    public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public List<Course> getMemberCourse() {
		return MemberCourse;
	}
	public Course getcoursex(int a){
		return MemberCourse.get(a);
	}
	public void setMemberCourse(Course course) {
		MemberCourse.add(course);
	}
	//课程预约,可以设置判断逻辑
    public void addCourse(Course a){
          MemberCourse.add(a);
    }

    //查看预约
    public void setLevel(MemberLevel level) {
        this.level = level;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public MemberLevel getLevel() {
        return level;
    }

    

    public String getPassword() {
        return password;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public Member(String a,String b,MemberLevel c,int m){
        memberId=a;
        password=b;
        level=c;
        change =m;
        MemberCourse =new ArrayList<>();
    }
    public String getMemberId() {
        return memberId;
    }

    public int getChange() {
        return change;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	public int calCharge(int a) {
		// TODO 自动生成的方法存根
		return charge-a;
	}
    }