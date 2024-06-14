package Building;

import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Workshop extends Building{
    public Workshop(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Workshop!" + ConsoleColors.RESET);
        Bot bot = Bot.getInstance();
        MyPlayer myPlayer = MyPlayer.getInstance();
        int up;
        if(who){
            up = getLevel()*100 + myPlayer.getGold();
            myPlayer.setGold(up);
        } else {
            up = getLevel()*100 + bot.getGold();
            bot.setGold(up);
        }
        System.out.println("Thank you for visiting the Workshop. You wallet now:\t" + ConsoleColors.CYAN_BOLD + up + ConsoleColors.RESET);
    }
    public void setLevel(){}
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Workshop" + "\t" + getLevel());
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
