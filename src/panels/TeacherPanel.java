package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import contracts.IOperations;

import clients.Message;

import data.AdapterLocalDB;
import data.DataValidationProxy;

import models.City;
import models.Gender;
import models.Teacher;

// The 'ConcreteComponent' class from Decorator Pattern
public class TeacherPanel extends BasicPanel {
	private static final long serialVersionUID = 1L;
	private static final int NUMBER_OF_ROWS = 6;
	private static final int NUMBER_OF_COLUMNS = 2;
	
	private final JLabel firstNameLabel = new JLabel("First Name:");
	private final JLabel lastNameLabel = new JLabel("Last Name:");
	private final JLabel personalIdLabel = new JLabel("Personal ID:");
	private final JLabel genderLabel = new JLabel("Gender:");
	private final JLabel cityLabel = new JLabel("City");
	
	private final JTextField firstNameTextField = new JTextField();
	private final JTextField lastNameTextField = new JTextField();
	private final JTextField personalIdTextField = new JTextField();
	
	private final JComboBox<Gender> genderComboBox = 
			new JComboBox<Gender>(new Gender[] {Gender.NotSelected, Gender.Male, Gender.Female});
	private final JComboBox<City> cityComboBox = 
			new JComboBox<City>(new City[] {City.NotSelected, City.Sofia, City.Varna, City.Ruse, City.Plovdiv, City.Other});

	private final JButton addTeacherButton = new JButton("Add Teacher");
	private final JButton clearButton = new JButton("Clear");
	
	private IOperations validationProxy;
	
	public TeacherPanel() {
		this.add(this.firstNameLabel);
		this.add(this.firstNameTextField);
		this.add(this.lastNameLabel);
		this.add(this.lastNameTextField);
		this.add(this.personalIdLabel);
		this.add(this.personalIdTextField);
		this.add(this.genderLabel);
		this.add(this.genderComboBox);
		this.add(this.cityLabel);
		this.add(this.cityComboBox);
		this.add(this.addTeacherButton);
		this.add(this.clearButton);
		
		this.addTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher teacher = getTeacher();
				
				if (teacher != null) {
					validationProxy = new DataValidationProxy(new AdapterLocalDB());
					validationProxy.addTeacher(teacher);
				}
			}
		});
		
		this.clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
	}
	
	@Override
	public void setBackgroundColor() {
		this.setBackground(Color.PINK);
	}

	@Override
	public void setLayoutManager() {
		this.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
	}
	
	/**
	 * Returns the data (names, city, gender ...) from the fields about the teacher. 
	 * The List<Teacher> structure must contains exactly one teacher!
	 */
	@Override
	public List<Teacher> getTeachers() {
		List<Teacher> fieldsData = new ArrayList<Teacher>();
		
		try {
			Teacher newTeacher = new Teacher(
					this.firstNameTextField.getText(),
					this.lastNameTextField.getText(),
					this.personalIdTextField.getText(),
					(Gender) this.genderComboBox.getSelectedItem(),
					(City) this.cityComboBox.getSelectedItem());
			
			fieldsData.add(newTeacher);
		}
		catch (NullPointerException ex) {
			Message.displayWarningMessage("Please fill all fields!");
		}
		
		if (fieldsData.size() > 1) {
			throw new IndexOutOfBoundsException("Fields must contain data for exactly one teacher!");
		}
		
		return fieldsData;
	}
	
	@Override
	public void clearFields() {
		this.firstNameTextField.setText("");
		this.lastNameTextField.setText("");
		this.personalIdTextField.setText("");
		this.genderComboBox.setSelectedItem(Gender.NotSelected);
		this.cityComboBox.setSelectedItem(City.NotSelected);
	}
	
	private Teacher getTeacher() {
		if (this.getTeachers().size() == 0) {
			return null;
		}
		
		return this.getTeachers().get(0);
	}
}
