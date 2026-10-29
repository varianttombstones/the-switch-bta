package varianttombstones.theswitch.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobPig;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.SwitchPig;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;
import varianttombstones.theswitch.item.ModItems;

@Mixin(MobPig.class)
public abstract class MobPigMixin extends MobAnimal implements SwitchPig {
	@Shadow
	public abstract boolean getSaddled();

	public MobPigMixin(World world) {
		super(world);
	}

	@Inject(
		method="updateAI",
		at=@At("TAIL")
	)
	protected void addSwitchTransformation(CallbackInfo ci) {
		if (!getSwitchProtectiveCharm()) {
			if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
				MobPsychoticPig psychoPig = new MobPsychoticPig(this.world);
				psychoPig.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
				psychoPig.setSkinVariant(this.getSkinVariant());
				psychoPig.setSaddled(this.getSaddled());

				if (this.hadNicknameSet)
				{
					psychoPig.setNickname(this.nickname);
				}

				this.world.entityJoinedWorld(psychoPig);
				this.remove();
				if (psychoPig.passenger != null) {
					psychoPig.ejectRider();
				}
			}
		}
	}

	@Inject(
		method="interact",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/entity/player/Player;getHeldItem()Lnet/minecraft/core/item/ItemStack;",
			shift = At.Shift.AFTER
		),
		cancellable = true
	)
	public void interact(Player player, CallbackInfoReturnable<Boolean> cir)
	{
		ItemStack heldItem = player.getHeldItem();
		if (getSaddled() && !getSwitchProtectiveCharm()
			&& heldItem != null && (heldItem.itemID == ModItems.SANITY_CHARM.id))
		{
			heldItem.stackSize -= 1;

			if (heldItem.stackSize <= 0) {
				player.setHeldItem(null);
			}

			setSwitchProtectiveCharm(true);

			cir.setReturnValue(true);
		}

		if (player.isSneaking() && getSwitchProtectiveCharm()) {
			ItemStack toInsert = new ItemStack(ModItems.SANITY_CHARM);
			player.inventory.insertItem(toInsert, true);
			if (toInsert.stackSize > 0) {
				this.dropItem(toInsert, 0.0F);
			}
			setSwitchProtectiveCharm(false);
			cir.setReturnValue(true);
		}
	}


	@Inject(
		method="defineSynchedData",
		at = @At("TAIL")
	)
	protected void defineSyncedData(CallbackInfo ci) {
		this.entityData.define(18, (byte)0, Byte.class);
	}

	@Inject(
		method="addAdditionalSaveData",
		at = @At("TAIL")
	)
	public void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		tag.putBoolean("SwitchProtected", this.getSwitchProtectiveCharm());
	}

	@Inject(
		method="readAdditionalSaveData",
		at = @At("TAIL")
	)
	public void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		this.setSwitchProtectiveCharm(tag.getBoolean("SwitchProtected"));
	}
}
