package com.example.user_pc.ezbooking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by key on 16. 6. 20..
 */
public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ezbook_info9";
    private static final int DATABASE_VERSION=2;
    static SQLiteDatabase db;
    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE customer( id TEXT,pw TEXT, phone TEXT, name TEXT, fame INTEGER, point INTEGER, email TEXT,coupon TEXT);");
        db.execSQL("CREATE TABLE restaurant( r_id TEXT, pw TEXT, phone TEXT, b_num TEXT, name TEXT, fame_limit INTEGER, Do_Si_address TEXT, Gu_address TEXT, other_address TEXT, category TEXT, email TEXT, holiday TEXT, open TEXT, close TEXT, info TEXT, coupon TEXT);");
        db.execSQL("CREATE TABLE reservation( num TEXT, customer_id TEXT, r_id TEXT, date TEXT, time TEXT, persons_num INTEGER, no_show TEXT,memo TEXT, approval TEXT);");
        db.execSQL("CREATE TABLE menu( r_id TEXT,menu_name TEXT,price INTEGER);");

        db.execSQL("INSERT INTO customer VALUES('ksy','123','01000000000','구수연',0,1000,'ksy@naver.com','0000');"); // Noshow 0~1은 fame 1등급
        db.execSQL("INSERT INTO customer VALUES('pjs','456','01001232340','박지수',2,1000,'pjs@naver.com','0000');"); //2~3은 fame 2등급
        db.execSQL("INSERT INTO customer VALUES('ksy2','789','01026440003','김소연',4,1000,'ksy70@naver.com','0000');"); //4~6은 fame 3등급
        db.execSQL("INSERT INTO customer VALUES('kjy','111','01011589443','권지용',7,1000,'kjs@naver.com','0000');"); //7~9는 fame 4등급
        db.execSQL("INSERT INTO customer VALUES('bbg','999','01023409870','박보검',10,1000,'bogum@naver.com','0000');"); //10이상은 fame 5등급

        db.execSQL("INSERT INTO restaurant VALUES('ggoggo','0123123','01012321111','010-10-10101','맛닭꼬(화곡1호점)',1,'서울특별시','강서구','화곡1동 화곡로 화곡빌딩','치킨','Gg@naver.com','없음','17:00','02:00','치킨전문점 호프','0101');");
        db.execSQL("INSERT INTO restaurant VALUES('gob mom','987123','01012329797','010-80-10101','곱창고(홍대점)',1,'서울특별시','마포구','홍익로안길 16 1층','기타','Gmom@naver.com','없음','16:30','24:00','곱창전문점','1111');");
        db.execSQL("INSERT INTO restaurant VALUES('hotdogmom','1542','01099991111','090-10-10101','불독스',2,'서울특별시','용산구','보광로 116 1층','분식','hdm@naver.com','공휴일','16:00','23:00','이태원 퓨전핫도그 식당','1001');");
        db.execSQL("INSERT INTO restaurant VALUES('pizza mom','112233','01011223344','012-34-10101','7번가피자(강남1호점)',3,'서울특별시','강남구','강남로 12 강남빌딩','피자','7stpizza@naver.com','없음','11:00','22:30','고급피자전문점','1100');");
        db.execSQL("INSERT INTO restaurant VALUES('gbmom','12345','01012345678','010-12-34567',' 원조할매국밥(홍대점)',1,'서울특별시','마포구','마포로 153 홍익빌딩','한식','hong@naver.com','없음','08:00','23:00','부산 돼지국밥 전문점','1011');");

        db.execSQL("INSERT INTO menu VALUES('ggoggo','후라이드치킨',7500);");
        db.execSQL("INSERT INTO menu VALUES('ggoggo','양념치킨',8500);");
        db.execSQL("INSERT INTO menu VALUES('ggoggo','닭발세트',6000);");
        db.execSQL("INSERT INTO menu VALUES('gob mom','곱창모듬구이',9900);");
        db.execSQL("INSERT INTO menu VALUES('gob mom','특양모듬구이',11900);");
        db.execSQL("INSERT INTO menu VALUES('gob mom','한우육회',12900);");
        db.execSQL("INSERT INTO menu VALUES('hotdogmom','BritishSet',35000);");
        db.execSQL("INSERT INTO menu VALUES('hotdogmom','빅핫도그(50cm)',16000);");
        db.execSQL("INSERT INTO menu VALUES('hotdogmom','김치핫도그',9900);");
        db.execSQL("INSERT INTO menu VALUES('pizza mom','슈퍼콤비네이션피자',16900);");
        db.execSQL("INSERT INTO menu VALUES('pizza mom','리치고구마치킨피자',19400);");
        db.execSQL("INSERT INTO menu VALUES('pizza mom','불고기피자',16900);");
        db.execSQL("INSERT INTO menu VALUES('gbmom','원조돼지국밥',7500);");
        db.execSQL("INSERT INTO menu VALUES('gbmom','섞어돼지국밥',8500);");
        db.execSQL("INSERT INTO menu VALUES('gbmom','순대+원조국밥set',9000);");

        db.execSQL("INSERT INTO reservation VALUES('1609102200pjs','pjs','ggoggo','20160910','22:00',6,'no','not spicy please.','yes');");
        db.execSQL("INSERT INTO reservation VALUES('1610102000ksy','key','ggoggo','20160918','20:00',10,'no','nothing','yes');");
        db.execSQL("INSERT INTO reservation VALUES('1612102100ksy','key','ggoggo','20160920','21:00',12,'no','no pork please.','no');");
        db.execSQL("INSERT INTO reservation VALUES('1609102200ksy2','ksy2','ggoggo','20160922','22:00',6,'no','2 children chairs please.','yes');");
        db.execSQL("INSERT INTO reservation VALUES('1609201800kjy','key','ggoggo','20160920','18:00',7,'no','nothing','yes');");
        db.execSQL("INSERT INTO reservation VALUES('1611131730bbg','big','ggoggo','20161113','17:30',11,'no','nothing','yes');");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }





}