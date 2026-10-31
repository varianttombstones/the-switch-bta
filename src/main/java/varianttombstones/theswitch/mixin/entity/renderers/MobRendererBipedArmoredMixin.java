package varianttombstones.theswitch.mixin.entity.renderers;

import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererBipedArmored;
import net.minecraft.core.entity.IArmorWearing;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemQuiver;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import varianttombstones.theswitch.item.ItemSanityAmulet;

@Mixin(MobRendererBipedArmored.class)
public class MobRendererBipedArmoredMixin<T extends Mob & IArmorWearing<HumanArmorShape>> extends MobRendererBiped<T> {
	public MobRendererBipedArmoredMixin(float shadowSize) {
		super(shadowSize);
	}

	@Override
	protected @Nullable StaticEntityModel getActiveModel(@NotNull T t) {
		return null;
	}

	@Inject(
		method="getAndSetupModelForLayer",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/item/ItemStack;getItem()Lnet/minecraft/core/item/Item;",
			shift = At.Shift.AFTER
		),
		cancellable = true)
	protected void getAndSetupModelForLayer(@NotNull T entity, float brightness, float partialTick, int layer,
											CallbackInfoReturnable<StaticEntityModel> cir) {
		ItemStack itemstack = entity.getItemInArmorSlot(HumanArmorShape.values()[layer - 1]);
		assert itemstack != null;
		Item item = itemstack.getItem();
		if (item instanceof ItemSanityAmulet) {
			this.bindTexture("/assets/theswitch/textures/armor/sanitycharm.png");

			StaticEntityModel model = this.getModel("armor.chestplate");
			cir.setReturnValue(this.setupAnimations(entity, model, partialTick, layer));
		}
	}
}
