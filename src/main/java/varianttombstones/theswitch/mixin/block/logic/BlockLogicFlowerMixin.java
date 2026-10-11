package varianttombstones.theswitch.mixin.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.Random;

@Mixin(BlockLogicFlower.class)
public class BlockLogicFlowerMixin extends BlockLogic {
	public BlockLogicFlowerMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Inject(
		method="updateTick",
		at=@At("TAIL")
	)
	private void checkSwitch(World world, TilePosc tilePos, Random rand, boolean isRandomTick, CallbackInfo ci)
	{
		Block<?> blockBelow = world.getBlockType(new TilePos(tilePos.x(), tilePos.y() - 1, tilePos.z()));
		if (this.block.id() != ModBlocks.DEAD_FLOWERS.id() &&
			(blockBelow == ModBlocks.FLESH || blockBelow == ModBlocks.LIVING_DIRT || blockBelow == ModBlocks.LIVING_GRASS))
		{
			world.setBlockTypeNotify(new TilePos(tilePos.x(), tilePos.y(), tilePos.z()), ModBlocks.DEAD_FLOWERS);
		}
	}
}
