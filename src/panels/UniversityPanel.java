package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import contracts.IOperations;

import clients.BasicFrame;
import clients.FrameCreator;
import clients.TeacherFrameCreator;
import data.AdapterLocalDB;
import data.DataValidationProxy;
import data.TeacherSortProxy;

import models.Teacher;

// The 'ConcreteComponent' class from Decorator Pattern
public class UniversityPanel extends BasicPanel {
	private static final long serialVersionUID = 1L;
	
	private static final int PANEL_PADDING = 10;
	private static final int DELETE_FRAME_WIDTH = 350;
	private static final int DELETE_FRAME_HEIGHT = 100;
	private static final int SCROLL_PANE_WIDHT = 500;
	private static final int SCROLL_PANE_HEIGHT = 420;
	private static final String[] COLUMN_NAMES = {"ID", "First name", "Last name", "Personal ID", "Gender", "City"};
	
	private final JButton addNewTeacherButton = new JButton("Add");
	private final JButton showAllTeachersButton = new JButton("Show");
	private final JButton updateTeacherButton = new JButton("Update");
	private final JButton deleteTeacherButton = new JButton("Delete");
	private final JButton sortTeacherButton = new JButton("Sort");
	
	private final JTable table = new JTable();
	private final DefaultTableModel defaultTableModel = new DefaultTableModel(0, 0);
	private final JScrollPane scrollPane = new JScrollPane(table, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	private FrameCreator teacherFrameCreator;
	private IOperations validationProxy;
	private IOperations sortProxy;
	private BasicPanel updatablePanelDecorator;
	
	public UniversityPanel() {
		this.defaultTableModel.setColumnIdentifiers(COLUMN_NAMES);
		this.table.setModel(this.defaultTableModel);
		this.scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDHT, SCROLL_PANE_HEIGHT));
		this.add(this.addNewTeacherButton);
		this.add(this.showAllTeachersButton);
		this.add(this.updateTeacherButton);
		this.add(this.deleteTeacherButton);
		this.add(this.sortTeacherButton);
		this.add(this.scrollPane);
		this.setBorder(new EmptyBorder(PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING));	
		
		this.addNewTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherFrameCreator = new TeacherFrameCreator();
				teacherFrameCreator.createFrame("New Teacher Frame", new TeacherPanel());
			}
		});
		
		this.showAllTeachersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validationProxy = new DataValidationProxy(new AdapterLocalDB());
				showAllTeachers(validationProxy.getAllTeachers());
			}
		});
		
		this.updateTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherPanel teacherPanel = new TeacherPanel();
				teacherFrameCreator = new TeacherFrameCreator();
				teacherFrameCreator.createFrame("Update Teacher Frame", teacherPanel);
				
				updatablePanelDecorator = new UpdatableTeacherPanel(teacherPanel);
				updatablePanelDecorator.setBackgroundColor();
				updatablePanelDecorator.setLayoutManager();				
			}
		});
		
		this.deleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherFrameCreator = new TeacherFrameCreator();
				TeacherPanel teacherPanel = new TeacherPanel();
				
				BasicFrame deletableTeacherFrame = teacherFrameCreator.createFrame("Delete Teacher Frame", teacherPanel);
				deletableTeacherFrame.setFrameSize(DELETE_FRAME_WIDTH, DELETE_FRAME_HEIGHT);
				
				DeletableTeacherPanel deletablePanelDecorator = new DeletableTeacherPanel(teacherPanel);
				deletablePanelDecorator.setBackgroundColor();
				deletablePanelDecorator.setLayoutManager();
			}
		});
		
		this.sortTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortProxy = new TeacherSortProxy(new AdapterLocalDB());
				showAllTeachers(sortProxy.getAllTeachers());
			}
		});
	}

	@Override
	public void setBackgroundColor() {
		this.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void setLayoutManager() {
		this.setLayout(new FlowLayout());
	}
	
	/**
	 * Returns the all data (teachers) from the JTable.
	 */
	@Override
	public List<Teacher> getTeachers() {
		return this.validationProxy.getAllTeachers();
	}

	/**
	 * Clears JTable from data by removing all the table rows.
	 */
	@Override
	public void clearFields() {
		this.defaultTableModel.setRowCount(0);
	}
	
	/**
	 * Fills JTable with teachers.
	 * @param teachers - all the teachers from the table in LocalDB class.
	 */
	private void showAllTeachers(List<Teacher> teachers) {
		this.clearFields();
		
		for (Teacher teacher : teachers) {
			this.defaultTableModel.addRow(new Object[] {
	        		teacher.getID(), teacher.getFirstName(), teacher.getLastName(), 
	        		teacher.getPersonalID(), teacher.getGender(), teacher.getCity()});
		}
	}
}
