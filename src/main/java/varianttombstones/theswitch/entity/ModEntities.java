package varianttombstones.theswitch.entity;

import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.factories.EntityFactory;
import net.minecraft.core.util.collection.NamespaceID;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.MobPsychoticCow;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;

public class ModEntities {
	public static EntityFactory<MobPsychoticPig> FACTORY_PSYCHOTIC_PIG;
	public static EntityFactory<MobPsychoticCow> FACTORY_PSYCHOTIC_COW;

	public static void InitEnemies(EntityDispatcher dispatcher)
	{
		FACTORY_PSYCHOTIC_PIG = EntityDispatcher.getInstance().addMapping(MobPsychoticPig.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticpig"), MobPsychoticPig::new);
		FACTORY_PSYCHOTIC_COW = EntityDispatcher.getInstance().addMapping(MobPsychoticCow.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticcow"), MobPsychoticCow::new);
	}
}
