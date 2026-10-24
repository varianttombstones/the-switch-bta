package varianttombstones.theswitch.mixin.entity;

import net.minecraft.core.entity.animal.MobAnimal;
import net.minecraft.core.entity.animal.MobSheep;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticSheep;

@Mixin(MobSheep.class)
public abstract class MobSheepMixin extends MobAnimal {
	@Shadow
	public abstract DyeColor getFleeceColor();

	public MobSheepMixin(World world) {
		super(world);
	}

	@Override
	protected void updateAI()
	{
		super.updateAI();
		if (this.world.getBlockBiome(new TilePos(this.x, this.y, this.z)) == TheSwitch.THE_SWITCH_BIOME && !this.world.isClientSide) {
			MobPsychoticSheep psychoticSheep = new MobPsychoticSheep(this.world);
			psychoticSheep.moveTo(this.x, this.y, this.z, this.yRot, this.xRot);
			psychoticSheep.setFleeceColor(this.getFleeceColor());

			if (this.hadNicknameSet)
			{
				psychoticSheep.setNickname(this.nickname);
			}

			this.world.entityJoinedWorld(psychoticSheep);
			this.remove();
		}
	}
}
