import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManager {

    private ArrayList<DataItem> cities;




    //bezparametrický konstruktor
    public DataManager() {
        this.cities = new ArrayList<>();
    }

    //metoda pro výpočet rozdílných vzdáleností
    public double getDistance(DataItem number1, DataItem number2) {
        //radius Země
        final double R = 6371.3;

        double latitudeRadianFirst = Math.toRadians(number1.getLatitude());
        double latitudeRadianSecond = Math.toRadians(number2.getLatitude());

        double latitudeDifference = Math.toRadians(number2.getLatitude() - number1.getLatitude());
        double longitudeDifference = Math.toRadians(number2.getLongitude() - number1.getLongitude());


        double a = Math.sin(latitudeDifference / 2) * Math.sin(latitudeDifference / 2) +
                Math.cos(latitudeRadianFirst) * Math.cos(latitudeRadianSecond) *
                        Math.sin(longitudeDifference / 2) * Math.sin(longitudeDifference / 2);

        double c = 2 * Math.atan2((Math.sqrt(a)),Math.sqrt(1 - a));

        return R * c;
    }

    //CSV data
   public void loadDada() {


        String[] lines = FileManager.fileToArray("worldcities.csv");

        for (String line : lines) {


           final String lineMatcher = "\"([^\"]+)\",\"[^\"]+\",\"([0-9.-]+)\",\"([0-9.-]+)\",\"([^\"]+)\",\"([^\"]+)\",\"([^\"]+)\".*";

           final Pattern pattern;
           //pattern, který se má shodovat
           pattern= Pattern.compile(lineMatcher);
           final Matcher matcher;
           //regulární výraz
           matcher= pattern.matcher(line);


            // pokud je nalezena shoda v line
            if (matcher.find()) {

                // vytvoří DataItem
                DataItem actualItem = buildCity(matcher);
                // přidání cities do ArrayList
                this.cities.add(actualItem);
            }
        }



    }






    //privátní metoda metoda pro výpis aktuálních hodnot
    private DataItem  buildCity(Matcher matcher) {
        // Deklarace hlavních proměnných, které budou použity pro práci
                            //vrací vstupní hodnoty zachycené z csv pomocí regex
        String cityName = matcher.group(1);
        double latitude = Double.parseDouble(matcher.group(2));
        double longitude = Double.parseDouble(matcher.group(3));
        String iso3 = matcher.group(6);

        // Inicializuje položku města
        return new DataItem(cityName, latitude, longitude, iso3);
    }





    public DataItem searcheLocation(DataItem searcheLocation) {

        // Na začátku je důležité získat první položku v seznamu, abychom ji mohli porovnat v iterační smyčce
        DataItem  searchedCity = this.cities.get(0);
        double nearestDistance = getDistance(searcheLocation, searchedCity);

        for (int i = 1; i < this.cities.size(); i++) {

            // Získá položku města v aktuálním indexu
            DataItem  actualCity = this.cities.get(i);



            // Vypočítá vzdálenost mezi koordinací města a naší koordinací

            double actualDistance = getDistance(searcheLocation, actualCity);

            // Porovná dané koordinace, pokud jsou blíže než dříve
            if (actualDistance < nearestDistance) {
                searchedCity = actualCity;
                nearestDistance = actualDistance;
            }
        }

        return searchedCity;
    }

    //metoda pro vyhledánání 5 požadovaných měst
    public DataItem[] searchFiveCities(DataItem searchedDataItem) {
        //pomocí pole najdu 5 měst podle indexu
        DataItem[] searchFiveCities = new DataItem [5];

        //cyklus pouze pro výpis 5 měst
        for (int index = 0; index < 5; index++) {

            //Získá nejbližší město
            DataItem  searchFiveCities1 = this.searcheLocation(searchedDataItem);
            searchFiveCities[index] = searchFiveCities1;

            // Odstraní již nalezené hledané místo(město) a zamezí jeho opakování
            //když se to nenapíše, vypíše se 5 stejných nalezených měst
            this.cities.remove(searchFiveCities1);
        }
        return searchFiveCities;
    }
    //vzdálenost mezi dvěma městy
    public double getDistance1(double lat1, double lon1, double lat2, double lon2){
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }

}
