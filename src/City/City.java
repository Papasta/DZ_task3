package City;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Building.Building;
import Building.Arsenal;
import Building.Healer;
import Building.Market;
import Building.Forge;
import Building.Tavern;
import Building.Academy;
import Person.Person;
import Person.Walking;
import Building.Workshop;

public abstract class City {
    private String symbol;

    public City() {
        symbol = "☗";
    }

    private Set<String> freeKeys = new HashSet<>();
    private final HashMap<String, Building> allBuilding = new HashMap<>();

    public HashMap<String, Building> getAllBuilding() {
        return allBuilding;
    }

    public Set<String> getFreeKeys() {
        return freeKeys;
    }

    public boolean notNullKeys() {
        return freeKeys.isEmpty();
    }

    public boolean notNull() {
        return allBuilding.isEmpty();
    }

    public String getSymbol() {
        return symbol;
    }

    public void nullKeys() {
        freeKeys.add("Arsenal");
        freeKeys.add("Healer");
        freeKeys.add("Market");
        freeKeys.add("Tavern");
        freeKeys.add("Forge");
        freeKeys.add("Workshop1");
        freeKeys.add("Workshop2");
        freeKeys.add("Workshop3");
        freeKeys.add("Workshop4");
        freeKeys.add("Academy");
    }

    public void enterAcademy() {
        if (allBuilding.containsKey("Academy") && !allBuilding.get("Academy").getPersAcademy().isEmpty()) {
            System.out.println("New type by Academy:\t");
            for (int i = 0; i < allBuilding.get("Academy").getPersAcademy().size(); i++) {
                Person nowPerson = allBuilding.get("Academy").getPersAcademy().get(i);
                System.out.print((i + 11) + "\t" + nowPerson.getNum() + "\t");
                System.out.print(nowPerson.getHealth() + "\t");
                System.out.print(nowPerson.getAttack() + "\t");
                System.out.print(nowPerson.getRangeAttack() + "\t");
                System.out.print(nowPerson.getDefence() + "\t");
                System.out.print(nowPerson.getSteps() + "\t");
                System.out.print(nowPerson.getPrice() + "\n");
            }
        }
    }

    public Person createPersonAcademy(int x, int y, int t) {
        Person pers = allBuilding.get("Academy").getPersAcademy().get(t);
        pers.setNum("♜");
        pers.setType(11);
        pers.setX(x);
        pers.setY(y);
        return pers;
    }

    public void addBuilding(String name) {
        switch (name) {
            case "Arsenal" -> allBuilding.put(name, new Arsenal(2, 2));
            case "Healer" -> allBuilding.put(name, new Healer(1, 2));
            case "Market" -> allBuilding.put(name, new Market(1, 1));
            case "Forge" -> allBuilding.put(name, new Forge(2, 1));
            case "Academy" -> allBuilding.put(name, new Academy(2, 3));
            case "Tavern" -> allBuilding.put(name, new Tavern(3, 2));
            case "Workshop1", "Workshop2", "Workshop3", "Workshop4" -> allBuilding.put(name, new Workshop(3, 3));

        }
    }

    public void save(String name) throws IOException {
        FileWriter nFile = new FileWriter(name + ".txt");
        nFile.write(freeKeys.size() + "\n");
        int cnt = 0;
        for (String key : getFreeKeys()) {
            cnt++;
            nFile.write(key);
            if (cnt != getFreeKeys().size())
                nFile.write("\n");
        }
        if(!getAllBuilding().keySet().isEmpty())
            nFile.write("\n");
        cnt = 0;
        for (String key : getAllBuilding().keySet()) {
            cnt++;
            allBuilding.get(key).save(nFile);
            if (cnt != getAllBuilding().keySet().size())
                nFile.write("\n");
        }
        nFile.close();
    }

    public void load(Scanner city) {
        int countFree = Integer.parseInt(city.nextLine());
        for (int i = 0; i < countFree; i++) {
            String nameFree = city.nextLine();
            freeKeys.add(nameFree);
        }
        while (city.hasNextLine()) {
            String name = city.next();
            int level = Integer.parseInt(city.next());
            switch (name) {
                case "Arsenal" -> allBuilding.put(name, new Arsenal(2, 2));
                case "Healer" -> allBuilding.put(name, new Healer(1, 2));
                case "Market" -> allBuilding.put(name, new Market(1, 1));
                case "Forge" -> allBuilding.put(name, new Forge(2, 1));
                case "Workshop1", "Workshop2", "Workshop3", "Workshop4" -> allBuilding.put(name, new Workshop(3, 3));
                case "Academy" -> {
                    allBuilding.put(name, new Academy(2, 3));
                    int type = Integer.parseInt(city.next());
                    int health = Integer.parseInt(city.next());
                    int attack = Integer.parseInt(city.next());
                    int rangeAttack = Integer.parseInt(city.next());
                    int defence = Integer.parseInt(city.next());
                    double steps = Double.parseDouble(city.next());
                    ((Academy) allBuilding.get(name)).getPersAcademy().add(new Walking(health, attack, rangeAttack, defence, steps, (attack / 10), 0, 0, type));
                }
                case "Tavern" -> {
                    allBuilding.put(name, new Tavern(3, 2));
                    double steps = Double.parseDouble(city.next());
                    double fine = Double.parseDouble(city.next());
                    ((Tavern) allBuilding.get(name)).setFine(fine);
                    ((Tavern) allBuilding.get(name)).setSteps(steps);
                }
            }
            allBuilding.get(name).setLevel(level);
        }
    }
}
