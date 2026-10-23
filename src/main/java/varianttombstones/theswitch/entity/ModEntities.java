package varianttombstones.theswitch.entity;

import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.factories.EntityFactory;
import net.minecraft.core.util.collection.NamespaceID;
import varianttombstones.theswitch.TheSwitch;
import varianttombstones.theswitch.entity.monster.*;

public class ModEntities {
	public static EntityFactory<MobPsychoticPig> FACTORY_PSYCHOTIC_PIG;
	public static EntityFactory<MobPsychoticCow> FACTORY_PSYCHOTIC_COW;
	public static EntityFactory<MobPsychoticSheep> FACTORY_PSYCHOTIC_SHEEP;
	public static EntityFactory<MobPsychoticDeer> FACTORY_PSYCHOTIC_DEER;
	public static EntityFactory<MobSpirit> FACTORY_SPIRIT;

	public static void InitEnemies(EntityDispatcher dispatcher)
	{
		FACTORY_PSYCHOTIC_PIG = EntityDispatcher.getInstance().addMapping(MobPsychoticPig.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticpig"), MobPsychoticPig::new);
		FACTORY_PSYCHOTIC_COW = EntityDispatcher.getInstance().addMapping(MobPsychoticCow.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticcow"), MobPsychoticCow::new);
		FACTORY_PSYCHOTIC_SHEEP = EntityDispatcher.getInstance().addMapping(MobPsychoticSheep.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticsheep"), MobPsychoticSheep::new);
		FACTORY_PSYCHOTIC_DEER = EntityDispatcher.getInstance().addMapping(MobPsychoticDeer.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "psychoticdeer"), MobPsychoticDeer::new);
		FACTORY_SPIRIT = EntityDispatcher.getInstance().addMapping(MobSpirit.class, NamespaceID.fromPool(TheSwitch.MOD_ID, "spirit"), MobSpirit::new);
	}
}
