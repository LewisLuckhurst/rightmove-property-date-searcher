import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String url;
        Date startDate;
        Date endDate;

        System.out.println("Enter first date in format dd/MM/yyyy");
        startDate = simpleDateFormat.parse(myObj.nextLine());

        System.out.println("Enter second date in format dd/MM/yyyy");
        endDate = simpleDateFormat.parse(myObj.nextLine());

        System.out.println("Enter rightmove url");
        url = myObj.nextLine();

        getProperties(startDate, endDate, url);
    }

    public static void getProperties(Date startDate, Date endDate, String url) {
        HashSet<String> propertiesToSearch = getPropertiesToSearch(url);
        getValidProperties(propertiesToSearch, startDate, endDate);
    }

    private static HashSet<String> getPropertiesToSearch(String url) {
        HashSet<String> propertyToLookUp = new HashSet<>();
        url = replaceIndexInUrl(url);

        try {
            int index = 0;
            while (index <= 700) {
                Document doc = Jsoup.connect(url + "&index=" + index).get();
                int numberOfProperties = doc.getElementById("l-searchResults").getElementsByClass("is-list").size();
                for (int i = 0; i < numberOfProperties; i++) {
                    String id = doc.getElementById("l-searchResults").getElementsByClass("is-list").get(i).attr("id");
                    propertyToLookUp.add(id);
                }

                index = index + 24;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return propertyToLookUp;
    }

    private static void getValidProperties(HashSet<String> propertyToLookUp, Date startDate, Date endDate) {
        Runtime rt = Runtime.getRuntime();
        try {
            for (String propertyId : propertyToLookUp) {
                String url = "https://www.rightmove.co.uk/property-to-rent/" + propertyId + ".html";
                Document doc;
                doc = Jsoup.connect(url).get();

                Date dateAvailable = new Date();
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = doc.getElementsByClass("_2RnXSVJcWbWv4IpBC1Sng6").get(0).childNodes().get(1).childNode(0).toString().trim();
                    dateAvailable = simpleDateFormat.parse(date);
                } catch (Exception ignored) {
                }

                if (dateAvailable.after(startDate) && dateAvailable.before(endDate)) {
                    rt.exec("open " + url);
                }
            }
        } catch (
                Exception e) {
            throw new RuntimeException();
        }
    }

    private static String replaceIndexInUrl(String url) {
        return url.replaceAll("index=([^;]*)&", "");
    }
}