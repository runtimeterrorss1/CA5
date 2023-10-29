pipeline {
    environment {
        DOCKERHUB_CRED = ''
    }

    agent any

    stages {
        stage('Login to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASS')]) {
                        sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASS'
                        DOCKERHUB_CRED = DOCKERHUB_USERNAME
                    }
                }
            }
        }

        stage('Build Flask Docker Image') {
            steps {
                sh 'docker build -t runtimeterrorss1/ca4-flaskserver:latest .'
            }
        }

        stage('Tag Flask Docker Image') {
            steps {
                sh "docker tag runtimeterrorss1/ca4-flaskserver:latest $DOCKERHUB_CRED/ca5-flask:latest"
            }
        }

        stage('Push MySQL Docker Image to Docker Hub') {
            steps {
                sh "docker push $DOCKERHUB_CRED/ca5-flask:latest"
            }
        }
    }
}
