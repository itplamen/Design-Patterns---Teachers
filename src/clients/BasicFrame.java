package clients;

import javax.swing.JFrame;

import panels.BasicPanel;

// Abstract Product
public abstract class BasicFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private BasicPanel framePanel;
	
	public BasicFrame(String title, BasicPanel panel) {
		this.setResizable(true);
		this.setFrameTitle(title);
		this.setFramePanel(panel);
	}
	
	public BasicPanel getFramePanel() {
		return this.framePanel;
	}
	
	public void setFramePanel(BasicPanel panel) {
		if (panel == null) {
			throw new NullPointerException("Frame's panel cannot be null!");
		}
		
		this.framePanel = panel;
		this.setContentPane(this.framePanel);
	}
	
	public void setFrameTitle(String title) {
		if (title.isEmpty()) {
			throw new NullPointerException("Frame title cannot be null!");
		}
		
		this.setTitle(title);
	}
	
	public void setFrameSize(int width, int height) {
		if (width <= 0) {
			throw new IndexOutOfBoundsException("Frame's width cannot be zero or negative!");
		}
		
		if (height <= 0) {
			throw new IndexOutOfBoundsException("Frame's height cannot be zero or negative!"); 
		}
		
		this.setSize(width, height);
	}
}