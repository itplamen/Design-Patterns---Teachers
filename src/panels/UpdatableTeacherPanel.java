package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import contracts.IOperations;

import clients.Message;

import data.AdapterLocalDB;
import data.DataValidationProxy;

import models.Teacher;

// The 'ConcreteDecorator' class
public class UpdatableTeacherPanel extends PanelDecorator {
	private static final long serialVersionUID = 1L;
	private static final int NUMBER_OF_ROWS = 7;
	private static final int NUMBER_OF_COLUMNS = 2;
	private static final int ID_LABEL_INDEX = 0;
	private static final int ID_TEXT_FIELD_INDEX = 1;
	private static final int ADD_TEACHER_BUTTON_INDEX = 12;
	private static final int UPDATE_BUTTON_INDEX = 12;
	
	private final JLabel idLabel = new JLabel("Enter ID:");
	private final JTextField idTextField = new JTextField();
	private final JButton updateTeacherButton = new JButton("Update Teacher");
	private final JButton clearButton = new JButton("Clear fields");
	
	private IOperations validationProxy;
	
	public UpdatableTeacherPanel(BasicPanel panel) {
		super(panel);
		
		this.getPanel().add(this.idLabel, ID_LABEL_INDEX);
		this.getPanel().add(this.idTextField, ID_TEXT_FIELD_INDEX);
		this.getPanel().remove(ADD_TEACHER_BUTTON_INDEX);
		this.getPanel().add(this.updateTeacherButton, UPDATE_BUTTON_INDEX);
		this.getPanel().remove(13);
		this.getPanel().add(this.clearButton);
		
		this.updateTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher teacher = getTeacher();
				
				if (teacher != null) {
					try {
						validationProxy = new DataValidationProxy(new AdapterLocalDB());
						int id = Integer.parseInt(idTextField.getText());
						validationProxy.updateTeacher(id, getTeacher());	
					}
					catch (NumberFormatException ex) {
						Message.displayWarningMessage("ID must be an integer number!");
					}
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
		this.getPanel().setBackground(Color.GRAY);
	}
	
	@Override
	public void setLayoutManager() {
		this.getPanel().setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
	}
	
	@Override
	public List<Teacher> getTeachers() {
		return this.getPanel().getTeachers();
	}
	
	@Override
	public void clearFields() {
		this.idTextField.setText("");
		this.getPanel().clearFields();
	}
	
	private Teacher getTeacher() {
		if (this.getTeachers().size() == 0) {
			return null;
		}
		
		return this.getTeachers().get(0);
	}
}
