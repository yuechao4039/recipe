/**
 * tomcat 运行CDI
 * 1.WEB-INFO下添加beans.xml文件，模版beans.xml.tpl
 * 2.引入依赖
 * <dependency>
     <groupId>org.jboss.weld.servlet</groupId>
     <artifactId>weld-servlet</artifactId>
     <version>1.1.10.Final</version>
    </dependency>
 * 3.web.xml添加Listener  org.jboss.weld.environment.servlet.Listener
 */
package com.sndj.recipe.cdi;
