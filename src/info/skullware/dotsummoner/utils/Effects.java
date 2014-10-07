package info.skullware.dotsummoner.utils;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

public class Effects {

	public static void Flash(Entity entity, float pDuration) {
		entity.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
				new AlphaModifier(pDuration, 0, 0.8f), new AlphaModifier(pDuration, 0.8f, 0))));
	}
}
