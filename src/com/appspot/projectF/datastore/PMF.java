package com.appspot.projectF.datastore;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

//GAEでデータストアを使うときはとりあえず定義
// データストアを利用するためのヘルパクラス
// 永続化マネージャを管理
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private PMF() {}
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
