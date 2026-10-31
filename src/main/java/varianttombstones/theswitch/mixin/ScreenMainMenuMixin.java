package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.ScreenMainMenu;
import net.minecraft.client.holiday.Holidays;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.util.helper.MathHelper;
import org.joml.Math;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScreenMainMenu.class)
public class ScreenMainMenuMixin extends Screen {
	@Shadow
	private String splashText = "missingno";

	@Shadow
	private void drawLogo(float partialTick) {
	}

	@Override
	public void render(int mx, int my, float partialTick) {
		I18n i18n = I18n.getInstance();
		this.renderBackground();
		int c = 274;
		int k = this.width / 2 - c / 2;
		byte byte0 = 30;
		if (!(Boolean) GameSettings.ALPHA_MENU.value) {
			if (Holidays.getCurrentThemedHoliday() == Holidays.HALLOWEEN)
			{
				this.mc.textureManager.loadTexture("/assets/theswitch/textures/misc/halloweenlogo.png").bind();
			} else {
				this.mc.textureManager.loadTexture("/assets/theswitch/textures/misc/theswitch_logo.png").bind();
			}
			GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(k, byte0, 0, 0, 274, 60, 0.0036496350364963502, 0.016666666666666666);
		} else {
			this.drawLogo(partialTick);
		}

		GLRenderer.pushFrame();
		GLRenderer.modelM4f().translate((float) (this.width / 2 + 140), 80.0F, 0.0F);
		GLRenderer.modelM4f().rotateZ(Math.toRadians(-20.0F));
		float scalar = 1.8F - MathHelper.abs(MathHelper.sin((float) (System.currentTimeMillis() % 1000L) / 1000.0F * (float) java.lang.Math.PI * 2.0F) * 0.1F);
		scalar = scalar * 100.0F / (float) (this.fontRenderer.stringWidthDouble(this.splashText) + (double) 32.0F);
		GLRenderer.modelM4f().scale(scalar, scalar, scalar);
		this.drawStringCenteredShadow(this.fontRenderer, this.splashText, 0, -8, 16776960);
		GLRenderer.popFrame();
		int versionTextColor = !(Boolean) GameSettings.ALPHA_MENU.value && this.mc.mainMenuBackground.isEnabled() ? 16777215 : 5263440;
		this.drawStringShadow(this.fontRenderer, "Better than Adventure! " + Minecraft.VERSION, 2, 2, versionTextColor);
		String disclaimer1 = i18n.translateKey("gui.main_menu.label.disclaimer.1");
		this.drawStringShadow(this.fontRenderer, disclaimer1, this.width - MathHelper.ceil(this.fontRenderer.stringWidthDouble(disclaimer1)) - 2, this.height - 20, 16777215);
		String disclaimer2 = i18n.translateKey("gui.main_menu.label.disclaimer.2");
		this.drawStringShadow(this.fontRenderer, disclaimer2, this.width - MathHelper.ceil(this.fontRenderer.stringWidthDouble(disclaimer2)) - 2, this.height - 10, 16777215);
		super.render(mx, my, partialTick);
	}
}
