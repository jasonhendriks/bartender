# bartender

A multi-user website that tracks an inventory of user's alcohol to pair with drink recipes

## Development Requirements

1. Git
1. Apache Maven
1. [Optional] [Heroku CLI client](https://devcenter.heroku.com/articles/heroku-cli)

## Installation

1. Download the [source code](https://github.com/jasonhendriks/bartender) to your workstation
    1. `git clone https://github.com/jasonhendriks/bartender`
1. [Optional] Connect your new local repository to Heroku:
    1. `heroku git:remote -a bartender`

## Running Locally

### Build the application and run with the Maven Spring Boot plug-in

```
$ mvn spring-boot:run -pl web
```

### Build the application and run with the Heroku CLI

```
$ mvn install
$ heroku local
```

## Deployment

### Continuous Integration

After committing and pushing any changes, GitHub will run the tests and, if successful, auto-deploy to Heroku.

### Manual Deploy with Heroku CLI

Push to the Heroku GIT remote to trigger a deployment:

```
git push heroku
```

## Production

### Debugging

View the production logs:

```
heroku logs --tail
```
