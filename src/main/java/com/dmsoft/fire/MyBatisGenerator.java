package com.dmsoft.fire;

import org.mybatis.generator.api.ShellRunner;

/**
 * myBatis-generator自动生成代码
 * generator为自定义的mybatis-generator-core-1.3.2-fix版本（由官方版本1.3.2改造）
 * <dependency>
 * <groupId>org.mybatis.generator</groupId>
 * <artifactId>mybatis-generator-core</artifactId>
 * <version>1.3.2-fix</version>
 * </dependency>
 * generatorConfig.xml需要配置classPathEntry以及entity、dao、mapper的地址（当前电脑绝对路径）
 *
 * @author zhixin.huang
 * @date 2020/5/14 11:40 上午
 */
public class MyBatisGenerator {
    public static void main(String[] args) {
        // overwrite为是否覆写
        String[] arg = {"-configfile", MyBatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").getPath(), "-overwrite"};
        ShellRunner.main(arg);
    }
}
