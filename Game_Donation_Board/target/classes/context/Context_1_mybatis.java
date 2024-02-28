package context;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.SQLException;

<<<<<<<< HEAD:Game_Donation_Board/target/classes/context/Context_1_mybatis.java


========
>>>>>>>> test:Game_Donation_Board/src/main/resources/context/Context_1_mybatis.java
@Configuration
public class Context_1_mybatis {

	@Bean
<<<<<<<< HEAD:Game_Donation_Board/target/classes/context/Context_1_mybatis.java
	public DataSource ds()  throws SQLException{
		 OracleDataSource ds = new OracleDataSource();
	        ds.setURL("jdbc:oracle:thin:@project522_high?TNS_ADMIN=C:/DEV/backend_project/Wallet_Project522/");
	        ds.setUser("ADMIN");
	        ds.setPassword("Multicampus522");
//		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		ds.setUsername("hr");
//		ds.setPassword("hr");
========
	public DataSource ds() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL("jdbc:oracle:thin:@project522_high?TNS_ADMIN=C:/DEV/backend_project/Wallet_Project522/");
		ds.setUser("ADMIN");
		ds.setPassword("Multicampus522");
>>>>>>>> test:Game_Donation_Board/src/main/resources/context/Context_1_mybatis.java
		return ds;
	}

	@Bean
	public SqlSessionFactory factoryBean(DataSource ds) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds);

		factoryBean.setConfigLocation(new ClassPathResource("config/mybatis/mybatis-config.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factoryBean) {
		return new SqlSessionTemplate(factoryBean);
	}
}
