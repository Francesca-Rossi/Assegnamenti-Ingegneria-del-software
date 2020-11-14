package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;


import java.util.*;

/**
 * The {@code ProvisioningManger} is use to add provisioning manager at the system
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 */
public class ProvisioningManager implements IObservable<Employee> {

    private List<Employee> managedEmployees;
    private Queue<Map> ordersQueue;
    private static final Logger logger = Logger.getLogger(ProvisioningManager.class);

    /**
     * Main costructor
     */
    public ProvisioningManager() {
        this.managedEmployees = new ArrayList<>();
        this.ordersQueue = new LinkedList<Map>();
    }

    /**
     * Add employee to the list of observer
     * @param employee
     */
    @Override
    public void addObserver(Employee employee) {
        managedEmployees.add(employee);
    }

    /**
     * remove employee from the list of observer
     * @param employee
     */
    @Override
    public void removeObserver(Employee employee) {
        managedEmployees.remove(employee);
    }


    public void handleProvisioning(Map<Params, Object> elements) {
        logger.info("Provisioning Manager received request");
        Optional<Employee> optionalEmployee = managedEmployees.stream()
                .filter(i -> !i.getWorking())
                .findFirst();
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setWorking(true);
            if (!ordersQueue.isEmpty())
                ordersQueue.stream().forEach(order -> employee.update(elements));
            employee.update(elements);
        }
        if (!optionalEmployee.isPresent()) {
            this.ordersQueue.add(elements);
        }
    }

}
