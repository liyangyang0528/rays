package com.lyyco.rays.service.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * TimeServerHandler
 * com.lyyco.rays.service.nio
 *
 * @Author liyangyang
 * 2018/3/19
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{
    /*
    the channelActive() method will be invoked
    when a connection is established and ready to generate traffic
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx){
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
        //A ChannelFuture represents an I/O operation which has not yet occurred.
        final ChannelFuture f = ctx.writeAndFlush(time);
        /*
        How do we get notified when a write request is finished then?
        This is as simple as adding a ChannelFutureListener to the returned
        ChannelFuture. Here, we created a new anonymous ChannelFutureListener
        which closes the Channel when the operation is done
         */
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                assert f == channelFuture;
                ctx.close();
            }
        });
    }


}
