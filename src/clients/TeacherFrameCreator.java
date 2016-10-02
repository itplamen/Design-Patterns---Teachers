package clients;

import panels.BasicPanel;

// Concrete Creator from the 'Factory Method' pattern. Creates teacher frame.
public class TeacherFrameCreator extends FrameCreator {

	@Override
	public BasicFrame createFrame(String frameTitle, BasicPanel panel) {
		return new TeacherFrame(frameTitle, panel);
	}
}
