package info.skullware.dotsummoner.common.util;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.graphics.Color;

public class PixelMplus {

	private static String PMP_10_REGULAR = "font/PixelMplus10-Regular.ttf";
	private static String PMP_10_BOLD = "font/PixelMplus10-Bold.ttf";

	private static String PMP_12_REGULAR = "font/PixelMplus12-Regular.ttf";
	private static String PMP_12_BOLD = "font/PixelMplus12-Bold.ttf";

	public static enum FontWeight {
		REGULAR10, BOLD10, REGULAR12, BOLD12
	};

	public static Text getTextRegular10(MultiSceneActivity activity, String text, float pX, float pY) {
		return getTextPmp(activity, text, pX, pY, 28, FontWeight.REGULAR10, Color.WHITE);
	}

	public static Text getTextRegular12(MultiSceneActivity activity, String text, float pX, float pY) {
		return getTextPmp(activity, text, pX, pY, 28, FontWeight.REGULAR12, Color.WHITE);
	}

	public static Text getTextPmp(MultiSceneActivity activity, String text, float pX, float pY,
			int size, FontWeight weight, int color) {
		ITexture texture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR);
		String fontWeight = null;
		switch (weight) {
		case REGULAR10:
			fontWeight = PMP_10_REGULAR;
			break;
		case REGULAR12:
			fontWeight = PMP_12_REGULAR;
			break;
		case BOLD10:
			fontWeight = PMP_10_BOLD;
			break;
		case BOLD12:
			fontWeight = PMP_12_BOLD;
			break;
		default:
			break;
		}
		Font font = FontFactory.createFromAsset(activity.getFontManager(), texture,
				activity.getAssets(), fontWeight, size, true, color);
		font.load();
		return new Text(pX, pY, font, text, activity.getVertexBufferObjectManager());
	}

	public static void setDefaultColor(Text text) {
		text.setColor(95f, 79f, 66f);
	}
}
