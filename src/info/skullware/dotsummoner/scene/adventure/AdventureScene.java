package info.skullware.dotsummoner.scene.adventure;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.PixelMplus;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import android.view.KeyEvent;

/*
 * 汎用アドベンチャーシーン
 */
public class AdventureScene extends KeyListenScene implements OnClickListener {

	private static final int BUTTON_START = 1;

	public AdventureScene(MultiSceneActivity baseActivity) {
		super(baseActivity);
		init();
	}

	@Override
	public void init() {
		// 背景ボタンを追加
		ButtonSprite btnStart = getBaseActivity().getResourceUtil().getButtonSprite("adventure/background.png",
				"adventure/background.png");
		btnStart.setTag(BUTTON_START);
		btnStart.setOnClickListener(this);
		attachChild(btnStart);
		registerTouchArea(btnStart);

		// takako
		Sprite takako = getBaseActivity().getResourceUtil().getSprite("adventure/takako.png");
		this.placeToCenterX(takako, 50);
		this.attachChild(takako);

		// TextArea
		Rectangle area = new Rectangle(10, 300, 780, 160, getBaseActivity().getVertexBufferObjectManager());
		area.setColor(org.andengine.util.color.Color.BLACK);
		area.registerEntityModifier(new AlphaModifier(3, 0, 0.5f));
		this.attachChild(area);

		// Font
		String str = "テキスト表示てすと\n2段めのテキスト\nLinux Mint 17 xfc\n4段めのテキスト、入るかな？";		
		Text text = PixelMplus.getTextRegular12(getBaseActivity(), str, 20, 310);
		attachChild(text);
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void prepareSoundAndMusic() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		return false;
	}

}
