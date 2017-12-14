package webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import controller.DistributedSystemsController;
import service.ServiceImpl;
import service.ServiceInterface;
import dao.DaoImpl;
import dao.DaoInterface;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "webconfig")
public class WebConfig {

	@Bean
    public DaoInterface dao() {
        return new DaoImpl();
    }
	
	@Bean
	public ServiceInterface service() {
		return new ServiceImpl(dao());
	}
	
	@Bean
	public DistributedSystemsController controller() {
		return new DistributedSystemsController(service());
	}
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
	
}