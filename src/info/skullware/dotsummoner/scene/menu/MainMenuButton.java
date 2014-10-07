package info.skullware.dotsummoner.scene.menu;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.common.util.PixelMplus.FontWeight;
import info.skullware.dotsummoner.common.util.ResourceUtil;
import info.skullware.dotsummoner.scene.battle.BattleScene;

import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.modifier.ease.EaseBackInOut;

import android.graphics.Color;

public class MainMenuButton {

	private static final int BUTTON_STORY = 1001;
	private static final int BUTTON_PARTY = 1002;
	private static final int BUTTON_BARCODE = 1003;
	private static final int BUTTON_OPTION = 1004;

	public static void setMainMenu(MultiSceneActivity activity, KeyListenScene scene,
			ButtonSprite.OnClickListener listener) {

		Sprite player = activity.getResourceUtil().getSprite("button/player.png");
		player.setPosition(480, 5);
		scene.attachChild(player);

		ButtonSprite btnStory = activity.getResourceUtil().getButtonSprite("button/story.png", "button/story_s.png");
		btnStory.setPosition(650, 30);
		btnStory.setTag(BUTTON_STORY);
		btnStory.setOnClickListener(listener);
		scene.attachChild(btnStory);
		scene.registerTouchArea(btnStory);
		setSlideButton(activity, btnStory);

		ButtonSprite btnParty = activity.getResourceUtil().getButtonSprite("button/party.png", "button/party.png");
		btnParty.setPosition(650, btnStory.getY() + btnStory.getHeight());
		btnParty.setTag(BUTTON_PARTY);
		btnParty.setOnClickListener(listener);
		scene.attachChild(btnParty);
		scene.registerTouchArea(btnParty);
		setSlideButton(activity, btnParty);

		ButtonSprite btnBarcode = activity.getResourceUtil().getButtonSprite("button/barcode.png",
				"button/barcode_s.png");
		btnBarcode.setPosition(650, btnParty.getY() + btnParty.getHeight());
		btnBarcode.setTag(BUTTON_BARCODE);
		btnBarcode.setOnClickListener(listener);
		scene.attachChild(btnBarcode);
		scene.registerTouchArea(btnBarcode);
		setSlideButton(activity, btnBarcode);

		ButtonSprite btnOption = activity.getResourceUtil().getButtonSprite("button/option.png", "button/option.png");
		btnOption.setPosition(650, btnBarcode.getY() + btnBarcode.getHeight());
		btnOption.setTag(BUTTON_OPTION);
		btnOption.setOnClickListener(listener);
		scene.attachChild(btnOption);
		scene.registerTouchArea(btnOption);
		setSlideButton(activity, btnOption);
	}

	public static void onClick(int tag, MultiSceneActivity activity) {
		switch (tag) {
		case BUTTON_STORY:
			// リソースの解放
			ResourceUtil.getInstance(activity).resetAllTexture();
			KeyListenScene scene = new BattleScene(activity);
			// MainSceneへ移動
			activity.getEngine().setScene(scene);
			// 遷移管理用配列に追加
			activity.appendScene(scene);
			break;
		}
	}

	public static void setMapName(MultiSceneActivity activity, KeyListenScene scene,
			ButtonSprite.OnClickListener listener) {

		Sprite mapname = activity.getResourceUtil().getSprite("button/mapname.png");
		mapname.setPosition(5, 5);
		scene.attachChild(mapname);

		Text text = PixelMplus.getTextPmp(activity, "竜の天国亭", 50, 15, 36, FontWeight.BOLD12, Color.rgb(85, 72, 62));
		scene.attachChild(text);

		Sprite icon = activity.getResourceUtil().getSprite("icon/sakaba.png");
		icon.setPosition(10, 15);
		scene.attachChild(icon);
	}

	private static void setSlideButton(MultiSceneActivity activity, ButtonSprite button) {
		float btnX = button.getX();
		button.setPosition(button.getX() + activity.getEngine().getCamera().getWidth(), button.getY());
		button.registerEntityModifier(new SequenceEntityModifier(new DelayModifier(1.4f), new MoveModifier(1.0f, button
				.getX(), btnX, button.getY(), button.getY(), EaseBackInOut.getInstance())));
	}
}
