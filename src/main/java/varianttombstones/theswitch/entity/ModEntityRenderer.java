package varianttombstones.theswitch.entity;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.MobRendererDeer;
import net.minecraft.client.render.entity.MobRendererHuman;
import net.minecraft.client.render.entity.MobRendererQuadruped;
import net.minecraft.client.render.entity.MobRendererSheep;
import net.minecraft.core.entity.animal.MobDeer;
import net.minecraft.core.entity.animal.MobSheep;
import varianttombstones.theswitch.entity.monster.*;
import varianttombstones.theswitch.entity.renderers.MobRendererPsychoticDeer;
import varianttombstones.theswitch.entity.renderers.MobRendererPsychoticPig;
import varianttombstones.theswitch.entity.renderers.MobRendererPsychoticSheep;
import varianttombstones.theswitch.entity.renderers.MobRendererSpirit;

public class ModEntityRenderer {
	public static void RegisterRenderers(EntityRendererDispatcher dispatcher)
	{
		dispatcher.assignRenderer(MobPsychoticPig.class, new MobRendererPsychoticPig(0.7F));
		dispatcher.assignRenderer(MobPsychoticCow.class, new MobRendererQuadruped(0.7F));
		dispatcher.assignRenderer(MobPsychoticSheep.class, new MobRendererPsychoticSheep(0.7F));
		dispatcher.assignRenderer(MobPsychoticDeer.class, new MobRendererPsychoticDeer(0.7F));
		dispatcher.assignRenderer(MobSpirit.class, new MobRendererSpirit(0F));
	}
}
