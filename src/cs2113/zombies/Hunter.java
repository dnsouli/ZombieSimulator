package cs2113.zombies;
import cs2113.util.Helper;

import java.util.ArrayList;
/**
 * Created by dannynsouli on 11/8/16.
 */

//Danny Nsouli

public class Hunter extends Human {

    public int color;
    public int x;
    public int y;
    public char direction;

    public Hunter(int x, int y, char direction){
        super(x, y, direction);

    }


    public void move() {
        //randomly turn
        //move forward

        ArrayList<Character> moveChoices = new ArrayList<Character>();

        //System.out.println(x + " " + y);
        int r = Helper.nextInt(10);



        if (r>0) {

            if (x + 1 < City.walls.length && City.walls[x + 1][y] == false && direction == 'r') {
                x = x+1;

            }
            if (y + 1 < City.walls[0].length && City.walls[x][y + 1] == false && direction == 'u') {
                y = y+1;

            }
            if (x - 1 > 0 && City.walls[x - 1][y] == false && direction == 'l') {
                x = x-1;

            }
            if (y - 1 > 0 && City.walls[x][y - 1] == false && direction == 'd') {
                y = y-1;

            }

        }
        else{
            int d = Helper.nextInt(4);
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

        }

    }

    public void checkSurroundingsHunter(ArrayList<Zombie>z, Hunter human){

        for(int i = 0; i<z.size(); i++) {

            if(human.direction == 'u' && z.get(i).y - human.y <= 10 && z.get(i).y - human.y > 1 && z.get(i).x == human.x){

                if(human.y+1<200 && human.y+1>=0 && human.x<200 && human.x>=0 && City.walls[human.x][human.y+1] == false)
                    human.y = human.y+1;


            }

            if(human.direction == 'd' && human.y-z.get(i).y<= 10 && human.y-z.get(i).y > 1 && z.get(i).x == human.x){

                if(human.y-1<200 && human.y-1>=0 && human.x<200 && human.x>=0 && City.walls[human.x][human.y-1] == false)
                    human.y = human.y-1;


            }

            if(human.direction == 'r' && z.get(i).x - human.x <= 10 && z.get(i).x - human.x > 1 && z.get(i).y == human.y){

                if(human.x+1<200 && human.x+1>=0 && human.y<200 && human.y>=0 && City.walls[human.x+1][human.y] == false)
                    human.x = human.x+1;


            }

            if(human.direction == 'l' && human.x-z.get(i).x <= 10 && human.x-z.get(i).x > 1 && z.get(i).y == human.y){

                if(human.x-1<200 && human.x-1>=0 && human.y<200 && human.y>=0 && City.walls[human.x-1][human.y] == false)
                    human.x = human.x-1;


            }


        }

    }

    public void killZombie(ArrayList<Zombie>z, Hunter hunter) {

        for(int i = 0; i<z.size(); i++) {


            if(hunter.y - z.get(i).y == 1 && hunter.x == z.get(i).x ){

                z.remove(i);


            }
            else if(hunter.y - z.get(i).y == -1 && hunter.x == z.get(i).x ){

                z.remove(i);


            }
            else if(hunter.x - z.get(i).x == 1 && hunter.y == z.get(i).y ){

                z.remove(i);


            }
            else if(hunter.x - z.get(i).x == -1 && hunter.y == z.get(i).y ){

                z.remove(i);


            }




        }


    }



}
