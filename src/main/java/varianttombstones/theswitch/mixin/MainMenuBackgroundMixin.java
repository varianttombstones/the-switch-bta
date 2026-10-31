package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.MainMenuBackground;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Mixin(targets = "net.minecraft.client.gui.MainMenuBackground$BackgroundListEntry")
public class MainMenuBackgroundMixin {
	@Inject(method = "loadBackgroundImage", at = @At("HEAD"), cancellable = true)
	public void loadBackgroundImage(CallbackInfoReturnable<BufferedImage> cir) throws IOException {
		InputStream stream = Minecraft.getMinecraft().textureManager.texturePacks.getResourceAsStream("/assets/theswitch/textures/misc/bg.png");
		cir.setReturnValue(ImageIO.read(stream));
	}
}
