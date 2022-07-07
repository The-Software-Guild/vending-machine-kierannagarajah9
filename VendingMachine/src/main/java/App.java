package main.java;

import com.sun.source.tree.NewArrayTree;
import main.java.controller.VendingMachineController;
import main.java.dao.ItemPersistenceException;
import main.java.dao.VendingMachineDao;
import main.java.dao.VendingMachineDaoImpl;
import main.java.service.ItemNoItemInventoryException;
import main.java.service.VendingMachineInsufficientFundsException;
import main.java.service.VendingMachineServiceLayer;
import main.java.service.VendingMachineServiceLayerImpl;
import main.java.ui.UserIO;
import main.java.ui.UserIOConsoleImpl;
import main.java.ui.VendingMachineView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws IOException, ItemPersistenceException, VendingMachineInsufficientFundsException, ItemNoItemInventoryException {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
        VendingMachineController controller = new VendingMachineController(view, service);
        controller.run();
    }
}
