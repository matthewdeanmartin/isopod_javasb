# isopod_javasb
Trivial text adventure game using java spring boot

## Goals
- Be able to run the app locally in Tomcat
- Able to run the app in ApiGateway + Lambda
- Enough logging to know what is broken

## Implementation
- Used [Spring Initializer](https://start.spring.io/)
- Used ChatGPT to create the app because I don't care about that part right now, the goal is to host the app.
- Bot decided to use Thyme for templating.
- Uses aws-serverless-java-container (henceforth Java Container)

## Sharp Edges
- Java Container doesn't support Session State.
  - The web security for csrf uses session.
  - Something is still attempting to use session, but now no longer crashes the app.
- Use maven's profiles for the shaded-jar and zip packaging. Best to copy paste because it has a lot of decisions.
  - If you use the zip option, you also need the assembly folder with the xml file outlining what files to include
  - Thyme can have a hard time finding the templates, you can find them by convention, by application.properties, by setting in code or putting in a specific place using the assembly/bin.xml file
  - I think spring-boot-maven-plugin was conflicting with the profiles that make shaded jars/zips, so I commented it out
- Logging by default will probably be insufficient
  - You can enable API gateway's access logging, which is not especially helpful
  - logback.xml might help
  - setting logging flags in application.properties might help
- I have had better luck with the explicit StreamLambdaHandler rather than the implicit Delegating one.
  - getHttpApiV2ProxyHandler is for HTTP Gateways and getAwsProxyHandler is for the REST Gateways
  - And there is a builder option, which may or may not be "better"

## TODO
- get some sort of authentication working
- Add Terraform and/or cdk and/or cloudformation and/or SAM
- Write github actions to validate and deploy