package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHandler {

	public static final int MAX_PRESSED_ARROW = 6;
	private static ImageHandler handler;
	
	private BufferedImage[][] arrow;
	private ImageHandler() throws IOException{
		arrow = new BufferedImage[4][];
		arrow[Note.LEFT] = new BufferedImage[7];
		arrow[Note.RIGHT] = new BufferedImage[7];
		arrow[Note.UP] = new BufferedImage[7];
		arrow[Note.DOWN] = new BufferedImage[7];
		arrow[Note.LEFT][0] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW));
		arrow[Note.LEFT][1] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_0));
		arrow[Note.LEFT][2] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_1));
		arrow[Note.LEFT][3] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_2));
		arrow[Note.LEFT][4] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_3));
		arrow[Note.LEFT][5] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_4));
		arrow[Note.LEFT][6] = ImageIO.read(new File(Constants.RECEIVE_LEFT_ARROW_PRESSED_5));
		
		arrow[Note.RIGHT][0] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW));
		arrow[Note.RIGHT][1] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_0));
		arrow[Note.RIGHT][2] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_1));
		arrow[Note.RIGHT][3] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_2));
		arrow[Note.RIGHT][4] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_3));
		arrow[Note.RIGHT][5] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_4));
		arrow[Note.RIGHT][6] = ImageIO.read(new File(Constants.RECEIVE_RIGHT_ARROW_PRESSED_5));
		
		arrow[Note.UP][0] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW));
		arrow[Note.UP][1] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_0));
		arrow[Note.UP][2] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_1));
		arrow[Note.UP][3] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_2));
		arrow[Note.UP][4] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_3));
		arrow[Note.UP][5] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_4));
		arrow[Note.UP][6] = ImageIO.read(new File(Constants.RECEIVE_UP_ARROW_PRESSED_5));
		
		arrow[Note.DOWN][0] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW));
		arrow[Note.DOWN][1] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_0));
		arrow[Note.DOWN][2] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_1));
		arrow[Note.DOWN][3] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_2));
		arrow[Note.DOWN][4] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_3));
		arrow[Note.DOWN][5] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_4));
		arrow[Note.DOWN][6] = ImageIO.read(new File(Constants.RECEIVE_DOWN_ARROW_PRESSED_5));
		
	}
	public static void createImageHandler() throws IOException{
		if(handler == null){
			handler = new ImageHandler();
		}
	}
	public static ImageHandler getImageHandler(){
		return handler;
	}
	public BufferedImage getLeftArrow(int index){
		return arrow[Note.LEFT][index];
	}
	public BufferedImage getRightArrow(int index){
		return arrow[Note.RIGHT][index];
	}
	public BufferedImage getUpArrow(int index){
		return arrow[Note.UP][index];
	}
	public BufferedImage getDownArrow(int index){
		return arrow[Note.DOWN][index];
	}
}
