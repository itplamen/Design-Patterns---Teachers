package panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import contracts.IOperations;

import models.Teacher;

import clients.Message;

import data.AdapterLocalDB;
import data.DataValidationProxy;

// The 'ConcreteDecorator' class
public class DeletableTeacherPanel extends PanelDecorator {
	private static final long serialVersionUID = 1L;
	private static final int NUMBER_OF_TEXT_FILED_COLUMNS = 10;
	
	private final JLabel idLabel = new JLabel("Enter ID:");
	private final JTextField idTextField = new JTextField();
	private final JButton deleteButton = new JButton("Delete");
	private final JButton clearButton = new JButton("Clear");
	
	private IOperations validationProxy;
	
	public DeletableTeacherPanel(BasicPanel panel) {
		super(panel);
		
		this.getPanel().removeAll();
		this.getPanel().add(this.idLabel);
		this.getPanel().add(this.idTextField);
		this.getPanel().add(this.deleteButton);
		this.getPanel().add(this.clearButton);
		
		this.idTextField.setColumns(NUMBER_OF_TEXT_FILED_COLUMNS); 
		
		this.deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (!isTextFieldFilled()) {
					 Message.displayWarningMessage("Please enter ID!");
				 }
				 else {
					 try {
						 validationProxy = new DataValidationProxy(new AdapterLocalDB());
						 int id = Integer.parseInt(idTextField.getText());
						 validationProxy.deleteTeacher(id);	
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
		this.getPanel().setBackground(Color.RED);
	}
	
	@Override
	public void setLayoutManager() {
		this.getPanel().setLayout(new FlowLayout());
	}
	
	@Override
	public List<Teacher> getTeachers() {
		return this.getPanel().getTeachers();
	}
	
	@Override
	public void clearFields() {
		this.idTextField.setText("");
	}
	
	private boolean isTextFieldFilled() {
		return !(this.idTextField.getText().isEmpty());
	}
}
