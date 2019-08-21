package com.app;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class EchoServerHandler extends SimpleChannelUpstreamHandler {
	 @Override
	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
	          e.getChannel().write(e.getMessage());
	    }
	 
	 @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
	        // Close the connection when an exception is raised.
	        e.getCause().printStackTrace();
	        e.getChannel().close();
	    }
	 
}
