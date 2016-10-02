package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Teacher;
import contracts.IOperations;

public class TeacherSortProxy implements IOperations {
	
	private IOperations teacherAdapter;
	
	public TeacherSortProxy(IOperations teacherAdapter) {
		this.teacherAdapter = teacherAdapter;
	}
	
	@Override
	public void addTeacher(Teacher teacher) {
		this.teacherAdapter.addTeacher(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		List<Teacher> sortedTeachers = new ArrayList<Teacher>(this.teacherAdapter.getAllTeachers());
		
		Collections.sort(sortedTeachers, new Comparator<Teacher>() {
            public int compare(Teacher firstTeacher, Teacher secondTeacher) {
                return firstTeacher.getFirstName().compareTo(secondTeacher.getFirstName());
            }
        });
		
		return sortedTeachers;
	}
	
	@Override
	public Teacher getTeacher(int id) {
		return this.teacherAdapter.getTeacher(id);
	}
	
	@Override
	public boolean updateTeacher(int id, Teacher teacher) {
		return this.teacherAdapter.updateTeacher(id, teacher);
	}

	@Override
	public boolean deleteTeacher(int id) {
		return this.teacherAdapter.deleteTeacher(id);
	}
}
