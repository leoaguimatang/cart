package com.exam.cart;

import org.drools.core.SessionConfiguration;
import org.drools.core.util.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.io.ResourceFactory;

import java.util.List;

public class RuleEngine {
    public static void process(ShoppingCart cart) throws Exception {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieSession kSession = null;
        if (StringUtils.isEmpty(cart.getRule())) {
            KieFileSystem kfs = ks.newKieFileSystem();
            kfs.write(ResourceFactory.newClassPathResource(cart.getRule()));
            KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
            KieContainer kieContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
            KieSessionConfiguration conf = SessionConfiguration.getDefaultInstance();
            kSession = kieContainer.newKieSession(conf);

            if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
                List<Message> errors = kieBuilder.getResults().getMessages(Message.Level.ERROR);
                StringBuilder sb = new StringBuilder("Errors:");
                for (Message msg : errors) {
                    sb.append("\n  " + msg);
                }
                throw new Exception(sb.toString());
            }
        } else {
            KieContainer kContainer = ks.getKieClasspathContainer();
            kSession = kContainer.newKieSession("ksession-rules");
        }

        kSession.insert(cart);

        kSession.fireAllRules();
    }
}
