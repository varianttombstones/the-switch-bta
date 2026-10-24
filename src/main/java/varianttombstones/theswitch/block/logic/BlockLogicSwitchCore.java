package varianttombstones.theswitch.block.logic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.theswitch.block.ModBlocks;
import varianttombstones.theswitch.item.ModItems;

import java.util.Random;

public class BlockLogicSwitchCore extends BlockLogic {
	public BlockLogicSwitchCore(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, @NotNull TilePosc tilePos, int data, @Nullable TileEntity tileEntity) {
		ItemStack[] stack = new ItemStack[1];
		if (dropCause == EnumDropCause.SILK_TOUCH)
		{
			stack[0] = new ItemStack(ModBlocks.SWITCH_CORE);
		} else {
			stack[0] = new ItemStack(ModItems.BLOOD_PEARL);
		}

		return stack;
	}
}
