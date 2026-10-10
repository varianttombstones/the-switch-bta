package varianttombstones.theswitch.entity.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRendererQuadruped;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;

@Environment(EnvType.CLIENT)
public class MobRendererPsychoticPig extends MobRendererQuadruped<MobPsychoticPig> {
	protected void renderSpecials(@NotNull TessellatorGeneral tessellator, @NotNull MobPsychoticPig entity, double x, double y, double z) {
		super.renderSpecials(tessellator, entity, x, y, z);
	}

	public MobRendererPsychoticPig(float shadowSize) {
		super(shadowSize);
	}

	public float getShadowSize(@NotNull MobPsychoticPig entity) {
		return entity.lookFadeAlpha < 0.9F ? 0.0F : super.getShadowSize(entity);
	}
	protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NotNull MobPsychoticPig entity, float brightness, float partialTick, int layer) {
		StaticEntityModel model = this.getModel("main");
		model.resetBones();
		BoneTransform head = model.getTransform("head");
		float bodyYaw = this.getBodyYaw(entity, partialTick);
		float headYaw = this.getHeadYaw(entity, partialTick) - bodyYaw;
		head.rotX = this.getHeadPitch(entity, partialTick);
		head.rotY = headYaw;
		BoneTransform leg0 = model.getTransform("leg0");
		BoneTransform leg1 = model.getTransform("leg1");
		BoneTransform leg2 = model.getTransform("leg2");
		BoneTransform leg3 = model.getTransform("leg3");
		float limbSwing = this.getLimbSwing(entity, partialTick);
		float limbYaw = this.getLimbYaw(entity, partialTick);
		leg0.rotX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw;
		leg1.rotX = (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbYaw);
		leg2.rotX = (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbYaw);
		leg3.rotX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		return model;
	}
}
