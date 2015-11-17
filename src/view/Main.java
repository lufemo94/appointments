package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.domain.Appointment;
import view.EditingCell;

public class Main extends Application {
	private Stage window;
	private VBox vbox;
	private Button btAdd, btRemove;
	private TextField txtTime, txtDesc;
	private TableView<Appointment> tableAppointments;
	private ObservableList<Appointment> dataAppointments = FXCollections.observableArrayList();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		configureVbox();
		configureWindow();
	}
	

	private void configureVbox()
	{
		vbox = new VBox(10);
		
		// Horizontal boxes
		HBox hbButtons = new HBox(20);
		hbButtons.setAlignment(Pos.CENTER);
		HBox hbInputs = new HBox(20);
		hbInputs.setAlignment(Pos.CENTER);
		
		// Buttons
		btAdd = new Button("Adicionar");
		btAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addButtonClicked();
			}
		});
		
		btRemove = new Button("Apagar");
		btRemove.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				//deleteButtonClicked();
			}
		});
		
		// Time Input
		txtTime = new TextField();
		txtTime.setPromptText("Horário");
		
		// Description Input
		txtDesc = new TextField();
		txtDesc.setPromptText("Descrição");
		
		// Table Appointments
		configureTable();
		
		
		hbInputs.getChildren().addAll(txtTime, txtDesc, btAdd, btRemove);
		vbox.getChildren().addAll(tableAppointments, hbButtons, hbInputs);
	}
	
	public void addButtonClicked()
	{
		Appointment appointment = new Appointment();
		appointment.setTime(txtTime.getText());
		appointment.setDescription(txtDesc.getText());
		tableAppointments.getItems().add(appointment);
		Appointment.addAppointment(appointment);
		txtTime.clear();
		txtDesc.clear();
	}
	
	private void configureTable()
	{
		tableAppointments = new TableView<Appointment>();
		
		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
		
		
		// Time Column
		TableColumn timeColumn = new TableColumn("Horário");
		timeColumn.setResizable(false);
		timeColumn.setPrefWidth(100);
		timeColumn.setSortable(false);
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		timeColumn.setCellFactory(cellFactory);

		// Description Column
		TableColumn descColumn = new TableColumn("Descrição");
		descColumn.setPrefWidth(510);
		descColumn.setResizable(false);
		descColumn.setSortable(false);
		descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		descColumn.setCellFactory(cellFactory);
		
		tableAppointments.setItems(dataAppointments);
		tableAppointments.setEditable(true);
		tableAppointments.getColumns().addAll(timeColumn, descColumn);
	}
	
	private void configureWindow()
	{
		window.setTitle("Agenda");
		Scene cena = new Scene(vbox, 600, 450);
		window.setScene(cena);
		window.setResizable(false);
		window.show();
	}

}
