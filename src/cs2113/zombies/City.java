package cs2113.zombies;
import java.util.ArrayList;
import cs2113.util.Helper;

import java.awt.Color;

//Danny Nsouli

public class City {

	/** walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans should never go into spaces that
	 *  have a wall.
	 */
	public static boolean walls[][];
	private static int width, height;
	static ArrayList<Human> humans = new ArrayList<Human>();
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Hunter> hunters = new ArrayList<Hunter>();

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];

		randomBuildings(numB);
		populate(numP);
		populateZombie();
		populateHunters();
	}


	/**
	 * Generates numPeople random people distributed throughout the city.
	 * People must not be placed inside walls!
	 *
	 * @param numP the number of people to generate
	 */
	private static void populate(int numP)
	{	int x;
		int y;
		int d;
		char direction;
		// Generate numPeople new humans randomly placed around the city.
		for(int i = 0; i<=numP; i++){
			x = Helper.nextInt(width);
			y = Helper.nextInt(height);
			d = Helper.nextInt(4);
			if(d == 0){
				direction = 'u';
			}
			else if(d == 1){
				direction = 'd';
			}
			else if(d == 2){
				direction = 'l';
			}
			else{
				direction = 'r';
			}
			while(walls[x][y] == true) {
				x = Helper.nextInt(width);
				y = Helper.nextInt(height);

			}
			humans.add(new Human(x, y, direction));
		}
		//
	}

	private static void populateZombie()
	{	int x;
		int y;
		int d;
		char direction;
		// Generate numPeople new humans randomly placed around the city.
		while(zombies.size()==0) {
			x = Helper.nextInt(width);
			y = Helper.nextInt(height);
			d = Helper.nextInt(4);
			if (d == 0) {
				direction = 'u';
			} else if (d == 1) {
				direction = 'd';
			} else if (d == 2) {
				direction = 'l';
			} else {
				direction = 'r';
			}
			while(walls[x][y] == true) {
				x = Helper.nextInt(width);
				y = Helper.nextInt(height);

			}
			zombies.add(new Zombie(x, y, direction));
		}
		//
	}


	private static void populateHunters()
	{	int x;
		int y;
		int d;
		char direction;
		// Generate numPeople new humans randomly placed around the city.
		for(int i = 0; i<=4; i++){
			x = Helper.nextInt(width);
			y = Helper.nextInt(height);
			d = Helper.nextInt(4);
			if (d == 0) {
				direction = 'u';
			} else if (d == 1) {
				direction = 'd';
			} else if (d == 2) {
				direction = 'l';
			} else {
				direction = 'r';
			}
			while (walls[x][y] == true) {
				x = Helper.nextInt(width);
				y = Helper.nextInt(height);

			}

			hunters.add(new Hunter(x, y, direction));
			//
		}

	}



	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	private static void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;

		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		// Move humans, zombies, etc

		for (int i = 0; i < humans.size(); i++) {
			humans.get(i).checkSurroundings(zombies, humans.get(i));
			humans.get(i).move();

		}
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).checkSurroundings(humans, zombies.get(i), zombies);
			zombies.get(i).move();
			zombies.get(i).infect(humans, zombies.get(i), zombies);
		}
		for (int i = 0; i < hunters.size(); i++) {
			hunters.get(i).checkSurroundingsHunter(zombies, hunters.get(i));
			hunters.get(i).move();
			hunters.get(i).killZombie(zombies, hunters.get(i));
		}

	}

	public static void resetSim(){
		zombies.clear();
		humans.clear();
		hunters.clear();
		for(int i = 0; i<walls.length; i++){
			for(int x = 0; x<walls[i].length; x++){
				walls[i][x] = false;
			}
		}

		ZombieSim.dp.repaintAndSleep(500);
		randomBuildings(ZombieSim.NUM_BUILDINGS);
		populate(ZombieSim.NUM_HUMANS);
		populateZombie();
		populateHunters();


	}


	/**
	 * Draws the buildings and all humans.
	 */
	public void draw(){
		/* Clear the screen */
		ZombieSim.dp.clear(Color.black);
		drawWalls();
		drawZombies();
		drawHunters();
		drawHumans();


	}

	public void drawHumans(){
		for(int i = 0; i<humans.size(); i++) {

			ZombieSim.dp.setPenColor(Color.white);

			ZombieSim.dp.drawDot(humans.get(i).x, humans.get(i).y);
			//System.out.println("Human local:" + humans.get(i).x + ", " + humans.get(i).y);
		}

	}

	public void drawHunters(){
		for(int i = 0; i<hunters.size(); i++) {

			ZombieSim.dp.setPenColor(Color.green);

			ZombieSim.dp.drawDot(hunters.get(i).x, hunters.get(i).y);

		}

	}

	public static void drawZombies(){
		for(int i = 0; i<zombies.size(); i++) {

			ZombieSim.dp.setPenColor(Color.red);

			ZombieSim.dp.drawDot(zombies.get(i).x, zombies.get(i).y);
			//System.out.println("Zombie local:" + zombies.get(i).x + ", " + zombies.get(i).y);

		}

	}

	/**
	 * Draws the buildings.
	 * First sets the color for drawing, then draws a dot at each space
	 * where there is a wall.
	 */
	private void drawWalls() {
		ZombieSim.dp.setPenColor(Color.DARK_GRAY);

		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}

}
