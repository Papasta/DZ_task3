package City;

import Player.Bot;
import Player.Player;
import Utils.ConsoleColors;

import java.util.Random;
import java.util.Set;

public class BotCity extends City{
    private static BotCity instance;
    private final int x = 9;
    private final int y = 4;
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean hasCityAtCell(int i, int j){
        return i == getX() && j == getY();
    }
    private BotCity(){}
    public static BotCity getInstance(){
        if (instance == null)
            instance = new BotCity();
        return instance;
    }
    public void upBuilding(){
        System.out.println("\tWhich one do you want to upgrade (visit)?:");
        Bot bot = Bot.getInstance();
        for (String key : getAllBuilding().keySet())
            System.out.println("\t" + key);
        String build = getRandomSetElement(getAllBuilding().keySet());
        System.out.println("\t\t" + ConsoleColors.RED + build + ConsoleColors.RESET);
        if (bot.resourceLimit(getAllBuilding().get(build))){
            if (getAllBuilding().get(build).getLevel()<3){
                getAllBuilding().get(build).setLevel(getAllBuilding().get(build).getLevel() + 1);
            }
            bot.enterHP();
            System.out.println("\tLevel now:\t" + getAllBuilding().get(build).getLevel());
            getAllBuilding().get(build).welcome(false);
        }

    }
    public <T> T getRandomSetElement(Set<T> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (T element : set) {
            if (i == randomIndex)
                return element;
            i++;
        }
        return null;
    }
    public void buyBuilding(){
        System.out.println("\tWhich one do you want to buy?");
        Bot bot = Bot.getInstance();
        String build = getRandomSetElement(getFreeKeys());
        getFreeKeys().remove(build);
        System.out.println("\t\t" + ConsoleColors.RED + build + ConsoleColors.RESET);
        addBuilding(build);
        if (bot.resourceLimit(getAllBuilding().get(build))){
            System.out.println("Thank you for buying the building.");
            bot.enterHP();
            getAllBuilding().get(build).welcome(false);
        }
    }
}
