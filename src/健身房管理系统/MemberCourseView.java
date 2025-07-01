package ��������ϵͳ;

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
        TableColumn<Coursecopy, String> aColumn2 = new TableColumn<>("�û��˺�");
        TableColumn<Coursecopy, String> bColumn2 = new TableColumn<>("�γ���");
        TableColumn<Coursecopy, String> cColumn2 = new TableColumn<>("�γ�����");
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
        //����һ������������
        for (int i = 0; i < 7; i++) {
            List4.add(new Coursecopy());
        }
        hView.setItems(List4);
        //�ײ�����Ĺ���
        HBox bottomHPane = new HBox(5);
        bottomHPane.setPadding(new Insets(10,0,0,0));
        bottomHPane.setAlignment(Pos.CENTER);
        Label aLabel = new Label("�û����� ");
        Label bLabel = new Label("�γ����ƣ� ");
        Label cLabel = new Label("���ڣ� ");
        aTf2 = new TextField();
        bTf2 = new TextField();
        cTf2 = new TextField();
        addBtn2 = new Button("���");
        updateBtn2 = new Button("�޸�");
        deleteBtn2 = new Button("ɾ��");
        bottomHPane.getChildren().addAll(aLabel,aTf2,bLabel,bTf2,cLabel,cTf2,addBtn2,updateBtn2,deleteBtn2);
        //
        mainPane.setCenter(hView);
        mainPane.setBottom(bottomHPane);
        //���TableView
        hView.setOnMouseClicked(e->{
            // �õ�TableView��ѡ���е��кţ�ûѡ�еĻ�����-1��
            int seleIndex = hView.getSelectionModel().getSelectedIndex();
            // �����ѡ��
             if(seleIndex!=-1){
                // �������б��еõ����ж���
                Coursecopy abcObj = List4.get(seleIndex);

                // ��ѡ���е���ֵ�ŵ���Ӧ���ı�����
                aTf2.setText(abcObj.getId());
                bTf2.setText(abcObj.getName());
                cTf2.setText(String.valueOf(abcObj.getDate()));
            }

        });
        // ���add��ť�¼�
        addBtn2.setOnAction(e->{
            // �����¶���
            Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(), LocalDate.parse(cTf2.getText()));
            List4.add(m);
        });

        // ����޸İ�ť�¼�
        updateBtn2.setOnAction(e->{
            // �õ�ѡ���е��к�
            int seleIndex = hView.getSelectionModel().getSelectedIndex();

            // �����ѡ����
            if(seleIndex!=-1){
                // �����޸����ݴ����¶���
                Coursecopy m = new Coursecopy(aTf2.getText(),bTf2.getText(),LocalDate.parse(cTf2.getText()));
                // �����кŸ��������б��е�Ԫ��
                List4.set(seleIndex,m);
            }
        });

        // ���ɾ����ť�¼�
        deleteBtn2.setOnAction(e->{
            // ��ȡѡ����
            int seleIndex = hView.getSelectionModel().getSelectedIndex();

            // �����ѡ����
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