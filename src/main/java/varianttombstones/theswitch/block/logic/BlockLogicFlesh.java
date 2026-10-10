package varianttombstones.theswitch.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BlockLogicFlesh extends BlockLogic {
	public BlockLogicFlesh(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
//		if (rand.nextInt(20) > 2)
//		{
//			return;
//		}

		Side side = Side.sides[rand.nextInt(6)];
		if (side != Side.BOTTOM || world.isBlockOpaqueCube(tilePos.add(side.direction(), new TilePos()))) {
			return;
		}

		float off1 = rand.nextFloat() * 0.9F + 0.05F;
		float off2 = rand.nextFloat() * 0.9F + 0.05F;
		float lOff = 0.01F;
		world.spawnParticle("dripBlood", (double) ((float) tilePos.x() + off1), (double) ((float) tilePos.y() - lOff), (double) ((float) tilePos.z() + off2), (double) 0.0F, (double) 0.0F, (double) 0.0F, 0, false);
	}
}
