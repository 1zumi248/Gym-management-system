package 健身房管理系统;

import 健身房管理系统.Main.MemberLevel;

public class dataView {

    private  String course;
    private String date;


    public dataView(String c,String d){
        course=c;
        date = d;
    }
    public String getCourse() {
        return course;
    }

    public String getDate() {
        return date;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public void setDate(String date) {
        this.date = date;
    }


}