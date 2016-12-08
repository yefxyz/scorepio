package com.andre.netty;

import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Service
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4); // (2)
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

		final ChannelFuture f = ctx.writeAndFlush(time); // (3)
		// f.addListener(new ChannelFutureListener() {
		// @Override
		// public void operationComplete(ChannelFuture future) {
		// assert f == future;
		// ctx.close();
		// }
		// }); // (4)
		// or just write:
		f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
