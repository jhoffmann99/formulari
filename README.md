# formulari backend application

This is the backend part for the formulari SaaS application

The frontend can be found [here](https://github.com/jhoffmann99/formulari-app)

## Development server

```
./mvnw spring-boot:run -Dspring-boot.run.arguments="--JWT_SECRET=<INSERT JWT SECRET HERE> --BREVO_API_KEY=<INSERT BREVO API KEY HERE>"
```