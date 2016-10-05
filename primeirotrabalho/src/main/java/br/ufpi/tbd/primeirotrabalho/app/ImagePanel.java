package br.ufpi.tbd.primeirotrabalho.app;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private BufferedImage image;

	public ImagePanel(InputStream is) {
		try {
			if(is != null){
				image = ImageIO.read(is);
			}else{
				image = null;
			}
			
		} catch (IOException ex) {
			
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}
}
