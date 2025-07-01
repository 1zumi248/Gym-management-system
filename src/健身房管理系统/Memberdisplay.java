package 健身房管理系统;

import java.time.LocalDate;

public class Memberdisplay {
private String coursename;
private LocalDate Coursedate;
public Memberdisplay(String s,LocalDate date){
	coursename=s;
	Coursedate=date;
}
public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public LocalDate getCoursedate() {
	return Coursedate;
}
public void setCoursedate(LocalDate coursedate) {
	Coursedate = coursedate;
}
}
