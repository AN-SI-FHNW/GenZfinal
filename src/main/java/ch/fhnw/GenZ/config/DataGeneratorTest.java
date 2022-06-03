/*
 * Author: Andrea Alec Simonek, Kevin Pini, Carla Kaufmann, Moana Kleiner
 * Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.config;

import ch.fhnw.GenZ.business.service.AgentService;
import ch.fhnw.GenZ.data.domain.Agent;
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

    // Create demo user
    private void demoUser() throws Exception {
        Agent agentUser = new Agent();
        agentUser.setEmail("user@user.com");
        agentUser.setPassword("password");
        agentUser.setName("user");
        agentService.saveAgent(agentUser);
    }
}
