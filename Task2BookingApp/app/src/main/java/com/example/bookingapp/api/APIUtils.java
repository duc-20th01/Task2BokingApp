package com.example.bookingapp.api;

public class APIUtils {

    private static String baseURL = "http://dinhgia.top:8080/booking/"; // Change it by your ip Wi-Fi

//    private static String baseURL="http://10.0.0.2:8081/";

    public static APIService getServer() {
        return APIClient.getClient(baseURL).create(APIService.class);
    }
}
