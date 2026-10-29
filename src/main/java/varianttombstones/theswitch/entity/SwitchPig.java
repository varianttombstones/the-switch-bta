package varianttombstones.theswitch.entity;

import net.minecraft.core.entity.animal.MobPig;

public interface SwitchPig {
	default boolean getSwitchProtectiveCharm()
	{
		if (this instanceof MobPig pig)
		{
			return (pig.getEntityData().getByte(18) & 1) != 0;
		}

		return false;
	}

	default void setSwitchProtectiveCharm(boolean flag) {
		if (this instanceof MobPig pig)
		{
			if (flag) {
				pig.getEntityData().set(18, (byte)1);
			} else {
				pig.getEntityData().set(18, (byte)0);
			}
		}
	}
}
