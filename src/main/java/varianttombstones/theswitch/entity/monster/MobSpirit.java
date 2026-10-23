package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.player.gamemode.Gamemodes;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.item.ItemTrickOrTreatBucket;

import java.util.Random;

public class MobSpirit extends MobMonster {
	private boolean hostile; // Needs to be updated so this saves.
	public MobSpirit(@NotNull World world) {
		super(world);
		this.setTextureIdentifier("theswitch", "spirit");
		this.moveSpeed = 0.3F;
	}

	public void onLivingUpdate() {
		if (this.world.isDaytime()) {
			float f = this.calcBrightness(1.0F);
			Weather weather = this.world.getCurrentWeather();
			if (f > 0.5F
				&& this.world.canBlockSeeTheSky(MathHelper.floor(this.x), MathHelper.floor(this.y), MathHelper.floor(this.z))
				&& this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F
				&& weather != null && !weather.isDamp() && (this.world.getCurrentWeather() != Weathers.OVERWORLD_FOG
				|| this.world.getWeatherManager().getWeatherPower() < 0.8F)
				&& this.world.getBlockBiome(new TilePos(x, y, z)) != TheSwitch.THE_SWITCH_BIOME) {
				this.remainingFireTicks = 120;
			}
		}

		if (target != null)
		{
			this.moveSpeed = 0.7F;
		} else {
			this.moveSpeed = 0.3F;
		}

		super.onLivingUpdate();
	}

	protected Entity findPlayerToAttack() {
		if (hostile)
		{
			Player entityplayer = this.world.getClosestPlayerToEntity(this, (double)16.0F);
			return entityplayer != null && this.canEntityBeSeen(entityplayer) && entityplayer.getGamemode().hasHostileMobs() ? entityplayer : null;
		} else {
			return null;
		}
	}

	public boolean interact(@NotNull Player player) {
		if (super.interact(player)) {
			return true;
		} else {
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() instanceof ItemTrickOrTreatBucket && !this.hostile) {
				if (!this.world.isClientSide) {
					Random random = new Random();
					if (random.nextBoolean())
					{
						if (player.gamemode != Gamemodes.CREATIVE)
						{
							hostile = true;
							this.target = player;
						}
					} else {
						((ItemTrickOrTreatBucket) itemstack.getItem()).filled = true;
						remove();
					}
				}
			}
		}

		return false;
	}

	public boolean hurt(Entity attacker, int i, DamageType type) {
		if (!this.world.isClientSide) {
			if (attacker != this) {
				if (type == DamageType.FIRE || type == DamageType.COMBAT)
				{
					if (type == DamageType.COMBAT)
					{
						if (attacker instanceof Player)
						{
							int attackerWeaponID = ((Player) attacker).getCurrentEquippedItem().itemID;

							if (attackerWeaponID != Items.TOOL_SWORD_GOLD.id)
							{
								return false;
							}

							if (attackerWeaponID != Items.TOOL_AXE_GOLD.id)
							{
								return false;
							}

							if (attackerWeaponID != Items.TOOL_SHOVEL_GOLD.id)
							{
								return false;
							}

							if (attackerWeaponID != Items.TOOL_PICKAXE_GOLD.id)
							{
								return false;
							}

							if (attackerWeaponID != Items.TOOL_HOE_GOLD.id)
							{
								return false;
							}
						}
					}
				}
				else
				{
					return false;
				}
			}
		}

		if (super.hurt(attacker, i, type)) {
			if (this.passenger != attacker && this.vehicle != attacker) {
				if (attacker != this) {
					if (attacker instanceof Player player) {
						if (!player.gamemode.hasHostileMobs()) {
							hostile = true;
							return true;
						}
					}

					this.target = attacker;
				}

				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
