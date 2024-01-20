# Bartender

A multi-user website that tracks an inventory of user's spirits and pairs the ingredients with recipes.

Try it out online: https://bartender-162eb18845e5.herokuapp.com

## Development Requirements

- Git
- Apache Maven
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

### Debugging

View the production logs:

```
heroku logs --tail
```

## Development Resources

## Programming

- [Spring Boot Tutorial – Bootstrap a Simple Application](https://www.baeldung.com/spring-boot-start)
- [Spring Boot MockMvc Example with @WebMvcTest](https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/)
- [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/)

## Drinks

- [Cocktails 101](https://www.cocktailemporium.com/pages/cocktails-101)
- [Cocktail and Drink Measures Explained](https://www.barschool.net/blog/cocktail-and-drink-measures-explained)
- [James Bond's Vesper Martini](https://www.thespruceeats.com/vesper-martini-recipe-760130)
- [The Savoy Cocktail Book](https://euvs-vintage-cocktail-books.cld.bz/1930-The-Savoy-Cocktail-Book)
- [LCBO](https://www.lcbo.com/)
