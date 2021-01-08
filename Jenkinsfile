pipeline {
   agent {
      label "default"
   }
   stages {
      stage('Checkout') {
         steps {
            script {
               git url: 'https://github.com/Durbois/ets-cave-enki-backend.git'
            }
         }
      }
      stage('Build') {
         agent {
            label "maven"
         }
         steps {
            sh 'mvn clean install'
         }
      }
      stage('Test') {
         agent {
            label "maven"
         }
         steps {
            sh 'mvn test'
         }
      }
      stage('Image') {
         agent {
            label "maven"
         }
         steps {
            sh 'docker image build -t taghuofongue/cave-enki-backend .'
            sh 'docker push taghuofongue/cave-enki-backend'
         }
      }
      stage('Deploy on test') {
         steps {
            script {
               env.PIPELINE_NAMESPACE = "test"
               kubernetesDeploy kubeconfigId: 'docker-desktop', configs: 'k8s/deployment-template.yaml'
            }
         }
      }
   }
}