import City.BotCity;
import City.MyCity;
import Field.Field;
import Player.Bot;
import Player.MyPlayer;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class SaveGame {
    public void saveall(){
        System.out.println("Do you want to save it? (0 or 1)");
        Scanner in = new Scanner(System.in);
        int save = Integer.parseInt(in.nextLine());
        BotCity botCity = BotCity.getInstance();
        MyCity myCity = MyCity.getInstance();
        Field field = Field.getInstance();
        if (save == 1){
            try {
                System.out.println("Enter name path:");
                String time = in.nextLine();
                Path path = Path.of("Save/" + time);
                File dir = new File(String.valueOf(path));
                dir.mkdir();
                field.save(String.valueOf(path));
                Bot bot = Bot.getInstance();
                MyPlayer myPlayer = MyPlayer.getInstance();
                myPlayer.save(String.valueOf(path));
                bot.save(String.valueOf(path));
                botCity.save(path + "/botCity");
                myCity.save(path + "/myCity");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
