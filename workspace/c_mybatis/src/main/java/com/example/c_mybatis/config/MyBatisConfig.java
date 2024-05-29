package com.example.c_mybatis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 스프링에서 설정할 클래스를 의미
// 메소드들에는 @Bean을 붙여서 등록을 해줘야 한다.
@Configuration

//final 필드인 변수의 생성자를 자동으로 생성해준다.
@RequiredArgsConstructor
public class MyBatisConfig {

    // xml 설정 파일 등등 리소스 파일들을 읽어오기 위해 주입 받음
    private final ApplicationContext applicationContext;

    // application.properties에서 spring.datasource.hikari로 시작하는 설정을 읽어와서 바인딩
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    // DataSource는 인터페이스
    // 데이터베이스를 연결하기 위한 설정들을 전달받아 DataSource를 반환!
    // DataSource는 데이터베이스 커넥션 풀을 관리한다.
    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        // SqlSessionFactoryBean은 SqlSessionFactory를 설정하고 생성하는 역할을 한다.
        // SqlSessionFactory는 실제 sql세션을 관리하고 실행하는 역할
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 데이터 소스를 저장
        sqlSessionFactoryBean.setDataSource(dataSource());

        // MyBatis 매퍼 파일의 위치를 설정
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));

        // MyBatis 설정 파일 위치를 설정
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/config.xml"));

        // 객체 생성
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        // 언더스코어와 카멜을 자동 매핑
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory;
    }
}
