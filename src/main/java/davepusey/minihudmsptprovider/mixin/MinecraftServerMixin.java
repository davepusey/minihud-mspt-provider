package davepusey.minihudmsptprovider.mixin;

import java.util.function.BooleanSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import davepusey.minihudmsptprovider.ServerMetrics;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/server/MinecraftServer;tickServer(Ljava/util/function/BooleanSupplier;)V")
    public void tickServer(BooleanSupplier shouldKeepTicking, CallbackInfo info, @Local(ordinal = 1) long m)
    {
        ServerMetrics.setLastTickSample(m);
    }
}
