package Building;

import Person.Person;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Market extends Building{
    public Market(int stone, int wood){
        super(stone, wood);
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Market! Exchange wood -> stone or stone -> wood (enter 1 or 2)" + ConsoleColors.RESET);
        int doo = 0;
        if (who){
            Scanner in = new Scanner(System.in);
            doo = Integer.parseInt(in.nextLine());
        } else {
            Random r = new Random();
            doo = r.nextInt(2) + 1;
        }
        switch (doo) {
            case 1 -> plusStone(who);
            case 2 -> plusWood(who);
        }
    }
    private void plusStone(boolean who){
        System.out.println("\t\tEnter the number of wood (for sale)");
        Bot bot = Bot.getInstance();
        MyPlayer myPlayer = MyPlayer.getInstance();
        int saleWood;
        if (who){
            Scanner in = new Scanner(System.in);
            saleWood = Integer.parseInt(in.nextLine());
            myPlayer.setStone(myPlayer.getStone() + saleWood);
            myPlayer.setWood(myPlayer.getWood() - saleWood);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + myPlayer.getWood() + "\tStone: " + myPlayer.getStone() + ConsoleColors.RESET);
        } else {
            Random r = new Random();
            saleWood = r.nextInt(bot.getStone()/10);
            System.out.println("\t\t" + saleWood);
            bot.setStone(bot.getStone() + saleWood);
            bot.setWood(bot.getWood() - saleWood);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + bot.getWood() + "\tStone: " + bot.getStone() + ConsoleColors.RESET);
        }
    }
    private void plusWood(boolean who){
        System.out.println("\t\tEnter the number of stone (for sale)");
        Bot bot = Bot.getInstance();
        MyPlayer myPlayer = MyPlayer.getInstance();
        int saleStone;
        if (who){
            Scanner in = new Scanner(System.in);
            saleStone = Integer.parseInt(in.nextLine());
            myPlayer.setStone(myPlayer.getStone() - saleStone);
            myPlayer.setWood(myPlayer.getWood() + saleStone);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + myPlayer.getWood() + "\tStone: " + myPlayer.getStone() + ConsoleColors.RESET);
        } else {
            Random r = new Random();
            saleStone = r.nextInt(bot.getStone()/10);
            bot.setStone(bot.getStone() - saleStone);
            bot.setWood(bot.getWood() + saleStone);
            System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWood: " + bot.getWood() + "\tStone: " + bot.getStone() + ConsoleColors.RESET);
        }
    }
    public void setLevel(){
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Market" + "\t" + getLevel());
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
