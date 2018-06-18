package com.codecool.jesztak.routes;

public class Routes {


    @WebRoute(path = "/index")
    public String index() {
        String response = "This is the response form /index page";
        return response;
    }

    @WebRoute(path = "/test1")
    public String test1() {
        String response = "This is the response form test1";
        return response;
    }

    @WebRoute(path = "/test2")
    public String index2() {
        String response = "This is the response form test2";
        return response;
    }

}
