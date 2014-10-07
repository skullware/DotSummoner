package info.skullware.dotsummoner;

import info.skullware.dotsummoner.common.KeyListenScene;
import info.skullware.dotsummoner.common.MultiSceneActivity;
import info.skullware.dotsummoner.scenes.title.TitleScene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MainActivity extends MultiSceneActivity implements SensorEventListener {

	// 画面サイズ
	private int CAMERA_WIDTH = 800;
	private int CAMERA_HEIGHT = 480;

	@Override
	public EngineOptions onCreateEngineOptions() {
		// サイズを指定し描画範囲をインスタンス化
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);		
		// ゲームのエンジンを初期化。
		// 第1引数 タイトルバーを表示しないモード
		// 第2引数 画面は縦向き（幅480、高さ800）
		// 第3引数 解像度の縦横比を保ったまま最大まで拡大する
		// 第4引数 描画範囲
		EngineOptions eo = new EngineOptions(
		// タイトルバー非表示モード
				true,
				// 画面縦向き
				ScreenOrientation.LANDSCAPE_FIXED,
				// 画面（480 * 800）解像度の縦横比を保ったまま最大まで拡大
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				// 描画範囲
				camera);

		return eo;
	}

	/**
	 * Sceneサブクラスを返す.
	 */
	@Override
	protected Scene onCreateScene() {
		// InitialSceneをインスタンス化し、返す
		// 同時に、遷移用の配列にも追加
		TitleScene titleScene = new TitleScene(this);
		getSceneArray().add(titleScene);
		return titleScene;
	}

	/**
	 * ActivityのレイアウトのIDを返す.
	 */
	@Override
	protected int getLayoutID() {
		return R.layout.activity_main;
	}

	/**
	 * SceneがセットされるViewのIDを返す.
	 */
	@Override
	protected int getRenderSurfaceViewID() {
		return R.id.renderview;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自動生成されたメソッド・スタブ

	}

	// 遷移用の配列に新たなシーンを追加
	@Override
	public void appendScene(KeyListenScene scene) {
		getSceneArray().add(scene);
	}

	// 遷移用の配列から全てのシーンを削除し、トップ画面を表示する
	@Override
	public void backToInitial() {
		getSceneArray().clear();
		KeyListenScene scene = new TitleScene(this);
		getSceneArray().add(scene);
		getEngine().setScene(scene);
	}

	// 現在のシーンを切り替える。遷移用の配列の状態は変更しない
	@Override
	public void refreshRunningScene(KeyListenScene scene) {
		getSceneArray().remove(getSceneArray().size() - 1);
		getSceneArray().add(scene);
		getEngine().setScene(scene);
	}

}
