package info.skullware.dotsummoner.common.util;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

public class Effects {

	public static void flash(Entity entity, float pDuration) {
		entity.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
				new AlphaModifier(pDuration, 0, 0.8f), new AlphaModifier(pDuration, 0.8f, 0))));
	}

	public static IEntityModifier getFlashModifier(float pDuration) {
		return new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(pDuration, 0,
				0.8f), new AlphaModifier(pDuration, 0.8f, 0)));
	}
}
