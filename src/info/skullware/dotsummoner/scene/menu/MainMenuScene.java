package info.skullware.dotsummoner.scene.menu;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.common.util.ResourceUtil;
import info.skullware.dotsummoner.scene.battle.LoadingScene;

import org.andengine.audio.music.Music;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.ease.EaseBackInOut;

import android.view.KeyEvent;

/*
 * 汎用アドベンチャーシーン
 */
public class MainMenuScene extends KeyListenScene implements OnClickListener {

	private static final int BUTTON_QUEST = 1001;
	private static final int BUTTON_PARTY = 1002;
	private static final int BUTTON_BARCODE = 1003;
	private static final int BUTTON_OPTION = 1004;

	private Music bgm;

	public MainMenuScene(MultiSceneActivity baseActivity) {
		super(baseActivity);
		init();
	}

	@Override
	public void init() {
		// 背景を追加
		attachChild(getBaseActivity().getResourceUtil().getSprite("menu/background.png"));

		// girl
		Sprite girl = getBaseActivity().getResourceUtil().getSprite("menu/f352.png");
		this.placeToCenterX(girl, 60);
		girl.registerEntityModifier(new FadeInModifier(5.0f));
		this.attachChild(girl);

		Sprite header = getBaseActivity().getResourceUtil().getSprite("menu/header.png");
		header.setPosition(5, 5);
		this.attachChild(header);
		
		// ボタンを追加
		setButtons();

		// メッセージエリア
		Rectangle area = new Rectangle(10, 300, 780, 160, getBaseActivity()
				.getVertexBufferObjectManager());
		area.setColor(Color.BLACK);
		area.registerEntityModifier(new AlphaModifier(3, 0, 0.5f));
		this.attachChild(area);

		// Font
		String str = "テキスト表示てすと\n2段めのテキスト\nLinux Mint 17 xfc\n4段めのテキスト、入るかな？";
		Text text = PixelMplus.getTextRegular12(getBaseActivity(), str, 20, 310);
		attachChild(text);
	}

	private void setButtons() {
		ButtonSprite btnQuest = getBaseActivity().getResourceUtil().getButtonSprite(
				"menu/quest.png", "menu/quest_s.png");
		btnQuest.setPosition(25, 70);
		btnQuest.setTag(BUTTON_QUEST);
		btnQuest.setOnClickListener(this);
		this.attachChild(btnQuest);
		this.registerTouchArea(btnQuest);
		setSlideButton(btnQuest);

		ButtonSprite btnParty = getBaseActivity().getResourceUtil().getButtonSprite(
				"menu/party.png", "menu/party_s.png");
		btnParty.setPosition(45, 130);
		btnParty.setTag(BUTTON_PARTY);
		btnParty.setOnClickListener(this);
		this.attachChild(btnParty);
		this.registerTouchArea(btnParty);
		setSlideButton(btnParty);

		ButtonSprite btnBarcode = getBaseActivity().getResourceUtil().getButtonSprite(
				"menu/barcode.png", "menu/barcode_s.png");
		btnBarcode.setPosition(65, 190);
		btnBarcode.setTag(BUTTON_BARCODE);
		btnBarcode.setOnClickListener(this);
		this.attachChild(btnBarcode);
		this.registerTouchArea(btnBarcode);
		setSlideButton(btnBarcode);

		ButtonSprite btnOption = getBaseActivity().getResourceUtil().getButtonSprite(
				"menu/option.png", "menu/option_s.png");
		btnOption.setPosition(85, 250);
		btnOption.setTag(BUTTON_OPTION);
		btnOption.setOnClickListener(this);
		this.attachChild(btnOption);
		this.registerTouchArea(btnOption);
		setSlideButton(btnOption);
	}

	private void setSlideButton(ButtonSprite button) {
		float btnX = button.getX();
		button.setPosition(button.getX() + getBaseActivity().getEngine().getCamera().getWidth(),
				button.getY());
		button.registerEntityModifier(new SequenceEntityModifier(new DelayModifier(1.4f),
				new MoveModifier(1.0f, button.getX(), btnX, button.getY(), button.getY(),
						EaseBackInOut.getInstance())));
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		bgm.stop();
		switch (pButtonSprite.getTag()) {
		case BUTTON_QUEST:
			// リソースの解放
			ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
			KeyListenScene scene = new LoadingScene(getBaseActivity(), 10);
			// MainSceneへ移動
			getBaseActivity().getEngine().setScene(scene);
			// 遷移管理用配列に追加
			getBaseActivity().appendScene(scene);
			break;
		}
	}

	@Override
	public void prepareSoundAndMusic() {
		bgm = getBaseActivity().getResourceUtil().getMusic("menu/menu.wav");
		bgm.setLooping(true);
		bgm.play();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		return false;
	}

}
