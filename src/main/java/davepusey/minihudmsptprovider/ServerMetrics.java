package davepusey.minihudmsptprovider;

import net.minecraft.server.MinecraftServer;

public class ServerMetrics
{
    private static long lastTickSample = 0;

    public static float MSPT(MinecraftServer server)
    {
        float lastTickTimeMs = (float) lastTickSample / 1000000;
        return lastTickTimeMs;
    }

    public static float TPS(MinecraftServer server)
    {
        float lastTickRate = Math.min(20, 1000 / MSPT(server));
        return lastTickRate;
    }

    public static void setLastTickSample(long m)
    {
        lastTickSample = m;
    }
}
