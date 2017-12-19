package tricata.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import tricata.draw.GameWindow;

public class TricataServer {

	private static final short PORT = 8080;
	private final String gameName, password;
	private final GameWindow instance;
	private Thread serverThread;

	public TricataServer(GameWindow instance, String gameName, String password) {
		this.gameName = gameName;
		this.password = password;
		this.instance = instance;
	}

	public void start() throws Exception {
		Thread t = new Thread(() -> {
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			try {
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup)
						.channel(NioServerSocketChannel.class)
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel sc) throws Exception {
								sc.pipeline().addLast(new TricataInboundHandler(instance));
							}
						})
						.option(ChannelOption.SO_BACKLOG, 128)
						.childOption(ChannelOption.SO_KEEPALIVE, true);
				ChannelFuture f = b.bind(PORT).sync();
				f.channel().closeFuture().sync();
			} catch (Exception ex) {

			} finally {
				workerGroup.shutdownGracefully();
				bossGroup.shutdownGracefully();
			}
		});
	}

	public void stop() {
		if (serverThread == null) {
			System.out.println("The thread wasn't initialized in the first place");
		}
	}
}
