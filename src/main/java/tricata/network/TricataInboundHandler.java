package tricata.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * custom class to read inbound information and translate it back to a readable Tricata instance.
 *
 * @author Kristian Hansen
 */
public class TricataInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) {

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {

	}
}
