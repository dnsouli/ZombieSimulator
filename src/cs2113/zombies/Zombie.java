package cs2113.zombies;
import cs2113.util.Helper;

import java.util.ArrayList;
/**
 * Created by dannynsouli on 11/8/16.
 */

//Danny Nsouli

public class Zombie extends Human {
    public int color;
//    public int x;
//    public int y;
//    public char direction;

    public Zombie(int x, int y, char direction){
        super(x, y, direction);

    }

    public void move() {
        //randomly turn
        //move forward

        ArrayList<Character> moveChoices = new ArrayList<Character>();

        //System.out.println(x + " " + y);
        int r = Helper.nextInt(5);



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

    public void checkSurroundings(ArrayList<Human>h, Zombie zombie, ArrayList<Zombie>z){

        for(int i = 0; i<h.size(); i++) {

            if(zombie.direction == 'u' && h.get(i).y - zombie.y <= 10 && h.get(i).y - zombie.y > 1 && h.get(i).x == zombie.x){

                if(zombie.y+1<200 && zombie.y+1>=0 && zombie.x<200 && zombie.x>=0 && City.walls[zombie.x][zombie.y+1] == false)
                zombie.y = zombie.y+1;

               // infect(h, zombie, z);

            }

            if(zombie.direction == 'd' && zombie.y-h.get(i).y<= 10 && zombie.y-h.get(i).y > 1 && h.get(i).x == zombie.x){

                if(zombie.y-1<200 && zombie.y-1>=0 && zombie.x<200 && zombie.x>=0 && City.walls[zombie.x][zombie.y-1] == false)
                zombie.y = zombie.y-1;

               // infect(h, zombie, z);

            }

            if(zombie.direction == 'r' && h.get(i).x - zombie.x <= 10 && h.get(i).x - zombie.x > 1 && h.get(i).y == zombie.y){

                if(zombie.x+1<200 && zombie.x+1>=0 && zombie.y<200 && zombie.y>=0 && City.walls[zombie.x+1][zombie.y] == false)
                zombie.x = zombie.x+1;

               // infect(h, zombie, z);

            }

            if(zombie.direction == 'l' && zombie.x-h.get(i).x <= 10 && zombie.x-h.get(i).x > 1 && h.get(i).y == zombie.y){

                if(zombie.x-1<200 && zombie.x-1>=0 && zombie.y<200 && zombie.y>=0 && City.walls[zombie.x-1][zombie.y] == false)
                zombie.x = zombie.x-1;

                //infect(h, zombie, z);

            }


        }

    }

    public void infect(ArrayList<Human>h, Zombie zombie, ArrayList<Zombie>z) {

        for(int i = 0; i<h.size(); i++) {

            //if(zombie.y - h.get(i).y == 1 || zombie.y - h.get(i).y == -1 || zombie.x - h.get(i).x == 1 || zombie.x - h.get(i).x == -1){
            if(zombie.y - h.get(i).y == 1 && zombie.x == h.get(i).x ){
                int newx = h.get(i).x;
                int newy = h.get(i).y;
                char newd = h.get(i).direction;
                h.remove(i);
                z.add(new Zombie(newx, newy, newd));
                //System.out.println("NEW ZOMBIE");
            }
            else if(zombie.y - h.get(i).y == -1 && zombie.x == h.get(i).x ){
                int newx = h.get(i).x;
                int newy = h.get(i).y;
                char newd = h.get(i).direction;
                h.remove(i);
                z.add(new Zombie(newx, newy, newd));
                //System.out.println("NEW ZOMBIE");
            }
            else if(zombie.x - h.get(i).x == 1 && zombie.y == h.get(i).y ){
                int newx = h.get(i).x;
                int newy = h.get(i).y;
                char newd = h.get(i).direction;
                h.remove(i);
                z.add(new Zombie(newx, newy, newd));
                //System.out.println("NEW ZOMBIE");
            }
            else if(zombie.x - h.get(i).x == -1 && zombie.y == h.get(i).y ){
                int newx = h.get(i).x;
                int newy = h.get(i).y;
                char newd = h.get(i).direction;
                h.remove(i);
                z.add(new Zombie(newx, newy, newd));
                //System.out.println("NEW ZOMBIE");
            }




        }


    }




}
