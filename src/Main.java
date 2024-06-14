import Field.Field;
import GameField.GameField;
import Player.MyPlayer;
import Player.Bot;
import Utils.ConsoleColors;

import java.io.File;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int x = 0;
        int botMoveCnt = 0;
        int myPlayerMoveCnt = 0;
        Scanner in = new Scanner(System.in);
        Field field = Field.getInstance();
        GameField gameField = new GameField();
        EditGame editGame = new EditGame();
        Bot bot = Bot.getInstance();
        MyPlayer myPlayer = MyPlayer.getInstance();

        if (Objects.requireNonNull(new File("Save/").listFiles()).length == 2) {
            System.out.println("Do you want to delete an old game? (0 or 1)");
            int dooDelete = Integer.parseInt(in.nextLine());
            if (dooDelete == 1)
                editGame.deleteOneDirectory();
        }
        System.out.println("What do you want to do?\n\t1) Start a new game\n\t2) Load the game\n\t3) Edit and load the game");
        int dooStart = Integer.parseInt(in.nextLine());
        switch (dooStart) {
            case 1 -> {
                field.nullCell();
                System.out.print("My move:\n");
                gameField.enterCatalog(true);
                myPlayer.moneyLimit(myPlayer.enterPerson());
                gameField.enterField();
                System.out.println("Enemy move:");
                gameField.enterCatalog(false);
                bot.moneyLimit(bot.enterPerson());
                gameField.nullKeys();
                myPlayerMoveCnt++;
                botMoveCnt++;
            }
            case 2 -> {
                String path = editGame.selecDir();
                editGame.loadAll(path);
            }
            case 3 -> {
                String path = editGame.selecDir();
                System.out.println("Next enter 0 or 1");

                System.out.println("What do you want Edit all field?");
                int dooEdit;
                dooEdit = Integer.parseInt(in.nextLine());
                if (dooEdit==1){
                    field.edit(path);
                    dooEdit = 0;
                }

                if (Objects.requireNonNull(new File("Save/").listFiles()).length > 2){
                    System.out.println("Do you want to delete your old game?");
                    dooEdit = Integer.parseInt(in.nextLine());
                    if (dooEdit==1){
                        editGame.deleteOneDirectory();
                        dooEdit = 0;
                    }
                }
                editGame.loadAll(path);
                System.out.println("Do you want to create a new cell?");
                dooEdit = Integer.parseInt(in.nextLine());
                if (dooEdit==1){
                    System.out.println("Enter x, y and value line by line");
                    x = Integer.parseInt(in.nextLine());
                    int y = Integer.parseInt(in.nextLine());
                    double value = Double.parseDouble(in.nextLine());
                    Field.setOneCell(x-1, y-1, "$");
                    bot.addFineBotPersons(value);
                    myPlayer.addFineToMyPersons(value);
                    dooEdit = 0;
                }
            }
        }

        gameField.enterField();
        while (gameField.notNullPerson() && gameField.notNullEnemy()) {
            System.out.print("My move:\n");
            System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
            int doo = Integer.parseInt(in.nextLine());
            switch (doo) {
                case 1 -> {
                    gameField.enterCatalog(true);
                    myPlayer.moneyLimit(myPlayer.enterPerson());
                    gameField.enterField();
                }
                case 2 -> {
                    myPlayer.motionPlayer();
                    gameField.enterField();
                }
                case 3 -> {
                    gameField.buyMyBuilding();
                    myPlayer.enterHP();
                    myPlayer.attackPlayer();
                    gameField.enterField();
                    System.out.println(ConsoleColors.GREEN + "My gold:\t" + myPlayer.getGold() + ConsoleColors.RESET);
                }
                case 4 -> {
                    myPlayerMoveCnt++;
                    SaveGame file = new SaveGame();
                    file.saveall();
                    gameField.nullingSteps(true);

                    System.out.println("Enemy move:");
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    System.out.println("Answer: 2");
                    bot.motionPlayer();
                    gameField.enterField();
                    System.out.print("What you want do?:\n\t1.Buy a person\n\t2.Move a person\n\t3.Attack the enemy\n\t4.Finish the move\n");
                    Random r = new Random();
                    //int doEnemy = r.nextInt(4) + 1;
                    int doEnemy = 3;
                    System.out.println("Answer: " + doEnemy);
                    switch (doEnemy) {
                        case 1 -> {
                            gameField.enterCatalog(false);
                            bot.moneyLimit(bot.enterPerson());
                            gameField.enterField();
                        }
                        case 2 -> {
                            bot.motionPlayer();
                            gameField.enterField();
                        }
                        case 3 -> {
                            gameField.buyBotBuilding();
                            bot.enterHP();
                            bot.attackPlayer();
                            gameField.enterField();
                            System.out.println(ConsoleColors.RED + "My gold:\t" + bot.getGold() + ConsoleColors.RESET);
                        }
                    }
                    gameField.nullingSteps(false);
                    botMoveCnt++;
                }
            }
        }
        if (gameField.notNullEnemy()) {
            System.out.println(ConsoleColors.RED_BOLD + "The enemy has won" + ConsoleColors.RESET);
            botMoveCnt++;
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "You're the winner" + ConsoleColors.RESET);
            myPlayerMoveCnt++;
        }
        System.out.println(ConsoleColors.CYAN_BOLD + "The number of my moves:\t" + myPlayerMoveCnt + "\t\tThe number of bot moves:\t" + botMoveCnt);
    }

}