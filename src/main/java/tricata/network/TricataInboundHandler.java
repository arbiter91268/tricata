package tricata.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import tricata.draw.GameWindow;

/**
 * custom class to read inbound information and translate it back to a readable Tricata instance.
 *
 * @author Kristian Hansen
 */
public class TricataInboundHandler extends ChannelInboundHandlerAdapter {

	private final GameWindow instance;

	public TricataInboundHandler(GameWindow instance) {
		this.instance = instance;
	}

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
