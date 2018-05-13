job "config-service" {
  datacenters = ["dc1"]
  type = "service"
  group "config" {
    count = 2
    task "config-api" {
      driver = "java"
      config {
        jar_path = "local/config.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/config.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
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