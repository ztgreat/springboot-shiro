package com.example.demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.rules.PropertyInfo;

/**
 * 
 * 自动生成映射工具类
 * Mybatis-Plus代码生成器使用
 */
public class AutoGeneratorHelper {
 
	/**
	 * 
	 * 测试 run 执行
	 * 
	 * <p>
	 * 配置方法查看
	 * </p>
	 * 
	 */
	public static void main(String[] args ) {
		

		AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("zt");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
         gc.setMapperName("%sMapper");
         gc.setXmlName("%sMapper");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImpl");
         gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public PropertyInfo processTypeConvert(GlobalConfig globalConfig,String fieldType) {
                System.out.println("转换类型：" + fieldType);
                 if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                     return DbColumnType.DATE;
                 }
                return  super.processTypeConvert(globalConfig,fieldType);
            }
        });

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=utf-8");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
      

        pc.setParent("com.example");
        pc.setModuleName("demo");
        mpg.setPackageInfo(pc);
        

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);

       

        // 执行生成
        mpg.execute();
		
	}
	
}
