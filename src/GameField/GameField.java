package GameField;

import City.MyCity;
import City.BotCity;
import Field.Field;
import Player.Bot;
import Player.MyPlayer;
import Utils.ConsoleColors;

import java.util.Random;
import java.util.Scanner;

public class GameField {
    Bot bot = Bot.getInstance();
    MyPlayer myPlayer = MyPlayer.getInstance();
    MyCity myCity = MyCity.getInstance();
    BotCity botCity = BotCity.getInstance();
    public void nullKeys (){
        botCity.nullKeys();
        myCity.nullKeys();
    }
    public void buyMyBuilding(){
        System.out.println("Do you want to buy or upgrade a building?\n\t1.Buy\n\t2.Upgrade\n\t3.No");
        Scanner in = new Scanner(System.in);
        int doo = Integer.parseInt(in.nextLine());
        switch (doo) {
            case 1 -> myCity.buyBuilding();
            case 2 -> myCity.upBuilding();
        }
    }
    public void buyBotBuilding() {
        if (botCity.notNull()) {
            System.out.println("Do you want to buy a building?");
            Random r = new Random();
            //int doo = r.nextInt(2) + 1;
            int doo = 1;
            if (doo == 1) {
                System.out.println(ConsoleColors.RED + "Yes" + ConsoleColors.RESET);
                botCity.buyBuilding();
            } else {
                System.out.println(ConsoleColors.PURPLE + "NO" + ConsoleColors.RESET);
            }
        } else {
            if (!botCity.notNullKeys()) {
                System.out.println("Do you want to buy or upgrade a building?\n\t1.Buy\n\t2.Upgrade\n\t3.No");
                Random r = new Random();
                int doo = r.nextInt(3) + 1;
                //int doo = 1;
                switch (doo) {
                    case 1 -> {
                        System.out.println(ConsoleColors.RED + "Buy" + ConsoleColors.RESET);
                        botCity.buyBuilding();
                    }
                    case 2 -> {
                        System.out.println(ConsoleColors.RED + "Upgrade" + ConsoleColors.RESET);
                        botCity.upBuilding();
                    }
                    case 3 -> System.out.println(ConsoleColors.RED + "No" + ConsoleColors.RESET);
                }
            } else {
                System.out.println("Do you want to upgrade a building?");
                Random r = new Random();
                int doo = r.nextInt(2) + 1;
                if (doo == 1) {
                    System.out.println(ConsoleColors.RED + "Yes" + ConsoleColors.RESET);
                    botCity.upBuilding();
                } else {
                    System.out.println(ConsoleColors.RED + "NO" + ConsoleColors.RESET);
                }
            }
        }
    }


    public boolean notNullPerson() {
        return !myPlayer.isEmptyMy();
    }
    public boolean notNullEnemy() {
        return !bot.isEmptyBot();
    }

    public void enterField() {
        boolean hasSomethingAtCell = true;
        System.out.print("\t");
        for (int k = 1; k < 11; k++)
            System.out.print(k + "\t");
        System.out.print("\n");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < 10; j++) {
                if (myCity.hasCityAtCell(i, j)) {
                    System.out.print(ConsoleColors.GREEN + myCity.getSymbol() + ConsoleColors.RESET + "\t");
                    hasSomethingAtCell = false;
                }
                if (botCity.hasCityAtCell(i, j)) {
                    System.out.print(ConsoleColors.RED + botCity.getSymbol() + ConsoleColors.RESET + "\t");
                    hasSomethingAtCell = false;
                }
                for (int w = 0; w < myPlayer.sizePlayer(); w++) {
                    if (hasSomethingAtCell && myPlayer.getMy(w).getX() == i && myPlayer.getMy(w).getY() == j) {
                        System.out.print(ConsoleColors.GREEN_BOLD + myPlayer.getMy(w).getNum() + ConsoleColors.RESET + "\t");
                        hasSomethingAtCell = false;
                        break;
                    }
                }
                for (int w = 0; w < bot.sizePlayer(); w++) {
                    if (hasSomethingAtCell && bot.getBot(w).getX() == i && bot.getBot(w).getY() == j) {
                        System.out.print(ConsoleColors.RED_BOLD + bot.getBot(w).getNum() + ConsoleColors.RESET + "\t");
                        hasSomethingAtCell = false;
                        break;
                    }
                }
                if (hasSomethingAtCell) {
                    System.out.print(Field.symbCell(i, j) + "\t");
                }
                hasSomethingAtCell = true;
            }
            System.out.println();
        }
    }


    public void enterCatalog(boolean who){
        System.out.println("Type Name         Health Attack AttackRange Defense Steps Price");
        System.out.println("1\t" + " Swordsman    " + "50\t" + " 5\t\t" + "1\t\t\t" + "8\t\t" + "3    " + " 10");
        System.out.println("2\t" + " Spearman     " + "35\t" + " 3\t\t" + "1\t\t\t" + "4\t\t" + "6    " + " 15");
        System.out.println("3\t" + " Axeman       " + "45\t" + " 9\t\t" + "1\t\t\t" + "3\t\t" + "4    " + " 20");
        System.out.println("4\t" + " Archerlev1   " + "30\t" + " 6\t\t" + "5\t\t\t" + "8\t\t" + "2    " + " 15");
        System.out.println("5\t" + " Archerlev2   " + "25\t" + " 3\t\t" + "3\t\t\t" + "4\t\t" + "4    " + " 19");
        System.out.println("6\t" + " Crossbowman  " + "40\t" + " 7\t\t" + "6\t\t\t" + "3\t\t" + "2    " + " 23");
        System.out.println("7\t" + " Knight       " + "30\t" + " 5\t\t" + "1\t\t\t" + "3\t\t" + "6    " + " 20");
        System.out.println("8\t" + " Cuirassier   " + "50\t" + " 2\t\t" + "1\t\t\t" + "7\t\t" + "5    " + " 23");
        System.out.println("9\t" + " HorseArcher  " + "25\t" + " 3\t\t" + "3\t\t\t" + "2\t\t" + "5    " + " 25");
        System.out.println("10  " + " God          " + "999\t" + "999\t\t" + "999\t\t\t" + "999\t\t" + "999\t " + " 99");
        if (who){
            myCity.enterAcademy();
        } else { botCity.enterAcademy();}
    }

    public void nullingSteps(boolean who) {
        if (who) {
            for (int w = 0; w < myPlayer.sizePlayer(); w++)
                myPlayer.getMy(w).setSteps(myPlayer.getMy(w).getStartsteps());
        } else {
            for (int w = 0; w < bot.sizePlayer(); w++) {
                bot.getBot(w).setSteps(bot.getBot(w).getStartsteps());
            }
        }
    }
}
