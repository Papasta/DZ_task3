package Player;

import Person.Person;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPlayer extends Player {
    ArrayList<Person> myPersons = new ArrayList<>();
    private static MyPlayer instance;

    private MyPlayer(){}

    public static MyPlayer getInstance() {
        if (instance == null)
            instance = new MyPlayer();
        return instance;
    }
    public  Person getMy(int num) {
        return myPersons.get(num);
    }

    public  boolean isEmptyMy() {
        return myPersons.isEmpty();
    }

    public  void removeMy(Person pers) {
        myPersons.remove(pers);
    }

    public  int sizePlayer() {
        return myPersons.size();
    }

    public  void moneyLimit(Person pers) {
        if (pers.getPrice() <= gold) {
            setGold(gold - pers.getPrice());
            System.out.println("Gold = " + gold);
            myPersons.add(pers);
        } else {
            System.out.println("LOL You don't have any money.");
        }
    }

    public  Person enterPerson() {
        System.out.print("Input coordinates:\n");
        Scanner in = new Scanner(System.in);
        int x = Integer.parseInt(in.nextLine());
        int y = Integer.parseInt(in.nextLine());
        System.out.print("Input Type: ");
        int t = Integer.parseInt(in.nextLine());
        x -= 1;
        y -= 1;
        return createPerson(x, y, t, true);
    }

    public  void motionPlayer() {
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < myPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + myPersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input coordinates where you want to go:\n");
        int x = Integer.parseInt(in.nextLine()) - 1;
        int y = Integer.parseInt(in.nextLine()) - 1;
        myPersons.get(count).mechanismMotion(x, y);
    }

    public  void attackPlayer() {
        System.out.print("Input number of the player:\n");
        for (int count = 0; count < myPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + myPersons.get(count).getNum() + ">>");
        }
        Scanner in = new Scanner(System.in);
        int countPerson = Integer.parseInt(in.nextLine()) - 1;
        System.out.print("Input number of the enemy:\n");
        Bot bot = Bot.getInstance();
        for (int count = 0; count < bot.sizePlayer(); count++) {
            System.out.println((count + 1) + ".  <<" + bot.getBot(count).getNum() + ">>");
        }
        int countEnemy = Integer.parseInt(in.nextLine()) - 1;
        bot.getBot(countEnemy).mechanismAttack(myPersons.get(countPerson));
        if (bot.getBot(countEnemy).getNum().equals("D")) {
            bot.removeBot(bot.getBot(countEnemy));
        }
    }

    public  void addFineToMyPersons(double value){
        for (int i = 0; i < sizePlayer(); i++) {
            myPersons.get(i).addNewFine("$", value);
        }
    }

    public  void save(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/MyPerson.txt");
        int cnt = 0;
        for (int i = 0; i < sizePlayer(); i++) {
            cnt++;
            nFile.write(myPersons.get(i).getType() + "\t");
            nFile.write(myPersons.get(i).getX() + "\t");
            nFile.write(myPersons.get(i).getY() + "\t");
            nFile.write(myPersons.get(i).getHealth() + "\t");
            nFile.write(myPersons.get(i).getAttack() + "\t");
            nFile.write(myPersons.get(i).getRangeAttack() + "\t");
            nFile.write(String.valueOf(myPersons.get(i).getDefence()));
            if (cnt != sizePlayer())
                nFile.write("\n");
        }
        nFile.close();
    }

    public  void load(Scanner botScan) {
        while (botScan.hasNextLine()) {
            int t = Integer.parseInt(botScan.next());
            int x = Integer.parseInt(botScan.next());
            int y = Integer.parseInt(botScan.next());
            Person pers = createPerson(x, y, t, true);
            int health = Integer.parseInt(botScan.next());
            if (health != pers.getHealth())
                pers.setHealth(health);

            int attack = Integer.parseInt(botScan.next());
            if (attack != pers.getAttack())
                pers.setAttack(attack);

            int rangeAttack = Integer.parseInt(botScan.next());
            if (rangeAttack != pers.getRangeAttack())
                pers.setRangeAttack(rangeAttack);

            int defence = Integer.parseInt(botScan.next());
            if (defence != pers.getDefence())
                pers.setDefence(defence);
            myPersons.add(pers);
        }
    }

    public  void enterHP() {
        for (int i = 0; i < sizePlayer(); i++) {
            System.out.print(myPersons.get(i).getNum() + "\tHP: ");
            System.out.print(myPersons.get(i).getHealth() + "\tAttack: ");
            System.out.print(myPersons.get(i).getAttack() + "\tRange attack: ");
            System.out.print(myPersons.get(i).getRangeAttack() + "\tDefence: ");
            System.out.print(myPersons.get(i).getDefence() + "\tSteps: ");
            System.out.print(myPersons.get(i).getSteps());
            System.out.print("\n");
        }
    }
}
