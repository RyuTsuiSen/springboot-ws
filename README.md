# springboot-ws

```sh
mvn clean install
mvn package
java -jar java -javaagent:{maven-repo-local}/glowroot/glowroot.jar -jar target/springboot-ws-1.0.0.jar
```

Check WSDL
> (http://localhost:8080/Service/Hello?wsdl)
> (http://localhost:8080/Service/Bonjour?wsdl)


Check Application by APM glowroot
> http://localhost:4000/

Check Circuit breakers - by hystrix dashboard

>  java -jar standalone-hystrix-dashboard-1.5.3-all.jar start

<br> -- put URL :

> http://localhost:7979/hystrix-dashboard/

<br> -- add stream : 

> http://localhost:8080/adapters.stream

<br> -- And monitor stream ;)


<br>-- Audit by app_logs.logs export on csf file  
<br>-- archaius update properties dynaic
<br>-- circuit breaker
<br>-- Handler error