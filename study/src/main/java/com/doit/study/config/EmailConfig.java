package com.doit.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Slf4j
@PropertySource("classpath:email.properties")
/**
 *
 * 이메일용 설정 파일
 */
public class EmailConfig {

    public EmailConfig() throws IOException {
        log.info("EmailConfig.java constructor called");
    }

    @Value("${spring.mail.transport.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttls;

    @Value("${spring.mail.debug}")
    private boolean debug;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.default.encoding}")
    private String encoding;

    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private String ssl;

//    @Value("${spring.mail.properties.mail.smtp.ssl.trust}")
//    private String trust;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.properties.mail.smtp.ssl.enable", ssl);
        properties.put("mail.smtp.debug", debug);
//        properties.put("mail.properties.mail.smtp.ssl.trust", trust);
        log.info("Properties = "+properties);

        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setPort(port);
        mailSender.setJavaMailProperties(properties);
        mailSender.setDefaultEncoding(encoding);

        return mailSender;
    }
}
