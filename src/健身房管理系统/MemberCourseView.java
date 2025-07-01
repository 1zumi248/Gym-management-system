package 健身房管理系统;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MemberCourseView extends  Application {
    private TableView<Coursecopy> hView;
    private TextField aTf2, bTf2, cTf2;
    private Button addBtn2, updateBtn2, deleteBtn2;
    private ObservableList<Coursecopy> List4;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        hView = new TableView<Coursecopy>();
        TableColumn<Coursecopy, String> aColumn2 = new TableColumn<>("用户账号");
        TableColumn<Coursecopy, String> bColumn2 = new TableColumn<>("课程名");
        TableColumn<Coursecopy, String> cColumn2 = new TableColumn<>("课程日期");
        aColumn2.setPrefWidth(200);
        bColumn2.setPrefWidth(200);
        cColumn2.setPrefWidth(200);
        hView.getColumns().addAll(aColumn2, bColumn2, cColumn2);
        aColumn2.setSortable(false);
        bColumn2.setSortable(false);
        cColumn2.setSortable(false);
        List4 = FXCollections.observableArrayList();
        aColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("Id"));
        bColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("name"));
        cColumn2.setCellValueFactory(new PropertyValueFactory<Coursecopy, String>("date"));
        //方案一建立副本来在
        for (int i = 0; i < 7; i++) {
            List4.add(new Coursecopy());
        }
        hView.setItems(List4);
        //底部组件的构建
        HBox bottomHPane = new HBox(5);
        bottomHPane.setPadding(new Insets(10,0,0,0));
        bottomHPane.setAlignment(Pos.CENTER);
        Label aLabel = new Label("用户名： ");
        Label bLabel = new Label("课程名称： ");
        Label cLabel = new Label("日期： ");
        aTf2 = new TextField();
        bTf2 = new TextField();
        cTf2 = new TextField();
        addBtn2 = new Button("添加");
        updateBtn2 = new Button("修改");
        deleteBtn2 = new Button("删除");
        bottomHPane.getChildren().addAll(aLabel,aTf2,bLabel,bTf2,cLabel,cTf2,addBtn2,updateBtn2,deleteBtn2);
        //
        mainPane.setCenter(hView);
        mainPane.setBottom(bottomHPane);
        //点击TableView
        hView.setOnMouseClicked(e->{
            // 得到TableView中选中行的行号（没选中的话返回-1）
            int seleIndex = hView.getSelectionModel().getSelectedIndex();
            // 如果有选中
             if(seleIndex!=-1){
                // 从数据列表中得到该行对象
                Coursecopy abcObj = List4.get(seleIndex);

                // 将选中行的列值放到对应的文本框中
                aTf2.setText(abcObj.getId());
                bTf2.setText(abcObj.getName());
                cTf2.setText(String.valueOf(abcObj.getDate()));
            }

        });
        // 点击add按钮事件
        addBtn2.setOnAction(e->{
            // 创建新对象
            Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(), LocalDate.parse(cTf2.getText()));
            List4.add(m);
        });

        // 点击修改按钮事件
        updateBtn2.setOnAction(e->{
            // 得到选中行的行号
            int seleIndex = hView.getSelectionModel().getSelectedIndex();

            // 如果有选中行
            if(seleIndex!=-1){
                // 根据修改内容创建新对象
                Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(),LocalDate.parse(cTf2.getText()));
                // 根据行号更新数据列表中的元素
                List4.set(seleIndex,m);
            }
        });

        // 点击删除按钮事件
        deleteBtn2.setOnAction(e->{
            // 获取选中行
            int seleIndex = hView.getSelectionModel().getSelectedIndex();

            // 如果有选中行
            if(seleIndex!=-1){
                List4.remove(seleIndex);
            }

        });
        mainPane.setCenter(hView);
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void mian(String[] args) {
        launch(args);
    }
}