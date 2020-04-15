import java.text.SimpleDateFormat;
import java.util.Date;

public class DataItem {

    private String name;
    private String country;
    private double latitude;
    private double longitude;

    public DataItem(String name, double latitude, double longitude, String country) {

        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    @Override
    public String toString() {
        return  "Lat: " + latitude + ","+
                " Lon: " + longitude + ", "+
                name + " => "+
                country;
    }


}
