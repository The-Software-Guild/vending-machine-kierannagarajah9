package main.java.ui;

import main.java.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    UserIO io;


    public VendingMachineView(UserIO io) {
        this.io = io;
    }


    public int displayMenuAndGetSelction() {
        io.print("Main Menu");
        io.print("1. Show items ");
        io.print("2. Insert money");
        io.print("3. Buy item");
        io.print("4. View balance");
        io.print("5. Return change");
        io.print("6. Exit");

        return io.readInt("Which option would you like to chose");
    }


    public void displayItems(List<Item> items) {
        for (Item item : items) {
            String itemInfo = String.format("%s. There are %d %ss left. Each %s costs ,%f,", item.getId(), item.getStock(), item.getName(), item.getName(), item.getCost());
            io.print(itemInfo);
        }
    }


    public String getUserMoney() {
        String amount = io.readString("How much money would you like to insert?");
        return amount;
    }


    public String getBuyChoice() {
        String choice = io.readString("What is the id of the item you would like to buy?");
        return choice;
    }


    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Your balance is " + balance);
    }

    public void displayChange(String stringChange) {
        io.print(stringChange);
    }

}

