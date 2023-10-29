pipeline {
    environment {
        FLASKAPP_IMAGE = 'runtimeterrorss1/ca4-flaskserver'
        DB_IMAGE = 'runtimeterrorss1/ca4'
    }

    agent any

    stages {
        stage('Login to Docker Hub') {
            steps {
                script {
                    // Use the Docker Hub credentials stored in Jenkins
                    withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDENTIALS_ID', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASS')]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASS"
                        DOCKERHUB_CRED = DOCKERHUB_USERNAME
                    }
                }
            }
        }

        stage('Pulling Docker Images') {
            steps {
                script {
                    // Pull Flask App image
                    sh "docker pull $FLASKAPP_IMAGE"

                    // Pull MySQL image
                    sh "docker pull $DB_IMAGE"
                }
            }
        }

        stage('Running Docker Containers') {
            steps {
                script {
                    // Run the MySQL container
                    sh "docker run -d -p 3307:3307 --name ca4-db $DB_IMAGE"

                    // Run the Flask App container linked to the MySQL container
                    sh "docker run -d -p 4000:4000 --name ca4-app --link ca4-db $FLASKAPP_IMAGE"
                }
            }
        }
    }
}
