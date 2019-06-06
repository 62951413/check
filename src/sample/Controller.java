package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    @FXML
    private Button update;
    @FXML
    private Button down;


    @FXML
    public void selectExcel(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择Excel文件");
        Stage selectFile = new Stage();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"), new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
        File file = fileChooser.showOpenDialog(selectFile);
        if (file != null) {
            System.out.println(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            Manager.createRecord(file, workbook, 1);
        }

    }
}
