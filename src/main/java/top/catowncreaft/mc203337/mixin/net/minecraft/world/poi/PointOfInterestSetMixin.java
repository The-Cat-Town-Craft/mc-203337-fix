package top.catowncreaft.mc203337.mixin.net.minecraft.world.poi;

import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestSet;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointOfInterestSet.class)
public class PointOfInterestSetMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(
        method = "add(Lnet/minecraft/world/poi/PointOfInterest;)Z",
        at = @At(
            value = "INVOKE",
            target = "java/lang/IllegalStateException.<init> (Ljava/lang/String;)V"
        ),
            cancellable = true)
    private void mc203337Fix(PointOfInterest poi, CallbackInfoReturnable<Boolean> cir) {
        LOGGER.error("POI data mismatch: already registered at :" + poi.getPos());
        cir.setReturnValue(false);
    }
}
