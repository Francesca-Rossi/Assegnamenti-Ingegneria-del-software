package classi;

public class Person
{
    private String name;
    private String surname;
    private String address;
    private String mail;
    private String password;

    public Person(String name, String surname, String address, String mail, String password)
    {
        this.name=name;
        this.surname=surname;
        this.mail=mail;
        this.address=address;
        this.password=password;
    }

    /**
     * Setter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter
     */
    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getAddress()
    {
        return address;
    }

    public String getMail()
    {
        return mail;
    }

    public String getPassword()
    {
        return password;
    }
}