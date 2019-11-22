import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.util.*;

public class JsonRead {
    public static <JSONObject, JSONArray> void main(String[] args) {
        // read files from carDetail.json
        JSONParser parser = new JSONParser();
        try{org.json.simple.JSONArray jsonArray =
                    (org.json.simple.JSONArray)parser.parse(new FileReader("./carDetail.json"));
            HashMap<String,HashMap<String,Object>> cars = new HashMap<String, HashMap<String,Object>>();
            HashMap<String,Integer> sellers = new HashMap<String, Integer>();
            //Make hashMap of cars
            for(Object carInfo:jsonArray) {
                org.json.simple.JSONObject car = (org.json.simple.JSONObject) carInfo;
                String lotNumber = (String) car.get("lotNumber");
                long purchasePrice = (Long) car.get("purchasePrice");
                long salePrice = (Long) car.get("salePrice");
                long profit = purchasePrice - salePrice;
                car.put("profit",profit);
                cars.put(lotNumber,car);
            }
            System.out.println("Cars HashMap: "+cars);
            // #2 retrieve all information with lot number.
            System.out.println("The information of Lot ID:500671 is "+cars.get("500671"));

            // Determine the seller who has the highest profit, cost, max price, average buyer price
            //HIGHEST PROFIT
            long maxPrice = Integer.MIN_VALUE;
            String maxSeller = null;
            for(Map.Entry<String,HashMap<String,Object>> car: cars.entrySet()) {
                if((long) car.getValue().get("profit") > maxPrice){
                    maxPrice = (long) car.getValue().get("profit");
                    org.json.simple.JSONArray party = (org.json.simple.JSONArray) car.getValue().get("party");
                    maxSeller = party.get(0).toString();
                }
            }
            System.out.println("The information of seller has the highest profit: "+maxSeller);

            // HIGHEST LOSS
            long minValue = Integer.MAX_VALUE;
            String minSeller = null;
            for(Map.Entry<String,HashMap<String,Object>> car: cars.entrySet()) {
                if((long) car.getValue().get("profit") < minValue){
                    maxPrice = (long) car.getValue().get("profit");
                    org.json.simple.JSONArray party = (org.json.simple.JSONArray) car.getValue().get("party");
                    minSeller = party.get(0).toString();
                }
            }
            System.out.println("The information of seller has the highest LOSS: "+minSeller);

            //MAX PRICED LOT
            long maxLotPrice = Integer.MIN_VALUE;
            String lotNumber = null;
            for(Map.Entry<String,HashMap<String,Object>> car: cars.entrySet()) {
                if((long) car.getValue().get("purchasePrice") > maxLotPrice){
                    maxLotPrice = (long) car.getValue().get("purchasePrice");
                    lotNumber = car.getKey();
                }
            }
            System.out.println("The highest price of car is: "+ lotNumber);

            //Average buyer price
            long total = 0;
            int number = 0;
            for(Map.Entry<String,HashMap<String,Object>> car:cars.entrySet()){
                total += (long) car.getValue().get("purchasePrice");
                number++;
            }
            System.out.println("The average price is: "+total/number);


        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
            // #3 Sort
//            List<Map.Entry<String, Object>> entryList =
//                    new ArrayList<Map.Entry<String, Object>>(cars.entrySet());
//            Collections.sort(entryList, new Comparator<Map.Entry<String, Object>>() {
//                public int compare(Map.Entry<String, Object> car1, Map.Entry<String, Object> car2) {
//                    return car1.getValue().salePrice.compareTo(car2.getValue().salePrice);
//                }
//            });
//            HashMap<String,Object> carSort = new HashMap<String, Object>();
//            for(Object car: entryList) {
//                carSort.put(car.get("lotNumber"),car);
//            }
//
//            // #4
//            HashMap<String,Integer> sellers = new HashMap<String, Integer>();
//            for(Object car: entryList) {
//                String seller = car.get("party")[0].get("partyType");
//                if(sellers.get(seller)){
//                    sellers.get(seller)++;
//                }else{
//                    sellers.get(seller) = 1;
//                }
//            }
//            Integer max = 0;
//            String seller;
//            for(Map.Entry<String,Integer> entry: sellers.entrySet()) {
//                if(entry.getValue()>max) {
//                    max = entry.getValue();
//                    seller = entry.getKey();
//                }
//            }
//            System.out.println("MOst cars seller: "+seller);
//
//            //#5
//            HashMap<String,Double> taxOfCars = new HashMap<String, Double>();
//            Reader readerTax = new FileReader("carTax.json");
//
//            JSONObject jsonTax = (JSONObject) parser.parse(readerTax);
//            for (Map.Entry<String,Object> car: cars.entrySet()) {
//                String country = car.getValue().get("country");
//                Double purchasePrice = car.getValue().get("purchasePrice");
//                Double tax = jsonTax.get(country)*0.01*purchasePrice;
//                taxOfCars.put(car.getKey(),tax);
//            }
//            System.out.println("car tax:"+taxOfCars);


    }


}


