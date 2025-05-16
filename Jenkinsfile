pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        GITHUB_REPO_URL = 'https://github.com/GnanendharReddy8/FinanceTracker.git'
        DOCKERHUB_USER = 'gnanendhar8'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${GITHUB_REPO_URL}"
            }
        }

        stage('Build JARs') {
            steps {
                script {
                    sh 'cd user-service && ./mvn clean package -DskipTests'
                    sh 'cd txn-service && ./mvn clean package -DskipTests'
                    sh 'cd report-service && ./mvn clean package -DskipTests'
                    sh 'cd api-gateway && ./mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    docker.build("${DOCKERHUB_USER}/user-service", "./user-service")
                    docker.build("${DOCKERHUB_USER}/txn-service", "./txn-service")
                    docker.build("${DOCKERHUB_USER}/report-service", "./report-service")
                    docker.build("${DOCKERHUB_USER}/api-gateway", "./api-gateway")
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        docker.image("${DOCKERHUB_USER}/user-service").push()
                        docker.image("${DOCKERHUB_USER}/txn-service").push()
                        docker.image("${DOCKERHUB_USER}/report-service").push()
                        docker.image("${DOCKERHUB_USER}/api-gateway").push()
                    }
                }
            }
        }

        // Other stages like Ansible and K8s...
    }
}
