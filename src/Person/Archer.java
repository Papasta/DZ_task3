package Person;

public class Archer extends Person{

    public Archer(int health, int attack, int rangeAttack, int defence, double steps, int price, int x, int y, int type){
        super(health, attack, rangeAttack, defence, steps, price, x, y, type);
        nullFine();
    }
    @Override
    public void nullFine(){
        getFine().put("*", (double) 1);
        getFine().put("!", (double) 1);
        getFine().put("#", 1.8);
        getFine().put("@", 2.2);
    }
}
