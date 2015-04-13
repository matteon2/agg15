/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Instruction.Condition;

import java.util.HashMap;

/**
 *
 * @author Andrew
 */
public class World {
    
    private final Cell[][] antWorld;
    private final HashMap<Integer, Ant> ants; //a list of alived ants with id as the key and the Ant object as the object
    private int seed;
    private int randomCallCounter = 0;
    
    public World(int antNum){
        antWorld = new Cell[150][150];
        ants = new HashMap<>();
        seed = 12345;
    }
    
    /**
     * GEOGRAPHY
     * check if the cell at position p is rocky and false if p is clear
     * @param p
     * @return boolean
     */
    public boolean rocky(Position p){
        return getAntWorld()[p.getX()][p.getY()].getRocky();
    }
    
    /**
     * GEOGRAPHY
     * true if there is an ant in the cell at position p
     * @param p
     * @return boolean
     */
    public boolean some_ant_is_at(Position p){
        return getAntWorld()[p.getX()][p.getY()].hasAnt();
    }
    
    /**
     * GEOGRAPHY
     * return the ant in the cell at position p
     * @param p
     * @return Ant
     * @throws Exception 
     */
    public Ant ant_at(Position p) throws Exception{
        return getAntWorld()[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * GEOGRAPHY
     * record the fact that the given ant is at position p
     * @param p
     * @param a 
     */
    public void set_ant_at(Position p, Ant a){
        getAntWorld()[p.getX()][p.getY()].setAnt(a);
        a.setPosition(p);
    }
    
    /**
     * GEOGRAPHY
     * record the fact that no ant is at position p
     * @param p 
     */
    public void clear_ant_at(Position p) throws Exception{
        getAntWorld()[p.getX()][p.getY()].clearAnt();
    }
    
    /**
     * GEOGRAPHY
     * true if an ant with the given id exists somewhere in the world
     * @param id
     * @return boolean
     */
    public boolean ant_is_alive(int id){
        return ants.containsKey(id);
    }
    
    /**
     * GEOGRAPHY
     * return current position of the ant with the given id
     * @param id
     * @return Position
     * @throws java.lang.Exception
     */
    public Position find_ant(int id) throws Exception{
        if(ant_is_alive(id)){
            Ant a = ants.get(id);
            return a.getPosition();
        }else{
            throw new Exception("The ant is not exist!");
        }
    }
    
    /**
     * GEOGRAPHY
     * kill the ant at position p means that the ant is not exist anymore, it should be removed from the ant list
     * @param p 
     */
    public void kill_ant_at(Position p) throws Exception{
        clear_ant_at(p);
        ants.remove(getAntWorld()[p.getX()][p.getY()].getAnt().getID());
    }
    
    /**
     * GEOGRAPHY
     * return the amount of food in the cell at position p
     * @param p
     * @return int
     */
    public int food_at(Position p){
        return getAntWorld()[p.getX()][p.getY()].getFoodNumber();
    }

    /**
     * GEOGRAPHY
     * record the fact that a given amount of food is at position p
     * @param p
     * @param f 
     */
    public void set_food_at(Position p, int f){
        getAntWorld()[p.getX()][p.getY()].setFoodNumber(f);
    }
    
    /**
     * GEOGRAPHY
     * true if the cell at position p is in the anthill of color c
     * @param p
     * @param c
     * @return boolean
     */
    public boolean anthill_at(Position p, Color c){
        return getAntWorld()[p.getX()][p.getY()].isAntHill();
    }
    
    /**
     * CHEMISTRY
     * set marker i of color c in cell p
     * @param p
     * @param c
     * @param marker
     * @throws Exception 
     */
    public void set_marker_at(Position p, Color c, int marker) throws Exception{
        getAntWorld()[p.getX()][p.getY()].setMarker(c, marker);
    }
    
    /**
     * CHEMISTRY
     * clear marker i of color c in cell p
     * @param p
     * @param c
     * @param marker 
     */
    public void clear_marker_at(Position p, Color c, int marker) throws Exception{
        getAntWorld()[p.getX()][p.getY()].clearMarker(c, marker);
    }
    
    /**
     * CHEMISTRY
     * true if marker i of color c is set in cell p
     * @param p
     * @param c
     * @param marker
     * @return boolean
     */
    public boolean check_marker_at(Position p, Color c, int marker){
        return getAntWorld()[p.getX()][p.getY()].checkMarker(c, marker);
    }
 
    /**
     * CHEMISTRY
     * true if ANY marker of color c is set in cell p
     * @param p
     * @param c
     * @return boolean
     */
    public boolean check_any_marker_at(Position p, Color c){
        return getAntWorld()[p.getX()][p.getY()].checkAnyMarker(c);
    }
    
    
    /**
     * PHENOMENOLOGY
     * takes a position p, a condition cond, and a color c (the color of the ant that is doing the sensing), 
     * and checks whether cond holds at p.
     * @param p
     * @param cond
     * @param c
     * @return boolean
     */
    public boolean cellMatches(Position p, Condition cond, Color c, int marker) throws Exception{
        boolean match;
        
        if(rocky(p)){
            match = cond.equals(Condition.ROCK);
        }
        else{
            switch(cond){
                case FRIEND:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color() == c);
                    break;
                case FOE:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color() != c);
                    break;
                case FRIENDWITHFOOD:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color() == c) && ant_at(p).has_food();
                    break;
                case FOEWITHFOOD:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color() != c) && ant_at(p).has_food();
                    break;
                case FOOD:
                    match = food_at(p) > 0;
                    break;
                case ROCK:
                    match = false;
                    break;
                case MARKER:
                    match = check_marker_at(p, c, marker);
                    break;
                case FOEMARKER:
                    match = check_any_marker_at(p, ant_at(p).other_color(c));
                    break;
                case HOME:
                    match = anthill_at(p, c);
                    break;
                case FOEHOME:
                    match = anthill_at(p, ant_at(p).other_color(c));
                    break;
                default:
                    match = false;
            }
        }
        return match;
    }
    
    
    /**
     * MARTIAL ARTS
     * check the number of adjacent ants from one group
     * @param p
     * @param c
     * @return int
     * @throws Exception 
     */
    public int adjacent_ants(Position p, Color c) throws Exception{
        int n = 0;
        for(int i = 0; i < 6; i++){
            Dir d = Dir.EAST;
            Position cel = ant_at(p).adjacent_cell(p, d.getDir(i));
            if(some_ant_is_at(cel) && ant_at(cel).get_color() == c){
                n++;
            }
        }
        return n;
    }
    
    /**
     * MARTIAL ARTS
     * Ant in position p die and become 3 units of food, the food number in the position p is changed.
     * @param p 
     */
    public void check_for_surrounded_ant_at(Position p) throws Exception{
        if(some_ant_is_at(p)){
            Ant a = ant_at(p);
            if(adjacent_ants(p, a.other_color(a.get_color())) >= 5){
                kill_ant_at(p);
                //if has_food(a) then 1 else 0
                int food;
                if(a.has_food()){
                    food = 1;
                }else{
                    food = 0;
                }
                set_food_at(p, food_at(p) + 3 + food);
            }
        }
    }
    
    /**
     * MARTIAL ARTS
     * checking possible death each time an ant moves
     * @param p 
     */
    public void check_for_surrounded_ants(Position p) throws Exception{
        check_for_surrounded_ant_at(p);
        for(int i = 0; i < 6; i++){
            Dir d = Dir.EAST;
            check_for_surrounded_ant_at(ant_at(p).adjacent_cell(p, d.getDir(i)));
        }
    }
    
    /**
     * KINETICS
     * takes an ant id, let the ant actually executes an instruction
     * @param id 
     */
    public void step(int id) throws Exception{
        if(ant_is_alive(id)){
            Position p;
            Ant a;
            p = find_ant(id);
            a = ant_at(p);
            if (a.get_resting() > 0){
                a.set_resting(a.get_resting()-1);
            }else{
                a.get_instruction(a.get_color(), a.get_state()).execute(this, a);
            }
        }
    }
    
    public void setSeed(int seed){
        this.seed = seed;
    }
    
    public int getSeed(){
        return seed;
    }
    
    /**
     * NUMBER THEORY
     * random number generator that same as customer's
     * @param n
     * @return int 
     */
    public int randomint(int n){
        for(; randomCallCounter < 3; randomCallCounter++){
            seed = seed * 22695477 + 1;
        }
        seed = (seed * 22695477) + 1;
	randomCallCounter++;
	int x = (int) ((Math.floor((double) seed / 65536)) % 16384);
	if (x < 0) {
            x = ((x + 16384) % 16384);
	}
        return x % n;
    }
    
    /**
     * initialize the world with empty cell
     * @return Cell[][]
     */
    public Cell[][] initWorld(){
        World w = new World(0);
        Cell[][] worldWithCell = w.getAntWorld();
        for(int i = 0; i < worldWithCell.length; i++){
            for(int j = 0; j < worldWithCell.length; j++){
                worldWithCell[i][j] = new Cell();
            }
        }
        return worldWithCell;
    }
    
    
    /**
     * make the random world 
     */
    public Cell[][] generateRandomWorld(){
        Cell[][] wwc = initWorld();
        RandomWorld r = new RandomWorld();
        char[][] rw = r.getRandomWorld();
        System.out.println(rw.length);
        for(int i = 0; i < rw.length; i++){
            for(int j = 0; j < rw.length; j++){
                if(rw[i][j] == '#'){
                    wwc[i][j].setRocky(true);
                }
                if(rw[i][j] == '.'){
                    
                }
                if(rw[i][j] == '+'){
                    wwc[i][j] = new AntHillCell(Color.RED);
                }
                if(rw[i][j] == '-'){
                    wwc[i][j] = new AntHillCell(Color.BLACK);
                }
                if(rw[i][j] == '1'){
                    wwc[i][j].setFoodNumber(1);
                }
                if(rw[i][j] == '2'){
                    wwc[i][j].setFoodNumber(2);
                }
                if(rw[i][j] == '3'){
                    wwc[i][j].setFoodNumber(3);
                }
                if(rw[i][j] == '4'){
                    wwc[i][j].setFoodNumber(4);
                }
                if(rw[i][j] == '5'){
                    wwc[i][j].setFoodNumber(5);
                }
                if(rw[i][j] == '6'){
                    wwc[i][j].setFoodNumber(6);
                }
                if(rw[i][j] == '7'){
                    wwc[i][j].setFoodNumber(7);
                }
                if(rw[i][j] == '8'){
                    wwc[i][j].setFoodNumber(8);
                }
                if(rw[i][j] == '9'){
                    wwc[i][j].setFoodNumber(9);
                }
            }
        }
        return wwc;
    }

    /**
     * @return the antWorld
     */
    public Cell[][] getAntWorld() {
        return antWorld;
    }
    
    
}
