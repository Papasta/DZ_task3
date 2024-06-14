package Building;

import Person.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Building {
    private int level, stone, wood;
    public Building(int stone, int wood){
        level = 1;
        this.stone = stone;
        this.wood = wood;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getStone() {
        return stone;
    }
    public int getWood() {
        return wood;
    }
    public abstract void welcome(boolean who);
    public abstract void save(FileWriter nFile) throws IOException;

    public abstract ArrayList<Person> getPersAcademy();
}
