package clients;

import panels.BasicPanel;

// Concrete Product
public class UniversityFrame extends BasicFrame {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_X = 10;
	private static final int FRAME_Y = 10;
	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 520;
	
	public UniversityFrame(String frameTitle, BasicPanel panel) {
		super(frameTitle, panel);
		
		this.setVisible(true);
		this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(UniversityFrame.EXIT_ON_CLOSE);
		
		this.getFramePanel().setBackgroundColor();
		this.getFramePanel().setLayoutManager();
	}
}
