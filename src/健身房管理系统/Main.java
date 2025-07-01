package 健身房管理系统;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.scene.control.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.cell.PropertyValueFactory;

public class Main extends Application {
    public enum MemberLevel {
        普通会员, 温度会员, 高温会员, 炎热会员;
    }
    private TableView<Course> reoView;
    private TextField Tf1, Tf2, Tf3, Tf4, Tf5;
    private Button addBtn4, updateBtn4, deleteBtn4;
    private ObservableList<Course> List5;
    private TableView<dataView> tableView;
    private TableColumn<dataView,String> courseTcol,dateTcol;
    private ObservableList<dataView> obsList1;
    private TableView<Coursecopy> hView;
    private TableView<Member> sView;
    private TextField aTf, bTf, cTf,dTf;
    private Button addBtn, updateBtn, deleteBtn,back9;
    private ObservableList<Member> List3;
    List<Member> member=new ArrayList<>();
    int count=0;
    int counter=0;
    int flag=0;
    int judge=0;
    int []tool=new int[6];
    CourseBooking []coursebook=new CourseBooking[6];
    Course coursenumber=new Course();
    DatePickerLimitExample []dp = new DatePickerLimitExample[6];

    HBox messageBox = new HBox();
    private static final String[] MESSAGES = {
            "保持正确性：确保训练动作的正确性，正确的动作才能加快健身的进度，同时避免受伤的风险" +
            "合理饮食：适量多餐饮食群营养均衡，适量碳水，适量蛋白，维生素和矿物质。\n" +
            "正确摄入蛋白质：蛋白质对于肌肉的修复和增长非常重要，确保每餐都有合理的蛋白质来源。\n" +
            "适量碳水化合物摄入：健身的目标是减脂，适量的碳水化合物摄入是必须的，特别是训练时。\n" +
            "补充水分维生素在大量运动中，适当的补充运动饮料", "休息的指导\n" +
            "适当休息：每周安排1-2天休息日，每天留出一定可调的训练日，每天留一定时间用于恢复休息。\n" +
            "睡眠充足：保证每天7到9小时的睡眠时间，避免熬夜和八小时以下睡眠\n" +
            "放松指导方式：泡温泉、按摩、洗热水澡、热敷等，有助于缓解运动疲劳。", "训练方式的选择\n" +
            "进行全身运动：适当分配各节部位的训练，避免单一动作。\n" +
            "运动循序渐进：开始时先有基础的运动，强度较低，中间时期适当增加强度，后期时再增加强度。\n" +
            "适当改变：根据身体情况适时调整负荷，用动作配合更好更高效。", "场地选择\n" +
            "室内训练：如瑜伽、普拉提、力量训练等，适合在健身房进行。\n" +
            "户外运动：如散步、慢跑、游泳等，可以在合适的户外环境和自然景观。"
    };

    private void updateMessage(Text messageText) {
        Random random = new Random();
        int index = random.nextInt(MESSAGES.length);
        messageText.setText(MESSAGES[index]);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Text messageText = new Text();
        updateMessage(messageText);
        messageBox.getChildren().add(messageText);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> updateMessage(messageText)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        messageText.setWrappingWidth(400); 
        
        //创建并初始化tableView的样式
        TableView<Memberdisplay> tableView = new TableView<>();
        // 创建列标题
        TableColumn<Memberdisplay, String> nameColumn = new TableColumn<>("课程名称");
        TableColumn<Memberdisplay, String> dateColumn = new TableColumn<>("上课日期");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("coursename"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Coursedate"));
        // 将列添加到tableView中
        tableView.getColumns().addAll(nameColumn, dateColumn);
        primaryStage.setResizable(false);
        
        //tool和coursebook初始化
        for(int i=0;i<6;++i){
            tool[i]=0;
            coursebook[i]=new CourseBooking();
        }

        //course初始化
        List<Course>course=new ArrayList<>();
        Course[] coursec = {
                new Course("瑜伽", 50, 0, 0, 0),
                new Course("有氧运动", 60, 0, 0, 0),
                new Course("力量训练", 70, 0, 0, 0),
                new Course("普拉提", 55, 0, 0, 0),
                new Course("游泳", 30, 0, 0, 0),
                new Course("私教训练", 300, 300,  270, 240)
        };
        for(int i=0;i<6;++i){
            course.add(coursec[i]);
        }
      
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/ChuangKeTieJinGangTi-2.otf"), 42);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/YouSheBiaoTiYuan-2.otf"), 36);
        Font buttonfont2 = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/YouSheBiaoTiYuan-2.otf"), 30);
        Font tipsfont = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/douyuFont-2.otf"), 28);
        Font buttonfont3 = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/YouSheBiaoTiYuan-2.otf"), 18);
        Font buttonfont4 = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/YouSheBiaoTiYuan-2.otf"), 24);
        Font labelfont = Font.loadFont(getClass().getResourceAsStream("/健身房管理系统/YouSheBiaoTiYuan-2.otf"), 36);
        primaryStage.setTitle("健身房会员管理系统");
        messageText.setFont(buttonfont3);
        
        //预约日期限制选择器初始化
        LocalDate maxDate = LocalDate.now().plusDays(6);
        LocalDate minDate = LocalDate.now();
        for(int i=0;i<6;++i){
            dp[i] = new DatePickerLimitExample();
            dp[i].setCellFactory();
        }
        
        //创建主界面
        BorderPane MainPane = new BorderPane();
        MainPane.setStyle("-fx-background-color: white;");
        
        Image image = new Image(getClass().getResource("/健身房管理系统/images/sample2.jpg").toString());
        Image image2 = new Image(getClass().getResource("/健身房管理系统/images/OIP-C.jpg").toString());
        Image image3 = new Image(getClass().getResource("/健身房管理系统/images/83e6668bbc5dfb71.jpg").toString());
        Image image4 = new Image(getClass().getResource("/健身房管理系统/images/strength.jpg").toString());
        Image image5 = new Image(getClass().getResource("/健身房管理系统/images/普拉提.jpg").toString());
        Image image6 = new Image(getClass().getResource("/健身房管理系统/images/780.jpg").toString());
        Image image7 = new Image(getClass().getResource("/健身房管理系统/images/R-C.jpg").toString());
        Image image8 = new Image(getClass().getResource("/健身房管理系统/images/错误.jpg").toString());
        
        ImageView imageView8 = new ImageView(image8);
        ImageView imageView = new ImageView(image);
        MainPane.getChildren().add(imageView);
        imageView.setX(-208);
        
        VBox tipsvbox = new VBox();
        HBox tips = new HBox();
        HBox firstoperator = new HBox();
        
        Label thefirstwords = new Label("欢迎使用健身房会员管理系统!");
        thefirstwords.setFont(customFont);
        thefirstwords.setTextFill(Color.DARKBLUE);
        thefirstwords.setAlignment(Pos.CENTER);
        thefirstwords.setTranslateX(290);
        thefirstwords.setTranslateY(-140);
        
        Button loginbutton = new Button("注册");
        loginbutton.setPrefWidth(160);
        loginbutton.setPrefHeight(80);
        loginbutton.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
        loginbutton.setFont(buttonfont);
        
        Button registerbutton = new Button("登录");
        registerbutton.setPrefWidth(160);
        registerbutton.setPrefHeight(80);
        registerbutton.setFont(buttonfont);
        registerbutton.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");

        firstoperator.getChildren().addAll(loginbutton,registerbutton);
        firstoperator.setSpacing(70);
        firstoperator.setAlignment(Pos.CENTER);
        firstoperator.setTranslateX(280);
        firstoperator.setTranslateY(80);
        
        tips.getChildren().add(thefirstwords);
        tipsvbox.getChildren().addAll(tips,firstoperator);
        tips.setAlignment(Pos.CENTER);
        MainPane.setCenter(tipsvbox);
        tipsvbox.setSpacing(50);
        tipsvbox.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(MainPane,1280,712);
        primaryStage.setScene(scene);
        primaryStage.show();

        //分别实现注册和登录功能
        BorderPane bpane = new BorderPane();
        GridPane registerPane = new GridPane();
        bpane.setCenter(registerPane);
        registerPane.setTranslateY(-80);
        registerPane.setAlignment(Pos.CENTER);
        Label registerlabel = new Label("注册");
        registerlabel.setFont(customFont);
        registerlabel.setTranslateX(300);
        registerlabel.setTranslateY(100);
        bpane.setTop(registerlabel);
        bpane.setAlignment(registerlabel, Pos.CENTER);

        Label Usernamelabel=new Label("用户名");
        TextField registerUsername = new TextField();
        Usernamelabel.setAlignment(Pos.CENTER);
        Usernamelabel.setTranslateX(300);
        registerUsername.setTranslateX(300);
        registerUsername.setPrefHeight(40);
        Usernamelabel.setFont(buttonfont2);

        Label Passwordlabel=new Label("密码");
        TextField registerPassword = new TextField();
        Passwordlabel.setAlignment(Pos.CENTER);
        Passwordlabel.setTranslateX(300);
        registerPassword.setTranslateX(300);
        registerPassword.setPrefHeight(40);
        Passwordlabel.setFont(buttonfont2);

        Label confirmpasswordlabel = new Label("确认密码");
        TextField confirmPassword = new TextField();
        confirmpasswordlabel.setAlignment(Pos.CENTER);
        confirmpasswordlabel.setTranslateX(300);
        confirmPassword.setTranslateX(300);
        confirmPassword.setPrefHeight(40);
        confirmpasswordlabel.setFont(buttonfont2);

        HBox bottomhbox=new HBox();
        Button registerSubmit = new Button("提交");
        Button back=new Button("返回");

        bottomhbox.getChildren().addAll(registerSubmit,back);
        bottomhbox.setTranslateX(300);
        bpane.setBottom(bottomhbox);
        bottomhbox.setAlignment(Pos.CENTER);
        bottomhbox.setSpacing(40);
        registerSubmit.setPrefHeight(80);
        registerSubmit.setPrefWidth(160);
        registerSubmit.setFont(buttonfont);
        registerSubmit.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
        back.setPrefHeight(80);
        back.setPrefWidth(160);
        back.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
        back.setFont(buttonfont);
        bottomhbox.setTranslateY(-60);
        registerSubmit.setOnAction(e -> {

            // 获取用户名和密码
            String username = registerUsername.getText();
            String password = registerPassword.getText();
            String conPassword = confirmPassword.getText();

            //检查是否为空
            if (username.isEmpty() || password.isEmpty() || conPassword.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText(null);
                alert.setContentText("不能为空，请输入。");
                alert.showAndWait();
                return;
            }

            //检查密码是否一致
            if (!password.equals(conPassword)) {
                // 显示错误提示
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText(null);
                alert.setContentText("两次输入的密码不一致，请重新输入。");
                alert.showAndWait();

            }

            // 显示用户注册信息和登录
            else if(password.equals(conPassword)){
                String title="成功";
                String registertips="注册成功";
                
                    for(int j=0;j<member.size();++j){
                        //检查是否 注册过
                        if(member.get(j).getMemberId().equals(username)){
                            title="错误";
                            registertips="用户名已存在，不能注册";
                            break;
                        }
                    }

                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.setTitle(title);
                information.setHeaderText(null);
                information.setContentText(registertips);

                // 保存用户信息
                if(title.equals("成功")){
                	member.add(new Member(username,password)); 
                }
                information.showAndWait();
                MainPane.setCenter(tipsvbox);
                registerUsername.clear();
                registerPassword.clear();
                confirmPassword.clear();
            }
        });

        back.setOnAction(e -> {
            // 返回中心
            MainPane.setCenter(tipsvbox);

        });
        registerPane.add(Usernamelabel,0,0);
        registerPane.add(registerUsername,1,0);
        registerPane.add(Passwordlabel,0,1);
        registerPane.add(registerPassword,1,1);
        registerPane.add(confirmpasswordlabel,0,2);
        registerPane.add(confirmPassword,1,2);
        registerPane.setHgap(20);
        registerPane.setVgap(20);

        //登录界面
        BorderPane bpane2=new BorderPane();
        GridPane loginpane=new GridPane();
        Label loginlabel=new Label("登录");
        loginlabel.setFont(customFont);
        loginlabel.setTranslateX(300);
        loginlabel.setTranslateY(100);

        bpane2.setTop(loginlabel);
        bpane2.setAlignment(loginlabel, Pos.CENTER);
        bpane2.setCenter(loginpane);

        Label loginUsernamelabel=new Label("用户名");
        TextField loginUsername = new TextField();
        loginUsername.setAlignment(Pos.CENTER);
        loginUsernamelabel.setTranslateX(300);
        loginUsername.setTranslateX(300);
        loginUsername.setPrefHeight(40);
        loginUsernamelabel.setFont(buttonfont2);
        Label Password=new Label("密码");
        TextField loginPassword = new TextField();
        Password.setAlignment(Pos.CENTER);
        Password.setTranslateX(300);
        loginPassword.setTranslateX(300);
        loginPassword.setPrefHeight(40);
        Password.setFont(buttonfont2);

        HBox bottomhbox2=new HBox();
        Button loginSubmit = new Button("确定");
        Button back2=new Button("返回");

        bottomhbox2.getChildren().addAll(back2,loginSubmit);
        bpane2.setBottom(bottomhbox2);
        bottomhbox2.setSpacing(40);
        loginSubmit.setPrefHeight(80);
        loginSubmit.setPrefWidth(160);
        loginSubmit.setFont(buttonfont);
        loginSubmit.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
        back2.setPrefHeight(80);
        back2.setPrefWidth(160);
        back2.setFont(buttonfont);
        back2.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
        bottomhbox2.setTranslateY(-80);
        bottomhbox2.setTranslateX(300);
        bottomhbox2.setAlignment(Pos.CENTER);

        loginpane.add(loginUsernamelabel,0,0);
        loginpane.add(loginUsername,1,0);
        loginpane.add(Password,0,1);
        loginpane.add(loginPassword,1,1);
        loginpane.setAlignment(Pos.CENTER);
        loginpane.setHgap(20);
        loginpane.setVgap(20);
        loginpane.setTranslateY(-100);
        
        loginSubmit.setOnAction(e -> {

            // 处理登录逻辑
            String username = loginUsername.getText();
            String password = loginPassword.getText();
            for(int i=0;i<member.size();++i){
            	if(username.equals(member.get(i).getMemberId()))
            		flag=i;
            }
            if((!username.equals("管理员"))){
            //登录成功后转到用户界面
            BorderPane userpane = new BorderPane();
            
            userpane.getChildren().add(imageView8);
            userpane.setStyle("-fx-background-color:#FFFFFF;"); // 背景颜色
            Label toptips=new Label("健身房会员管理系统");
            Button coursebutton=new Button("预约课程");
            Button coursedel=new Button("取消预约");
            Button courseque=new Button("查询课程");
            Button back3=new Button("返回");
            Button chargebutton=new Button("收费");
            HBox bottombox=new HBox();
            coursebutton.setFont(buttonfont);
            coursebutton.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            coursebutton.setPrefWidth(200);
            coursebutton.setPrefHeight(60);
            coursedel.setFont(buttonfont);
            coursedel.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            coursedel.setPrefWidth(200);
            coursedel.setPrefHeight(60);
            courseque.setFont(buttonfont);
            courseque.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            courseque.setPrefWidth(200);
            courseque.setPrefHeight(60);
            back3.setFont(buttonfont);
            back3.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            back3.setPrefWidth(220);
            back3.setPrefHeight(40);
            chargebutton.setFont(buttonfont);
            chargebutton.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            chargebutton.setPrefWidth(200);
            chargebutton.setPrefHeight(60);
            bottombox.getChildren().add(back3);
           
            coursebutton.setTranslateX(-60);
            coursedel.setTranslateX(-60);
            courseque.setTranslateX(-60);
            bottombox.setTranslateY(-10);
            bottombox.setAlignment(Pos.CENTER);

            userpane.setAlignment(coursebutton,Pos.CENTER);
            userpane.setAlignment(bottombox,Pos.CENTER);
            toptips.setFont(tipsfont);
            toptips.setTextFill(Color.STEELBLUE);
            userpane.setTop(toptips);
            userpane.setAlignment(toptips,Pos.CENTER);
            toptips.setTranslateY(30);
            toptips.setTranslateX(-510);

            VBox userinformation = new VBox();
            userinformation.setSpacing(20); // 布局
            userinformation.setAlignment(Pos.CENTER); // 布局方式

            VBox funbtubox=new VBox();
            funbtubox.setSpacing(20); // 布局
            funbtubox.setAlignment(Pos.CENTER); // 布局方式

            HBox funbox1=new HBox(coursebutton);
            HBox funbox2=new HBox(coursedel);
            HBox funbox3=new HBox(courseque);
            HBox usernamehbox = new HBox();
            usernamehbox.setSpacing(10); // 布局
            usernamehbox.setTranslateX(10); // 位置

            HBox membership = new HBox();
            membership.setSpacing(10); // 布局
            membership.setTranslateX(10); // 位置

            HBox balancebox=new HBox();
            balancebox.setSpacing(10);
            balancebox.setTranslateX(10);

            HBox chargebox=new HBox();
            chargebox.setSpacing(10);
            chargebox.setTranslateX(10);
            
            HBox showbox=new HBox();
            showbox.setPrefHeight(200);
            showbox.setPrefWidth(200);
            HBox chargebtu=new HBox(chargebutton);
            chargebtu.setTranslateX(10);

            Label namelabel = new Label("用户名");
            namelabel.setFont(labelfont);; // 布局
            namelabel.setTextFill(Color.DIMGREY); // 布局颜色

            Label memberlabel = new Label("会员等级");
            memberlabel.setFont(labelfont); // 布局
            memberlabel.setTextFill(Color.DIMGREY); // 布局颜色

            Label namefield = new Label(username);
            namefield.setFont(labelfont); // 布局
            
            namefield.setTranslateX(28); // 位置

            Label memberfield = new Label("会员等级");
            memberfield.setFont(labelfont); // 布局
            memberfield.setTranslateX(12); // 位置
            

            Label balance=new Label("余额");
            balance.setFont(labelfont); // 布局
            balance.setTextFill(Color.DIMGREY);

            Label balancefield=new Label("0");
            balancefield.setFont(labelfont); // 布局
            balancefield.setTranslateX(12);
            //balancefield.setTextFill(Color.DARKCYAN);

            Label charge=new Label("应收费用");
            charge.setFont(labelfont); // 布局
            charge.setTextFill(Color.DIMGREY);

            Label chargefield=new Label("0");
            chargefield.setFont(labelfont); // 布局
            chargefield.setTranslateX(12);

            usernamehbox.getChildren().addAll(namelabel, namefield);
            membership.getChildren().addAll(memberlabel, memberfield);
            balancebox.getChildren().addAll(balance,balancefield);
            chargebox.getChildren().addAll(charge,chargefield);
            showbox.getChildren().add(messageBox);
            messageText.setFont(buttonfont4);
            messageBox.setTranslateX(140);
            messageBox.setTranslateY(160);

            funbtubox.getChildren().addAll(funbox1,funbox2,funbox3);
            userinformation.getChildren().addAll(usernamehbox, membership,balancebox,chargebox,chargebtu);
            userpane.setLeft(userinformation);
            userpane.setCenter(showbox);
            //showbox.setAlignment(Pos.CENTER);
            userpane.setRight(funbtubox);
            userpane.setBottom(bottombox);

            boolean found = false;

            for (int o = 0; o < member.size(); ++o) {
                if (username.equals(member.get(o).getMemberId()) && password.equals(member.get(o).getPassword())) {
                    found = true;
                    
                    int chargef=0;
                    flag=o;
                    for(int i=0;i<member.get(flag).getMemberCourse().size();++i){
                    	for(int j=0;j<7;++j){
                    		if(!member.get(flag).getMemberCourse().get(i).getCdate(j).isEqual(LocalDate.of(1970, 1, 1))){
                    			
                    			Calculator calf=new Calculator(0,member.get(flag).getLevel(),member.get(flag).getMemberCourse().get(i));
                    			chargef+=calf.payment();
                    		}
                    	}
                    }
                    
                    memberfield.setText(String.valueOf(member.get(o).getLevel()));
                    balancefield.setText(String.valueOf(member.get(o).getChange()));
                    chargefield.setText(String.valueOf(chargef));
                    MainPane.setCenter(userpane);
                    loginUsername.clear();
                    loginPassword.clear();

                    //收费按钮
                    chargebutton.setOnAction(cge->{
                        if(chargefield.getText().equals("0")){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("提示");
                            alert.setHeaderText(null);
                            alert.setContentText("没有收费");
                            alert.showAndWait();
                        }
                        else if(!chargefield.getText().equals("0")){
                        	if(Integer.valueOf(chargefield.getText())>Integer.valueOf(balancefield.getText())){
                        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("提示");
                                alert.setHeaderText(null);
                                alert.setContentText("余额不足，请充值");
                                alert.showAndWait();
                        	}
                        	else if(Integer.valueOf(chargefield.getText())<=Integer.valueOf(balancefield.getText())){
                        		balancefield.setText(String.valueOf(Integer.valueOf(balancefield.getText())-Integer.valueOf(chargefield.getText())));
                        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("提示");
                                alert.setHeaderText(null);
                                alert.setContentText("收费成功");
                                alert.showAndWait();
                        	}
                        }
                    });
                   
                }
            }
            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText(null);
                alert.setContentText("用户名或密码错误，请重新登录。");
                alert.showAndWait();
            }

            //预约课程界面
            GridPane coursepane=new GridPane();
            BackgroundImage background = new BackgroundImage(image8, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

            // 创建并获取GridPane
            Background backgroundObj = new Background(background);
            coursepane.setBackground(backgroundObj);
            HBox coursehbox=new HBox();
            //开始初始化用户已预约的课程
            LocalDate [][]dps=new LocalDate[6][7];
            for(int i=0;i<6;++i){
            	for(int j=0;j<7;++j){
            		dps[i][j]=LocalDate.of(1970, 1, 1);
            	}
            }

            coursehbox.getChildren().add(coursepane);
            ImageView imageview2=new ImageView(image2);
            ImageView imageview3=new ImageView(image3);
            ImageView imageview4=new ImageView(image4);
            ImageView imageview5=new ImageView(image5);
            ImageView imageview6=new ImageView(image6);
            ImageView imageview7=new ImageView(image7);
            coursepane.setAlignment(Pos.CENTER);
            coursehbox.setAlignment(Pos.CENTER);
            Button calculator=new Button("确定");
            Button back4=new Button("返回");
            back4.setFont(buttonfont);
            back4.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            calculator.setFont(buttonfont);
            calculator.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            HBox empty=new HBox();
            calculator.setPrefHeight(80);
            calculator.setPrefWidth(160);
            calculator.setFont(buttonfont);
            back4.setPrefHeight(80);
            back4.setPrefWidth(160);
            back4.setFont(buttonfont);
            Label coursename=new Label("选择课程");
            Label date=new Label("选择日期");
            CheckBox cbs[]=new CheckBox[6];
            cbs[0]=new CheckBox("瑜伽");
            cbs[1]=new CheckBox("有氧运动");
            cbs[2]=new CheckBox("力量训练");
            cbs[3]=new CheckBox("普拉提");
            cbs[4]=new CheckBox("游泳");
            cbs[5]=new CheckBox("私教训练");
            for(int j=0;j<6;++j){
                cbs[j].setFont(Font.font("Arial", FontWeight.BOLD, 20));
            }
            
            coursepane.addColumn(0,empty,imageview2,imageview3,imageview4,imageview5,imageview6,imageview7);
            imageview2.setTranslateX(108);
            imageview3.setTranslateX(108);
            imageview4.setTranslateX(108);
            imageview5.setTranslateX(108);
            imageview6.setTranslateX(108);
            imageview7.setTranslateX(108);
            coursepane.addColumn(1,coursename,cbs[0],cbs[1],cbs[2],cbs[3],cbs[4],cbs[5]);
            coursepane.addColumn(2,date,dp[0].getDp(),dp[1].getDp(),dp[2].getDp(),dp[3].getDp(),dp[4].getDp(),dp[5].getDp());
            coursepane.addRow(7, back4,calculator);
            back4.setTranslateX(80);
            calculator.setTranslateX(80);
            coursepane.setHgap(60);
            coursepane.setVgap(40);
            coursename.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            date.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            
            //获取课程信息
            dp[0].getDp().setOnAction(f->{
                                
                Period period = Period.between(minDate,dp[0].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[0].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[0][tool[0]] =dp[0].getDp().getValue();
                tool[0]++;
                coursebook[0].calculator(days);
        		}
            });
            dp[1].getDp().setOnAction(f->{

            	Period period = Period.between(minDate,dp[1].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[1].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[1][tool[1]] =dp[1].getDp().getValue();
                tool[1]++;
                coursebook[1].calculator(days);
        		}
            });
            dp[2].getDp().setOnAction(f->{
            	Period period = Period.between(minDate,dp[2].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[2].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[2][tool[2]] =dp[2].getDp().getValue();
                tool[2]++;
                coursebook[2].calculator(days);
        		}
            });
            dp[3].getDp().setOnAction(f->{
            	Period period = Period.between(minDate,dp[3].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[3].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[3][tool[3]] =dp[3].getDp().getValue();
                tool[3]++;
                coursebook[3].calculator(days);
        		}
            });
            dp[4].getDp().setOnAction(f->{
            	Period period = Period.between(minDate,dp[4].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[4].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[4][tool[4]] =dp[4].getDp().getValue();
                tool[4]++;
                coursebook[4].calculator(days);
        		}
            });
            dp[5].getDp().setOnAction(f->{

            	Period period = Period.between(minDate,dp[5].getDp().getValue());
        		int days=period.getDays();
        		if(coursebook[5].getCoursex(days)==0){
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText(null);
                    alert.setContentText("没有预约课程");
                    alert.showAndWait();
        		}
        		else{
        		dps[5][tool[5]] =dp[5].getDp().getValue();
                tool[5]++;
                coursebook[5].calculator(days);
        		}
            });
            
                calculator.setOnAction(c->{
                            
                int totalmoney=0;
                for(int i=0;i<6;++i) {
                	
                    if (cbs[i].isSelected()) {
                    	int coursenumber=0;//记录每个课程用户已预约课程
                        Course c1copy = new Course(course.get(i));
                        Calculator cal = new Calculator(0, member.get(flag).getLevel(), c1copy);
                        for(int a=0;a<7;++a){
                        	if(!dps[i][a].isEqual(LocalDate.of(1970, 1, 1))){
                        		coursenumber++;
                        		Period period = Period.between(minDate,dps[i][a]);
                        		int days=period.getDays();
                        		c1copy.setCdate(dps[i][a], days);
                        	}
                        }
                        
                        if(member.get(flag).getMemberCourse().size()>=1){
                        for(int j=0;j<member.get(flag).getMemberCourse().size();++j){
                        	Boolean jud=false;
                         	for(int k=0;k<member.get(flag).getMemberCourse().size();++k){
                         		if(c1copy.getName().equals(member.get(flag).getMemberCourse().get(k).getName())){
                         			jud=true;
                         		}
                         	}
                        for(int y=0;y<7;++y){
                        	if(member.get(flag).getcoursex(j).getName().equals(c1copy.getName())&&!member.get(flag).getcoursex(j).getCdate(y).isEqual(c1copy.getCdate(y))){
                            	member.get(flag).getMemberCourse().get(j).setCdate(c1copy.getCdate(y), y);
                            	 
                            	
                        }
                        	else if(jud==false&&judge==0&&!member.get(flag).getcoursex(j).getName().equals(c1copy.getName())){
                        		member.get(flag).setMemberCourse(c1copy);
                        		judge=1;
                        		 
                        	}
                        	
                        }
                        
                        }
                        judge=0;
                        }
                        else  {member.get(flag).setMemberCourse(c1copy);
                       
                        }
                        totalmoney = totalmoney+(coursenumber*cal.payment());
                    }
                }
                member.get(flag).setCharge(totalmoney);
                chargefield.setText(String.valueOf(totalmoney));
                MainPane.setCenter(userpane);
            });
            courseque.setOnAction(c->{
            	imageView.setVisible(false);
            	BorderPane baspane=new BorderPane();
            	Button back5=new Button("返回");
            	back5.setFont(buttonfont);
            	back5.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            	back5.setPrefWidth(200);
            	back5.setPrefHeight(60);
                VBox coursevbox = new VBox(tableView);
                //查询课程界面初始化
                
                ObservableList<Memberdisplay> data = FXCollections.observableArrayList();
                data.clear();
                for(int i=0;i<member.get(flag).getMemberCourse().size();++i){
                    for(int j=0;j<7;++j){
                        if(!(member.get(flag).getMemberCourse().get(i).getCdate(j).isEqual(LocalDate.of(1970, 1, 1)))){
                            data.add(new Memberdisplay(member.get(flag).getMemberCourse().get(i).getName(),member.get(flag).getMemberCourse().get(i).getCdate(j)));
                           
                        }
                    }
                }
                tableView.setItems(data);
                tableView.setPrefSize(100,600);
                baspane.setCenter(coursevbox);	
                baspane.setBottom(back5);
                baspane.setAlignment(back5, Pos.CENTER);
                back5.setTranslateY(-30);
                back5.setOnAction(b->{
                	MainPane.setCenter(userpane);
                    data.clear();
                	
                });
                MainPane.setCenter(baspane);
            });

            //获取记录信息并清除指针为空时显示为删除

            
            //预约课程界面

            coursebutton.setOnAction(j->{
                imageView.setVisible(false);
                MainPane.setCenter(coursepane);
            });
            //预约课程界面
            back4.setOnAction(p->{
            	
                MainPane.setCenter(userpane);
            });            
           
            //取消预约课程

            coursedel.setOnAction(k->{
                //需要一个选择要取消的课程
         	   Stage delstage=new Stage();
         	   BorderPane delpane=new BorderPane();

         	   VBox delinfor=new VBox();
         	   HBox delname=new HBox();
         	   HBox deldate=new HBox();
         	   HBox tipbox=new HBox();
         	   Label tip=new Label("请按格式输入：2024-06-01");
         	   tipbox.getChildren().add(tip);
         	   Label delnamelabel=new Label("请要取消预约的课程");
         	   Label deldatelabel=new Label("请要取消日期");
         	   TextField delnamefield=new TextField();
         	   TextField deldatefield=new TextField();
         	   Button confirm=new Button("确定");
         	   confirm.setPrefWidth(80);
         	   confirm.setPrefHeight(40);
         	   delpane.setBottom(confirm);
         	   delpane.setAlignment(confirm, Pos.CENTER);
         	   delname.getChildren().addAll(delnamelabel,delnamefield);
         	   deldate.getChildren().addAll(deldatelabel,deldatefield);
         	   delinfor.getChildren().addAll(delname,deldate,tipbox);
         	   deldatefield.setTranslateX(42);
         	   delnamefield.setTranslateX(5);
         	   delinfor.setSpacing(20);
         	   delinfor.setTranslateX(20);
         	  delinfor.setTranslateY(20);
         	   Scene newScene = new Scene(delpane, 380, 200);
         	  delstage.setTitle("取消预约课程");
              delpane.setCenter(delinfor);
              delstage.setScene(newScene);
              delstage.show();
                confirm.setOnAction(r->{
                	int x=3;
                	if(member.get(flag).getMemberCourse().size()==0){
                		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("提示");
                        alert.setHeaderText(null);
                        alert.setContentText("之前没有预约课程");
                        alert.showAndWait();
                        x=0;
                	}
                	 for(int i =0 ;i<member.get(flag).getMemberCourse().size();i++) {

                         for (int j = 0; j < 7; j++) {
                             //预约课程删除
                             if (delnamefield.getText().equals(member.get(flag).getMemberCourse().get(i).getName())&& String.valueOf(member.get(flag).getMemberCourse().get(i).getCdate(j)).equals(deldatefield.getText())) {
                                   //获取要删除的预约
                                     //默认EPOCH为01970 1 1
                                 if(!(String.valueOf(LocalDate.now()).equals(deldatefield.getText()))){
                                	 int bal=0 ;   
                                	 Calculator cal2=new Calculator(member.get(flag).getCharge(),member.get(flag).getLevel(),member.get(i).getMemberCourse().get(i));
                                	 member.get(flag).getMemberCourse().get(i).setCdate(LocalDate.of(1970, 1, 1),j);
                                	 bal=cal2.paymentdel(); 
                                	 member.get(flag).setCharge(bal);
                                	 chargefield.setText(String.valueOf(bal));
                                     x=1;
                                 }
                                 else{
                                	 x=2;
                                 }

                             }
                         }
                     }
                	 if(x!=1&&x!=2&&x!=0){
                		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("提示");
                         alert.setHeaderText(null);
                         alert.setContentText("课程不存在，请重新选择");
                         alert.showAndWait();
                	 }
                	 if(x==1){
                		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("提示");
                         alert.setHeaderText(null);
                         alert.setContentText("取消成功");
                         alert.showAndWait();
                	 }
                	 if(x==2){
                		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("提示");
                         alert.setHeaderText(null);
                         alert.setContentText("之前预约不能取消");
                         alert.showAndWait();
                	 }
                });
            });
            back3.setOnAction(x -> {
                // 处理逻辑
            	
            	for(int i=0;i<6;++i){
                    dp[i] = new DatePickerLimitExample();
                    dp[i].setCellFactory();
                }
            	for(int i=0;i<6;++i){
                	for(int j=0;j<7;++j){
                		dps[i][j]=LocalDate.of(1970, 1, 1);
                		tool[i]=0;
                	}
                }
                imageView.setVisible(true);
                MainPane.setCenter(tipsvbox);
               
                //tableView.getColumns().clear();
                
            });
            }
            
            //管理员界面
            else if(username.equals("管理员")&&password.equals(String.valueOf(111111))){
            	//管理员界面
                BorderPane managerpane=new BorderPane();
                managerpane.getChildren().add(imageView8);
               
                Button memberview=new Button("查询用户");
                Button courseview=new Button("查询课程");
                Button bookingview=new Button("预约课程");
                Button back6=new Button("返回");
                back6.setPrefWidth(160); // 选择按钮为100
                back6.setPrefHeight(80);
                back6.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                back6.setFont(buttonfont);
                memberview.setPrefWidth(260); // 选择按钮为100
                memberview.setPrefHeight(80);
                memberview.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                memberview.setFont(buttonfont);
                courseview.setPrefWidth(260); // 选择按钮为100
                courseview.setPrefHeight(80);
                courseview.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                courseview.setFont(buttonfont);
                bookingview.setPrefWidth(260); // 选择按钮为100
                bookingview.setPrefHeight(80);
                bookingview.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                bookingview.setFont(buttonfont);
                managerpane.setBottom(back6);
                managerpane.setAlignment(back6, Pos.CENTER);
                back6.setTranslateY(-20);
                back6.setOnAction(t->{
                	imageView.setVisible(true);
                	MainPane.setCenter(tipsvbox);
                });
                VBox mainbox=new VBox();
                mainbox.setSpacing(60);
                mainbox.getChildren().addAll(memberview,courseview,bookingview);
                mainbox.setTranslateX(400);
                imageView.setVisible(false);
                mainbox.setAlignment(Pos.CENTER);
                managerpane.setCenter(mainbox);
                
                MainPane.setCenter(managerpane);
                
                bookingview.setOnAction(b->{
                	               
            BorderPane admpane = new BorderPane();
            
            TableView<Coursecopy> hView = new TableView<Coursecopy>();
            VBox viewbox=new VBox(hView); 
            hView.setPrefHeight(580);
            admpane.setCenter(viewbox);
            admpane.setAlignment(viewbox, Pos.CENTER);
            TableColumn<Coursecopy, String> aColumn2 = new TableColumn<>("用户名");
            TableColumn<Coursecopy, String> bColumn2 = new TableColumn<>("课程");
            TableColumn<Coursecopy, String> cColumn2 = new TableColumn<>("课程日期");
            aColumn2.setPrefWidth(200);
            bColumn2.setPrefWidth(200);
            cColumn2.setPrefWidth(200);
            hView.getColumns().addAll(aColumn2, bColumn2, cColumn2);
            aColumn2.setSortable(false);
            bColumn2.setSortable(false);
            cColumn2.setSortable(false);
            ObservableList<Coursecopy>  List4 = FXCollections.observableArrayList();
            aColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("Id"));
            bColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("name"));
            cColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("date"));
            //获取用户信息
            
            int[][][] data=new int[member.size()][6][7];
            for(int i=0;i<member.size();++i){
            	for(int j=0;j<6;j++){
            		for(int k=0;k<7;k++){
            			data[i][j][k]=0;
            		}
            	}
            }
            for(int i=0;i<member.size();++i){
            	for(int j=0;j<member.get(i).getMemberCourse().size();++j){
            		for(int k=0;k<7;++k){
            			if(!member.get(i).getMemberCourse().get(j).getCdate(k).isEqual(LocalDate.of(1970, 1, 1))){
            	Coursecopy copy=new Coursecopy(member.get(i).getMemberId(),member.get(i).getMemberCourse().get(j).getName(),member.get(i).getMemberCourse().get(j).getCdate(k));
            			List4.add(copy);
            			data[i][j][k]=1;
            			}
            }
            }
            }
            hView.setItems(List4);
            //底部功能
            HBox bottomHPane = new HBox(5);
            bottomHPane.setPadding(new Insets(10,10,0,0));
            bottomHPane.setAlignment(Pos.CENTER);
            Label aLabel = new Label("用户名");
            aLabel.setFont(buttonfont);
            Label bLabel = new Label("课程");
            bLabel.setFont(buttonfont);
            Label cLabel = new Label("日期");
            cLabel.setFont(buttonfont);
            TextField aTf2 = new TextField();
            aTf2.setPrefHeight(39);
            TextField bTf2 = new TextField();
            bTf2.setPrefHeight(39);
            TextField cTf2 = new TextField();
            cTf2.setPrefHeight(39);
            Button addBtn2 = new Button("添加");
            addBtn2.setPrefHeight(40);
            addBtn2.setPrefWidth(80);
            addBtn2.setFont(buttonfont3);
            addBtn2.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            Button updateBtn2 = new Button("修改");
            updateBtn2.setPrefHeight(40);
            updateBtn2.setPrefWidth(80);
            updateBtn2.setFont(buttonfont3);
            updateBtn2.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            Button deleteBtn2 = new Button("删除");
            deleteBtn2.setPrefHeight(40);
            deleteBtn2.setPrefWidth(80);
            deleteBtn2.setFont(buttonfont3);
            deleteBtn2.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            Button back7=new Button("返回");
            back7.setPrefHeight(40);
            back7.setPrefWidth(80);
            back7.setFont(buttonfont3);
            back7.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
            back7.setOnAction(o->{
            	MainPane.setCenter(managerpane);
            });
            bottomHPane.getChildren().addAll(aLabel,aTf2,bLabel,bTf2,cLabel,cTf2,addBtn2,updateBtn2,deleteBtn2,back7);
            admpane.setBottom(bottomHPane);
            bottomHPane.setTranslateY(-20);
            //查询TableView
            hView.setOnMouseClicked(l->{
                // 获取TableView选中的行和列
                int seleIndex = hView.getSelectionModel().getSelectedIndex();
                // 获取选中
                 if(seleIndex!=-1){
                    // 获取选中行的值并设置到输入框中
                    Coursecopy abcObj = List4.get(seleIndex);

                    // 选中行的值设置到输入框中
                    aTf2.setText(abcObj.getId());
                    bTf2.setText(abcObj.getName());
                    cTf2.setText(String.valueOf(abcObj.getDate()));
                }

            });
            // 添加按钮
            addBtn2.setOnAction(j->{
                // 获取输入框
            	for(int i=0;i<member.size();++i){
            		
            		if(aTf2.getText().equals(member.get(i).getMemberId())){
            			
            			for(int h=0;h<member.get(i).getMemberCourse().size();++h){
            			if(bTf2.getText().equals(member.get(i).getMemberCourse().get(h).getName())){	
            				Period period = Period.between(minDate,LocalDate.parse(cTf2.getText()));
            				int days=period.getDays();
            				member.get(i).getMemberCourse().get(h).setCdate(LocalDate.parse(cTf2.getText()), days);
            			}
            			}
            		}
            		else {
            			System.out.print(0);
            			 for(int i1=0;i1<6;++i1){
                           if(bTf2.getText().equals(course.get(i1))){
                        	   Course copyx=new Course(course.get(i1));
                        	   Period period = Period.between(minDate,LocalDate.parse(cTf2.getText()));
               				   int days=period.getDays();
               				   copyx.setCdate(LocalDate.parse(cTf2.getText()), days);
               				   member.add(new Member(aTf2.getText(),"000000",copyx));
               				   
                           }
                          }
            		}
            	}
            	if(member.size()==0){
            		
            		for(int i1=0;i1<6;++i1){
            			
                        if(bTf2.getText().equals(course.get(i1).getName())){
                     	   Course copyx=new Course(course.get(i1));
                     	   Period period = Period.between(minDate,LocalDate.parse(cTf2.getText()));
            				   int days=period.getDays();
            				   copyx.setCdate(LocalDate.parse(cTf2.getText()), days);
            				   
            				   member.add(new Member(aTf2.getText(),"000000",copyx));
            				  
            				   
                        }
                       }
            	}
                Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(), LocalDate.parse(cTf2.getText()));
                
                List4.add(m);
            });

            // 修改按钮
            updateBtn2.setOnAction(k->{
                // 获取选中行
                int seleIndex = hView.getSelectionModel().getSelectedIndex();
        
                // 获取选中
                if(seleIndex!=-1){
                    // 获取修改的数据并设置到输入框中
                	int countx=0;
                    Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(),LocalDate.parse(cTf2.getText()));
                    for(int i=0;i<member.size();++i){
                    	
                    	for(int j=0;j<member.get(i).getMemberCourse().size();++j){
                    		
                    		for(int z=0;z<7;++z){
                    			if(data[i][j][z]==1){
                    				countx++;
                        			if(countx==(seleIndex+1)){
                    			        member.get(i).getMemberCourse().get(j).setCdate(m.getDate(), z);
                    		}
                    	}
                    }
                    }
                    }
                    // 获取选中行的值并设置到输入框中
                    List4.set(seleIndex,m);
                }
            });

            // 删除按钮
            deleteBtn2.setOnAction(m->{
                // 获取选中
                int seleIndex = hView.getSelectionModel().getSelectedIndex();
                int countx=0;
                for(int i=0;i<member.size();++i){
                	
                	for(int j=0;j<member.get(i).getMemberCourse().size();++j){
                		
                		for(int z=0;z<7;++z){
                			if(data[i][j][z]==1){
                				countx++;
                    			if(countx==(seleIndex+1)){
                			        member.get(i).getMemberCourse().get(j).delCdate(z);
                		}
                	}
                }
                }
                }
                // 获取选中
                if(seleIndex!=-1){
                	
                    List4.remove(seleIndex);
                    
                }
            });
            MainPane.setCenter(admpane);
            });
            memberview.setOnAction(k->{
                    BorderPane MemberViewPane= new BorderPane();
                    MemberViewPane.setPadding(new Insets(10, 10, 10, 10));
                    //sView需要添加
                    
                    TableView sView = new TableView<Member>();
                    TableColumn<Member, String> aColumn = new TableColumn<>("会员等级");
                    TableColumn<Member, String> bColumn = new TableColumn<>("密码");
                    TableColumn<Member, String> cColumn = new TableColumn<>("会员等级");
                    TableColumn<Member, String> dColumn = new TableColumn<>("余额");
                    aColumn.setPrefWidth(200);
                    bColumn.setPrefWidth(200);
                    cColumn.setPrefWidth(200);
                    dColumn.setPrefWidth(200);
                    sView.getColumns().addAll(aColumn, bColumn, cColumn,dColumn);
                    sView.setPrefHeight(400);
                    aColumn.setSortable(false);
                    bColumn.setSortable(false);
                    cColumn.setSortable(false);
                    dColumn.setSortable(false);
                    List3 = FXCollections.observableArrayList();
                    aColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("memberId"));
                    bColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("password"));
                    cColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("level"));
                    dColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("change"));
                    sView.setItems(List3);
                    //底部功能
                    VBox bovbx=new VBox();
                    bovbx.setSpacing(20);
                    HBox bottomHPane1 = new HBox(5);
                    HBox bottomHPane2 = new HBox(5);
                    bottomHPane1.setPadding(new Insets(10,0,0,0));
                    bottomHPane1.setAlignment(Pos.CENTER);
                    bottomHPane2.setPadding(new Insets(10,0,0,0));
                    bottomHPane2.setAlignment(Pos.CENTER);
                    Label aLabel = new Label("会员名: ");
                    aLabel.setFont(buttonfont);
                    Label bLabel = new Label("密码: ");
                    bLabel.setFont(buttonfont);
                    Label cLabel = new Label("会员等级: ");
                    cLabel.setFont(buttonfont);
                    Label dlabel = new Label("余额:");
                    dlabel.setFont(buttonfont);
                    aTf = new TextField();
                    aTf.setPrefHeight(40);
                    bTf = new TextField();
                    bTf.setPrefHeight(40);
                    cTf = new TextField();
                    cTf.setPrefHeight(40);
                    dTf = new TextField();
                    dTf.setPrefHeight(40);
                    addBtn = new Button("添加");
                    addBtn.setPrefHeight(40);
                    addBtn.setPrefWidth(80);
                    addBtn.setFont(buttonfont3);
                    addBtn.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                    updateBtn = new Button("修改");
                    updateBtn.setPrefHeight(40);
                    updateBtn.setPrefWidth(80);
                    updateBtn.setFont(buttonfont3);
                    updateBtn.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                    deleteBtn = new Button("删除");
                    deleteBtn.setPrefHeight(40);
                    deleteBtn.setPrefWidth(80);
                    deleteBtn.setFont(buttonfont3);
                    deleteBtn.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                    back9 = new Button("返回");
                    back9.setPrefHeight(40);
                    back9.setPrefWidth(80);
                    back9.setFont(buttonfont3);
                    back9.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                    bottomHPane1.getChildren().addAll(aLabel,aTf,bLabel,bTf,cLabel,cTf,dlabel,dTf);
                    bottomHPane2.getChildren().addAll(addBtn,updateBtn,deleteBtn,back9);
                    bovbx.getChildren().addAll(bottomHPane1,bottomHPane2);
                    //查询TableView
                    sView.setOnMouseClicked(m->{
                        // 获取TableView选中的行和列
                        int seleIndex = sView.getSelectionModel().getSelectedIndex();
                        // 获取选中
                        if(seleIndex!=-1){
                            // 获取选中行的值并设置到输入框中
                            Member abcObj = List3.get(seleIndex);
                            // 选中行的值设置到输入框中
                            aTf.setText(abcObj.getMemberId());
                            bTf.setText(abcObj.getPassword());
                            cTf.setText(String.valueOf(abcObj.getLevel()));
                            dTf.setText(String.valueOf(abcObj.getChange()));

                        }

                    });
                    back9.setOnAction(o->{
                        MainPane.setCenter(managerpane);
                    });
                    // 添加按钮
                    addBtn.setOnAction(l->{
                        // 获取输入框
                        Member m = new Member(aTf.getText(),bTf.getText(), MemberLevel.valueOf(cTf.getText()),Integer.valueOf(dTf.getText()));
                        member.add(m);
                        List3.add(m);
                    });

                    // 修改按钮
                    updateBtn.setOnAction(o->{
                        // 获取选中行
                        int seleIndex = sView.getSelectionModel().getSelectedIndex();

                        // 获取选中
                        if(seleIndex!=-1){
                            Member temp =new Member();
                            temp=List3.get(seleIndex);
                            // 获取修改的数据并设置到输入框中
                            Member m = new Member(aTf.getText(),bTf.getText(), MemberLevel.valueOf(cTf.getText()),Integer.valueOf(dTf.getText()));
                            for(int i =0 ;i<member.size();i++){
                                if(member.get(i).getMemberId().equals(temp.getMemberId())){
                                    member.get(i).setMemberId(aTf.getText());
                                    member.get(i).setPassword(bTf.getText());
                                    member.get(i).setLevel(MemberLevel.valueOf(cTf.getText()));
                                    member.get(i).setChange(Integer.valueOf(dTf.getText()));
                                }

                            }
                            // 获取选中行的值并设置到输入框中
                            List3.set(seleIndex,m);
                        }
                    });

                    // 删除按钮
                    deleteBtn.setOnAction(m->{
                        // 获取选中
                        int seleIndex = sView.getSelectionModel().getSelectedIndex();

                        // 获取选中

                        if(seleIndex!=-1){
                            Member temp =new Member();
                            temp=List3.get(seleIndex);
                            for(int i = 0;i<member.size();i++){
                                if(member.get(i).getMemberId().equals(temp.getMemberId()))
                                     member.remove(i);
                            }
                            List3.remove(seleIndex);
                        }

                    });
                    MemberViewPane.setCenter(sView);
                     MainPane.setCenter(MemberViewPane);
                }); 
            //结束
            courseview.setOnAction(h->{
                TableView<Course> reoView =new TableView<>();
                reoView.setPrefHeight(480);
                BorderPane AllcoursePane = new BorderPane();
                AllcoursePane.setPadding(new Insets(10, 10, 10, 10));
                reoView = new TableView<Course>();
                TableColumn<Course, String> aColumn3 = new TableColumn<>("课程");
                TableColumn<Course, String> bColumn3 = new TableColumn<>("基础会员价格");
                TableColumn<Course, String> cColumn3 = new TableColumn<>("月会员价格");
                TableColumn<Course, String> dColumn3 = new TableColumn<>("季度会员价格");
                TableColumn<Course, String> eColumn3 = new TableColumn<>("年会员价格");
                
                aColumn3.setPrefWidth(200);
                bColumn3.setPrefWidth(200);
                cColumn3.setPrefWidth(200);
                eColumn3.setPrefWidth(200);
                dColumn3.setPrefWidth(200);
                reoView.getColumns().addAll(aColumn3, bColumn3, cColumn3, dColumn3, eColumn3);
                aColumn3.setSortable(false);
                bColumn3.setSortable(false);
                cColumn3.setSortable(false);
                dColumn3.setSortable(false);
                eColumn3.setSortable(false);
                List5 = FXCollections.observableArrayList();
                aColumn3.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
                bColumn3.setCellValueFactory(new PropertyValueFactory<Course, String>("priceForBasicMember"));
                cColumn3.setCellValueFactory(new PropertyValueFactory<Course, String>("priceForMonthlyMember"));
                dColumn3.setCellValueFactory(new PropertyValueFactory<Course, String>("priceForQuarterlyMember"));
                eColumn3.setCellValueFactory(new PropertyValueFactory<Course, String>("priceForAnnualMember"));
                //获取课程信息
                for (int i = 0; i < course.size(); i++) {
                    Course a = new Course();
                    a=course.get(i);
                    List5.add(a);
                }
                reoView.setItems(List5);
                //底部功能
                VBox bovbx=new VBox();
                bovbx.setSpacing(20);
                HBox bottomHPane1 = new HBox(5);
                HBox bottomHPane2 = new HBox(5);
                bottomHPane1.setPadding(new Insets(10, 0, 0, 0));
                bottomHPane1.setAlignment(Pos.CENTER);
                bottomHPane2.setPadding(new Insets(10, 0, 0, 0));
                bottomHPane2.setAlignment(Pos.CENTER);
                Label aLabel = new Label("课程");
                aLabel.setFont(buttonfont);
                Label bLabel = new Label("价格");
                bLabel.setFont(buttonfont);
                Label cLabel = new Label("月");
                cLabel.setFont(buttonfont);
                Label dLabel = new Label("季度");
                dLabel.setFont(buttonfont);
                Label eLabel = new Label("年");
                eLabel.setFont(buttonfont);
                Tf1 = new TextField();
                Tf2 = new TextField();
                Tf3 = new TextField();
                Tf4 = new TextField();
                Tf5 = new TextField();
                addBtn4 = new Button("添加");
                addBtn4.setPrefHeight(40);
                addBtn4.setPrefWidth(80);
                addBtn4.setFont(buttonfont3);
                addBtn4.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                updateBtn4 = new Button("修改");
                updateBtn4.setPrefHeight(40);
                updateBtn4.setPrefWidth(80);
                updateBtn4.setFont(buttonfont3);
                updateBtn4.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                deleteBtn4 = new Button("删除");
                deleteBtn4.setPrefHeight(40);
                deleteBtn4.setPrefWidth(80);
                deleteBtn4.setFont(buttonfont3);
                deleteBtn4.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                Button back8 = new Button("返回");
                back8.setPrefHeight(40);
                back8.setPrefWidth(80);
                back8.setFont(buttonfont3);
                back8.setStyle("-fx-background-color: #000080; -fx-text-fill: white;");
                back8.setOnAction(o->{
                    MainPane.setCenter(managerpane);
                });
                bottomHPane1.getChildren().addAll(aLabel, Tf1, bLabel, Tf2, cLabel, Tf3, dLabel, Tf4, eLabel, Tf5);
                bottomHPane2.getChildren().addAll(addBtn4, updateBtn4, deleteBtn4,back8);
                bovbx.getChildren().addAll(bottomHPane1,bottomHPane2);
                //
                AllcoursePane.setCenter(reoView);
                AllcoursePane.setBottom(bovbx);
                //查询TableView
                TableView<Course> finalReoView = reoView;
                reoView.setOnMouseClicked(m -> {
                    // 获取TableView选中的行和列
                    int seleIndex = finalReoView.getSelectionModel().getSelectedIndex();
                    // 获取选中
                    if (seleIndex != -1) {
                        // 获取选中行的值并设置到输入框中
                        Course abcObj = List5.get(seleIndex);
                        // 选中行的值设置到输入框中
                        Tf1.setText(abcObj.getName());
                        Tf2.setText(String.valueOf(abcObj.getPriceForBasicMember()));
                        Tf3.setText(String.valueOf(abcObj.getPriceForMonthlyMember()));
                        Tf4.setText(String.valueOf(abcObj.getPriceForQuarterlyMember()));
                        Tf5.setText(String.valueOf(abcObj.getPriceForAnnualMember()));
                    }

                });
                // 添加按钮
                addBtn4.setOnAction(z -> {
                    // 获取输入框
                    Course m = new Course(Tf1.getText(), Integer.valueOf(Tf2.getText()), Integer.valueOf(Tf3.getText()), Integer.valueOf(Tf4.getText()), Integer.valueOf(Tf5.getText()));
                    course.add(m);
                    List5.add(m);
                });

                // 修改按钮
                TableView<Course> finalReoView1 = reoView;
                updateBtn4.setOnAction(q -> {
                    // 获取选中行
                    int seleIndex = finalReoView1.getSelectionModel().getSelectedIndex();

                    // 获取选中
                    if (seleIndex != -1) {
                        int j =0;
                        Course ssy = new Course();
                        ssy = List5.get(seleIndex);
                        for(int i =0 ;i< course.size() ; i++){
                            if(ssy.getName().equals(course.get(i).getName()))
                                j=i;
                        }
                        // 获取修改的数据并设置到输入框中
                        Course m = new Course(Tf1.getText(), Integer.valueOf(Tf2.getText()), Integer.valueOf(Tf3.getText()), Integer.valueOf(Tf4.getText()), Integer.valueOf(Tf5.getText()));
                        course.get(j).setName(Tf1.getText());
                        course.get(j).setPriceForBasicMember(Integer.valueOf(Tf2.getText()));
                        course.get(j).setPriceForMonthlyMember(Integer.valueOf(Tf3.getText()));
                        course.get(j).setPriceForQuarterlyMember(Integer.valueOf(Tf4.getText()));
                        course.get(j).setPriceForAnnualMember(Integer.valueOf(Tf5.getText()));
                        // 获取选中行的值并设置到输入框中
                        List5.set(seleIndex, m);
                    }
                });

                // 删除按钮
                TableView<Course> finalReoView2 = reoView;
                deleteBtn4.setOnAction(w -> {
                    // 获取选中
                    int seleIndex = finalReoView2.getSelectionModel().getSelectedIndex();
                    // 获取选中
                    if (seleIndex != -1) {
                        Course m = new Course();
                        m = List5.get(seleIndex);
                        for(int i =0 ;i< course.size() ; i++){
                            if(m.getName().equals(course.get(i).getName()))
                                 course.remove(i);
                        }
                        List5.remove(seleIndex);
                    }

                });
                AllcoursePane.setCenter(reoView);
                MainPane.setCenter(AllcoursePane);
            });
            }    
            });
        
        back2.setOnAction(n -> {

            // 处理逻辑
            imageView.setVisible(true);
            MainPane.setCenter(tipsvbox);

        });

        //登录和注册界面
        loginbutton.setOnAction(e -> {
            imageView.setVisible(true);
            MainPane.setCenter(bpane);
        });
        registerbutton.setOnAction(e -> {
            imageView.setVisible(true);
            MainPane.setCenter(bpane2);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}