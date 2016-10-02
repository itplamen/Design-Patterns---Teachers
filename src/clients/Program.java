package clients;

import panels.UniversityPanel;

public class Program {
	
	public static void main(String[] args) {
		
		FrameCreator universityFrameCreator = new UniversityFrameCreator();
		universityFrameCreator.createFrame("University Frame", new UniversityPanel());
	}
}
