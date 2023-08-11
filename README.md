# ci-pipeline
this is CI (Contineous Integration) pipeline written in groovy script Jenkinsfile.
it will autometically compile the code and test it, package it, and the create a docker image from docker file.
after that, it will push docker image to dockerhub. amazon ecr and as well on nexus.
after code compilation, the pipeline checks for code qualitu using sonarqube.
