package info.skullware.dotsummoner.scene.menu;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.scene.KeyListenScene;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.common.util.ResourceUtil;

import org.andengine.audio.music.Music;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import android.view.KeyEvent;

/*
 * 汎用アドベンチャーシーン
 */
public class MainMenuScene extends KeyListenScene implements OnClickListener {

	private static final int BUTTON_START = 1;
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
		this.placeToCenterX(girl, 50);
		girl.registerEntityModifier(new FadeInModifier(5.0f));
//		this.attachChild(girl);

		// ボタンを追加
		MainMenuButton.setMainMenu(getBaseActivity(), this, this);
		
		// マップ名
		MainMenuButton.setMapName(getBaseActivity(), this, this);		

		// TextArea
		Rectangle area = new Rectangle(10, 300, 780, 160, getBaseActivity().getVertexBufferObjectManager());
		area.setColor(Color.BLACK);
		area.registerEntityModifier(new AlphaModifier(3, 0, 0.5f));
		this.attachChild(area);

		// Font
		String str = "テキスト表示てすと\n2段めのテキスト\nLinux Mint 17 xfc\n4段めのテキスト、入るかな？";
		Text text = PixelMplus.getTextRegular12(getBaseActivity(), str, 20, 310);
		attachChild(text);
	}

	@Override
	public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		switch (pButtonSprite.getTag()) {
		case BUTTON_START:
			bgm.stop();
			// リソースの解放
			ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
			KeyListenScene scene = new MainMenuScene(getBaseActivity());
			// MainSceneへ移動
			getBaseActivity().getEngine().setScene(scene);
			// 遷移管理用配列に追加
			getBaseActivity().appendScene(scene);
			break;
		}
		
		// メインメニュー
		MainMenuButton.onClick(pButtonSprite.getTag(), getBaseActivity());
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
