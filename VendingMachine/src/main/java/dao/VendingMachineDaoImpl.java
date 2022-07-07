package main.java.dao;

import main.java.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDao {
    Map<String, Item> items = new HashMap<>();
    public static final String FILE = "VendingMachine.txt";


    public List<Item> getItems() throws FileNotFoundException {
        loadLibrary();
        List<Item> itemList = new ArrayList<>(items.values());
        return itemList;
    }


    private Item unmarshalItem(String data){
        String[] array = data.split("::");

        Item itemFromFile = new Item(array[0]);  // the first element is the id which is needed in the contructor
        itemFromFile.setName(array[1]);
        itemFromFile.setCost(new BigDecimal(array[2]).setScale(2));
        itemFromFile.setStock(Integer.parseInt(array[3]));

        return itemFromFile;
    }


    private String marshalItem(Item item) {
        String formattedData = String.format("%s::%s::%f::%d", item.getId(), item.getName(), item.getCost(), item.getStock());
        return formattedData;
    }


    public void loadLibrary() throws FileNotFoundException {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(FILE)));

        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            Item currentItem = unmarshalItem(currentLine);
            items.put(currentItem.getId(), currentItem);
        }
        scan.close();
    }



    public void writeLibrary() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(FILE));

        List<Item> items = this.getItems();
        for(Item item:items){
            String itemAsText = marshalItem(item);
            out.println(itemAsText);
        }
        out.flush();
        out.close();
    }

}
