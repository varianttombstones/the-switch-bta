package varianttombstones.theswitch.entity.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.entity.monster.MobPsychoticDeer;

@Environment(EnvType.CLIENT)
public class MobRendererPsychoticDeer extends MobRenderer<MobPsychoticDeer> {
	public MobRendererPsychoticDeer(float shadowSize) {
		super(shadowSize);
	}

	protected void preRenderTransform(@NotNull MobPsychoticDeer entity, double x, double y, double z, float yaw, float partialTick) {
		super.preRenderTransform(entity, x, y, z, yaw, partialTick);
		GLRenderer.modelM4f().scale(1.1F, 1.1F, 1.1F);
	}

	@Nullable
	protected StaticEntityModel getAndSetupModelForLayer(@NotNull MobPsychoticDeer entity, float brightness, float partialTick, int layer) {
		StaticEntityModel model = this.getModel("main");
		model.resetBones();
		BoneTransform neck = model.getTransform("neck");
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
		neck.rotX = (double)headPitch;
		head.rotX = (double)0.0F;
		head.rotY = (double)headYaw;

		leg0.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		leg1.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
		leg2.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
		leg3.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		return model;
	}
}
