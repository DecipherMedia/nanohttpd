package fi.iki.elonen;

import java.io.IOException;

public class ServerRunner {
    public static void run(Class serverClass) {
        try {
            executeInstance((NanoHTTPD) serverClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeInstance(NanoHTTPD server) {
        try {
            server.start();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
            System.exit(-1);
        }

        /*System.out.println("Server started, Hit Enter to stop.\n");

        try {
            System.in.read();
        } catch (Throwable ignored) {
        }*/
	
	// Get the server thread and wait on it.
        try {
            server.myThread.join();
        } catch(InterruptedException e) {
            System.err.println("Server interrupted:\n" + e);
            System.exit(-1);
        }

        server.stop();
        System.out.println("Server stopped.\n");
    }
}
