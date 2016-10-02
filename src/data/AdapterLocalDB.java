package data;

import java.util.List;

import models.Teacher;
import contracts.IOperations;

// The 'Adapter' class
public class AdapterLocalDB implements IOperations {
	
	private LocalDB adapteeLocalDB;
	
	public AdapterLocalDB() {
		this.adapteeLocalDB = LocalDB.getInstance();
	}

	@Override
	public void addTeacher(Teacher entity) {
		this.adapteeLocalDB.create(entity);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return this.adapteeLocalDB.read();
	}

	@Override
	public Teacher getTeacher(int id) {
		for (Teacher teacher : this.adapteeLocalDB.read()) {
			if (teacher.getID() == id) {
				return teacher;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean updateTeacher(int id, Teacher teacher) {
		return this.adapteeLocalDB.update(id, teacher);
	}
	
	@Override
	public boolean deleteTeacher(int id) {
		Teacher teacherToDelete = this.getTeacher(id);
		
		if (teacherToDelete == null) {
			return false;
		}
		
		this.adapteeLocalDB.delete(teacherToDelete);
		return true;
	}
}
