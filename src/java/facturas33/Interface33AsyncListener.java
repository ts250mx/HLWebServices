/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas33;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Ruben
 */
class Interface33AsyncListener implements AsyncListener {


    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onComplete");

        
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onError");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onStartAsync");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onTimeout");
            //we can send appropriate response to client
            ServletResponse response = event.getAsyncContext().getResponse();
            PrintWriter out = response.getWriter();
            out.write("TimeOut Error in Processing");
    }
    
}
