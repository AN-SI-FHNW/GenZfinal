package ch.fhnw.acrm.config;

import ch.fhnw.acrm.business.service.AgentService;
import ch.fhnw.acrm.data.domain.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("test")
@Configuration
public class DataGeneratorTest {

    @Autowired
    private AgentService agentService;

    @PostConstruct
    private void init() throws Exception {
        demoUser();
    }

    private void demoUser() throws Exception {
        Agent agentUser = new Agent();
        agentUser.setEmail("user@user.com");
        agentUser.setPassword("password");
        agentUser.setName("user");
        agentService.saveAgent(agentUser);
    }
}
