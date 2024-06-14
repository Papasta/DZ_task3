import City.BotCity;
import City.MyCity;
import Field.Field;
import Player.Bot;
import Player.MyPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

public class EditGame {
    public void loadAll(String path) throws FileNotFoundException {
        BotCity botCity = BotCity.getInstance();
        MyCity myCity = MyCity.getInstance();
        Field field = Field.getInstance();

        FileReader fieldReader = new FileReader(path + "/field.txt");
        Scanner fieldScan = new Scanner(fieldReader);
        field.load(fieldScan);

        FileReader botCityReader = new FileReader(path + "/botCity.txt");
        Scanner botCityScan = new Scanner(botCityReader);
        botCity.load(botCityScan);

        FileReader myCityReader = new FileReader(path + "/myCity.txt");
        Scanner myCityScan = new Scanner(myCityReader);
        myCity.load(myCityScan);

        Bot bot = Bot.getInstance();
        FileReader botReader = new FileReader(path + "/bot.txt");
        Scanner botScan = new Scanner(botReader);
        bot.load(botScan);

        MyPlayer myPlayer = MyPlayer.getInstance();
        FileReader myPlayerReader = new FileReader(path + "/MyPerson.txt");
        Scanner myPlayerScan = new Scanner(myPlayerReader);
        myPlayer.load(myPlayerScan);
    }
    private void deleteDirectory(File directory) {
        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
    public void deleteOneDirectory(){
        if (Objects.requireNonNull(new File("Save/").listFiles()).length > 1) {
            Scanner in = new Scanner(System.in);
            enterAll();
            String dirName = in.nextLine();
            deleteDirectory(new File("Save/" + dirName));
        }
    }
    public void enterAll(){
        File file = new File("Save/");
        int counter = 0;
        for (File curFile : Objects.requireNonNull(file.listFiles())) {
            counter++;
            if (curFile.getName().equals("temp.txt")){
                counter--;
                continue;
            }
            System.out.println(counter + ". " + curFile.getName());
        }
    }
    public String selecDir(){
        if (Objects.requireNonNull(new File("Save/").listFiles()).length > 1) {
            Scanner in = new Scanner(System.in);
            File file = new File("Save/");
            int counter = 0;
            for (File curFile : Objects.requireNonNull(file.listFiles())) {
                counter++;
                if (curFile.getName().equals("temp.txt")) {
                    counter--;
                    continue;
                }
                System.out.println(counter + ". " + curFile.getName());
            }
            System.out.println("Enter the name of the folder of the downloaded game:");
            String dirName = in.nextLine();
            String path = ("Save/" + dirName);
            return path;
        }
        return null;
    }
}
