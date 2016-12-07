package com.andre.netty;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
@Component
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// // Discard the received data silently.
		// ((ByteBuf) msg).release(); // (3)

		// // With Response.
		// ByteBuf in = (ByteBuf) msg;
		// try {
		// while (in.isReadable()) { // (1)
		// System.out.print((char) in.readByte());
		// System.out.flush();
		// }
		// } finally {
		// ReferenceCountUtil.release(msg); // (2)
		// }

		// Echo.
		try {
			ctx.writeAndFlush(msg);
		} catch (Exception e) {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

}
