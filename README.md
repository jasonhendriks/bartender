# Bartender

A multi-user website that tracks an inventory of users' spirits and pairs the ingredients with recipes.

Try it out online: https://bartender-162eb18845e5.herokuapp.com

## Requirements

- [JDK v21](https://openjdk.org/projects/jdk/21/)
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Apache Maven v3.9](https://maven.apache.org/install.html)
- [Optional] [Heroku CLI client](https://devcenter.heroku.com/articles/heroku-cli)

## Installation

1. Download the [source code](https://github.com/jasonhendriks/bartender) to your workstation

```
git clone https://github.com/jasonhendriks/bartender
```

2. [Optional] If you installed the Heroku CLI, connect your local repository to Heroku:

```
heroku git:remote -a bartender
```

### Keeping the dependencies up-to-date

```
mvn versions:display-plugin-updates
```

## Running Locally

### Build the application and run with the Maven Spring Boot plug-in

```
$ mvn install
$ mvn spring-boot:run -pl web
```

Then access the application in your web browser: http://localhost:8080

### Build the application and run with the Heroku CLI

```
$ mvn install
$ heroku local
```

Then access the application in your web browser: http://localhost:5001

## Deployment to Production

### Continuous Integration

After committing and pushing any changes, GitHub will run the tests. If successful, Heroku will automatically retrieve
the changes, build and deploy.

### Manual Deploy with Heroku CLI

Push to the Heroku GIT remote to manually trigger a deployment:

```
git push heroku
```

## Production Support

### REST API documentation

Access the RESt API docs
at [/swagger-ui/index.html](https://bartender-162eb18845e5.herokuapp.com/swagger-ui/index.html).

### Debugging

View the production logs:

```
heroku logs --tail
```

## Development Resources

## Programming

- [Deploying Spring Boot Applications to Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
- [Spring Boot Tutorial – Bootstrap a Simple Application](https://www.baeldung.com/spring-boot-start)
- [Spring Boot MockMvc Example with @WebMvcTest](https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/)
- [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/)
- [Which Java Type do you use for JPA collections and why?](https://stackoverflow.com/a/17950928)
- [An Overview of Identifiers in Hibernate/JPA](https://www.baeldung.com/hibernate-identifiers)
- [Why You Should Avoid Using JPA/Hibernate in Production](https://azhidkov.pro/en/posts/21/04/why-jpa-should-be-avoided/)
- [Spring boot - Not a managed type](https://stackoverflow.com/questions/28664064/spring-boot-not-a-managed-type)
- [Design Smell: Default Constructor](https://blog.ploeh.dk/2011/05/30/DesignSmellDefaultConstructor/)
- [Passkeys: Accelerating the Availability of Simpler, Stronger Passwordless Sign-Ins](https://fidoalliance.org/passkeys/)
- [FIDO Security Key Support Comes To Auth0](https://auth0.com/blog/fido-security-key-support-comes-to-auth0/)
- [Passkeys Authentication Challenge with Spring Boot](https://developer.auth0.com/resources/guides/web-app/spring#)

## Drinks

- [Cocktails 101](https://www.cocktailemporium.com/pages/cocktails-101)
- [Cocktail and Drink Measures Explained](https://www.barschool.net/blog/cocktail-and-drink-measures-explained)
- [James Bond's Vesper Martini](https://www.thespruceeats.com/vesper-martini-recipe-760130)
- [The Savoy Cocktail Book](https://euvs-vintage-cocktail-books.cld.bz/1930-The-Savoy-Cocktail-Book)
- [LCBO](https://www.lcbo.com/)
