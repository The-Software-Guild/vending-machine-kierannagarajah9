package main.java.service;

import main.java.dao.VendingMachineDao;
import main.java.dao.VendingMachineDaoImpl;
import main.java.dto.Change;
import main.java.dto.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    VendingMachineDao dao;


    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;

    }

    private BigDecimal balance = new BigDecimal("0");


    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal addToBalance(String amt) {

        BigDecimal newAmt = new BigDecimal(amt);
        balance = balance.add(newAmt);
        System.out.println(balance);
        return balance;
    }


    public void updateItemQuantity(Item item) {
        item.setStock(item.getStock() - 1);
    }

    public Item getItem(String id) throws FileNotFoundException {
        Item myItem = new Item("0");
        List<Item> items = dao.getItems();
        for (Item item : items) {
            if (item.getId().equals(id))
                myItem = item;
        }
        return myItem;
    }


    public void buyItem(Item item) throws IOException {
        System.out.println("balance: " + balance);
        System.out.println("item cost " + item.getCost());
        if (balance.compareTo(item.getCost()) > 0) {
            updateItemQuantity(item);
            balance = balance.subtract(item.getCost());
        }
        dao.writeLibrary();
    }


    public List<Item> getAllItems() throws FileNotFoundException {
        return dao.getItems().stream()
                .filter(item -> item.getStock() > 0)
                .sorted(Comparator.comparing((i -> Integer.parseInt(i.getId()))))
                .collect(java.util.stream.Collectors.toList());
    }


    public String findChange() {
        String change = Change.getChange(balance);
        balance = new BigDecimal("0");
        return change;
    }


}
