package varianttombstones.theswitch.entity.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.util.helper.Colors;
import net.minecraft.core.entity.animal.MobSheep;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.entity.monster.MobPsychoticSheep;

@Environment(EnvType.CLIENT)
public class MobRendererPsychoticSheep extends MobRenderer<MobPsychoticSheep> {
	public MobRendererPsychoticSheep(float shadowSize) {
		super(shadowSize);
	}

	protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NotNull MobPsychoticSheep entity, float brightness, float partialTick, int layer) {
		StaticEntityModel model;
		int colorIndex = entity.getFleeceColor().blockMeta;
		Color color = Colors.allSheepColors[colorIndex];
		switch (layer) {
			case 0:
			default:
				model = this.getModel("main");
				break;
			case 1:
				GLRenderer.setColor3f(brightness * (float)color.getRed() / 255.0F, brightness * (float)color.getGreen() / 255.0F, brightness * (float)color.getBlue() / 255.0F);
				model = this.getModel("main");
				break;
			case 2:
				GLRenderer.setColor3f(brightness * (float)color.getRed() / 255.0F, brightness * (float)color.getGreen() / 255.0F, brightness * (float)color.getBlue() / 255.0F);
				model = this.getModel("wool");
				break;
		}

		this.bindTexture("/assets/minecraft/textures/entity/sheep/wool_overlay.png");

		model.resetBones();
		BoneTransform head = model.getTransform("head");
		BoneTransform leg0 = model.getTransform("leg0");
		BoneTransform leg1 = model.getTransform("leg1");
		BoneTransform leg2 = model.getTransform("leg2");
		BoneTransform leg3 = model.getTransform("leg3");
		float bodyYaw = this.getBodyYaw(entity, partialTick);
		float headYaw = this.getHeadYaw(entity, partialTick) - bodyYaw;
		float headPitch = this.getHeadPitch(entity, partialTick);
		float limbSwing = this.getLimbSwing(entity, partialTick);
		float limbYaw = this.getLimbYaw(entity, partialTick);
		leg0.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		leg1.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
		leg2.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
		leg3.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		head.rotX = (double)headPitch;
		head.rotY = (double)headYaw;

		return model;
	}

	protected int maxRenderLayer(@NotNull MobPsychoticSheep entity) {
		return entity.getSheared() ? 1 : 2;
	}
}
