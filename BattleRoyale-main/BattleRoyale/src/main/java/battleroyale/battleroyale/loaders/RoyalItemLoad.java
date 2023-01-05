package battleroyale.battleroyale.loaders;

import battleroyale.battleroyale.db.SqlManager;

public class RoyalItemLoad {
    public RoyalItemLoad() {

    }
    public static void load(SqlManager sql) {
        StringBuilder tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("royal_items");
        tableConstructor.append(" (");
        tableConstructor.append("item_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,");
        tableConstructor.append("name varchar(64) NOT NULL,");
        tableConstructor.append("description text NOT NULL,");
        tableConstructor.append("quality varchar(16) DEFAULT 'COMMON',");
        tableConstructor.append("original_id int(4) DEFAULT '7',");
        tableConstructor.append("original_data int(4) DEFAULT '0',");
        tableConstructor.append("min_damage int(9) DEFAULT '0',");
        tableConstructor.append("max_damage int(9) DEFAULT '0',");
        tableConstructor.append("min_mdamage int(9) DEFAULT '0',");
        tableConstructor.append("max_mdamage int(9) DEFAULT '0',");
        tableConstructor.append("critical_chance int(3) DEFAULT '0',");
        tableConstructor.append("critical_strength int(4) DEFAULT '0',");
        tableConstructor.append("armor int(9) DEFAULT '0',");
        tableConstructor.append("marmor int(9) DEFAULT '0',");
        tableConstructor.append("resistance int(9) DEFAULT '0',");
        tableConstructor.append("health int(9) DEFAULT '0',");
        tableConstructor.append("regeneration int(9) DEFAULT '0',");
        tableConstructor.append("PRIMARY KEY (item_id), UNIQUE(item_id), INDEX(item_id)");
        tableConstructor.append(")");
        sql.createTable(String.valueOf(tableConstructor));
        tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("chest_common");
        tableConstructor.append(" (");
        tableConstructor.append("chest_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,");
        tableConstructor.append("world text NOT NULL,");
        tableConstructor.append("x int(9) DEFAULT '0',");
        tableConstructor.append("y int(9) DEFAULT '0',");
        tableConstructor.append("z int(9) DEFAULT '0',");
        tableConstructor.append("PRIMARY KEY (chest_id), UNIQUE(chest_id), INDEX(chest_id)");
        tableConstructor.append(")");
        sql.createTable(String.valueOf(tableConstructor));
        tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("chest_rare");
        tableConstructor.append(" (");
        tableConstructor.append("chest_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,");
        tableConstructor.append("world text NOT NULL,");
        tableConstructor.append("x int(9) DEFAULT '0',");
        tableConstructor.append("y int(9) DEFAULT '0',");
        tableConstructor.append("z int(9) DEFAULT '0',");
        tableConstructor.append("PRIMARY KEY (chest_id), UNIQUE(chest_id), INDEX(chest_id)");
        tableConstructor.append(")");
        sql.createTable(String.valueOf(tableConstructor));
        tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("chest_epic");
        tableConstructor.append(" (");
        tableConstructor.append("chest_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,");
        tableConstructor.append("world text NOT NULL,");
        tableConstructor.append("x int(9) DEFAULT '0',");
        tableConstructor.append("y int(9) DEFAULT '0',");
        tableConstructor.append("z int(9) DEFAULT '0',");
        tableConstructor.append("PRIMARY KEY (chest_id), UNIQUE(chest_id), INDEX(chest_id)");
        tableConstructor.append(")");
        sql.createTable(String.valueOf(tableConstructor));
        tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("chest_legendary");
        tableConstructor.append(" (");
        tableConstructor.append("chest_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,");
        tableConstructor.append("world text NOT NULL,");
        tableConstructor.append("x int(9) DEFAULT '0',");
        tableConstructor.append("y int(9) DEFAULT '0',");
        tableConstructor.append("z int(9) DEFAULT '0',");
        tableConstructor.append("PRIMARY KEY (chest_id), UNIQUE(chest_id), INDEX(chest_id)");
        tableConstructor.append(")");
        sql.createTable(String.valueOf(tableConstructor));
    }
}
