package varianttombstones.theswitch.mixin.entity.renderers;

import net.minecraft.client.render.entity.MobRendererPig;
import net.minecraft.client.render.entity.MobRendererQuadruped;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.entity.SwitchPig;

@Mixin(MobRendererPig.class)
public class MobRendererPigMixin extends MobRendererQuadruped<MobPig> {

	public MobRendererPigMixin(float shadowSize) {
		super(shadowSize);
	}

	@Override
	protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NotNull MobPig entity, float brightness, float partialTick, int layer) {
		boolean sitting = entity.getSitting();
		StaticEntityModel model;

		switch (layer)
		{
			case 1:
				this.bindTexture("/assets/minecraft/textures/entity/saddle.png");
				model = this.getModel("saddle");
				break;
			case 2:
				this.bindTexture("/assets/theswitch/textures/entity/pig_charm.png");
				model = this.getModel("saddle");
				break;
			default:
				model = this.getModel("main");
				break;
		}

		model.resetBones();
		BoneTransform head = model.getTransform("head");
		float bodyYaw = this.getBodyYaw(entity, partialTick);
		float headYaw = this.getHeadYaw(entity, partialTick) - bodyYaw;
		float headPitch = this.getHeadPitch(entity, partialTick);
		if (sitting) {
			BoneTransform body = model.getTransform("body");
			BoneTransform leg0 = model.getTransform("leg0");
			BoneTransform leg1 = model.getTransform("leg1");
			body.rotX = (double)-27.5F * (double) MathHelper.DEG_TO_RAD;
			body.posY = (double)-4.0F;
			body.posZ = (double)1.0F;
			leg0.rotX = (double)(-90.0F * MathHelper.DEG_TO_RAD);
			leg0.rotY = (double)(15.0F * MathHelper.DEG_TO_RAD);
			leg0.posX = (double)-1.0F;
			leg0.posY = (double)-4.0F;
			leg1.rotX = (double)(-90.0F * MathHelper.DEG_TO_RAD);
			leg1.rotY = (double)(-15.0F * MathHelper.DEG_TO_RAD);
			leg1.posX = (double)1.0F;
			leg1.posY = (double)-4.0F;
			head.posZ = (double)1.0F;
			head.rotX += (double)headPitch;
			head.rotY += (double)headYaw;
		} else {
			head.rotX = (double)headPitch;
			head.rotY = (double)headYaw;
			BoneTransform leg0 = model.getTransform("leg0");
			BoneTransform leg1 = model.getTransform("leg1");
			BoneTransform leg2 = model.getTransform("leg2");
			BoneTransform leg3 = model.getTransform("leg3");
			float limbSwing = this.getLimbSwing(entity, partialTick);
			float limbYaw = this.getLimbYaw(entity, partialTick);
			leg0.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
			leg1.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
			leg2.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbYaw);
			leg3.rotX = (double)(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbYaw);
		}

		return model;
	}

	@Override
	protected int maxRenderLayer(@NotNull MobPig entity) {
		SwitchPig pig = (SwitchPig) entity;
		if (pig.getSwitchProtectiveCharm())
		{
			return 2;
		} else if(entity.getSaddled())
		{
			return 1;
		} else {

			return 0;
		}
	}
}
