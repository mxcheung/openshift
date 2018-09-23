This is a repo to use as a quickstart to start working with Spring Boot and deploying to the WildFly application server.  This uses the mvn build system and creates a .war file that can be deployed.  The code also contains a sample REST based service listening at /hello.

As an added bonus, this repo can also be deployed directly as a docker container using the WildFly S2I builder image on OpenShift 3 with the following command:

	oc new-app wildfly:10.0~https://github.com/gshipley/bootwildfly.git


What, you don't have OpenShift 3 yet? Fix that immediately: www.openshift.org/vm


https://github.com/tecong/openshift-demos

https://github.com/jboss-developer/jboss-eap-quickstarts//tree/7.1.0.GA

http://blog.codeleak.pl/2015/02/openshift-build-spring-boot-application.html

https://github.com/kolorobot/openshift-wildfly-spring-boot

http://www.mastertheboss.com/jboss-frameworks/spring/springboot-with-jpa-on-wildfly