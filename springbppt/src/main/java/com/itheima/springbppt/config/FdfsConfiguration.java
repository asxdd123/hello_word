package com.itheima.springbppt.config;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")    //指定你要执行的配置文件
@Import(FdfsClientConfig.class) //导入FastDFS-Client组件
public class FdfsConfiguration {
}
