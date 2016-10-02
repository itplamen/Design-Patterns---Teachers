package clients;

// Singleton
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class Message {
	private static Message instance;
	
	private Message() {
		
	}
	
	public static Message getInstance() {
		if (instance == null) {
			instance = new Message();
		}
		
		return instance;
	}
	
	public static void displayWarningMessage(String description) {
		JOptionPane.showMessageDialog
		(
			new JFrame(),
            description,
            "Warning Message",
            JOptionPane.WARNING_MESSAGE
		);
	}
	
	public static void displaySuccessMessage(String description) {
		JOptionPane.showMessageDialog
		(
			new JFrame(), 
			description, 
			"Success Message", 
			JOptionPane.OK_OPTION
		);	
	}
}
