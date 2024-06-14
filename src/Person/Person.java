package Person;
import Field.Field;

import java.util.HashMap;

public abstract class Person {
    private int health, attack, rangeAttack, defence, price, x, y, type;
    private double steps, startsteps;
    private String num;
    public Person(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, int type){
        this.health = health;
        this.attack = attack;
        this.rangeAttack = rangeAttack;
        this.defence = defence;
        this.steps = steps;
        startsteps = steps;
        if (type > 10){
            num = "♜";
        } else if (type==10){
            num = "❤";
        } else {
            num = String.valueOf(type);
        }
        this.price = price;
        this.x = x;
        this.y = y;
        this.type = type;
    }
    private final HashMap<String, Double> fine = new HashMap<>();

    public double getStartsteps(){
        return startsteps;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getHealth(){
        return health;
    }
    public int getAttack(){ return attack; }
    public int getRangeAttack(){
        return rangeAttack;
    }
    public int getDefence(){
        return defence;
    }
    public double getSteps(){
        return steps;
    }
    public int getPrice(){
        return price;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public String getNum() { return num; }
    public void setHealth(int health){
        this.health = health;
    }
    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setSteps(double steps){
        this.steps = steps;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setRangeAttack(int rangeAttack) {
        this.rangeAttack = rangeAttack;
    }
    public void setStartsteps(double startsteps) {
        this.startsteps = startsteps;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public HashMap<String, Double> getFine() {
        return fine;
    }
    public void minusFine(double minus){
        for (String key : fine.keySet()) {
            Double newFine = fine.get(key);
            newFine -= minus;
            fine.put(key, newFine);
        }
    }
    public void addNewFine(String newfine, double value){
        fine.put(newfine, value);
    }
    public double getOneFine(String symbol){
        return getFine().get(symbol);
    }
    public void nullFine(){
        fine.put("*", (double) 0);
        fine.put("!", (double) 0);
        fine.put("#", (double) 0);
        fine.put("@", (double) 0);
    }

    public void mechanismMotion(int x1, int y1){
        int j = getY();
        double step = 0;
        System.out.println("Now health: " + getHealth());
        while (j < y1){
            j += 1;
            step += getOneFine(Field.symbCell(x, j));
        }
        while (j > y1){
            j -= 1;
            step += getOneFine(Field.symbCell(x, j));
        }
        int i = getX();
        while (i > x1){
            i -= 1;
            step += getOneFine(Field.symbCell(i, j));
        }
        while (i < x1){
            i += 1;
            step += getOneFine(Field.symbCell(i, j));
        }
        if (step <= getSteps()){
            setSteps(getSteps() - step);
            setY(j);
            setX(i);
        } else { System.out.println("No steps"); }
        System.out.println("Steps: " + getSteps());
    }
    public void mechanismAttack(Person attacker){
        System.out.println("Now health defender: " + health);
        System.out.println("Now defence: " + defence);
        int difference = Math.abs((attacker.getX() + attacker.getY()) - (x + y));
        if (difference <= attacker.getRangeAttack()){
            if (attacker.getAttack() > defence){
                int damage = attacker.getAttack() - defence;
                defence = 0;
                if (damage < health){
                    health = health - damage;
                } else {
                    num = "D";
                    System.out.println("You kill enemy");
                    return;
                }
            } else {
                defence = defence - attacker.getAttack();
            }
        } else { System.out.print("You can't attack:\n"); }
        System.out.println("Health: " + health);
        System.out.println("Defence: " + defence);
    }
}
