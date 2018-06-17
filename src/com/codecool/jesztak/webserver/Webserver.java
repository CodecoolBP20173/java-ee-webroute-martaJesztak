package com.codecool.jesztak.webserver;

import com.codecool.jesztak.handler.Routes;
import com.codecool.jesztak.handler.WebRoute;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class Webserver {

    private Class<?> routeClass;
    private int port;
    private int maxThreads;
    HttpServer server;
    private Routes routes;

    public Webserver(Class<?> routeClass, int port, int maxThreads) {
        this.routeClass = routeClass;
        this.port = port;
        this.maxThreads = maxThreads;
        this.routes = new Routes();
    }

    public void initialize() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        setupContext();
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public void setupContext() {
        Method[] routes = routeClass.getDeclaredMethods();
        for (Method method : routes) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                String path = method.getAnnotation(WebRoute.class).path();
                server.createContext(path, httpExchange -> {
                    try {
                        String response = (String)method.invoke(new Routes());
                        httpExchange.sendResponseHeaders(200, response.length());
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("BAD");
                    }
                });
            }

        }
    }

    public static void main(String[] args) throws Exception {

        Webserver server = new Webserver(Routes.class, 8080,0);
        server.initialize();

    }

}
