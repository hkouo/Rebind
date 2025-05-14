package com.hkouo.rebind.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.hkouo.rebind.mapper")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // mapper 파일 위치
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/**/*.xml")
        );

        // MyBatis Configuration 수동 설정
        Configuration config = new Configuration();
        config.setMapUnderscoreToCamelCase(true);
        config.setLogImpl(Slf4jImpl.class);  // 핵심: 로그 구현체 수동 지정
        factoryBean.setConfiguration(config);

        return factoryBean.getObject();
    }
}
