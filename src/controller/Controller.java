package controller;

import java.awt.event.ActionEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.domain.Appointment;


public class Controller implements EventHandler{
	private ObservableList<Appointment> dataAppointments = FXCollections.observableArrayList();
	
	public TableView<Appointment> getTableView(TableView tableAppointments)
	{
		tableAppointments = new TableView<Appointment>();
		return tableAppointments;
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}
	

	
}
