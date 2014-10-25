package info.skullware.dotsummoner.scene.battle.sprite;

import info.skullware.dotsummoner.common.activity.MultiSceneActivity;
import info.skullware.dotsummoner.common.util.PixelMplus;
import info.skullware.dotsummoner.common.util.PixelMplus.FontWeight;
import info.skullware.dotsummoner.param.unit.EnemyUnit;
import info.skullware.dotsummoner.param.unit.PlayerUnit;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.R.integer;
import android.graphics.Color;

public class StatusArea extends Sprite implements OnClickListener {

	private MultiSceneActivity activity;

	public static StatusArea createInstance(MultiSceneActivity activity) {
		Sprite sprite = activity.getResourceUtil().getSprite("battle/status.png");
		StatusArea area = new StatusArea(activity, sprite.getX(), sprite.getY(),
				sprite.getTextureRegion(), sprite.getVertexBufferObject());
		return area;
	}

	private int ZINDEX = 100;
	private Text name;
	private Sprite bitmap;
	private Text level;
	private Text exp;
	private Text cost;
	private Text life;
	private Text attack;
	private Text range;
	private ButtonSprite close;

	private StatusArea(MultiSceneActivity activity, float pX, float pY,
			ITextureRegion pTextureRegion, ISpriteVertexBufferObject pSpriteVertexBufferObject) {
		super(pX, pY, pTextureRegion, pSpriteVertexBufferObject);
		this.setZIndex(ZINDEX);
		this.setVisible(false);
		this.activity = activity;

		int x = 260;
		int y = 38;

		name = PixelMplus.getTextPmp(activity, "XXXXXYYYYYZZZZZ", 10, 10, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		name.setZIndex(this.getZIndex() + 1);
		this.attachChild(name);
		level = PixelMplus.getTextPmp(activity, "99", x, y, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		level.setZIndex(this.getZIndex() + 1);
		this.attachChild(level);
		exp = PixelMplus.getTextPmp(activity, "9999/9999", x, y + 25 * 1, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		exp.setZIndex(this.getZIndex() + 1);
		this.attachChild(exp);
		cost = PixelMplus.getTextPmp(activity, "99", x, y + 25 * 2, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		cost.setZIndex(this.getZIndex() + 1);
		this.attachChild(cost);

		life = PixelMplus.getTextPmp(activity, "9999/9999", x, y + 25 * 3, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		life.setZIndex(this.getZIndex() + 1);
		this.attachChild(life);
		attack = PixelMplus.getTextPmp(activity, "999", x, y + 25 * 4, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		attack.setZIndex(this.getZIndex() + 1);
		this.attachChild(attack);
		range = PixelMplus.getTextPmp(activity, "9", x, y + 25 * 5, 24, FontWeight.BOLD10,
				Color.parseColor("#55483E"));
		range.setZIndex(this.getZIndex() + 1);
		this.attachChild(range);
		close = activity.getResourceUtil().getButtonSprite("battle/button/close.png",
				"battle/button/close_s.png");
		close.setPosition(this.getWidth() - close.getWidth() - 10, 10);
		close.setOnClickListener(this);
		close.setZIndex(this.getZIndex() + 1);
		this.attachChild(close);
	}

	public void setUnitData(EnemyUnit unit) {
		this.setPosition(400, 5);

	}

	public void setUnitData(PlayerUnit unit) {
		if (bitmap != null)
			this.detachChild(bitmap);

		this.setPosition(0, 5);
		this.name.setText(unit.getName());
		this.level.setText(String.valueOf(unit.getLevel()));
		this.exp.setText(String.valueOf(unit.getExp()) + "/" + String.valueOf(unit.getNext()));
		this.cost.setText(String.valueOf(unit.getCost()));

		this.life.setText(String.valueOf(unit.getLife()));
		this.attack.setText(String.valueOf(unit.getAttack()));
		this.range.setText(String.valueOf(unit.getRange()));

		this.bitmap = activity.getResourceUtil().getSprite("unit/" + unit.getImagePath());
		this.bitmap.setPosition(80 - bitmap.getWidth() / 2, 150 - 5 - bitmap.getHeight());
		this.bitmap.setZIndex(this.getZIndex() + 1);
		this.attachChild(bitmap);

		this.setVisible(true);
	}

	public void hidden() {
		this.setVisible(false);
		this.detachChild(bitmap);
	}

	@Override
	public void onClick(ButtonSprite arg0, float arg1, float arg2) {
		this.hidden();
	}

}
