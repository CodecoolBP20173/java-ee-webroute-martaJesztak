package com.codecool.jesztak.webserver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import com.codecool.jesztak.handler.Routes;
import com.codecool.jesztak.webserver.Webserver;
import com.sun.net.httpserver.HttpServer;

public class Test {

    public static void main(String[] args) throws Exception {

        Webserver server = new Webserver(Routes.class, 8080,0);
        server.initialize();



    }
}
