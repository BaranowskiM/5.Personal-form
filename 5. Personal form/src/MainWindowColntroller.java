import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Scanner;

import java.io.File;

public class MainWindowColntroller {

    private Stage primaryStage;
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private String firstName;
    private String lastName;
    private Integer room;
    private Integer startTime;
    private Integer endTime;
    private FileChooser fileChooser = new FileChooser();
    private Scanner in = null;
    private PrintWriter out = null;
    private int workingHours;
    private File file;


    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, Integer> roomColumn;
    @FXML private TableColumn<Person, Integer> startHourColumn;
    @FXML private TableColumn<Person, Integer> endHourColumn;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField roomField;
    @FXML private TextField startHourField;
    @FXML private TextField endHourField;

    @FXML private Rectangle room101;
    @FXML private Rectangle room102;
    @FXML private Rectangle room103;
    @FXML private Rectangle room104;
    @FXML private Rectangle room105;
    @FXML private Rectangle room106;

    @FXML private Button load;
    @FXML private Button add;
    @FXML private Button save;
    @FXML private Button raport;


    @FXML void loadButtonAction(ActionEvent event){


        fileChooser.setInitialDirectory(new File("C:\\Users\\install\\Documents\\JAVA\\!!Studia\\materiały\\4. INU\\Prace domowe\\INU_HW3_MB\\src"));
        file = fileChooser.showOpenDialog(primaryStage);
        personList.clear();
        try {
            in = new Scanner(file);

            while (in.hasNext()) {
                firstName = in.next();
                lastName = in.next();
                room = in.nextInt();
                startTime = in.nextInt();
                endTime = in.nextInt();

                personList.add(new Person(firstName, lastName, room, startTime, endTime, workingHours));
                tableView.setItems(personList);
            }
        }catch(IOException c){
            c.printStackTrace();
        } finally {
            if(in!=null) in.close();
        }
    }


    @FXML void addButtonAction(ActionEvent event){
        Integer roomInt = Integer.valueOf(roomField.getText());
        Integer startHour = Integer.valueOf(startHourField.getText());
        Integer endHour = Integer.valueOf(endHourField.getText());

        personList.add(new Person(firstNameField.getText(), lastNameField.getText(), roomInt, startHour, endHour, workingHours));
        tableView.setItems(personList);
    }


    @FXML void saveButtonAction(ActionEvent event){

        try{
            //out = new PrintWriter(("C:\\Users\\install\\Documents\\JAVA\\!!Studia\\materiały\\4. INU\\Prace domowe\\INU_HW3_MB\\src\\Pliki\\List.txt"));
            out = new PrintWriter(file);
            for(Person person: personList){
                out.printf("%s %s %d %d %d\r\n", person.getFirstName(), person.getLastName(), person.getRoom(), person.getStartTime(), person.getEndTime());
            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (in != null) out.close();
        }
    }

    @FXML void raportButtonAction(ActionEvent event){

        FileChooser saveChooser = new FileChooser();
        saveChooser.setInitialDirectory(new File("C:\\Users\\install\\Documents\\JAVA\\!!Studia\\materiały\\4. INU\\Prace domowe\\INU_HW3_MB\\src\\Raporty"));
        saveChooser.setTitle("Choose location to save raport");
        saveChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        File selectedFile = saveChooser.showSaveDialog(null);
        File file = new File(String.valueOf(selectedFile));

        PrintWriter outFile = null;

        try{
            outFile = new PrintWriter(file);
            Person temp;

            for(Person person: personList){
                    for(int j = 0; j < personList.size() - 1; j++){
                        if(personList.get(j).getWorkingHours() > personList.get(j + 1).getWorkingHours()){
                            temp = personList.get(j + 1);
                            personList.set(j + 1, personList.get(j));
                            personList.set(j,  temp);
                        }
                    }
            }

            for(Person person: personList){

                outFile.printf("%s %s %d %d %d\r\n", person.getFirstName(), person.getLastName(), person.getRoom(), person.getStartTime(), person.getEndTime());
            }


        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (in != null) outFile.close();
        }
    }

    public void initialize() {
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

    roomColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("room"));

    startHourColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("startTime"));

    endHourColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("endTime"));

    tableView.getSelectionModel().selectedItemProperty().addListener(
            (ov, old_value, new_value) -> {
                firstNameField.setText(new_value.getFirstName());
                lastNameField.setText(new_value.getLastName());
                roomField.setText(String.valueOf(new_value.getRoom()));
                startHourField.setText(String.valueOf(new_value.getStartTime()));
                endHourField.setText(String.valueOf(new_value.getEndTime()));

                if(new_value.getRoom() == 101){
                    room101.setFill(Color.YELLOW);
                    room102.setFill(Color.valueOf("1f93ff"));
                    room103.setFill(Color.valueOf("1f93ff"));
                    room104.setFill(Color.valueOf("1f93ff"));
                    room105.setFill(Color.valueOf("1f93ff"));
                    room106.setFill(Color.valueOf("1f93ff"));
                } else if(new_value.getRoom() == 102){
                    room101.setFill(Color.valueOf("1f93ff"));
                    room102.setFill(Color.YELLOW);
                    room103.setFill(Color.valueOf("1f93ff"));
                    room104.setFill(Color.valueOf("1f93ff"));
                    room105.setFill(Color.valueOf("1f93ff"));
                    room106.setFill(Color.valueOf("1f93ff"));
                } else if(new_value.getRoom() == 103){
                    room101.setFill(Color.valueOf("1f93ff"));
                    room102.setFill(Color.valueOf("1f93ff"));
                    room103.setFill(Color.YELLOW);
                    room104.setFill(Color.valueOf("1f93ff"));
                    room105.setFill(Color.valueOf("1f93ff"));
                    room106.setFill(Color.valueOf("1f93ff"));
                } else if(new_value.getRoom() == 104){
                    room101.setFill(Color.valueOf("1f93ff"));
                    room102.setFill(Color.valueOf("1f93ff"));
                    room103.setFill(Color.valueOf("1f93ff"));
                    room104.setFill(Color.YELLOW);
                    room105.setFill(Color.valueOf("1f93ff"));
                    room106.setFill(Color.valueOf("1f93ff"));
                } else if(new_value.getRoom() == 105){
                    room101.setFill(Color.valueOf("1f93ff"));
                    room102.setFill(Color.valueOf("1f93ff"));
                    room103.setFill(Color.valueOf("1f93ff"));
                    room104.setFill(Color.valueOf("1f93ff"));
                    room105.setFill(Color.YELLOW);
                    room106.setFill(Color.valueOf("1f93ff"));
                } else if(new_value.getRoom() == 106){
                    room101.setFill(Color.valueOf("1f93ff"));
                    room102.setFill(Color.valueOf("1f93ff"));
                    room103.setFill(Color.valueOf("1f93ff"));
                    room104.setFill(Color.valueOf("1f93ff"));
                    room105.setFill(Color.valueOf("1f93ff"));
                    room106.setFill(Color.YELLOW);
                }
            }
    );
}

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML public void closeStage(){
        primaryStage.close();
    }

}
