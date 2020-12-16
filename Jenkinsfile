pipeline {

  environment {
    PROJECT = "cave-enki"
    APP_NAME = "cave-enki"
    FE_SVC_NAME = "${APP_NAME}-backend"
    CLUSTER = "jenkins-cd"
    CLUSTER_ZONE = "europe-west1-d"
    IMAGE_TAG = "gcr.io/${PROJECT}/${APP_NAME}"
    JENKINS_CRED = "${PROJECT}"
  }

  agent {
    kubernetes {
      label 'master'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: golang
    image: golang:1.10
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
  stages {
    /*stage('Build target jar') {
          steps {
            sh("./mvnw clean install")
          }
        }

    stage('Build and push image with Container Builder') {
      steps {
        container('gcloud') {
          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${IMAGE_TAG} ."
        }
      }
    }*/

    stage('Deploy Backend') {
          // Canary branch
          when { branch 'development' }
          steps {
            container('kubectl') {
              // Change deployed image in canary to the one we just built
              sh("kubectl get ns cave-enki || kubectl create ns cave-enki")
              sh("kubectl apply -f ./k8s/db-secret.yaml")
            }
          }
        }

  }
}
