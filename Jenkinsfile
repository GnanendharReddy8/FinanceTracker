pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        GITHUB_REPO_URL = 'https://github.com/GnanendharReddy8/FinanceTracker.git'
        USER_IMAGE = 'gnanendhar8/user-service'
        TXN_IMAGE = 'gnanendhar8/txn-service'
        REPORT_IMAGE = 'gnanendhar8/report-service'
        GATEWAY_IMAGE = 'gnanendhar8/api-gateway'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${GITHUB_REPO_URL}"
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    docker.build("${USER_IMAGE}", "./user-service")
                    docker.build("${TXN_IMAGE}", "./txn-service")
                    docker.build("${REPORT_IMAGE}", "./report-service")
                    docker.build("${GATEWAY_IMAGE}", "./api-gateway")
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        docker.image("${USER_IMAGE}").push()
                        docker.image("${TXN_IMAGE}").push()
                        docker.image("${REPORT_IMAGE}").push()
                        docker.image("${GATEWAY_IMAGE}").push()
                    }
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        ansiblePlaybook(
                            playbook: 'ansible-playbook.yaml',
                            inventory: 'inventory'
                        )
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh '''
                    kubectl apply -f k8s/
                    '''
                }
            }
        }
    }
}