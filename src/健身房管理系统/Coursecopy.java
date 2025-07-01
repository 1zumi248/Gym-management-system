package 健身房管理系统;
import java.time.LocalDate;

public class Coursecopy {
    private String Id;
    private String name;
    private LocalDate date;
       public Coursecopy(){
           Id = " ";
           name =" ";
           date =LocalDate.of(1970, 1, 1);

   }
   public void setCoursecopy(Coursecopy a){
	   Id=a.Id;
	   name=a.name;
	   date=a.date;
   }
   public Coursecopy(String a,String b,LocalDate c){
           Id = a;
           name =b ;
           date =c ;
   }
    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        Id = id;
    }
}
