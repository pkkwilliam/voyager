package mo.bitcode.kyrie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({
				"mo.bitcode.core",
//				"mo.bitcode.web_socket",
				"mo.bitcode.kyrie",
//				"org.springframework.web.socket.config"
})
@EnableJpaRepositories(
				basePackages = {"mo.bitcode.core.service", "mo.bitcode.kyrie"})
@EntityScan({"mo.bitcode.core.entityModel", "mo.bitcode.sms.model", "mo.bitcode.kyrie"})
@SpringBootApplication
public class KyrieApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyrieApplication.class, args);
	}

}
