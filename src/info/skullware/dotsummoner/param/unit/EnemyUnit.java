package info.skullware.dotsummoner.param.unit;

public class EnemyUnit extends Unit {

	public EnemyUnit(String unitId, String imagePath) {
		super(unitId, imagePath);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EnemyUnit(Unit unit) {
		super(unit.getUnitId(), unit.getImagePath());
	}

}
