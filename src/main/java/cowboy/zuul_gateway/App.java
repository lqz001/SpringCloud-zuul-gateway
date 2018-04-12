package cowboy.zuul_gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import cowboy.zuul_gateway.filter.AccessFilter;

/**
 * zuul
 *
 */
@EnableZuulProxy
@SpringCloudApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello zuul!" );
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }
    /**
     * 添加认证过滤器
     * @return
     */
    @Bean
    public AccessFilter accessFilter(){
    	return new AccessFilter();
    }
}
