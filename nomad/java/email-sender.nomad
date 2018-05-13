job "email-sender-service" {
  datacenters = ["dc1"]
  type = "service"
  group "emails" {
    count = 2
    task "emails-api" {
      driver = "java"
      config {
        jar_path = "local/email-sender.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/email-sender.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "emails"
        clientSecret = "pass"
        accessTokenUri = "http://167.99.139.178:8080/uaa/oauth/token"
      }
      resources {
        cpu = 300
        memory = 400
        network {
          port "http" {}
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}