package edu.wctc;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PaintCalculator {

    private ArrayList<Paintable> paintableList = new ArrayList<>();
    private Garage garage;
    private Scanner keyboard;

    public static void main(String[] args)
    {
        new PaintCalculator();
    }

    public PaintCalculator() {
        keyboard = new Scanner(System.in);

        int option = 0;

        while (option != 5) {
            printMenu();

            String s = keyboard.nextLine();
            try {
                int choice = Integer.parseInt(s);
                switch (choice) {
                    case 1:
                        createRoom();
                        break;
                    case 2:
                        // writeFile(); -Added by Kris
                        RoomWriter rw  = new RoomWriter();
                        try {
                            rw.writeRoomFile("room.dat", paintableList);
                            System.out.println("Rooms saved to file.");
                        } catch (IOException e) {
                            System.out.println("IO Exception Error:");
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        // readFile() -Added by Kris;
                        RoomReader rr = new RoomReader();
                        try {
                            paintableList = rr.readRoomFile("room.dat");
                            //Check for garage
                            for (Paintable p : paintableList) {
                                 if (p.getClass().getName() == "edu.wctc.Garage")
                                    garage = (Garage)p;
                            }
                            Room.setRoomCount(rr.getRoomsRead());
                            System.out.println(rr.getRoomsRead() + " room(s) read from file.");
                        } catch (IOException e) {
                            System.out.println("IO Exception Error:");
                            System.out.println(e.getMessage());
                        } catch (ClassNotFoundException e) {
                            System.out.println("Class Not Found Exception Error:");
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        printRooms();
                        break;
                    case 5:
                        if (garage == null) {
                            System.out.print("No garage yet. Add? (Enter Y to Add): ");
                            s = keyboard.nextLine();
                            if (s.equalsIgnoreCase("y")) {
                                garage = new Garage();
                                paintableList.add(garage);
                            }
                        } else System.out.println(garage.toString());
                        break;
                    case 6:
                        System.out.println("Goodbye");
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }

    }

    private void printRooms() {
        if (paintableList.isEmpty()) {
            System.out.println("No rooms yet");
        }

        for (Paintable room : paintableList) {
            System.out.println(room.toString());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add room");
        System.out.println("2. Write rooms to file");
        System.out.println("3. Read rooms from file");
        System.out.println("4. View rooms");
        System.out.println("5. Garage");
        System.out.println("6. Exit");
        System.out.println();
    }

    private int promptForDimension(String name) {
        System.out.print("Enter the room's " + name + ": ");
        String response = keyboard.nextLine();
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void createRoom() {
        int length = promptForDimension("length");
        int width = promptForDimension("width");
        int height = promptForDimension("height");

        try {
            Room room = new Room(length, width, height);
            paintableList.add(room);

            System.out.println("Room successfully created");
        } catch (BadWidthException | BadHeightException e) {
            System.out.println("Could not create room.");
        }
    }
}
