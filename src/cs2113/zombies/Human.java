package cs2113.zombies;
import cs2113.util.Helper;

import java.util.ArrayList;
/**
 * Created by dannynsouli on 11/8/16.
 */

//Danny Nsouli

public class Human {

    public int color;
    public int x;
    public int y;
    public char direction;

    public Human(int x, int y, char direction){
        this.x = x;
        this.y = y;
        this.direction = direction;

    }


    //public void draw(){
        //set color
        //drawDot(x,y)

    //}

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

    public void checkSurroundings(ArrayList<Zombie>z, Human human){

        for(int i = 0; i<z.size(); i++) {

            if(human.direction == 'u' && z.get(i).y - human.y <= 10 && z.get(i).y - human.y > 1 && z.get(i).x == human.x){

                human.direction = 'd';
                if(human.y-2<200 && human.y-2>=0 && human.x<200 && human.x>=0 && City.walls[human.x][human.y-2] == false)
                human.y = human.y-2;

            }


            if(human.direction == 'd' && human.y-z.get(i).y<= 10 && human.y-z.get(i).y > 1 && z.get(i).x == human.x){

                human.direction = 'u';
                if(human.y+2<200 && human.y+2>=0 && human.x<200 && human.x>=0 && City.walls[human.x][human.y+2] == false)
                human.y = human.y+2;

            }

            if(human.direction == 'r' && z.get(i).x - human.x <= 10 && z.get(i).x - human.x > 1 && z.get(i).y == human.y){

                human.direction = 'l';
                if(human.x-2<200 && human.x-2>=0 && human.y<200 && human.y>=0 && City.walls[human.x-2][human.y] == false)
                human.x = human.x-2;


            }

            if(human.direction == 'l' && human.x-z.get(i).x <= 10 && human.x-z.get(i).x > 1 && z.get(i).y == human.y){

                human.direction = 'r';
                if(human.x+2<200 && human.x+2>=0 && human.y<200 && human.y>=0 && City.walls[human.x+2][human.y] == false)
                human.x = human.x+2;


            }


        }

    }



}
