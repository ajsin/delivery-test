package com.delivery.api.common.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "deliveryJpaEntityManagerFactory",
        transactionManagerRef = "deliveryTransactionManager",
        basePackages = {"com.delivery.api.domain.user.repository.orm", "com.delivery.api.domain.delivery.repository.orm"}
)
@MapperScan(basePackages = {"com.delivery.api.domain.user.repository.dao", "com.delivery.api.domain.delivery.repository.dao"}, sqlSessionFactoryRef = "deliverySqlSessionFactory")
public class DBConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Primary
    @Bean(name = "deliveryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource deliveryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "deliverySqlSessionFactory")
    public SqlSessionFactory deliverySqlSessionFactory(@Qualifier("deliveryDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations());
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "deliverySessionTemplate")
    public SqlSessionTemplate deliverySqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:/mapper/*.xml");
        List<Resource> resources = new ArrayList<>();
        if (!mapperLocations.isEmpty()) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    log.error("Mybatis resources Get Exception ", e);
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    @Primary
    @Bean(name = "deliveryJpaEntityManagerFactory" )
    public LocalContainerEntityManagerFactoryBean deliveryJpaEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("deliveryDataSource") DataSource dataSource ) {
        return  entityManagerFactoryBuilder.dataSource(dataSource).packages("com.delivery.api.domain.user.model.entity", "com.delivery.api.domain.delivery.model.entity").build();
    }

    @Primary
    @Bean(name = "deliveryTransactionManager")
    public JpaTransactionManager deliveryTransactionManager(
            @Qualifier("deliveryJpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean mfBean
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( mfBean.getObject() );
        return transactionManager;
    }
}