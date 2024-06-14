package Player;

import Person.Person;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bot extends Player{
    ArrayList<Person> enemyPersons = new ArrayList<>();
    private static Bot instance;
    private int totalHealth = 0;

    private Bot() {}

    public static Bot getInstance(){
        if (instance == null)
            instance = new Bot();
        return instance;
    }
    public boolean isEmptyBot(){ return enemyPersons.isEmpty();}
    public int sizePlayer(){
        return enemyPersons.size();
    }
    public Person getBot(int num) { return enemyPersons.get(num); }
    public void removeBot(Person pers){ enemyPersons.remove(pers); }
    public boolean containsEnemy(Person pers){ return enemyPersons.contains(pers); }
    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }
    public  int getTotalHealth() {
        return totalHealth;
    }

    public  void moneyLimit(Person pers) {
        if (pers.getPrice() <= gold) {
            setGold(gold - pers.getPrice());
            System.out.println("Gold = " + gold);
            enemyPersons.add(pers);
            setTotalHealth(getTotalHealth()+ pers.getHealth());
        } else {
            System.out.println("You don't have any money.");
        }
    }

    public  void addFineBotPersons(double value){
        for (int i = 0; i < sizePlayer(); i++) {
            enemyPersons.get(i).addNewFine("$", value);
        }
    }

    public  void enterHP() {
        for (int i = 0; i < sizePlayer(); i++) {
            System.out.print(enemyPersons.get(i).getNum() + "\tHP: ");
            System.out.print(enemyPersons.get(i).getHealth() + "\tAttack: ");
            System.out.print(enemyPersons.get(i).getAttack() + "\tRange attack: ");
            System.out.print(enemyPersons.get(i).getRangeAttack() + "\tDefence: ");
            System.out.print(enemyPersons.get(i).getDefence() + "\tSteps: ");
            System.out.print(enemyPersons.get(i).getSteps());
            System.out.print("\n");
        }
    }
    public  Person enterPerson() {
        System.out.println("Input coordinates:");
        Random r = new Random();
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        System.out.print((x + 1) + "\n" + (y + 1) + "\n");
        System.out.println("Input Type: ");
        //int t = r.nextInt(10 + botCity.getAllBuilding().get("Academy").size()) + 1;
        int t = 5;
        System.out.println(t);
        return createPerson(x, y, t, false);
    }
    public  void motionPlayer() {
        System.out.print("Input number of the player:\n");
        int count = 0;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson + 1));
        System.out.println("Input coordinates where you want to go:");
        int x = r.nextInt(9);
        int y = r.nextInt(9);
        System.out.print((x + 1) + "\n" + (y + 1) + "\n");
        enemyPersons.get(countPerson).mechanismMotion(x, y);
    }
    //@Override
    public  void attackPlayer() {
        System.out.print("Input number of the player:\n");
        MyPlayer myPlayer = MyPlayer.getInstance();
        int count;
        for (count = 0; count < enemyPersons.size(); count++) {
            System.out.println((count + 1) + ".  <<" + enemyPersons.get(count).getNum() + ">>");
        }
        Random r = new Random();
        int countPerson = r.nextInt(count);
        System.out.println("Answer: " + (countPerson + 1));
        System.out.println("Input number of the enemy:");
        int mi = 10000;
        int countEnemy = 0;
        for (count = 0; count < myPlayer.sizePlayer(); count++) {
            System.out.println((count + 1) + ".  <<" + myPlayer.getMy(count).getNum() + ">>");
            if (mi > myPlayer.getMy(count).getDefence()) {
                mi = myPlayer.getMy(count).getDefence();
                countEnemy = count;
            }
        }
        System.out.println("Answer: " + (countEnemy + 1));
        myPlayer.getMy(countEnemy).mechanismAttack(enemyPersons.get(countPerson));
        if (myPlayer.getMy(countEnemy).getNum().equals("D"))
            myPlayer.removeMy(myPlayer.getMy(countEnemy));
    }
    public  void save(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/Bot.txt");
        int cnt = 0;
        for (int i = 0; i < sizePlayer(); i++) {
            cnt++;
            nFile.write(enemyPersons.get(i).getType() + "\t");
            nFile.write(enemyPersons.get(i).getX() + "\t");
            nFile.write(enemyPersons.get(i).getY() + "\t");
            nFile.write(enemyPersons.get(i).getHealth() + "\t");
            nFile.write(enemyPersons.get(i).getAttack() + "\t");
            nFile.write(enemyPersons.get(i).getRangeAttack() + "\t");
            nFile.write(String.valueOf(enemyPersons.get(i).getDefence()));
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
            Person pers = createPerson(x, y, t, false);
            int health = Integer.parseInt(botScan.next());
            if (pers != null && health != pers.getHealth())
                pers.setHealth(health);
            int attack = Integer.parseInt(botScan.next());
            if (pers != null && attack != pers.getAttack())
                pers.setAttack(attack);
            int rangeAttack = Integer.parseInt(botScan.next());
            if (pers != null && rangeAttack != pers.getRangeAttack())
                pers.setRangeAttack(rangeAttack);
            int defence = Integer.parseInt(botScan.next());
            if (pers != null && defence != pers.getDefence())
                pers.setDefence(defence);
            enemyPersons.add(pers);
        }
    }
}
