package ro.bogdantruca.siitandroidfundamentals;

public class User {

    public static final int MAX_VALUE = 7;
    public static int sValue = 10;

    private String mName;
    static String sNmae;

    private int getSum() {
        int sum = 0;
        return sum + sValue;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
