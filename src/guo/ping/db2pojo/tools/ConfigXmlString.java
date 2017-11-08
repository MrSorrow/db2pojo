package guo.ping.db2pojo.tools;

import java.net.URL;

public class ConfigXmlString {

    public static final String CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE generatorConfiguration PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n" +
            "\n" +
            "<generatorConfiguration>\n" +
            "  <context id=\"testTables\" targetRuntime=\"MyBatis3\">\n" +
            "    <commentGenerator>\n" +
            "      <!-- 是否去除自动生成的注释 true：是 ： false:否 -->\n" +
            "      <property name=\"suppressAllComments\" value=\"true\"/>\n" +
            "    </commentGenerator>\n" +
            "    <!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->\n" +
            "    <jdbcConnection driverClass=\"com.mysql.jdbc.Driver\" connectionURL=\"jdbc:mysql://localhost:3306/test\" userId=\"root\" password=\"1234\"/>\n" +
            "    <!-- <jdbcConnection driverClass=\"oracle.jdbc.OracleDriver\" connectionURL=\"jdbc:oracle:thin:@127.0.0.1:1521:yycg\" \n" +
            "\t\t\tuserId=\"yycg\" password=\"yycg\"> </jdbcConnection> -->\n" +
            "    <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL \n" +
            "\t\t\t和 NUMERIC 类型解析为java.math.BigDecimal -->\n" +
            "    <javaTypeResolver>\n" +
            "      <property name=\"forceBigDecimals\" value=\"false\"/>\n" +
            "    </javaTypeResolver>\n" +
            "    <!-- targetProject:生成PO类的位置 -->\n" +
            "    <javaModelGenerator targetPackage=\"hhh.model\" targetProject=\"/Users/guoping/Desktop/test\">\n" +
            "      <!-- enableSubPackages:是否让schema作为包的后缀 -->\n" +
            "      <property name=\"enableSubPackages\" value=\"false\"/>\n" +
            "      <!-- 从数据库返回的值被清理前后的空格 -->\n" +
            "      <property name=\"trimStrings\" value=\"true\"/>\n" +
            "    </javaModelGenerator>\n" +
            "    <!-- targetProject:mapper映射文件生成的位置 -->\n" +
            "    <sqlMapGenerator targetPackage=\"hhh.mapper\" targetProject=\"/Users/guoping/Desktop/test\">\n" +
            "      <!-- enableSubPackages:是否让schema作为包的后缀 -->\n" +
            "      <property name=\"enableSubPackages\" value=\"false\"/>\n" +
            "    </sqlMapGenerator>\n" +
            "    <!-- targetPackage：mapper接口生成的位置 -->\n" +
            "    <javaClientGenerator type=\"XMLMAPPER\" targetPackage=\"hhh.mapper\" targetProject=\"/Users/guoping/Desktop/test\">\n" +
            "      <!-- enableSubPackages:是否让schema作为包的后缀 -->\n" +
            "      <property name=\"enableSubPackages\" value=\"false\"/>\n" +
            "    </javaClientGenerator>\n" +
            "    <!-- 指定数据库表 -->\n" +
            "    <table tableName=\"tb_card\"/>\n" +
            "  </context>\n" +
            "</generatorConfiguration>\n";

}
