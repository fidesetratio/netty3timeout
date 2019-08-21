package com.app;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

public class IdleStateAware extends IdleStateAwareChannelHandler {
	@Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
        if (e.getState() == IdleState.READER_IDLE) {
          System.out.println("close");
        } else if (e.getState() == IdleState.WRITER_IDLE) {
           System.out.println("writer");
        }
    }
}
