package varianttombstones.theswitch.entity;

import net.minecraft.client.render.EntityRendererDispatcher;
import varianttombstones.theswitch.entity.monster.MobPsychoticPig;
import varianttombstones.theswitch.entity.renderers.MobRendererPsychoticPig;

public class ModEntityRenderer {
	public static void RegisterRenderers(EntityRendererDispatcher dispatcher)
	{
		dispatcher.assignRenderer(MobPsychoticPig.class, new MobRendererPsychoticPig(0.7F));
	}
}
