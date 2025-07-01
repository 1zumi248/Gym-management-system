package 健身房管理系统;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DatePickerLimitExample {
	private LocalDate maxDate = LocalDate.now().plusDays(6);
    private LocalDate minDate = LocalDate.now();
    private DatePicker dp;
    private final Map<LocalDate, Integer> dateSelectionCount = new HashMap<>();

    public DatePicker setCellFactory() {
        dp.setDayCellFactory(getDayCellFactory());
        return dp;
    }
    public DatePickerLimitExample(){
        dp=new DatePicker();
    }

    public DatePicker getDp() {
        return dp;
    }

    public Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item.isBefore(minDate)||item.isAfter(maxDate)){
                        	this.setDisable(true);
                        }
                        if (!empty && item != null) {
                            int count = dateSelectionCount.getOrDefault(item, 0);
                            if (count > 0) {
                                this.setDisable(true);
                                this.setStyle("-fx-background-color: #ffc0cb;");
                            }

                            this.setOnMouseClicked(event -> {
                                if (count == 0) {
                                    dateSelectionCount.put(item, count + 1);
                                }
                            });
                        }
                    }
                };
            }
        };
    }
}

