package davepusey.minihudmsptprovider;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniHUDMSPTProvider implements ModInitializer
{
	public static final String MOD_ID = "minihud-mspt-provider";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final int textColor = Colors.GRAY;

	@Override
	public void onInitialize()
	{
		ServerTickEvents.END_SERVER_TICK.register((server) ->
		{
			MutableText header = Text.literal(server.getCurrentPlayerCount() + "/" + server.getMaxPlayerCount() + " players").withColor(textColor);
			MutableText footer = Text.literal("TPS: " + String.format("%.1f", ServerMetrics.TPS(server)) + 
			                                " MSPT: " + String.format("%.1f", ServerMetrics.MSPT(server))).withColor(textColor);

			PlayerListHeaderS2CPacket packet = new PlayerListHeaderS2CPacket(header, footer);
			server.getPlayerManager().sendToAll(packet);
		});
	}
}