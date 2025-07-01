package 健身房管理系统;

import java.time.LocalDate;
import java.util.ArrayList;
public class Course {
    private String name;
    private int priceForBasicMember;
    private int priceForMonthlyMember;
    private int priceForQuarterlyMember;
    private int priceForAnnualMember;

    private LocalDate []Cdate = new LocalDate[7];

    public Course(){
        name =" ";
        priceForBasicMember=0;
        priceForMonthlyMember=0;
        priceForQuarterlyMember=0;
        priceForAnnualMember=0;
        for(int i=0;i<7;i++){
            Cdate[i]=LocalDate.of(1970, 1, 1);
        }

    }
    public Course(Course c){
    	this.name=c.name;
    	this.priceForBasicMember=c.priceForBasicMember;
    	this.priceForMonthlyMember=c.priceForQuarterlyMember;
    	this.priceForAnnualMember=c.priceForAnnualMember;
    	this.Cdate=c.Cdate;
    }
    public void delCdate(int a){
    	Cdate[a]=LocalDate.of(1970, 1, 1);
    }
    public LocalDate[] getCdate() {
		return Cdate;
	}
    public LocalDate getCdate(int i) {
		return Cdate[i];
	}
	public void setCdate(LocalDate cdate,int a) {
		
		Cdate[a]=cdate;
	}
	public Course(String a,int b,int c,int d,int e){
        name=a;
        priceForBasicMember=b;
        priceForMonthlyMember=c;
        priceForQuarterlyMember=d;
        priceForAnnualMember=e;
        for(int i=0;i<7;i++){
            Cdate[i]=LocalDate.of(1970, 1, 1);
        }
    }



    public void setPriceForBasicMember(int priceForBasicMember) {
        this.priceForBasicMember = priceForBasicMember;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceForAnnualMember(int priceForAnnualMember) {
        this.priceForAnnualMember = priceForAnnualMember;
    }

    public void setPriceForMonthlyMember(int priceForMonthlyMember) {
        this.priceForMonthlyMember = priceForMonthlyMember;
    }

    public void setPriceForQuarterlyMember(int priceForQuarterlyMember) {
        this.priceForQuarterlyMember = priceForQuarterlyMember;
    }

    public String getName() {
        return name;
    }

    public int getPriceForBasicMember() {
        return priceForBasicMember;
    }

    public int getPriceForAnnualMember() {
        return priceForAnnualMember;
    }

    public int getPriceForMonthlyMember() {
        return priceForMonthlyMember;
    }

    public int getPriceForQuarterlyMember() {
        return priceForQuarterlyMember;
    }


}