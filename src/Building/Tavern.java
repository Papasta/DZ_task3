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

public class Tavern extends Building {
    public Tavern(int stone, int wood){
        super(stone, wood);
    }
    private double steps = 0, fine = 0;

    public double getFine() {
        return fine;
    }
    public double getSteps() {
        return steps;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }
    @Override
    public void welcome(boolean who) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\t\tWelcome to the Tavern! Upgrade your skills" + ConsoleColors.RESET);
        Bot bot = Bot.getInstance();
        MyPlayer myPlayer = MyPlayer.getInstance();
        if (getLevel()<4){
            double up;
            System.out.println("\t\tChoose one skills: steps, fine");
            if(who){
                Scanner in = new Scanner(System.in);
                String name = in.nextLine();
                switch (name) {
                    case "steps" -> {
                        System.out.println("\t\tHow much to increase? (0-2)");
                        up = Double.parseDouble(in.nextLine());
                        setSteps(getSteps() + up);

                    }
                    case "fine" -> {
                        System.out.println("\t\tHow much to increase? (0-1)");
                        up = Double.parseDouble(in.nextLine());
                        setFine(getFine() + up);
                    }
                }
                for(int i = 0; i < myPlayer.sizePlayer(); i++){
                    myPlayer.getMy(i).setStartsteps(getSteps() + myPlayer.getMy(i).getStartsteps());
                    myPlayer.getMy(i).minusFine(getSteps());
                }
            } else {
                Random r = new Random();
                int doo = r.nextInt(2);
                if (doo == 0) {
                    System.out.println(ConsoleColors.CYAN_BOLD + "steps" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.CYAN_BOLD + "fine" + ConsoleColors.RESET);
                }
                System.out.println("\t\tHow much to increase? (0-2)");
                up = r.nextDouble(3);
                System.out.println(ConsoleColors.CYAN_BOLD + up + ConsoleColors.RESET);
                if (doo == 0) {
                    setSteps(up);
                } else {
                    setFine(up);
                }
                for(int i = 0; i < bot.sizePlayer(); i++){
                    bot.getBot(i).setStartsteps(steps + bot.getBot(i).getStartsteps());
                    bot.getBot(i).minusFine(getFine());
                }
            }
        }
        System.out.println("Thank you for visiting the Tavern.");
    }
    @Override
    public void save(FileWriter nFile) throws IOException {
        nFile.write("Tavern" + "\t" + getLevel() + "\t");
        nFile.write( steps + "\t" + fine);
    }
    @Override
    public ArrayList<Person> getPersAcademy() {
        return null;
    }
}
