package tricata.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TricataClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) {
		ByteBuf contents = (ByteBuf)message;

		context.close();
		contents.release();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}
}
