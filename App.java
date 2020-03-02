import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;





public class App {
	
	ImageProcessor imageprocessor = new ImageProcessor();
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	private JFrame frame;
	private JLabel imageLabel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.initGUI();
		app.runMainLoop(args);

	}
	
	private void initGUI() {
		frame = new JFrame("Camera Input Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);
	}
	
	private void runMainLoop(String[] args) {
		
		Mat mat = new Mat();
		Image tempImage;
		 VideoCapture cap = new VideoCapture(0);
		 cap.set(Videoio.CAP_PROP_FRAME_WIDTH, 320);
	     cap.set(Videoio.CAP_PROP_FRAME_HEIGHT, 240);
		 
		 if(cap.isOpened()) {
			 while(true) {
				 cap.read(mat);
				 
				 if(!mat.empty()) {
					 tempImage= imageprocessor.toBufferedImage(mat);
					 ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
					 imageLabel.setIcon(imageIcon);
					 frame.pack();
					 
				 }
				 else {
					 System.out.println("-- Frame not captured -- Break!");
				 }
			 }
		 }
		 else {
			 System.out.println("Couldn't open capture.");
		 }
	}

}
