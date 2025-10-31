package davepusey.minihudmsptprovider;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniHUDMSPTProvider implements ModInitializer
{
	public static final String MOD_ID = "minihud-mspt-provider";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final int textColor = CommonColors.GRAY;

	@Override
	public void onInitialize()
	{
		ServerTickEvents.END_SERVER_TICK.register((server) ->
		{
			MutableComponent header = Component.literal(server.getPlayerCount() + "/" + server.getMaxPlayers() + " players").withColor(textColor);
			MutableComponent footer = Component.literal("TPS: " + String.format("%.1f", ServerMetrics.TPS(server)) + 
			                                " MSPT: " + String.format("%.1f", ServerMetrics.MSPT(server))).withColor(textColor);

			ClientboundTabListPacket packet = new ClientboundTabListPacket(header, footer);
			server.getPlayerList().broadcastAll(packet);
		});
	}
}