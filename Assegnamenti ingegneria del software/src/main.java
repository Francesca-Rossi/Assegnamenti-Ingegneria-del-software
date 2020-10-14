public class main {
    public static void main(String args[]) {
        Circolo circolo = new Circolo();

        Persona socio = new Socio();
        socio
                .setName("")
                .setSurname("")
                .setEmail("")
                .setType(Type.SOCIO);

        Activity gara = new Race();
        ((Socio) socio).subscribe(gara);
        ((Socio) socio).delete(gara);

        ((Socio) socio).toString();
        

        Persona admin = new Amministratore(circolo);
        ((Amministratore) admin).addUser(socio);
        ((Amministratore) admin).deleteUser(socio);
        ((Amministratore) admin).subscribe(gara);
        ((Amministratore) admin).delete(gara);

        admin
                .setName("")
                .setSurname("")
                .setEmail("")
                .setType(Type.ADMIN);
    }
}
