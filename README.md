# astra-spring-boot-osaeed
For testing Astra connectivity with springboot

Based on the Spring Boot starter at Awesome Astra 
https://awesome-astra.github.io/docs/pages/develop/frameworks/spring/#22-project-setup

A few things to do
1. In the Setup Project section of step 2.2, the Spring Initializer template link may link to a deprecated version.  Choose version 2.6.11 or above
2. In pom.xml, make sure the astra-spring-boot-starter version is 0.3.3 or above
3. The starter project uses src/resources/templates/applciation.properties, but this project uses src/resources/templates/application.yaml.  Details in section 2.3.  Update application.yaml with your astra token, astra db-id, astra region (assumes AWS).  By default, the spring boot starter downloads the secure connection bundle for astra, but this customer's security requirements do not allow, so download-scb is set to false, and a path is specified where the bundle will be located.  The bundle has a specific naming convention, so it will be best to run the first time with download-scb enabled = true
