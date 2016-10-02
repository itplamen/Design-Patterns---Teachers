package clients;

import panels.BasicPanel;

// Creator from the 'Factory Method' pattern. Defines abstract operation.
public abstract class FrameCreator {
	
	public abstract BasicFrame createFrame(String frameTitle, BasicPanel panel);
}
