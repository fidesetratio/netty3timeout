package com.app;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

public class TelnetServer {
	 static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
	
	public static void main(String args[]) {
		ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
		final Timer timer = new HashedWheelTimer();
		final ChannelHandler  idleStateHandler = new IdleStateHandler(timer, 1, 1, 0);// timer must be shared.
				
		 bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				 ChannelPipeline p = Channels.pipeline(idleStateHandler, new IdleStateAware());
				 p.addLast("echo", new EchoServerHandler());
	             return p;
			}
			 
		 });
		
		bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.receiveBufferSize", 1048576);
        bootstrap.setOption("child.sendBufferSize", 1048576);
	
        bootstrap.bind(new InetSocketAddress(PORT));
	
	}
}
