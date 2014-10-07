package info.skullware.dotsummoner.scene.title;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.common.util.PixelMplus.FontWeight;
import info.skullware.dotsummoner.common.util.ResourceUtil;
import info.skullware.dotsummoner.scene.menu.MainMenuScene;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import android.graphics.Color;
import android.view.KeyEvent;

/*
 * タイトルシーン
 */
public class TitleScene extends KeyListenScene implements ButtonSprite.OnClickListener {

	private static final int BUTTON_START = 1;

	/**
	 * コンストラクタ
	 */
	public TitleScene(MultiSceneActivity context) {
		super(context);
		init();
	}

	@Override
	public void init() {
		// 背景ボタンを追加
		ButtonSprite btnStart = getBaseActivity().getResourceUtil().getButtonSprite("title/background.png",
				"title/background.png");
		btnStart.setTag(BUTTON_START);
		btnStart.setOnClickListener(this);
		attachChild(btnStart);
		registerTouchArea(btnStart);

		// ロゴを追加
		Sprite logo = getBaseActivity().getResourceUtil().getSprite("title/logo.png");
		logo.setPosition(160, 40);
		this.attachChild(logo);
		logo.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new MoveModifier(3, logo.getX(),
				logo.getX(), logo.getY(), logo.getY() + 10), new MoveModifier(3, logo.getX(), logo.getX(),
				logo.getY() + 10, logo.getY()))));

		// Font
		Text text = PixelMplus.getTextPmp(getBaseActivity(), "Touch Screen !", 350, 260, 48, FontWeight.BOLD12, Color.BLACK);
		text.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new AlphaModifier(2.0f, 1, 0),
				new AlphaModifier(2.0f, 0, 1))));
		attachChild(text);
	}

	/*
	 * ボタン押下
	 * 
	 * @see
	 * org.andengine.entity.sprite.ButtonSprite.OnClickListener#onClick(org.
	 * andengine.entity.sprite.ButtonSprite, float, float)
	 */
	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		switch (pButtonSprite.getTag()) {
		case BUTTON_START:
			// リソースの解放
			ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
			KeyListenScene scene = new MainMenuScene(getBaseActivity());
			// MainSceneへ移動
			getBaseActivity().getEngine().setScene(scene);
			// 遷移管理用配列に追加
			getBaseActivity().appendScene(scene);
			break;
		}

	}

	@Override
	public void prepareSoundAndMusic() {
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		return false;
	}

}
