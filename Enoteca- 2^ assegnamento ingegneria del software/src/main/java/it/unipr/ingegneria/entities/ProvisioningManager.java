package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObservable;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProvisioningManager implements IObservable<Employee> {

    private List<Employee> managedEmployees;
    private static final Logger logger = Logger.getLogger(ProvisioningManager.class);

    public ProvisioningManager() {
        this.managedEmployees = new ArrayList<>();
    }


    @Override
    public void addObserver(Employee employee) {
        managedEmployees.add(employee);
    }

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
            employee.update(elements);
        }
    }

}
