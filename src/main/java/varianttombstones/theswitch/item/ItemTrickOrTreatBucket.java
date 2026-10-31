package varianttombstones.theswitch.item;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import varianttombstones.theswitch.block.ModBlocks;

import java.util.List;
import java.util.Random;

public class ItemTrickOrTreatBucket extends Item {
	public boolean actionBuffer = false; // This exists so that it doesnt active immediately on fill.

	public ItemTrickOrTreatBucket(@NotNull String translationKey, @NotNull String namespaceId, int id) {
		super(translationKey, namespaceId, id);
	}

	public @Nullable ItemStack onUse(@NotNull ItemStack selfStack, @NotNull World world, @NotNull Player player) {
		if (!selfStack.getData().getBoolean("Filled"))
		{
			return selfStack;
		}

		if (!actionBuffer)
		{
			actionBuffer = true;
			return selfStack;
		}

		world.dropItem(new TilePos(player.x, player.y, player.z), getRandomItem());
		selfStack.getData().putBoolean("Filled", false);
		this.actionBuffer = false;

		return selfStack;
	}

	private ItemStack getRandomItem() {
		Random random = new Random();
		List<ItemStack> possibleItems = new java.util.ArrayList<>(List.of());
		possibleItems.add(new ItemStack(Items.FOOD_PORKCHOP_COOKED));
		possibleItems.add(new ItemStack(Items.FOOD_PORKCHOP_RAW));
		possibleItems.add(new ItemStack(Items.AMMO_ARROW_GOLD));
		possibleItems.add(new ItemStack(Items.INGOT_GOLD));
		possibleItems.add(new ItemStack(Items.SADDLE));
		possibleItems.add(new ItemStack(Items.SEEDS_WHEAT));
		possibleItems.add(new ItemStack(Items.SEEDS_PUMPKIN));
		possibleItems.add(new ItemStack(Items.FOOD_PUMPKIN_PIE));
		possibleItems.add(new ItemStack(Blocks.PUMPKIN));
		possibleItems.add(new ItemStack(Blocks.PUMPKIN_CARVED_IDLE));
		possibleItems.add(new ItemStack(Items.CHAINLINK));
		possibleItems.add(new ItemStack(Items.LANTERN_FIREFLY_BLUE));
		possibleItems.add(new ItemStack(Items.LANTERN_FIREFLY_GREEN));
		possibleItems.add(new ItemStack(Items.LANTERN_FIREFLY_ORANGE));
		possibleItems.add(new ItemStack(Items.LANTERN_FIREFLY_RED));
		possibleItems.add(new ItemStack(Items.ARMOR_WOLF_IRON));
		possibleItems.add(new ItemStack(Items.ARMOR_WOLF_GOLD));
		possibleItems.add(new ItemStack(Items.CLOTH));
		possibleItems.add(new ItemStack(Items.COAL));
		possibleItems.add(new ItemStack(Items.CLAY));
		possibleItems.add(new ItemStack(Items.RECORD_13));
		possibleItems.add(new ItemStack(Items.RECORD_BLOCKS));
		possibleItems.add(new ItemStack(Items.RECORD_CAT));
		possibleItems.add(new ItemStack(Items.RECORD_CHIRP));
		possibleItems.add(new ItemStack(Items.RECORD_DOG));
		possibleItems.add(new ItemStack(Items.RECORD_MELLOHI));
		possibleItems.add(new ItemStack(Items.TOOL_COMPASS));
		possibleItems.add(new ItemStack(Items.TOOL_CLOCK));
		possibleItems.add(new ItemStack(Items.TOOL_CALENDAR));
		possibleItems.add(new ItemStack(Blocks.DIRT));
		possibleItems.add(new ItemStack(ModItems.GHOST_ORB));
		possibleItems.add(new ItemStack(Items.ORE_RAW_GOLD));
		possibleItems.add(new ItemStack(Items.TOOL_PICKAXE_GOLD));
		possibleItems.add(new ItemStack(Items.INGOT_IRON));
		possibleItems.add(new ItemStack(Items.ORE_RAW_IRON));
		possibleItems.add(new ItemStack(Items.TOOL_FIRESTRIKER_IRON));
		possibleItems.add(new ItemStack(Items.BUCKET_IRON));
		possibleItems.add(new ItemStack(Blocks.NOTEBLOCK));
		possibleItems.add(new ItemStack(Blocks.BLOCK_OLIVINE));
		possibleItems.add(new ItemStack(Blocks.TROMMEL_IDLE));
		possibleItems.add(new ItemStack(Items.STATUE_STONE));
		possibleItems.add(new ItemStack(Blocks.WORKBENCH));
		possibleItems.add(new ItemStack(Items.SLIMEBALL));
		possibleItems.add(new ItemStack(Blocks.CHEST_PLANKS_OAK));
		possibleItems.add(new ItemStack(Items.ROPE));
		possibleItems.add(new ItemStack(Items.EGG_CHICKEN));
		possibleItems.add(new ItemStack(Items.FEATHER_CHICKEN));
		possibleItems.add(new ItemStack(Items.FLAG));
		possibleItems.add(new ItemStack(Items.AMMO_ARROW_FLAMING));
		possibleItems.add(new ItemStack(Items.AMMO_ARROW));
		possibleItems.add(new ItemStack(Items.FLINT));
		possibleItems.add(new ItemStack(Items.DUST_REDSTONE));
		possibleItems.add(new ItemStack(Items.JAR_BUTTERFLY_BLUE));
		possibleItems.add(new ItemStack(Items.JAR_BUTTERFLY_ORANGE));
		possibleItems.add(new ItemStack(Items.JAR_BUTTERFLY_PINK));
		possibleItems.add(new ItemStack(Items.JAR_BUTTERFLY_SILVER));
		possibleItems.add(new ItemStack(Items.WHEAT));
		possibleItems.add(new ItemStack(Items.QUARTZ));
		possibleItems.add(new ItemStack(Items.PAINTING));
		possibleItems.add(new ItemStack(Items.PAINTBRUSH));

		return new ItemStack(possibleItems.get(random.nextInt(possibleItems.size())));
	}
}
