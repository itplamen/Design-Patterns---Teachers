package panels;

import java.util.List;

import javax.swing.JPanel;

import models.Teacher;

// The 'Component' abstract class from Decorator Pattern
public abstract class BasicPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public abstract void setBackgroundColor();
	
	public abstract void setLayoutManager();
	
	public abstract List<Teacher> getTeachers();
	
	public abstract void clearFields();
}
