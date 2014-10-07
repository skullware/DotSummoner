CREATE TABLE "player" (
    "_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "level" INTEGER NOT NULL,
    "exp" INTEGER NOT NULL,
    "next" INTEGER NOT NULL,
    "coin" INTEGER NOT NULL,
    "cost" INTEGER NOT NULL
);
CREATE TABLE "binder" (
    "_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "unit_id" TEXT NOT NULL,
    "use" INTEGER NOT NULL,
    "level" INTEGER NOT NULL,
    "exp" INTEGER NOT NULL,
    "next" INTEGER NOT NULL,
    "life" INTEGER NOT NULL,
    "attack" INTEGER NOT NULL,
    "range" INTEGER NOT NULL,
    "action_value" INTEGER NOT NULL,
    "counter_value" INTEGER NOT NULL,
    "passive_value" INTEGER NOT NULL
);
INSERT INTO "binder" VALUES(1,'U00010',1,1,0,100,18,2,1,0,0,0);
INSERT INTO "binder" VALUES(2,'U00100',1,1,10,100,30,10,1,0,0,0);
INSERT INTO "binder" VALUES(3,'U00110',1,1,20,100,30,13,1,0,0,0);
INSERT INTO "binder" VALUES(4,'U00230',1,1,30,100,7,1,1,0,0,0);
INSERT INTO "binder" VALUES(5,'U00120',1,1,40,100,40,12,2,0,0,0);
INSERT INTO "binder" VALUES(6,'U00150',1,1,50,100,48,14,1,0,0,0);
INSERT INTO "binder" VALUES(7,'U00320',1,1,60,100,20,13,1,0,0,0);
INSERT INTO "binder" VALUES(8,'U00420',1,1,70,100,25,6,3,0,0,0);
INSERT INTO "binder" VALUES(9,'U00280',1,1,80,100,64,8,1,0,5,0);
INSERT INTO "binder" VALUES(10,'U00260',1,1,90,100,46,17,1,0,0,0);
INSERT INTO "binder" VALUES(11,'U00300',1,1,0,100,34,14,2,0,0,0);
INSERT INTO "binder" VALUES(12,'U00240',1,1,0,100,44,12,1,0,0,0);
INSERT INTO "binder" VALUES(13,'U00430',1,1,0,100,90,10,1,0,0,10);
CREATE TABLE "units" (
    "_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "unit_id" TEXT NOT NULL,
    "image_path" TEXT NOT NULL,
    "rarity" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "cost" INTEGER NOT NULL,
    "life_base" INTEGER NOT NULL,
    "attack_base" INTEGER NOT NULL,
    "range_base" INTEGER NOT NULL,
    "life_rate" REAL NOT NULL,
    "attack_rate" REAL NOT NULL,
    "range_rate" REAL NOT NULL,
    "action_name" TEXT,
    "action_id" INTEGER NOT NULL,
    "action_base" INTEGER NOT NULL,
    "counter_name" TEXT,
    "counter_id" INTEGER NOT NULL,
    "counter_base" INTEGER NOT NULL,
    "passive_name" TEXT,
    "passive_id" INTEGER NOT NULL,
    "passive_base" INTEGER NOT NULL
);
INSERT INTO "units" VALUES(1,'U00010','mon_001.png',1,'スライム',1,18,2,1,1.0,0.0,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(2,'U00100','mon_010.png',1,'見習い剣士',3,30,10,1,0.7,0.5,0.0,'カウンター',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(3,'U00110','mon_011.png',1,'砦の兵士',3,30,13,1,0.9,0.5,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(4,'U00230','mon_023.png',1,'草原の妖精',1,7,1,1,0.2,0.2,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(5,'U00120','mon_012.png',1,'二叉槍の悪魔',4,40,12,2,1.3,0.9,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(6,'U00150','mon_015.png',1,'天界の尖兵',4,48,14,1,1.4,1.2,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(7,'U00320','mon_032.png',1,'骸骨戦士',3,20,13,1,1.4,0.2,0.0,'',0,0,'',0,0,'再生',0,0);
INSERT INTO "units" VALUES(8,'U00420','mon_042.png',1,'森の狩人',3,25,6,3,0.4,0.5,0.0,'',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(9,'U00280','mon_028.png',2,'森の精霊',4,64,8,1,1.3,1.1,0.0,'',0,0,'枝葉の壁',0,5,'',0,0);
INSERT INTO "units" VALUES(10,'U00260','mon_026.png',2,'炎の精霊',4,46,17,1,0.7,1.4,0.0,'',0,0,'',0,0,'炎の剣',0,0);
INSERT INTO "units" VALUES(11,'U00300','mon_030.png',2,'大気の精霊',4,34,14,2,0.8,1.1,0.0,'',0,0,'鎌鼬',0,0,'',0,0);
INSERT INTO "units" VALUES(12,'U00240','mon_024.png',2,'波の精霊',4,44,12,1,1.1,1.3,0.08,'潮の渦',0,0,'',0,0,'',0,0);
INSERT INTO "units" VALUES(13,'U00430','mon_043.png',1,'石のゴーレム',5,90,10,1,1.0,1.0,0.0,'',0,0,'',0,0,'石の体',0,10);
CREATE TABLE "lottery" (
    "_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "rarity" INTEGER NOT NULL,
    "unit_id" TEXT NOT NULL
);
CREATE TABLE "scanlog" (
    "_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "number" INTEGER NOT NULL,
    "data" TEXT NOT NULL
);
