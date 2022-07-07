package main.java.service;


import main.java.dao.ItemPersistenceException;
import main.java.dto.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    BigDecimal addToBalance(String amt) throws ItemPersistenceException;

    List<Item> getAllItems() throws ItemPersistenceException, FileNotFoundException;

    Item getItem(String id) throws ItemPersistenceException, FileNotFoundException;

    void buyItem(Item item) throws ItemPersistenceException, ItemNoItemInventoryException, VendingMachineInsufficientFundsException, IOException;

    BigDecimal getBalance() ;

    String findChange();

}
