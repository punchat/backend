job "id-service" {
  datacenters = ["dc1"]
  type = "service"
  group "id" {
    count = 2
    task "id-api" {
      driver = "java"
      config {
        jar_path = "local/id.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/id.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "id"
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