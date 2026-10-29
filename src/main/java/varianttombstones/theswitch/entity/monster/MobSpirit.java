package varianttombstones.theswitch.entity.monster;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.player.gamemode.Gamemodes;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.item.ItemTrickOrTreatBucket;
import varianttombstones.theswitch.item.ModItems;

import java.util.Random;

public class MobSpirit extends MobMonster {
	private boolean hostile; // Needs to be updated so this saves.
	public MobSpirit(@NotNull World world) {
		super(world);
		this.setTextureIdentifier("theswitch", "spirit");
		this.moveSpeed = 0.3F;
		this.attackStrength = 3;
		this.mobDrops.add(new WeightedRandomLootObject(new ItemStack(ModItems.ECTOPLASM), 0, 1));
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
			Player entityplayer = world.getClosestPlayerToEntity(this, 16.0F);
			return entityplayer != null && canEntityBeSeen(entityplayer) && entityplayer.getGamemode().hasHostileMobs() ? entityplayer : null;
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
				if (!world.isClientSide && !((ItemTrickOrTreatBucket) itemstack.getItem()).filled) {
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
						world.playSoundEffectAtTile(player, SoundCategory.ENTITY_SOUNDS, new TilePos(x, y, z),
							"theswitch:mob.spirit.vanish", 1F, 1F);

						for (int i = 0; i < 10; i++)
						{
							double posX = x + random.nextDouble(-.25, .26);
							double posZ = z + random.nextDouble(-.25, .26);
							world.spawnParticle("smoke", posX, y, posZ, 0, 0.5F, 0, 0, false);
						}

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
							if (((Player) attacker).getCurrentEquippedItem() == null)
							{
								return false;
							}

							int attackerWeaponID = ((Player) attacker).getCurrentEquippedItem().itemID;

							if (attackerWeaponID != Items.TOOL_SWORD_GOLD.id
							&& attackerWeaponID != Items.TOOL_AXE_GOLD.id
							&& attackerWeaponID != Items.TOOL_SHOVEL_GOLD.id
							&& attackerWeaponID != Items.TOOL_PICKAXE_GOLD.id
							&& attackerWeaponID != Items.TOOL_HOE_GOLD.id)
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
			if (attacker != this) {
				if (attacker instanceof Player player) {
					if (player.gamemode.hasHostileMobs()) {
						hostile = true;
						this.target = attacker;
					}
				}

				return true;
			}
		}

		return false;
	}

	public String getLivingSound() {
		return "theswitch:mob.spirit.speak";
	}

	protected String getHurtSound() {
		return "theswitch:mob.spirit.death";
	}

	protected String getDeathSound() {
		return "theswitch:mob.spirit.hurt";
	}
}
