package model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import model.dao.DBConnection;




@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private int id;
	private String time;
	private String description;
	
	public Appointment(String time, String description) {
		this.time = time;
		this.description = description;
	}
	public Appointment()
	{
		
	}
	public int getId() {
		return id;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void addAppointment(Appointment newAppointment)
	{
		DBConnection.getInstance().getEm().getTransaction().begin();
		DBConnection.getInstance().getEm().persist(newAppointment);
		DBConnection.getInstance().getEm().getTransaction().commit();
	}
	
	public static void removerCompromisso(int id) throws NullPointerException
	{
		Appointment comp = buscarId(id);
		DBConnection.getInstance().getEm().getTransaction().begin();
		DBConnection.getInstance().getEm().remove(comp);
		DBConnection.getInstance().getEm().getTransaction().commit();

	};
	
	public static Appointment buscarId(int id) throws NullPointerException
	{
		try {
			Appointment comp = DBConnection.getInstance().getEm().find(Appointment.class, id);
			comp.getId();
			return comp;
		}
		catch (NullPointerException e) {
			throw e;
		}
	}
	
	public static List<Appointment> buscarLista()
	{
		Query comp = model.dao.DBConnection.getInstance().getEm().createQuery("SELECT p FROM Compromisso p");
		List<Appointment> compromissos = comp.getResultList();
		
		return compromissos;
	}
	
	public static void alterarCompromisso()
	{
		DBConnection.getInstance().getEm().getTransaction().begin();
		DBConnection.getInstance().getEm().getTransaction().commit();
	}
}
