package cs2113.zombies;

import cs2113.util.DotPanel;
import cs2113.util.Helper;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;

import javax.swing.JFrame;

//Danny Nsouli
/*
 * Human Behavior Rules:
 * 		if (1 in 10 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space if not blocked by a wall
 *
 *
 */

public class ZombieSim extends JFrame implements MouseListener, KeyListener, ActionListener {

	private static final long serialVersionUID = -5176170979783243427L;


	protected static DotPanel dp;

	/* Defining constants using static final variables */
	public static final int MAX_X = 200;
	public static final int MAX_Y = 200;
	private static final int DOT_SIZE = 3;
	public static final int NUM_HUMANS = 200;
	public static final int NUM_BUILDINGS = 80;


	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen.
	 */
	public ZombieSim() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Braaiinnnnnsss");

		/* Creats and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);

		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		dp.addMouseListener(this);
		this.addKeyListener(this);

		/* Creats city */
		City world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);

		/* This is the Run Loop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having humans take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */
		while (true) {
			// Run update rules for world and everything in it
			world.update();
			// Draw to screen and then refresh
			world.draw();
			dp.repaintAndSleep(33);

		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window  */
		new ZombieSim();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == 0) {
			//System.out.print("RESET");
			City.resetSim();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (City.walls[e.getX() / DOT_SIZE][e.getY() / DOT_SIZE] == false) {
			City.drawZombies();
			char direction;
			int d = Helper.nextInt(4);
			if (d == 0) {
				direction = 'u';
			} else if (d == 1) {
				direction = 'd';
			} else if (d == 2) {
				direction = 'l';
			} else {
				direction = 'r';
			}
			City.zombies.add(new Zombie(e.getX() / DOT_SIZE, e.getY() / DOT_SIZE, direction));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
