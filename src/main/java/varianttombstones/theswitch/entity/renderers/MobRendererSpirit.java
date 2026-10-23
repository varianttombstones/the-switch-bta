package varianttombstones.theswitch.entity.renderers;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererZombie;
import net.minecraft.client.render.renderer.BlendFactor;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.State;
import net.minecraft.core.entity.monster.MobSpider;
import net.minecraft.core.entity.monster.MobZombie;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.entity.monster.MobSpirit;

@Environment(EnvType.CLIENT)
public class MobRendererSpirit extends MobRendererBiped<MobSpirit> {
	public MobRendererSpirit(float shadowSize) {
		super(shadowSize);
	}

	protected @Nullable StaticEntityModel getActiveModel(@NotNull MobSpirit entity) {
		return this.getModel("main");
	}

	protected @Nullable StaticEntityModel setupAnimations(@NotNull MobSpirit entity, @Nullable StaticEntityModel model, float partialTick, int layer) {
		if (model == null) {
			return null;
		} else {
			if (layer == 1) {
				this.bindTexture("/assets/theswitch/textures/entity/spirit/eyes.png");
				GLRenderer.setLightmapCoord2i(15, 15);
				GLRenderer.enableState(State.BLEND);
				GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.ONE_MINUS_SRC_ALPHA);
				GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, (1.0F - entity.getBrightness(partialTick)) * 0.5F);
			}

			StaticEntityModel model1 = super.setupAnimations(entity, model, partialTick, layer);
			BoneTransform rightArm = model.getTransform("rightArm");
			BoneTransform leftArm = model.getTransform("leftArm");
			float limbPitch = this.getLimbPitch(entity, partialTick);
			float swingProgress = entity.getSwingProgress(partialTick);
			float var7 = MathHelper.sin(swingProgress * (float) java.lang.Math.PI);
			float var8 = MathHelper.sin((1.0F - (1.0F - swingProgress) * (1.0F - swingProgress)) * (float) Math.PI);
			rightArm.rotZ = (double)0.0F;
			leftArm.rotZ = (double)0.0F;
			rightArm.rotY = (double)(-(0.1F - var7 * 0.6F));
			leftArm.rotY = (double)(0.1F - var7 * 0.6F);
			rightArm.rotX = (double)(-90.0F * MathHelper.DEG_TO_RAD);
			leftArm.rotX = (double)(-90.0F * MathHelper.DEG_TO_RAD);
			rightArm.rotX -= (double)(var7 * 1.2F - var8 * 0.4F);
			leftArm.rotX -= (double)(var7 * 1.2F - var8 * 0.4F);
			rightArm.rotZ += (double)(MathHelper.cos(limbPitch * 0.09F) * 0.05F + 0.05F);
			leftArm.rotZ -= (double)(MathHelper.cos(limbPitch * 0.09F) * 0.05F + 0.05F);
			rightArm.rotX += (double)(MathHelper.sin(limbPitch * 0.067F) * 0.05F);
			leftArm.rotX -= (double)(MathHelper.sin(limbPitch * 0.067F) * 0.05F);
			return model1;
		}
	}
}
