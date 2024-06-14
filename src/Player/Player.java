package Player;

import Building.Building;
import City.BotCity;
import City.MyCity;
import Person.Person;
import Person.Archer;
import Person.Rider;
import Person.Walking;
import Utils.ConsoleColors;

public abstract class Player {
    protected int gold = 999;
    private int stone = 999;
    private int wood = 999;
    public Player(){}
    public int getStone() { return stone;}
    public void setStone(int stone) {
        this.stone = stone;
    }
    public int getWood() { return wood;}
    public void setWood(int wood) {
        this.wood = wood;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getGold() { return gold; }
    public boolean resourceLimit(Building home){
        if (home.getStone() <= getStone() && home.getWood() <= getWood()){
            setStone(getStone() - home.getStone());
            setWood(getWood() - home.getWood());
            System.out.println(ConsoleColors.CYAN_BOLD + "Now wood:\t" + getWood() + "\nNow stone:\t" + getStone() +ConsoleColors.RESET);
            return true;
        } else {
            System.out.println("You don't have resource.");
            return false;
        }
    }
    protected Person createPerson(int x, int y, int t, boolean who) {
        switch (t) {
            case 1 -> {
                return new Walking(50, 5, 1, 8, 3, 10, x, y, 1);
            }
            case 2 -> {
                return new Walking(35, 3, 1, 4, 6, 15, x, y, 2);
            }
            case 3 -> {
                return new Walking(45, 9, 1, 3, 4, 20, x, y, 3);
            }
            case 4 -> {
                return new Archer(30, 6, 5, 8, 2, 15, x, y, 4);
            }
            case 5 -> {
                return new Archer(25, 3, 3, 4, 4, 19, x, y, 5);
            }
            case 6 -> {
                return new Archer(40, 7, 6, 3, 2, 23, x, y, 6);
            }
            case 7 -> {
                return new Rider(30, 5, 1, 3, 6, 20, x, y, 7);
            }
            case 8 -> {
                return new Rider(50, 2, 1, 7, 5, 23, x, y, 8);
            }
            case 9 -> {
                return new Rider(25, 3, 3, 2, 5, 25, x, y, 9);
            }
            case 10 -> {
                return new Archer(999, 999, 999, 999, 999, 99, x, y, 10);
            }
            default -> {
                if (t>10){
                    if(who){
                        MyCity myCity = MyCity.getInstance();
                        return myCity.createPersonAcademy(x, y, t-11);
                    } else {
                        BotCity botCity = BotCity.getInstance();
                        return botCity.createPersonAcademy(x, y, t-11);}
                }
            }
        }
        return null;
    }

}
