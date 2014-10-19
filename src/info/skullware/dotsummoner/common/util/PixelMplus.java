package info.skullware.dotsummoner.common.util;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.StrokeFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;

public class PixelMplus {

	public static enum FontWeight {
		REGULAR10, BOLD10, REGULAR12, BOLD12
	}
	/*
	 * ストロークフォント
	 */
	public static Text getStrokeText(BaseGameActivity activity, String text, float pX, float pY,
			int size, FontWeight weight, int fontColor, int strokeColor) {
		ITexture texture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR);
		String fontWeight = getFontWeight(weight);

		StrokeFont font = FontFactory.createStrokeFromAsset(activity.getFontManager(), texture,
				activity.getAssets(), fontWeight, 28, true, fontColor, 2, strokeColor);
		font.load();
		return new Text(pX, pY, font, text, activity.getVertexBufferObjectManager());
	}

	public static Text getStrokeTextRegular10(MultiSceneActivity activity, String text, float pX,
			float pY) {
		return getStrokeText(activity, text, pX, pY, 28, FontWeight.REGULAR10, Color.WHITE,
				Color.BLACK);
	}
	public static Text getStrokeTextBold10(MultiSceneActivity activity, String text, float pX,
			float pY) {
		return getStrokeText(activity, text, pX, pY, 28, FontWeight.BOLD10, Color.WHITE,
				Color.BLACK);
	}
	public static Text getStrokeTextRegular12(MultiSceneActivity activity, String text, float pX,
			float pY) {
		return getStrokeText(activity, text, pX, pY, 28, FontWeight.REGULAR12, Color.BLACK,
				Color.DKGRAY);
	}
	
	/*
	 * ノーマルフォント
	 */
	public static Text getTextPmp(MultiSceneActivity activity, String text, float pX, float pY,
			int size, FontWeight weight, int color) {
		ITexture texture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR);
		String fontWeight = getFontWeight(weight);

		Font font = FontFactory.createFromAsset(activity.getFontManager(), texture,
				activity.getAssets(), fontWeight, size, true, color);
		font.load();
		return new Text(pX, pY, font, text, activity.getVertexBufferObjectManager());
	}

	public static Text getTextRegular10(MultiSceneActivity activity, String text, float pX, float pY) {
		return getTextPmp(activity, text, pX, pY, 28, FontWeight.REGULAR10, Color.WHITE);
	};

	public static Text getTextRegular12(MultiSceneActivity activity, String text, float pX, float pY) {
		return getTextPmp(activity, text, pX, pY, 28, FontWeight.REGULAR12, Color.WHITE);
	}

	public static void setDefaultColor(Text text) {
		text.setColor(95f, 79f, 66f);
	}

	private static String getFontWeight(FontWeight fontWeight) {
		switch (fontWeight) {
		case REGULAR10:
			return PMP_10_REGULAR;
		case REGULAR12:
			return PMP_12_REGULAR;
		case BOLD10:
			return PMP_10_BOLD;
		case BOLD12:
			return PMP_12_BOLD;
		default:
			return null;
		}
	}

	private static String PMP_10_REGULAR = "font/PixelMplus10-Regular.ttf";

	private static String PMP_10_BOLD = "font/PixelMplus10-Bold.ttf";

	private static String PMP_12_REGULAR = "font/PixelMplus12-Regular.ttf";

	private static String PMP_12_BOLD = "font/PixelMplus12-Bold.ttf";
}
