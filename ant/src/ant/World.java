/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Game.Game;
import Game.Player;
import Instruction.Condition;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Andrew
 */
public class World {
    
    private final Cell[][] antWorld;
    private final HashMap<Integer, Ant> ants; //a list of alived ants with id as the key and the Ant object as the object
    
    private final RandomWorld ranWorld;
    private ArrayList<AntHillCell> antHills;
    
    public World(){
        antWorld = new Cell[150][150];
        ants = new HashMap<>();
        ranWorld = new RandomWorld();
        antHills = new ArrayList();
    }
    
    public World(Cell[][] world){
        this.antWorld = world;
        this.ants = new HashMap<>();
        this.antHills = new ArrayList();
        this.ranWorld = new RandomWorld();
    }
    
    public boolean isRocky(Position p){
        return antWorld[p.getX()][p.getY()].isContainRock();
    }
    
    public Cell[][] initialiseWorld(){
        
        char[][] world = ranWorld.getRandomWorld();
        
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                
                if(world[i][j] == '#'){
                    Cell c = new Cell();
                    c.setContainRock(true);
                    c.setIsClear(false);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '.'){
                    Cell c = new Cell();
                    c.setIsClear(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '+'){
                    AntHillCell ahc = new AntHillCell(Color.RED);
                    ahc.setIsClear(false);
                    ahc.setContainRock(false);
                    ahc.setContainsRedAnt(true);
                    antWorld[i][j] = ahc;
                }
                if(world[i][j] == '-'){
                    AntHillCell ahc = new AntHillCell(Color.BLACK);
                    ahc.setIsClear(false);
                    ahc.setContainRock(false);
                    ahc.setContainsBlackAnt(true);
                    antWorld[i][j] = ahc;
                }
                if(world[i][j] == '1'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(1);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '2'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(2);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '3'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(3);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '4'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(4);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '5'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(5);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '6'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(6);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '7'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(7);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '8'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(8);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
                if(world[i][j] == '9'){
                    Cell c = new Cell();
                    c.setIsClear(false);
                    c.setFoodNumber(9);
                    c.setHasFood(true);
                    antWorld[i][j] = c;
                }
            }
        }
        return antWorld;
    }
    
    
//    public World getWorld(){
//        this.initialiseWorld();
//        
//    }
//    
    
    public static void main(String args[]){
        World w = new World();
        w.initialiseWorld();
    }

    
    /**
     * GEOGRAPHY
     * true if there is an ant in the cell at position p
     * @param p
     * @return boolean
     */
    public boolean some_ant_is_at(Position p){
        return initialiseWorld()[p.getX()][p.getY()].hasAnt();
    }
    
    /**
     * GEOGRAPHY
     * return the ant in the cell at position p
     * @param p
     * @return Ant
     * @throws Exception 
     */
    public Ant ant_at(Position p) throws Exception{
        return initialiseWorld()[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * GEOGRAPHY
     * record the fact that the given ant is at position p
     * @param p
     * @param a 
     */
    public void set_ant_at(Position p, Ant a){
        initialiseWorld()[p.getX()][p.getY()].setAnt(a);
        a.setPosition(p);
    }
    
    /**
     * GEOGRAPHY
     * record the fact that no ant is at position p
     * @param p 
     */
    public void clear_ant_at(Position p) throws Exception{
        initialiseWorld()[p.getX()][p.getY()].clearAnt();
    }
    
    /**
     * GEOGRAPHY
     * true if an ant with the given id exists somewhere in the world
     * @param id
     * @return boolean
     */
    public boolean ant_is_alive(int id){
        return getAnts().containsKey(id);
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
            Ant a = getAnts().get(id);
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
        getAnts().remove(initialiseWorld()[p.getX()][p.getY()].getAnt());
    }
    
    /**
     * GEOGRAPHY
     * return the amount of food in the cell at position p
     * @param p
     * @return int
     */
    public int food_at(Position p){
        return initialiseWorld()[p.getX()][p.getY()].getFoodNumber();
    }

    /**
     * GEOGRAPHY
     * record the fact that a given amount of food is at position p
     * @param p
     * @param f 
     */
    public void set_food_at(Position p, int f){
        initialiseWorld()[p.getX()][p.getY()].setFoodNumber(f);
    }
    
    /**
     * GEOGRAPHY
     * true if the cell at position p is in the anthill of color c
     * @param p
     * @param c
     * @return boolean
     */
    public boolean anthill_at(Position p, Color c){
        return initialiseWorld()[p.getX()][p.getY()].isAntHill();
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
        initialiseWorld()[p.getX()][p.getY()].setMarker(c, marker);
    }
    
    /**
     * CHEMISTRY
     * clear marker i of color c in cell p
     * @param p
     * @param c
     * @param marker 
     */
    public void clear_marker_at(Position p, Color c, int marker) throws Exception{
        initialiseWorld()[p.getX()][p.getY()].clearMarker(c, marker);
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
        return initialiseWorld()[p.getX()][p.getY()].checkMarker(c, marker);
    }
 
    /**
     * CHEMISTRY
     * true if ANY marker of color c is set in cell p
     * @param p
     * @param c
     * @return boolean
     */
    public boolean check_any_marker_at(Position p, Color c){
        return initialiseWorld()[p.getX()][p.getY()].checkAnyMarker(c);
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
        
        if(initialiseWorld()[p.getX()][p.getY()].isContainRock()){
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

    
  

 
    
    public void setCell(int x, int y, Cell cell){
        antWorld[x][y] = cell;
    }
    
    public Cell getCell(int x, int y){
        return antWorld[x][y];
    }

    /**
     * @return the ants
     */
    public HashMap<Integer, Ant> getAnts() {
        return ants;
    }

//    private boolean setChange(int cellID) {
//        return changes.add(cellID);
//    }
    
//    public void resetChanges() {
//	changes = new HashSet<Integer>();
//    }
    
    public ArrayList<AntHillCell> getAntHills(){
        return antHills;
    }
    
    
//    public void triggerUpdates(int cellID) throws Exception {
//	update(cellID);
//        for (int i = 0; i < 6; i++) {
//            update(getAhead(i, cellID, lengthX));
//        }
//    }

//    /**
//     * 
//     * @param cellID 
//     */
//    private void update(int tileID) throws Exception {
//		
//        int x1 = ((tileID % lengthX) + lengthX) % lengthX;
//	int y1 = tileID / lengthX;
//        Cell currentCell = getCell(x1, y1);
//        if ((!currentCell.isRocky()) && ((EmptyCell) currentCell).hasAnt()) {
//            int antCount = 0;
//            for (int i = 0; i < 6; i++) {
//                int newCellID = getAhead(i, tileID, lengthX);
//                int x2 = ((newCellID % lengthX) + lengthX) % lengthX;
//		int y2 = newCellID / lengthX;
//		Cell newCell = getCell(x2, y2);
//                    if ((!newCell.isRocky()) && ((EmptyCell) newCell).hasAnt() && (!((EmptyCell) newCell).getAnt().get_color().equals(((EmptyCell) currentCell).getAnt().get_color()))) {
//			antCount++;
//                    }
//		}
//		if (antCount >= 5) {
//                    if (Game.DEBUG) {
//			System.out.println("DEBUG | ANT DEATH: " + ((EmptyCell) currentCell).getAnt().get_color() + " AntDied " + ((EmptyCell) currentCell).getAnt().getID());
//                    }
//                    ants.remove(((EmptyCell) currentCell).getAnt().getID());
//                    setChange(tileID);
//                    ((EmptyCell) currentCell).clearAnt();
//                    ((EmptyCell) currentCell).setFoodNumber(((EmptyCell) currentCell).getFoodNumber() + 3);
//                }
//            }
//	}

}
