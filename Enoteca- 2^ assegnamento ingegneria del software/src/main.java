/**
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 */

import entities.*;
import utils.*;


public class main {
    public static void main(String args[]) {
        WineShop shop=new WineShop();
        Wine lambrusco=new Wine(1, "Lambrusco 1", 2010, "Marchionni", "");
        Wine malvasia=new Wine(1, "Malvasia 1", 2007, "Ferrari", "");
        Client c1=new Client(1, "Pippo", "Baudo", "Pippo.baudo@gmail.com", "1234", Type.CLIENT);
        Client c2=new Client(2, "Fra", "Bianchi", "Fra.bianchi@gmail.com", "12345", Type.CLIENT);
        Client c3=new Client(3, "Marco", "Polo", "Marco.polo@gmail.com", "1234588", Type.CLIENT);
        Employee employee=new Employee(1,"Gianni", "Neri", "gn@gmail.com", "abcdef", Type.EMPLOYEE, shop);
        shop.registerEmployee(employee); //registro l'impiegato nel sistema
        //acquisto iniziale dei vini
        employee.buyWine(lambrusco,4 );
        employee.buyWine(malvasia, 6);
        //c1 si registra al negozio shop
        employee.registerClient(c1);
        //c1 acquista un certo vino
        c1.buyWine(lambrusco, 3);
        //c2 si registra al negozio
        employee.registerClient(c2);
        //c2 compra le bottiglie di un vino
        c2.buyWine(malvasia, malvasia.getQuantity());
        //c3 si registra nel negozio
        employee.registerClient(c3);
        //c3 vuole acquistare la malvasia, ma non è più disponibile
        c3.buyWine(malvasia, 3);
        //l'impiegato invia gli ordini
        employee.sendWine();
        //l'impiegato acquista della nuova malvasia
        employee.buyWine(malvasia, 8);

    }
}
