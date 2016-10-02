package contracts;

import java.util.List;

import models.Teacher;

public interface IOperations {
	
	public void addTeacher(Teacher teacher);

    public List<Teacher> getAllTeachers();
    
    public Teacher getTeacher(int id);
    
    public boolean updateTeacher(int id, Teacher teacher);
    
    public boolean deleteTeacher(int id);
}
