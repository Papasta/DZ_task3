package Field;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Field {
    private static Field instance;

    private Field() {
    }

    public static Field getInstance() {
        if (instance == null)
            instance = new Field();
        return instance;
    }

    static ArrayList<ArrayList<String>> allCell = new ArrayList<>();

    public void nullCell() {
        for (int i = 0; i < 10; i++) {
            ArrayList<String> list3 = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                list3.add(null);
            allCell.add(list3);
        }
        String s = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Random r = new Random();
                int t = r.nextInt(100) + 1;
                if (t > 40) {
                    s = "*";
                } else if (t > 20) {
                    s = "#";
                } else if (t > 7) {
                    s = "!";
                } else {
                    s = "@";
                }
                allCell.get(i).set(j, s);
            }
        }
    }

    public static void setOneCell(int x, int y, String newFine) {
        allCell.get(x).set(y, newFine);
    }

    public static String symbCell(int x, int y) {
        return allCell.get(x).get(y);
    }

    public void save(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/field.txt");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                nFile.write(allCell.get(i).get(j));
            nFile.write("\n");
        }
        nFile.close();
    }

    public void edit(String path) throws Exception {
        FileWriter nFile = new FileWriter(path + "/field.txt");
        System.out.println("Enter string of cells without space. Size = 10");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String cell = in.nextLine();
            nFile.write(cell + "\n");
        }
        nFile.close();
    }

    public void load(Scanner fieldScan) {
        for (int i = 0; i < 10; i++) {
            ArrayList<String> list3 = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                list3.add(null);
            allCell.add(list3);
        }
        String s = "";
        for (int i = 0; i < 10; i++) {
            s = fieldScan.nextLine();
            int j = 0;
            for (char c : s.toCharArray()) {
                allCell.get(i).set(j, String.valueOf(c));
                j++;
            }
        }
    }
}
