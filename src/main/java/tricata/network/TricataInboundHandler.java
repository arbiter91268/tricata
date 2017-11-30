package tricata.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * custom class to read inbound information and translate it back to a readable Tricata instance.
 *
 * @author Kristian Hansen
 */
public class TricataInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) {
		ByteBuf in = (ByteBuf)message;
		try {
			while (in.isReadable()) {
				System.out.print((char)in.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(message);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {

	}
}
