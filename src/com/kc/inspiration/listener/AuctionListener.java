package com.kc.inspiration.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kc.inspiration.thread.AuctionThread;

/**
 * Application Lifecycle Listener implementation class AuctionListener
 *
 */
public class AuctionListener implements ServletContextListener {

	private AuctionThread auctionThread;
    /**
     * Default constructor. 
     */
    public AuctionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	if(auctionThread==null){
    		auctionThread=new AuctionThread();
    		auctionThread.start();
    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	if(auctionThread!=null&&auctionThread.isInterrupted()){
    		auctionThread.interrupt();
    	}
    }
	
}
