import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
       DataItem dataItem = new DataItem("Opava", 49.9389009,17.9024174,"CZE");
       System.out.println("Souřadnice Opavy:");
       System.out.println(dataItem);




       DataManager dataManager = new DataManager();
       dataManager.loadDada();

       System.out.println("\nVzdalenost mezi Opavou a Ostravou= " + dataManager.getDistance1(49.9389009,17.9024174,49.8304,18.25) + " km");


       DataItem searchLocation = dataManager.searcheLocation(new DataItem("Opava", 49.9389009,17.9024174,"CZE"));

       System.out.println("Nejbližší město: "+searchLocation);



       System.out.println("\nNejbližší velká města:");
       DataItem[] searchFiveCities = dataManager.searchFiveCities(new DataItem("Opava", 49.9389009,17.9024174,"CZE"));


       //pomocí cyklu foreach vypíše 5 nejbližších měst
       for (DataItem actualDataItem : searchFiveCities) {
           System.out.println(actualDataItem);
       }




    }

}

