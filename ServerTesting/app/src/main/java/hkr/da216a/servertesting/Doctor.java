package hkr.da216a.servertesting;

public class Doctor {

    public final static String TABLE_NAME = "doctor";
    public final static String ID_COLUMN = "id_doctor";
    public final static String NAME_COLUMN = "name";
    public final static String USERNAME_COLUMN = "username";
    public final static String PASSWORD_COLUMN = "password";

    private int id;
    private String name;
    private String username;
    private String password;

    public Doctor(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }


}
