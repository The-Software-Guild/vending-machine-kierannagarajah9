package main.java.controller;

import main.java.dao.ItemPersistenceException;
import main.java.dao.VendingMachineDao;
import main.java.dao.VendingMachineDaoImpl;
import main.java.dto.Change;
import main.java.dto.Item;
import main.java.service.ItemNoItemInventoryException;
import main.java.service.VendingMachineInsufficientFundsException;
import main.java.service.VendingMachineServiceLayer;
import main.java.service.VendingMachineServiceLayerImpl;
import main.java.ui.VendingMachineView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
    VendingMachineView view;
    VendingMachineServiceLayer service;


    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }


    public void run() throws IOException, ItemPersistenceException, VendingMachineInsufficientFundsException, ItemNoItemInventoryException {
        int option;
        boolean keepGoing = true;

        while (keepGoing) {
            option = view.displayMenuAndGetSelction();


            switch (option) {
                case 1:
                    showItemList();
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    buyItem();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    returnChange();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }


        }
    }


    public void showItemList() throws ItemPersistenceException, FileNotFoundException {
        List<Item> allItems = service.getAllItems();
        view.displayItems(allItems);
    }

    public void addMoney() throws ItemPersistenceException {
        String amount = view.getUserMoney();
        service.addToBalance(amount);

    }


    public void buyItem() throws IOException, ItemPersistenceException, VendingMachineInsufficientFundsException, ItemNoItemInventoryException {
        String itemChoice = view.getBuyChoice();
        Item item = service.getItem(itemChoice);
        service.buyItem(item);

    }

    public void viewBalance() {
        BigDecimal balance = service.getBalance();
        view.displayBalance(balance);
    }

    public void returnChange() {
        String change = service.findChange();
        view.displayChange(change);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }


}
