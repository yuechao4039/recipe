/**
 *
 *
 * 1.Tomcat conf/context.xml里面配置资源
 * <Resource name="jdbc/test"
     auth="Container"
     type="javax.sql.DataSource"
     driverClassName="com.mysql.jdbc.Driver"
     url="jdbc:mysql://192.168.226.132:3306/test"
     username="root" password="HuaLaLa@4039"
     maxActive="20" maxIdle="10"
     maxWait="10000" />
    2.web.xml 配置
     <resource-ref>
     <description>DB Connection</description>
     <res-ref-name>jdbc/test</res-ref-name>
     <res-type>javax.sql.DataSource</res-type>
     <res-auth>Container</res-auth>
     </resource-ref>
 */
package com.sndj.recipe.jndi;