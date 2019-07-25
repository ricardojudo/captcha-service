pipeline{
  agent any

  tools {
    maven 'maven'
    jdk 'jdk8'
  }
  stages{

    stage('Source checkout') {
      steps {
        checkout scm
        sh "ls -lat"
      }
    }

    stage('Build'){
      steps{
        echo 'Building...'
             sh 'mvn verify'
             sh 'mvn compile'
      }
    }
    
    stage('Test'){
      steps{
        echo 'Running unit tests...'
        sh 'mvn test'
        junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
      }
    }
    
    stage('Package'){
        steps{
          sh 'mvn clean install -Dmaven.test.skip=true'
          sh '$(aws ecr get-login --no-include-email --region us-east-1)'
          sh 'docker build . -t captcha-service:v1.0'
          sh 'docker tag captcha-service:v1.0 931219806454.dkr.ecr.us-east-1.amazonaws.com/captcha-service:v1.0'
          sh 'docker push 931219806454.dkr.ecr.us-east-1.amazonaws.com/captcha-service:v1.0'
        }
    }
    
    stage('Deploy'){
      steps{
        sh 'echo Deploy to OpenShift'
    
          
      }
    }

  }
}

