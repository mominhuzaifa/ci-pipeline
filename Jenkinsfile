pipeline{
    options{
    buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
    agent any
    tools{maven 'maven_3.8.8'}

    // Stages
    stages{
        // code compilation
        stage("code compilation"){
            steps{
                sh 'mvn clean compile'
            }
        }

        // code test
        stage("code test"){
            steps{
                sh 'mvn clean test'
            }
        }

        // code package
        stage("code package"){
            steps{
                sh 'mvn clean package'
            }
        }

        // sonarqube code quality check
        stage("sonarqube code quality check"){
            environment{
                scannerHome = tool 'SonarQubeScanner'
            }
            steps{
                withSonarQubeEnv('sonar-server'){
                    sh '${scannerHome}/bin/sonar-scanner'
                    sh 'mvn sonar:sonar'
                } // withSonarQubeEnv ends
                timeout(time:10, unit:'MINUTES'){
                    waitForQualityGate abortPipeline: true
                } // timeout ends
            }
        } // sonarqube stage end

        // pushing image to dockerHub
        stage("pushing image to dockerhub"){
            environment{
                DOCKERHUB_CREDENTIALS = credentials('dockerhub')
            }
            steps{
                withCredentials([string(credentialsId:'dockerhub', variable:'dockerhub')]){
                    script{
                        sh 'docker login docker.io -u mominhuzaifa -p ${dockerhub}' // login
                        sh 'docker build -t mominhuzaifa/apache-img .'  // build image
                        sh 'docker tag mominhuzaifa/apache-img mominhuzaifa/apache-img:apache2-img-v1'  // tagging the image
                        sh 'docker push mominhuzaifa/apache-img:apache2-img-v1'     // push to dockerhub
                    }
                }
            }
        } // dockerhub ends

        // push to Amazon ECR
        stage("push docker image to amazon ecr"){
            steps{
                script{
                    withDockerRegistry([credentialsId:'ecr:ap-south-1:ecr-credential', url:'https://150387322390.dkr.ecr.ap-south-1.amazonaws.com']){
                        sh 'docker build -t apache-docker-img .'        // build
                        sh 'docker tag apache-docker-img:latest 150387322390.dkr.ecr.ap-south-1.amazonaws.com/apache-docker-img:apache2-img-v1.02'     // tag
                        sh 'docker push 150387322390.dkr.ecr.ap-south-1.amazonaws.com/apache-docker-img:apache2-img-v1.02'     // push
                    }
                }
            }
        }   // ecr stage end

        // push to nexus
        stage("push docker image to nexus"){
            environment{
                DOCKERHUB_CREDENTIALS = credentials('dockerhub')
            }
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId:'nexus-user-credentials', usernameVariable:'jenkins-user', passwordVariable: 'nexus')]){
                        sh 'docker login -u admin -p ${nexus} http://43.205.115.96:8085/repository/docker-hosted-repo/'  // login
                        sh 'docker build -t apache-img .'   // build
                        sh 'docker tag apache-img 43.205.115.96:8085/apache-img:apache2-img-v1.1'    // tag
                        sh 'docker push 43.205.115.96:8085/apache-img:apache2-img-v1.1'  // push
                    }
                }
            }
        }   // push to nexus ends

        // deleting docker image from jenkins
        stage("deleting docker images from jenkins"){
            steps{
                sh 'docker rmi -f $(docker images -q)'
            }
        }   // delete docker images ends
    }
}