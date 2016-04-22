package arenaMode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.UIStack;

public class FullscreenSetup {
	
	public static JPanel setFullscreen(JFrame jFrame) {
		jFrame.setLayout(new BorderLayout());
        jFrame.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight() - 30);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jFrame);
        JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(jFrame.getWidth(), jFrame.getHeight()));
		mainPanel.setLayout(new BorderLayout());
		jFrame.add(mainPanel);
     	jFrame.setResizable(false);
        jFrame.setVisible(true);
		UIStack.setMainPanel(mainPanel);
        return mainPanel;
	}
	
}
