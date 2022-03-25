package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-03-25 18:03
 */
public class AbstractSql {
    public String database;
    public String[] tableNames;
    public int tableNum = 100;

    /**
     * 清空数据
     */
    protected void cleanUpData() {
        System.out.println(String.format("USE `%s`;", this.database));
        for (String tableName : this.tableNames) {
            for (int i = 0; i < this.tableNum; i++) {
                String sql = String.format("TRUNCATE TABLE `%s_%s`;", tableName, i);
                System.out.println(sql);
            }
        }
    }
}
