job "messaging-service" {
  datacenters = ["dc1"]
  type = "service"
  group "messaging" {
    count = 2
    task "messaging-api" {
      driver = "java"
      config {
        jar_path = "local/messaging.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/messaging.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "messaging"
        clientSecret = "pass"
        accessTokenUri = "http://167.99.139.178:8080/uaa/oauth/token"
      }
      resources {
        cpu = 300
        memory = 450
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