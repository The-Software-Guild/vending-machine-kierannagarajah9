package main.java.dao;

import main.java.dto.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface VendingMachineDao {
    List<Item> getItems() throws FileNotFoundException;

    void writeLibrary() throws IOException;


}
